package com.flx.springboot.scaffold.exception.handler;

import com.flx.springboot.scaffold.common.enums.ErrorMsgEnum;
import com.flx.springboot.scaffold.common.result.ResultResponse;
import com.flx.springboot.scaffold.exception.element.BizException;
import com.flx.springboot.scaffold.exception.element.ParamException;
import com.flx.springboot.scaffold.exception.element.RedisException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

/**
 * @Author Fenglixiong
 * @Create 2018.11.10 14:11
 * @Description 全局异常处理类，注意要被引用的项目扫描到才行
 **/
@Slf4j
@ResponseBody
@ControllerAdvice
public class CoreExceptionHandler {

    /**
     * 自定义传参异常类
     * @param request
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(ParamException.class)
    public ResultResponse<String> paramExceptionHandler(HttpServletRequest request, Exception e){
        log.error("【异常地址】：{}",request.getRequestURL().toString());
        log.error("【异常类型】传参异常paramException：{}",e.getMessage());
        return ResultResponse.error(ErrorMsgEnum.PARAM_INVILAD,e.getMessage());
    }

    /**
     * 自定义Hibernate校验异常类
     * @param request
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResultResponse methodNotValidExceptionHandler(HttpServletRequest request,MethodArgumentNotValidException e){
        log.error("【异常地址】：{}",request.getRequestURL().toString());
        log.error("【异常类型】字段校验异常MethodArgumentNotValidException：{}",e.getMessage());
        String errorMsg = "字段校验异常";
        if(e.getBindingResult()!=null&&e.getBindingResult().getFieldError()!=null){
            errorMsg = e.getBindingResult().getFieldError().getDefaultMessage();
        }
        return ResultResponse.error(ErrorMsgEnum.PARAM_CHECK,errorMsg);
    }

    /**
     * 传参异常类
     * @param request
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResultResponse<String> missingRequestParamExceptionHandler(HttpServletRequest request,Exception e){
        log.error("【异常地址】：{}",request.getRequestURL().toString());
        log.error("【异常类型】缺少参数异常missingRequestParamException：{}",e.getMessage());
        return ResultResponse.error(ErrorMsgEnum.PARAM_MISSING,e.getMessage());
    }

    /**
     * 自定义Json转换异常类
     * @param request
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResultResponse jsonConvertExceptionHandler(HttpServletRequest request,Exception e){
        log.error("【异常地址】：{}",request.getRequestURL().toString());
        log.error("【异常类型】业务异常JsonConvertException：{}",e.getMessage());
        return ResultResponse.error(ErrorMsgEnum.JSON_CONVERT_ERROR,e.getMessage());
    }

    /**
     * 自定义业务异常类
     * @param request
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(BizException.class)
    public ResultResponse bizExceptionHandler(HttpServletRequest request,Exception e){
        log.error("【异常地址】：{}",request.getRequestURL().toString());
        log.error("【异常类型】业务异常BizException：{}",e.getMessage());
        return ResultResponse.error(e.getMessage());
    }

    /**
     * 自定义Redis异常类
     * @param request
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(RedisException.class)
    public ResultResponse<String> redisExceptionHandler(HttpServletRequest request, Exception e){
        log.error("【异常地址】：{}",request.getRequestURL().toString());
        log.error("【异常类型】缓存异常redisException：{}",e.getMessage());
        return ResultResponse.error(ErrorMsgEnum.REDIS_ERROR,e.getMessage());
    }

    /**
     * 全局异常类
     * @param request
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public ResultResponse defaultExceptionHandler(HttpServletRequest request,Exception e){
        log.error("【异常地址】：{}",request.getRequestURL().toString());
        String message;
        if(e instanceof NullPointerException){
            message = "空指针异常";
            log.error("【异常类型】空指针异常NullPointerException：{}",e.getMessage());
        } else if(e instanceof IllegalArgumentException){
            message = "参数异常";
            log.error("【异常类型】参数异常IllegalArgumentException：{}",e.getMessage());
        }else if(e instanceof ArithmeticException){
            message = "算数异常";
            log.error("【异常类型】算数异常ArithmeticException：{}",e.getMessage());
        }else {
            message = "未知异常";
            log.error("【异常类型】未知异常UnknownException：{}",e.getMessage());
        }
        e.printStackTrace();
        return ResultResponse.error(message);
    }

    @PostConstruct
    public void init() {
        log.info("*************************************************");
        log.info("*                                               *");
        log.info("*                 GlobalException               *");
        log.info("*                                               *");
        log.info("*************************************************");
    }
}
