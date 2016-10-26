package com.huza.test_161002_firebase2;

/**
 * Created by HuZA on 2016-10-04.
 */

public class UserData {
    String uid;
    String name;
    String email;
    int age;

    public UserData() {}

    public UserData(String email, String name, int age) {
        this.uid = null;
        this.name = name;
        this.email = email;
        this.age = age;
    }

    public UserData(String email, String name, String uid, int age) {
        this.uid = uid;
        this.name = name;

        this.email = email;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
