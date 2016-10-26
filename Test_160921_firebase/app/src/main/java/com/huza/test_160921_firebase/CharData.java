package com.huza.test_160921_firebase;

/**
 * Created by HuZA on 2016-09-30.
 */

public class CharData {
    String userName;
    String message;

    public CharData() {}

    public CharData(String userName, String message) {
        this.userName = userName;
        this.message = message;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
