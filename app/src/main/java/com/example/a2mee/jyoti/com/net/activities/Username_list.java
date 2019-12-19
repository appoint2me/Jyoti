package com.example.a2mee.jyoti.com.net.activities;

/**
 * Created by A2mee on 9/12/2018.
 */

public class Username_list {

    private String permission;
    private String permissionValue;
    private String type;



    public Username_list(String permission,String permissionValue, String type) {

        if ( permission.equals("GRN Inspection")){
            this.permission = permission;
        }
        if ( permission.equals("Quarantine Store")){
            this.permission = permission;
        }
        if (permission.equals("Final Inspection")){
            this.permission = permission;
        }
        if ( permission.equals("Assembly Inspection")){
            this.permission = permission;
        }
        if ( permission.equals("Send to FG")){
            this.permission = permission;
        }
        if ( permission.equals("Dispatch Operator")){

            this.permission = "Receive At Store";
        }
        if ( permission.equals("Packaging")){
            this.permission = "Packaging Stage 2";
        }
        if (permission.equals("Store Operator")){
            this.permission = permission;
        }
        if (permission.equals("Production")){
            this.permission = permission;
        }
        if (permission.equals("Quarantine Store")){
            this.permission = permission;
        }

        this.permissionValue = permissionValue;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public String getPermission() {
        return permission;
    }

    public String getPermissionValue() {
        return permissionValue;
    }
}
