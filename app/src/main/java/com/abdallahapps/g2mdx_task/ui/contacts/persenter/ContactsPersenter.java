package com.abdallahapps.g2mdx_task.ui.contacts.persenter;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Build;
import android.provider.ContactsContract;
import android.util.Log;

import com.abdallahapps.g2mdx_task.APP;
import com.abdallahapps.g2mdx_task.generalUtils.Utils;
import com.abdallahapps.g2mdx_task.model.data.dto.Contact;
import com.abdallahapps.g2mdx_task.ui.contacts.view.ContactsActivity;
import com.abdallahapps.g2mdx_task.ui.contacts.view.ContactsView;

import java.util.ArrayList;
import java.util.List;

import static com.facebook.FacebookSdk.getApplicationContext;

public class ContactsPersenter {

    ContactsView contactsView;
    List<Contact> selectUsers;
    Cursor phones;
    private static final int PERMISSIONS_REQUEST_READ_CONTACTS = 100;

    public ContactsPersenter(ContactsView contactsView){
        this.contactsView = contactsView;
        selectUsers = new ArrayList<>();
    }

    public void loadContacts(){
        // Check the SDK version and whether the permission is already granted or not.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && APP.context.checkSelfPermission(Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
           // APP.context.requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, ContactsActivity.Load_Contacts_Request);
            Utils.permissionGrant(APP.context,Manifest.permission.READ_CONTACTS,ContactsActivity.Load_Contacts_Request);
            //After this point you wait for callback in onRequestPermissionsResult(int, String[], int[]) overriden method
        } else {
            // Android version is lesser than 6.0 or the permission is already granted.
            phones = APP.context.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC");
            LoadContact loadContact = new LoadContact();
            loadContact.execute();
        }
    }


    class LoadContact extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected Void doInBackground(Void... voids) {
            // Get Contact list from Phone

            if (phones != null) {
                Log.e("count", "" + phones.getCount());
                if (phones.getCount() == 0) {

                }

                while (phones.moveToNext()) {
                    Bitmap bit_thumb = null;
                    String id = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.CONTACT_ID));
                    String name = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                    String phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));


                    Contact selectUser = new Contact();
                    selectUser.setName(name);
                    selectUser.setPhone(phoneNumber);
                    selectUsers.add(selectUser);


                }
            } else {
                Log.e("Cursor close 1", "----------------");
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            contactsView.onSuccessLoadContacts(selectUsers);
        }
    }
}
