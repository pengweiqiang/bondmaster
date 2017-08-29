package com.huake.bondmaster.util;

import android.text.TextUtils;

import java.math.BigDecimal;

/**
 * @author will on 2017/8/29 09:49
 * @email pengweiqiang64@163.com
 * @description
 * @Version
 */

public class BigDecimalUtil {

    /**
     * 保留两位小数
     * @param str
     * @param decimalsCount 保留几位小数
     * @return
     */
    public static String formartDoubleStr(String str,int decimalsCount){
        if(TextUtils.isEmpty(str)){
            return "";
        }
        StringBuilder decimalFormatStr = new StringBuilder("#");
        for(int i = 0 ;i<decimalsCount;i++){
            if(i==0){
                decimalFormatStr.append(".0");
            }else{
                decimalFormatStr.append("0");
            }
        }
        BigDecimal bigDecimal = new BigDecimal(str);
        java.text.DecimalFormat df =new java.text.DecimalFormat(decimalFormatStr.toString());
        return df.format(bigDecimal.doubleValue());
    }

}
