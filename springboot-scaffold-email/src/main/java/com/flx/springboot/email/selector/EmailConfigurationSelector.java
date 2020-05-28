package com.flx.springboot.email.selector;

import com.flx.springboot.email.enums.EmailMode;
import com.flx.springboot.email.service.ComplexEmailService;
import com.flx.springboot.email.service.SimpleEmailService;

/**
 * @Author: Fenglixiong
 * @Date: 2020/5/28 19:43
 * @Description:
 */
public class EmailConfigurationSelector extends EmailModeImportSelector {

    @Override
    protected String[] selectImports(EmailMode emailMode) {
        switch(emailMode) {
            case SIMPLE:
                return new String[]{SimpleEmailService.class.getName()};
            case COMPLEX:
                return new String[]{ComplexEmailService.class.getName()};
            default:
                return null;
        }
    }
}
