package com.example.day3.model;

import android.os.Handler;

import com.example.day3.api.UserApi;
import com.example.day3.contract.IRegContract;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class RegModel implements IRegContract.IRegModle {
    Handler handler=new Handler();
    private RegCallback mRegCallback;
    @Override
    public void reg(HashMap<String, String> parmas) {
        OkHttpClient okHttpClient=new OkHttpClient.Builder()
                .readTimeout(5,TimeUnit.SECONDS)
                .connectTimeout(5,TimeUnit.SECONDS)
                .writeTimeout(5,TimeUnit.SECONDS)
                .build();
        FormBody.Builder formBody = new FormBody.Builder();
        for (Map.Entry<String,String> p:parmas.entrySet()){
            formBody.add(p.getKey(),p.getValue());
        }
        Request request = new Request.Builder().url(UserApi.USER_REG).post(formBody.build()).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if (mRegCallback!=null){
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            mRegCallback.onFailure("网络异常，请稍后再试");
                        }
                    });
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (mRegCallback!=null){
                    final String result = response.body().string();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            mRegCallback.onResponse(result);
                        }
                    });
                }
            }
        });
    }

    @Override
    public void login(HashMap<String, String> parmas) {

    }

    public void setmRegCallback(RegCallback mRegCallback) {
        this.mRegCallback = mRegCallback;
    }

    public interface RegCallback{
        void onFailure(String msg);
        void onResponse(String result);
    }
}
