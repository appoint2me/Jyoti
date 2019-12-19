package com.example.a2mee.jyoti.com.net.models;

public class ItemDetailsModel {

    private String qrcode;
    private String itemdetails;
    private String grnentrydate;
    private String grnno;
    private String itemId;

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public ItemDetailsModel(String qrcode, String itemdetails, String grnentrydate, String grnno, String itemId) {
        this.qrcode = qrcode;
        this.itemdetails = itemdetails;
        this.grnentrydate = grnentrydate;
        this.grnno = grnno;
        this.itemId=itemId;
    }

    public String getQrcode() {
        return qrcode;
    }

    public void setQrcode(String qrcode) {
        this.qrcode = qrcode;
    }

    public String getItemdetails() {
        return itemdetails;
    }

    public void setItemdetails(String itemdetails) {
        this.itemdetails = itemdetails;
    }

    public String getGrnentrydate() {
        return grnentrydate;
    }

    public void setGrnentrydate(String grnentrydate) {
        this.grnentrydate = grnentrydate;
    }

    public String getGrnno() {
        return grnno;
    }

    public void setGrnno(String grnno) {
        this.grnno = grnno;
    }
}
