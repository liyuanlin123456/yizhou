package com.example.day3.net;

import com.example.day3.entity.UserEntity;

public interface RequestCallback {
    void failure(String msg);
    void successMsg(String msg);
    void success(UserEntity userEntity);
}
