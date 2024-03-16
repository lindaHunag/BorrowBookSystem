package com.example.demo.aspect;

import com.example.demo.enums.StatusCodeEnum;
import com.example.demo.exception.UserException;
import com.example.demo.model.dto.res.ApiResDTO;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class ExceptionAspect {

    @Around(value = "execution(public * com.example.demo.controller.*.*(..)) ")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            return joinPoint.proceed();
        } catch (UserException e) {
            return ApiResDTO.builder(e.getStatus(), e.getMessage()).build();
        } catch (Exception e) {
            return ApiResDTO.builder(StatusCodeEnum.SystemError).build();
        }

    }
}
