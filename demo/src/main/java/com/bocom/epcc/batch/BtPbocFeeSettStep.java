package com.bocom.epcc.batch;

import com.bocom.epcc.dao.EpccBatchStatDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class BtPbocFeeSettStep extends AbstractBatchStep{
    
    @Autowired
    private EpccBatchStatDao epccBatchStatDao;
    
    @Override
    public boolean execute(){
        // 示例：查询BTID在某个范围内的费用汇总
        String startBtId = "B202511010001";
        String endBtId = "B202511300024";
        
        try {
            // 调用DAO查询汇总费用
            List<BigDecimal> feeList = epccBatchStatDao.sumFeeByGroup(startBtId, endBtId);
            
            // 处理查询结果
            if (feeList != null && !feeList.isEmpty()) {
                System.out.println("查询到 " + feeList.size() + " 条费用汇总记录");
                
                BigDecimal totalSum = BigDecimal.ZERO;
                for (BigDecimal fee : feeList) {
                    totalSum = totalSum.add(fee);
                }
                
                System.out.println("总费用汇总: " + totalSum);
                
                // 这里可以添加业务逻辑，比如写入文件、更新数据库等
                // ...
                
                return true;
            } else {
                System.out.println("未查询到符合条件的记录");
                return true; // 没有数据也视为成功执行
            }
        } catch (Exception e) {
            System.err.println("查询费用汇总失败: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}
