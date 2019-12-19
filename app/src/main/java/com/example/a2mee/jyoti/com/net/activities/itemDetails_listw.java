package com.example.a2mee.jyoti.com.net.activities;

/**
 * Created by A2mee on 9/5/2018.
 */

public class itemDetails_listw {

    private float igi;
    private float ssss;
    private int stageOnePackagingId;
    private String stageOneQrCode;
    private String itemDetails;
    private int packetSizeQty;

    public float getIgi() {
        return igi;
    }

    public float getSsss() {
        return ssss;
    }

    public int getStageOnePackagingId() {
        return stageOnePackagingId;
    }

    public String getStageOneQrCode() {
        return stageOneQrCode;
    }

    public String getItemDetails() {
        return itemDetails;
    }

    public int getPacketSizeQty() {
        return packetSizeQty;
    }

    public itemDetails_listw(String itemDetails, int packetSizeQty, String stageOneQrCode, int stageOnePackagingId, float qtttty, float qtyq) {

        this.stageOneQrCode = stageOneQrCode;
        this.packetSizeQty = packetSizeQty;
        this.stageOnePackagingId = stageOnePackagingId;
        this.ssss = qtttty;
        this.igi = qtyq;

    }


}
