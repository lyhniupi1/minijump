package com.bocom.epcc.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class EpccBatchStatDao {
    
    @Autowired
    private SqlMap sqlMap;
    
    /**
     * 根据EBS_TRANDATE、EBS_INSTSSRID分组，汇总EBS_NETSERVERFEE+EBS_EXCHANGEFEE的值
     * 条件：EBS_TRANSTT in('00','03')，并且 EBS_BTID between 两个参数之间
     *
     * @param startBtId 起始BTID
     * @param endBtId 结束BTID
     * @return 汇总值列表
     */
    public List<BigDecimal> sumFeeByGroup(String startBtId, String endBtId) {
        Map<String, Object> params = new HashMap<>();
        params.put("startBtId", startBtId);
        params.put("endBtId", endBtId);
        return sqlMap.queryForList("epccBatchStat.sumFeeByGroup", params);
    }
    
    /**
     * 根据EBS_TRANDATE、EBS_INSTSSRID分组，汇总EBS_NETSERVERFEE+EBS_EXCHANGEFEE的值
     * 条件：EBS_TRANSTT in('00','03')，并且 EBS_BTID between 两个参数之间
     * 返回包含分组信息的对象列表（如果需要）
     */
    public List<FeeSumResult> sumFeeByGroupWithInfo(String startBtId, String endBtId) {
        Map<String, Object> params = new HashMap<>();
        params.put("startBtId", startBtId);
        params.put("endBtId", endBtId);
        return sqlMap.queryForList("epccBatchStat.sumFeeByGroupWithInfo", params);
    }
    
    /**
     * 分组汇总结果对象
     */
    public static class FeeSumResult {
        private String transDate;      // EBS_TRANDATE
        private String instSsrId;      // EBS_INSTSSRID
        private BigDecimal totalFee;   // EBS_NETSERVERFEE + EBS_EXCHANGEFEE 汇总
        
        public FeeSumResult() {
        }
        
        public FeeSumResult(String transDate, String instSsrId, BigDecimal totalFee) {
            this.transDate = transDate;
            this.instSsrId = instSsrId;
            this.totalFee = totalFee;
        }
        
        public String getTransDate() {
            return transDate;
        }
        
        public void setTransDate(String transDate) {
            this.transDate = transDate;
        }
        
        public String getInstSsrId() {
            return instSsrId;
        }
        
        public void setInstSsrId(String instSsrId) {
            this.instSsrId = instSsrId;
        }
        
        public BigDecimal getTotalFee() {
            return totalFee;
        }
        
        public void setTotalFee(BigDecimal totalFee) {
            this.totalFee = totalFee;
        }
    }
}