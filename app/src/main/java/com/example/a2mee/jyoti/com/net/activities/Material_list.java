package com.example.a2mee.jyoti.com.net.activities;

/**
 * Created by A2mee on 7/27/2018.
 */

public class Material_list {

    private String barCode;
    private String status;
    private String qty;
    private String itemId;
    private String mtlIssuSumId;
    private String itemName;




    public Material_list(String status, String qty, String itemName, String mtlIssuSumId,String itemId,String barcode) {
        this.itemId=itemId;
        this.status = status;
        this.qty = qty;
        this.itemName=itemName;
        this.barCode=barCode;
        this.mtlIssuSumId=mtlIssuSumId;


    }



    public String getItemId() {
        return itemId;
    }

    public String getBarcode() {
        return barCode;
    }

    public String getMtlIssuSumId() {
        return mtlIssuSumId;
    }
    public String getItemName() {
        return itemName;
    }

    public String getStatus() {
        return status;
    }

    public String getQty() {
        return qty;
    }

    public String getBarCode() {
        return itemId;
    }
}
