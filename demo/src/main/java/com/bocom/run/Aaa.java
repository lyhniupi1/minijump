package com.bocom.run;



import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Aaa {

    public static void main(String[] args) {
        String[] data = {
            "-3049.1968", "-2944.6880", "-2793.0723", "0.0000", "0.0000", "0.0000",
            "-1068.6131", "-1188.6137", "-1081.7470", "-1105.8055", "0.0000", "0.0000",
            "0.0000", "0.0000", "-2360.8056", "-3071.9848", "-2997.2632", "-2945.6542",
            "-2911.6724", "0.0000", "0.0000", "-2350.7533", "-3011.7947", "-2121.0121",
            "-3129.6914", "-2946.7772", "0.0000", "0.0000", "-2433.4574", "-2969.9633"
        };

        // 设置要保留的小数位数
        int scale = 2; // 0表示取整，可以改成1或2来保留小数位
        
        System.out.println("原始数据 -> 四舍六入五成双结果 (保留" + scale + "位小数)");
        System.out.println("==========================================");
        
        BigDecimal sum = BigDecimal.ZERO;
        
        for (int i = 0; i < data.length; i++) {
            String numStr = data[i];
            BigDecimal original = new BigDecimal(numStr);
            BigDecimal rounded = original.setScale(scale, RoundingMode.HALF_EVEN);
            
            // 累加求和
            sum = sum.add(rounded);
            
            // 输出每个数字的处理结果
            System.out.printf("%2d: %-12s -> %-10s (原始: %s)%n", 
                    (i + 1), numStr, rounded, original);
        }
        
        System.out.println("==========================================");
        System.out.println("总和: " + sum);
    }
}