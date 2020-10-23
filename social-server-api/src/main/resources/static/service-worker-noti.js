self.addEventListener('push', function(event) {
    console.log(event);
    if (!(self.Notification && self.Notification.permission === 'granted')) {
        return;
    }

    var data = {};
    if (event.data) {
        data = event.data.json();
    }
    var title = data.title;
    var message = data.message;
    var icon = "img/bi-symbol-light.png";

    self.clickTarget = data.clickTarget;

    console.log("message", message);
    event.waitUntil(self.registration.showNotification(title, {
        body: message,
        tag: 'programmerse-facebook',
        icon: icon,
        badge: icon
    }));
});

self.addEventListener('notificationclick', function(event) {
    console.log('[Service Worker] Notification click Received.');

    event.notification.close();

    if(clients.openWindow){
        event.waitUntil(clients.openWindow(self.clickTarget));
    }
});