package com.abdallahapps.g2mdx_task.ui.home.view;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.abdallahapps.g2mdx_task.R;
import com.abdallahapps.g2mdx_task.ui.base.view.BaseActivity;
import com.abdallahapps.g2mdx_task.ui.contacts.view.ContactsActivity;
import com.abdallahapps.g2mdx_task.ui.eventCalender.view.AddingEventCalenderActivity;
import com.abdallahapps.g2mdx_task.ui.map.view.MapActivity;
import com.abdallahapps.g2mdx_task.ui.notes.view.NotesActivity;
import com.abdallahapps.g2mdx_task.ui.searchCountry.view.SearchCountryActivity;
import com.bumptech.glide.Glide;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.share.ShareApi;
import com.facebook.share.Sharer;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;

import java.util.Arrays;

import androidx.annotation.Nullable;

public class HomeActivity extends BaseActivity implements HomeView , View.OnClickListener {

    Button mShowNotesBTN,mSearchCountryBTN,mLoadContactsBTN,mMapPageBTN,mCachImageBtn,mLoadImageBtn,mAddingEventBtn,mShareImageBtn;
    ImageView LoadIV;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initViews();

    }

    void initViews(){
        LoadIV = findViewById(R.id.LoadIV);
        mShowNotesBTN=findViewById(R.id.mShowNotesBTN);
        mSearchCountryBTN=findViewById(R.id.mSearchCountryBTN);
        mLoadContactsBTN=findViewById(R.id.mLoadContactsBTN);
        mMapPageBTN=findViewById(R.id.mMapPageBTN);
        mCachImageBtn=findViewById(R.id.mCachImageBtn);
        mLoadImageBtn=findViewById(R.id.mLoadImageBtn);
        mAddingEventBtn=findViewById(R.id.mAddingEventBtn);
        mShareImageBtn =findViewById(R.id.mShareImageBtn);

        mShowNotesBTN.setOnClickListener(this);
        mSearchCountryBTN.setOnClickListener(this);
        mLoadContactsBTN.setOnClickListener(this);
        mMapPageBTN.setOnClickListener(this);
        mCachImageBtn.setOnClickListener(this);
        mLoadImageBtn.setOnClickListener(this);
        mAddingEventBtn.setOnClickListener(this);
        mShareImageBtn.setOnClickListener(this);
    }

    @Override
    public void onSuccess() {

    }

    @Override
    public void onError(int type) {

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.mShowNotesBTN:{
                startActivity(new Intent(HomeActivity.this, NotesActivity.class));
                break;
            }

            case R.id.mLoadContactsBTN:{
                startActivity(new Intent(HomeActivity.this, ContactsActivity.class));
                break;
            }

            case R.id.mSearchCountryBTN:{
                startActivity(new Intent(HomeActivity.this, SearchCountryActivity.class));
                break;
            }

            case R.id.mMapPageBTN:{
                startActivity(new Intent(HomeActivity.this, MapActivity.class));
                break;
            }

            case R.id.mAddingEventBtn:{
                startActivity(new Intent(HomeActivity.this, AddingEventCalenderActivity.class));
                break;
            }

            case R.id.mShareImageBtn:{
                LoginManager.getInstance().logInWithPublishPermissions(this, Arrays.asList("publish_actions"));

                Bitmap image = BitmapFactory.decodeResource(getResources(), R.drawable.logo);
                SharePhoto photo = new SharePhoto.Builder()
                        .setBitmap(image)
                        .build();

                SharePhotoContent content = new SharePhotoContent.Builder()
                        .addPhoto(photo)
                        .build();

                ShareApi.share(content, new FacebookCallback<Sharer.Result>() {
                    @Override
                    public void onSuccess(Sharer.Result result) {
                        Toast.makeText(HomeActivity.this, "sucess", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancel() {

                    }

                    @Override
                    public void onError(FacebookException error) {
                        Toast.makeText(HomeActivity.this, ""+error.getMessage(), Toast.LENGTH_SHORT).show();
                        Toast.makeText(HomeActivity.this, ""+error.toString(), Toast.LENGTH_SHORT).show();

                    }
                });
                break;
            }

            case R.id.mCachImageBtn:{
                Glide.with(this).load("https://abdullah-app.com/images/dora_full.png").submit();
                break;
            }

            case  R.id.mLoadImageBtn:{
                Glide.with(this).load("https://abdullah-app.com/images/dora_full.png").into(LoadIV);
                break;
            }



        }

    }

}
