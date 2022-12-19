package com.logicore.rest.services.serviceprocessor2;

public class ServiceProcessed2 {

    private int id;
    private boolean processed;


    public ServiceProcessed2() {
    }

    public ServiceProcessed2(int id, boolean processed) {
        this.id = id;
        this.processed = processed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isProcessed() {
        return processed;
    }

    public void setProcessed(boolean processed) {
        this.processed = processed;
    }
}
