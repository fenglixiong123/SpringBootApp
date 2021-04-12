package com.flx.springboot.scaffold.common.validate;

import lombok.Data;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author fenglixiong
 * @date 2021-01-01 12:12
 */
@Data
public class ValidationResult {

    private final boolean success;

    private final Map<String, String> messageMap;

    public ValidationResult(boolean success, Map<String, String> messageMap) {
        this.success = success;
        this.messageMap = messageMap;
    }

    public static ValidationResult success() {
        return new ValidationResult(true, Collections.emptyMap());
    }


    public static ValidationResult failure() {
        return new ValidationResult(false, new HashMap<>());
    }

    public ValidationResult addMessage(String propertyPath, String message) {
        messageMap.put(propertyPath, message);
        return this;
    }
}
