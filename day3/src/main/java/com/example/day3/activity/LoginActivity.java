package com.example.day3.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.day3.R;
import com.example.day3.contract.ILoginContract;
import com.example.day3.entity.UserEntity;
import com.example.day3.presenter.LoginPresenter;

import java.util.HashMap;

public class LoginActivity extends AppCompatActivity implements ILoginContract.ILoginView {

    private EditText ed_phone;
    private EditText ed_pwd;
    private Button btn_login;
    private Button btn_zc;
    private LoginPresenter presenter;
    private String mobile;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ed_phone = findViewById(R.id.ed_phone);
        ed_pwd = findViewById(R.id.ed_pwd);
        btn_login = findViewById(R.id.btn_login);
        btn_zc = findViewById(R.id.btn_zc);
        btn_zc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegActivity.class);
                startActivity(intent);
            }
        });
        initData();
    }

    private void initData() {
        presenter = new LoginPresenter(this);
    }

    public void login(View view){
        mobile = ed_phone.getText().toString();
        password = ed_pwd.getText().toString();
        HashMap<String,String> params=new HashMap<>();
        params.put("mobile",mobile);
        params.put("password",password);
        if (presenter!=null){
            presenter.login(params);
        }
    }

    @Override
    public void mobileError(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void pwdError(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void failure(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void success(UserEntity userEntity) {
        Toast.makeText(this,userEntity.getMsg()+"",Toast.LENGTH_SHORT).show();
        if (userEntity.getMsg().equals("登录成功")){
            Intent intent = new Intent(LoginActivity.this, SuccessActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void successMsg(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }
}
