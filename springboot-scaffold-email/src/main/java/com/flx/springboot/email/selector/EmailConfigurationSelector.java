package com.flx.springboot.email.selector;

import com.flx.springboot.email.annotation.EnableEmail;
import com.flx.springboot.email.config.ComplexEmailConfiguration;
import com.flx.springboot.email.config.SimpleEmailConfiguration;
import com.flx.springboot.email.enums.EmailMode;

/**
 * @Author: Fenglixiong
 * @Date: 2020/5/28 19:43
 * @Description:
 */
public class EmailConfigurationSelector extends EmailModeImportSelector<EnableEmail> {

    @Override
    protected String[] selectImports(EmailMode emailMode) {
        switch(emailMode) {
            case SIMPLE:
                return new String[]{SimpleEmailConfiguration.class.getName()};
            case COMPLEX:
                return new String[]{ComplexEmailConfiguration.class.getName()};
            default:
                return null;
        }
    }
}
