package com.huake.bondmaster.di.qualifier;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

import javax.inject.Qualifier;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by pengweiqiang on 2017/2/26.
 */

@Qualifier
@Documented
@Retention(RUNTIME)
public @interface BondMasterUrl {

}
