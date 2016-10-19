package com.zhiyi.analysis;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zhoutaotao on 11/27/15.
 */
public class Test {
    public static void main(String[] args) {
        Pattern pattern = Pattern.compile("(\\d+\\.\\d+|\\d+)");
        String input = "按需处理";
        Matcher m = pattern.matcher(input);
        if (m.find()) {
            try {
                String salePrice = m.group(1);
                String unit = input.substring(salePrice.length());
                System.out.println(salePrice);
                System.out.println(unit);
            } catch (Exception e) {

            }
        }
    }
}
