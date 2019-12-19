package com.example.a2mee.jyoti.com.net.utils;

/**
 * Created by A2mee on 3/15/2018.
 */
public class Config  /*extends Activity*/ {

    //Keys for email and password as   b defined in our $_POST['key'] in login.php
    public static final String mylogin_PREF_NAME = "Login Successfull!!";

    public static final String BASE_URL = "http://192.168.2.6:8091/";
    public static final String URL_CHECK_STORAGE_BIN_QR_CODE=BASE_URL+"putAway/mapItemToStorageBin?";
    public static final String URL_CHECK_ITEM_QR_CODE=BASE_URL+"putAway/storeItem?";
    public static final String URL_GET_ITEM_DETAILS=BASE_URL+"putAway/acceptedList";
    //If server response is equal to this that means login is successful
    public static final String Register_SUCCESS = "Registration Sucessful!!";
    /* public static final String KEY_conform = "conform_password";*/
    //Keys for Sharedpreferences
    //This would be the name of our shared preferences
    public static final String SHARED_PREF_NAME = "myloginapp";
    //This would be used to store the email of
    //
    // current logged in user
    public static final String EMAIL_SHARED_PREF = "email";
    public static final String password_SHARED_PREF = "password";
    //We will use this to store the boolean in sharedpreference to track user is loggedinn or not
    public static final String LOGGEDINN_SHARED_PREF = "loggedinn";
    //static final String BASE_URL = "http://192.168.2.12/roh/";
    // http://parent.api.mydschool.com/app/
    //public static final String BASE_URL = "http://192.168.2.4:8091/";
    // public static String BASE_URL = "http://192.168.2.8:8091/";
    public static String Username_SHARED_PREF = "user_id";
    public static final String GET_TOTAL_COMPLAINT_COUNT=BASE_URL+"modelPlan/getTotalComplCount?userName=";
    public static final String GET_ON_GOING_ASSEMBLY=BASE_URL+"modelPlan/getPendingAssms?userName=";
    public static final String GET_PENDING_COMPONENTS=BASE_URL+"modelPlan/getCompsByAssm?userName=";
    public static final String GET_NEW_ASSEMBLY=BASE_URL+"modelPlan/getAssms?userName=";
    public static final String GET_COMPONENT_BY_ASSEMBLY=BASE_URL+"modelPlan/getCompsByAssm?userName=";
    public static final String GET_STORAGE_STOCK_BY_COMPONENT_CODE=BASE_URL+"modelPlan/getStockByComp?compCode=";
    public static final String POST_UPDATE_STOCKS=BASE_URL+"modelPlan/updateStocks";


}
