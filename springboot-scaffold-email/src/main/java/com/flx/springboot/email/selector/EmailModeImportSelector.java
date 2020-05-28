package com.flx.springboot.email.selector;

import com.flx.springboot.email.utils.AttributesUtils;
import com.flx.springboot.email.enums.EmailMode;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.GenericTypeResolver;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

import java.lang.annotation.Annotation;

/**
 * @Author: Fenglixiong
 * @Date: 2020/5/28 19:49
 * @Description:
 */
public abstract class EmailModeImportSelector<A extends Annotation> implements ImportSelector {

    public static final String DEFAULT_EMAIL_MODE_ATTRIBUTE_NAME = "mode";

    @Nullable
    protected String getEmailModeAttributeName() {
        return DEFAULT_EMAIL_MODE_ATTRIBUTE_NAME;
    }

    @Override
    public final String[] selectImports(AnnotationMetadata importingClassMetadata) {

        Class<?> annType = GenericTypeResolver.resolveTypeArgument(this.getClass(), EmailModeImportSelector.class);
        Assert.state(annType != null, "Unresolvable type argument for EmailModeImportSelector");
        AnnotationAttributes attributes = AttributesUtils.attributesFor(importingClassMetadata, annType);
        if (attributes == null) {
            throw new IllegalArgumentException(String.format("@%s is not present on importing class '%s' as expected", annType.getSimpleName(), importingClassMetadata.getClassName()));
        } else {
            EmailMode emailMode = (EmailMode)attributes.getEnum(this.getEmailModeAttributeName());
            String[] imports = this.selectImports(emailMode);
            if (imports == null) {
                throw new IllegalArgumentException("Unknown EmailMode: " + emailMode);
            } else {
                return imports;
            }
        }
    }

    @Nullable
    protected abstract String[] selectImports(EmailMode emailMode);
}
