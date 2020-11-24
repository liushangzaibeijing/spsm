package com.xiu.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.annotation.ResponseStatusExceptionResolver;

/**
 * @ClassName AServiceException
 * @Desc 自定异常A
 * @Author xieqx
 * @Date 2020/11/24 16:34
 **/
@ResponseStatus(code = HttpStatus.BAD_REQUEST,reason = "@ResponseStatus自定义异常")
public class AServiceException extends Exception {
}
