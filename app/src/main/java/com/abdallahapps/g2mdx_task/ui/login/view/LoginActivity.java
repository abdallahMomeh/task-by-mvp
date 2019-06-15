package com.abdallahapps.g2mdx_task.ui.login.view;


import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.abdallahapps.g2mdx_task.APP;
import com.abdallahapps.g2mdx_task.R;
import com.abdallahapps.g2mdx_task.generalUtils.Constants;
import com.abdallahapps.g2mdx_task.model.DataManager;
import com.abdallahapps.g2mdx_task.model.data.dto.User;
import com.abdallahapps.g2mdx_task.model.data.source.preferences.SharedManager;
import com.abdallahapps.g2mdx_task.ui.base.view.BaseActivity;
import com.abdallahapps.g2mdx_task.ui.home.view.HomeActivity;
import com.abdallahapps.g2mdx_task.ui.login.persenter.LoginPersenter;
import com.facebook.CallbackManager;

import com.facebook.login.widget.LoginButton;

import androidx.annotation.Nullable;

public class LoginActivity extends BaseActivity implements LoginView , View.OnClickListener {

    Button mLoginFbBtn,mLoginBtn;
    EditText mUsernameET,mPasswordET;
    LoginButton loginButton;
    CheckBox mRemmbermeBox;
    LoginPersenter loginPersenter;

    String email;
    String facebookEmail;
    String facebookToken;
    String facebookName;
    String facebookImage;

    CallbackManager callbackManager;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initViews();
        APP.context = this;
        if (!SharedManager.newInstance().getBoolean(Constants.InsertFirstUser,false)){
            User user = new User();
            user.setUsername("admin");
            user.setPassword("admin");
            DataManager.getInstance().insertUser(user);
            SharedManager.newInstance().setBoolean(Constants.InsertFirstUser,true);
        }
        loginPersenter = new LoginPersenter(this);

        callbackManager = CallbackManager.Factory.create();
    }

    void initViews(){

        mLoginFbBtn = findViewById(R.id.mLoginFbBtn);
        mLoginBtn = findViewById(R.id.mLoginBtn);
        mUsernameET = findViewById(R.id.mUsernameET);
        mPasswordET = findViewById(R.id.mPasswordET);
        loginButton = (LoginButton) findViewById(R.id.login_button);
        mRemmbermeBox = findViewById(R.id.mRemmbermeBox);

        mLoginBtn.setOnClickListener(this);
        mLoginFbBtn.setOnClickListener(this);
        loginButton.setOnClickListener(this);

        if (SharedManager.newInstance().getObject(Constants.remembered_user,User.class)!=null){
            User user =SharedManager.newInstance().getObject(Constants.remembered_user,User.class);
            mPasswordET.setText(user.getPassword());
            mUsernameET.setText(user.getUsername());
            mRemmbermeBox.setChecked(true);
        }
    }

    @Override
    public void onSuccess() {
        startActivity(new Intent(this, HomeActivity.class));
    }

    @Override
    public void onError(int type) {

        switch (type){

            case Constants.enter_username_password:{
                Toast.makeText(this, R.string.enterUsername, Toast.LENGTH_SHORT).show();
                break;
            }

            case Constants.invalid_username_password:{
                Toast.makeText(this, R.string.invaledUser, Toast.LENGTH_SHORT).show();
                break;
            }

        }

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.mLoginBtn:{
                loginPersenter.login(mUsernameET.getText().toString(),mPasswordET.getText().toString());
                break;
            }

            case R.id.mLoginFbBtn:{
                loginPersenter.loginWithFacebook(callbackManager);
                break;
            }

            case R.id.login_button:{
                loginPersenter.loginWithFacebook(loginButton,callbackManager);
                break;
            }

        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onSuccessLogin(User user) {
        if (mRemmbermeBox.isChecked()){
            SharedManager.newInstance().saveObject(user,Constants.remembered_user);
        }
        startActivity(new Intent(LoginActivity.this,HomeActivity.class));
    }

}

