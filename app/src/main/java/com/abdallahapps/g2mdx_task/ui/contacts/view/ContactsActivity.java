package com.abdallahapps.g2mdx_task.ui.contacts.view;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

import com.abdallahapps.g2mdx_task.APP;
import com.abdallahapps.g2mdx_task.R;
import com.abdallahapps.g2mdx_task.generalUtils.Utils;
import com.abdallahapps.g2mdx_task.model.data.dto.Contact;
import com.abdallahapps.g2mdx_task.ui.base.view.BaseActivity;
import com.abdallahapps.g2mdx_task.ui.contacts.persenter.ContactsPersenter;
import com.abdallahapps.g2mdx_task.ui.home.view.HomeView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ContactsActivity extends BaseActivity implements ContactsView {

    ContactsPersenter contactsPersenter;
    ContactsRVAdapter contactsRVAdapter;
    List<Contact>contactList;
    RecyclerView mContactsRV;

    Toolbar toolbar;
    public static final int Load_Contacts_Request=101;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        APP.context = this;
        contactList = new ArrayList<>();
        initViews();
        contactsPersenter = new ContactsPersenter(this);
        contactsPersenter.loadContacts();
    }

    void initViews(){
        toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.arrow_back_white_24);
        setSupportActionBar(toolbar);

        mContactsRV = findViewById(R.id.mContactsRV);

        contactsRVAdapter = new ContactsRVAdapter(contactList);
        mContactsRV.setLayoutManager(new LinearLayoutManager(this));
        mContactsRV.setAdapter(contactsRVAdapter);

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onSuccess() {

    }

    @Override
    public void onError(int type) {

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == Load_Contacts_Request){
            if(Utils.checkPermission(this,Manifest.permission.READ_CONTACTS)) {
                contactsPersenter.loadContacts();
            }else
                Toast.makeText(this, R.string.needPermission, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onSuccessLoadContacts(List<Contact> contacts) {
        contactList.clear();
        contactList.addAll(contacts);
        contactsRVAdapter.notifyDataSetChanged();
    }

}
