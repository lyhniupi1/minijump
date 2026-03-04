package com.bocom.epcc.batch;

public abstract class AbstractBatchStep {
    protected String chkDate;
    protected String stepId;
    
    public boolean execute(){
        return true;
    }
}
