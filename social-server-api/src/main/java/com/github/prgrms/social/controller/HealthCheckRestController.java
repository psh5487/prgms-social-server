package com.github.prgrms.social.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.github.prgrms.social.controller.ApiResult.OK;

@RestController
@RequestMapping("api/_hcheck")
@Api(tags = "헬스체크 APIs")
public class HealthCheckRestController {

  @GetMapping
  @ApiOperation(value = "헬스체크 (API 토큰 필요없음)")
  public ApiResult<Long> healthCheck() {
    return OK(System.currentTimeMillis());
  }

}