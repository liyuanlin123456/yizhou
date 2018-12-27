package com.example.day3.contract;

import java.util.HashMap;

public interface IRegContract {
    public abstract class RegPresenter{
        public abstract void register(HashMap<String,String> params);
        public abstract void login(HashMap<String,String> params);
    }
    interface IRegModle{
        void reg(HashMap<String,String> parmas);
        void login(HashMap<String,String> parmas);
    }
    interface IRegView{
        void mobileError(String error);
        void success(String result);
        void failure(String msg);
    }
}
