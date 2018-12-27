package com.example.day3.presenter;

import com.example.day3.contract.ILoginContract;
import com.example.day3.entity.UserEntity;
import com.example.day3.model.LoginModel;
import com.example.day3.net.RequestCallback;

import java.util.HashMap;

public class LoginPresenter extends ILoginContract.LoginPresenter {
    private LoginModel loginModel;
    private ILoginContract.ILoginView iLoginView;

    public LoginPresenter(ILoginContract.ILoginView iLoginView) {
        this.loginModel=new LoginModel();
        this.iLoginView = iLoginView;
    }

    @Override
    public void login(HashMap<String, String> params) {

        if (loginModel!=null){
            loginModel.login(params, new RequestCallback() {
                @Override
                public void failure(String msg) {
                    if (iLoginView!=null){
                        iLoginView.failure(msg);
                    }
                }

                @Override
                public void successMsg(String msg) {
                    if (iLoginView!=null){
                        iLoginView.successMsg(msg);
                    }
                }

                @Override
                public void success(UserEntity userEntity) {
                    if (iLoginView!=null){
                        iLoginView.success(userEntity);
                    }
                }
            });
        }
    }
}
