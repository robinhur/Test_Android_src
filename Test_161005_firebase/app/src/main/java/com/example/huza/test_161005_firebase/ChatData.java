package com.example.huza.test_161005_firebase;

/**
 * Created by HuZA on 2016-10-05.
 */

public class ChatData {

    String id;
    String message;

    public ChatData() {
    }

    public ChatData(String id, String message) {
        this.id = id;
        this.message = message;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
