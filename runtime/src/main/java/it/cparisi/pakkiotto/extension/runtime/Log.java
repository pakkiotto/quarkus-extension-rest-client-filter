package it.cparisi.pakkiotto.extension.runtime;

import javax.interceptor.InterceptorBinding;
import java.lang.annotation.*;

@InterceptorBinding
@Documented
@Target({ElementType.ANNOTATION_TYPE, ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Log {}
