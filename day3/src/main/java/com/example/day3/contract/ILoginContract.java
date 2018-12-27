package com.example.day3.contract;

import com.example.day3.entity.UserEntity;
import com.example.day3.net.RequestCallback;

import java.util.HashMap;

public interface ILoginContract {
    public abstract class LoginPresenter{
        public abstract void login(HashMap<String,String> params);
    }
    interface ILoginModel{
        void login(HashMap<String,String> params, RequestCallback requestCallback);
    }
    interface ILoginView{
        void mobileError(String msg);
        void pwdError(String msg);
        void failure(String msg);
        void success(UserEntity userEntity);
        void successMsg(String msg);
    }
}
