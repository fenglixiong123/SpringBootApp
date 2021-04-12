package com.flx.springboot.scaffold.common.validate;

import com.flx.springboot.scaffold.common.result.ResultResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.HibernateValidator;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.*;

/**
 * @author fenglixiong
 * @date 2018-08-13 11:09
 */
@Slf4j
@Component
public class ValidationUtils {

    private static final Validator validator;

    static {
        ValidatorFactory validatorFactory = Validation.byProvider(HibernateValidator.class)
                .configure()
                .addProperty("hibernate.validator.fail_fast", "false")
                .buildValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    /**
     * 数据检验，按照@Valid进行检验以及
     *
     * @param obj
     * @param <T>
     * @return
     */
    public static <T> ValidationResult validate(T obj) {
        Set<ConstraintViolation<T>> constraintViolationSet = validator.validate(obj);
        ValidationResult failureResult = ValidationResult.failure();
        Iterator<ConstraintViolation<T>> it = constraintViolationSet.iterator();
        while (it.hasNext()) {
            ConstraintViolation<T> constraintViolation = it.next();
            String message = constraintViolation.getMessage();
            String propertyPath = constraintViolation.getPropertyPath().toString();
            failureResult.addMessage(propertyPath, message);
        }
        if (failureResult.getMessageMap().isEmpty()) {
            return ValidationResult.success();
        }
        return failureResult;
    }

    /**
     * 用于将ValidationResult转换为ResultResponse
     * @param validationResult 验证结果
     * @return 返回对象
     */
    public static ResultResponse toResult(ValidationResult validationResult) {
        if (validationResult.isSuccess()) {
            return ResultResponse.success();
        }
        Map<String, String> messageMap = validationResult.getMessageMap();
        List<String> errorMessageList = new ArrayList<>();
        for (Map.Entry<String, String> entry : messageMap.entrySet()) {
            String message = entry.getKey() + entry.getValue();
            errorMessageList.add(message);
        }
        return ResultResponse.error("Scaffold.Valid.Object.Error", StringUtils.join(errorMessageList, "|"));
    }

}
