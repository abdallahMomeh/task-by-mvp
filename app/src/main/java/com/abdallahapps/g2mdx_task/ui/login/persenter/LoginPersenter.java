package com.abdallahapps.g2mdx_task.ui.login.persenter;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.abdallahapps.g2mdx_task.APP;
import com.abdallahapps.g2mdx_task.generalUtils.Constants;
import com.abdallahapps.g2mdx_task.model.DataManager;
import com.abdallahapps.g2mdx_task.model.data.dto.User;
import com.abdallahapps.g2mdx_task.ui.login.view.LoginView;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

public class LoginPersenter {


    String email;
    String facebookEmail;
    String facebookToken;
    String facebookName;
    String facebookImage;

    LoginView loginView;

    public LoginPersenter(LoginView loginView){
        this.loginView = loginView;
    }


    public void login(String username , String password){

        if (username.isEmpty() || password.isEmpty() ){
            loginView.onError(Constants.enter_username_password);
            return;
        }

        User user = DataManager.getInstance().login(username , password);

        if (user == null ){
            loginView.onError(Constants.invalid_username_password);
        }else {
            DataManager.getInstance().saveUser(user);
            loginView.onSuccessLogin(user);
        }

    }

    // login with login button of facebook
    public void loginWithFacebook(LoginButton loginButton ,CallbackManager callbackManager){

        final String EMAIL = "email";

        loginButton.setReadPermissions(EMAIL,"public_profile");
        // If you are using in a fragment, call loginButton.setFragment(this);

        // Callback registration
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

                Log.d("loginFacebook",""+loginResult.toString());
                getDataFromFacebook(loginResult);

            }

            @Override
            public void onCancel() {
                Toast.makeText(APP.context, "cancel", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException exception) {
                Toast.makeText(APP.context, "error "+exception.getMessage(), Toast.LENGTH_SHORT).show();
                Toast.makeText(APP.context, "error "+exception.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void loginWithFacebook(CallbackManager callbackManager){

        String EMAIL = "email";
        final LoginManager loginManager =LoginManager.getInstance();
        //callbackManager = CallbackManager.Factory.create();
        loginManager.logInWithReadPermissions((Activity) APP.context, Arrays.asList(EMAIL));
        loginManager.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d("loginFacebook",""+loginResult.toString());
                getDataFromFacebook(loginResult);

            }
            @Override
            public void onCancel() {
                Log.d("loginFacebook","cancel");
            }

            @Override
            public void onError(FacebookException error) {
                error.printStackTrace();
                Log.d("loginFacebook",""+error);
            }
        });
    }

    void getDataFromFacebook(final LoginResult loginResult){
        GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                Log.d("loginFacebook",response.toString());
                try {
                    JSONObject jsonObject = response.getJSONObject(); //.getJSONObject("graphObject");
                    facebookImage=response.getJSONObject().getJSONObject("picture").getJSONObject("data").getString("url");
                    if(!jsonObject.isNull("email")){
                        facebookEmail=jsonObject.getString("email");
                    }
                    facebookName=jsonObject.getString("name");
                    loginView.onSuccess();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                facebookToken = loginResult.getAccessToken().getToken();
                // fireBaseToken=FirebaseInstanceId.getInstance().getToken();

            }
        });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,name,email,gender,picture");
        request.setParameters(parameters);
        request.executeAsync();
    }


}
