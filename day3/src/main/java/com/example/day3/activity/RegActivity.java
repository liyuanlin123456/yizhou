package com.example.day3.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.day3.R;
import com.example.day3.contract.IRegContract;
import com.example.day3.presenter.RegPresenter;

import java.util.HashMap;

public class RegActivity extends AppCompatActivity implements IRegContract.IRegView {

    private EditText ed_phone;
    private EditText ed_pwd;
    private Button btn_zc;
    private RegPresenter regPresenter;
    private String mobile;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);
        ed_phone = findViewById(R.id.ed_phone);
        ed_pwd = findViewById(R.id.ed_pwd);
        btn_zc = findViewById(R.id.btn_zc);
        initData();
    }

    private void initData() {
        regPresenter = new RegPresenter(this);
    }

    public void register(View view){
        mobile = ed_phone.getText().toString();
        password = ed_pwd.getText().toString();
        HashMap<String,String> params=new HashMap<>();
        params.put("mobile",mobile);
        params.put("password",password);
        regPresenter.register(params);
    }

    @Override
    public void mobileError(String error) {
        Toast.makeText(this,error,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void success(String result) {
        Toast.makeText(this,result,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void failure(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (regPresenter!=null){
            regPresenter.destroy();
        }
    }
}
