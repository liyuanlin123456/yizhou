package com.example.day3.presenter;

import com.example.day3.contract.IRegContract;
import com.example.day3.model.RegModel;
import com.example.day3.utils.ValidatorUtil;

import java.util.HashMap;

public class RegPresenter extends IRegContract.RegPresenter {
    private RegModel mRegModel;
    private IRegContract.IRegView iRegView;

    public RegPresenter(IRegContract.IRegView iRegView) {
        this.mRegModel = new RegModel();
        this.iRegView = iRegView;
    }

    @Override
    public void register(HashMap<String, String> params) {
        String mobile = params.get("mobile");
        if (!ValidatorUtil.isMobile(mobile)){
            if (iRegView!=null){
                iRegView.mobileError("手机号不合法");
            }
            return;
        }
        if (mRegModel!=null){
            mRegModel.reg(params);
            mRegModel.setmRegCallback(new RegModel.RegCallback() {
                @Override
                public void onFailure(String msg) {
                    if (iRegView!=null){
                        iRegView.failure(msg);
                    }
                }

                @Override
                public void onResponse(String result) {
                    if (iRegView!=null){
                        iRegView.success(result);
                    }
                }
            });
        }
    }

    @Override
    public void login(HashMap<String, String> params) {

    }
    public void destroy(){
        if (iRegView!=null){
            iRegView=null;
        }
    }
}
