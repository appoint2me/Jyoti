package com.example.a2mee.jyoti.com.net.models;

public class OnGoingAssemblyModel {
    private String assemblyCode;
    private String assemblyDesc;
    private String pickAssmQty;
    private String status;


    public OnGoingAssemblyModel(String assemblyCode, String assemblyDesc, String pickAssmQty, String status) {
        this.assemblyCode = assemblyCode;
        this.assemblyDesc = assemblyDesc;
        this.pickAssmQty = pickAssmQty;
        this.status = status;
    }

    public String getAssemblyCode() {
        return assemblyCode;
    }

    public void setAssemblyCode(String assemblyCode) {
        this.assemblyCode = assemblyCode;
    }

    public String getAssemblyDesc() {
        return assemblyDesc;
    }

    public void setAssemblyDesc(String assemblyDesc) {
        this.assemblyDesc = assemblyDesc;
    }

    public String getPickAssmQty() {
        return pickAssmQty;
    }

    public void setPickAssmQty(String pickAssmQty) {
        this.pickAssmQty = pickAssmQty;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
