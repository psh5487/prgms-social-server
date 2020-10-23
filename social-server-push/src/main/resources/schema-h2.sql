DROP TABLE IF EXISTS subscriptions CASCADE;

CREATE TABLE subscriptions (
  seq               bigint NOT NULL AUTO_INCREMENT,
  user_seq          bigint NOT NULL,
  endpoint          varchar(200) NOT NULL,
  public_key        varchar(200) NOT NULL,
  auth              varchar(200) DEFAULT NULL,
  create_at         datetime NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  CONSTRAINT unq_subscriptions UNIQUE (user_seq, endpoint)
);