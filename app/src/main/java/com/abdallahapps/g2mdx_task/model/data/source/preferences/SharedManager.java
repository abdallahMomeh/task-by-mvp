package com.abdallahapps.g2mdx_task.model.data.source.preferences;

import android.content.Context;
import android.content.SharedPreferences;

import com.abdallahapps.g2mdx_task.APP;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class SharedManager {


    private static final String APP_PREFERENCE = "g2mdex-task";
    private static SharedManager mSharedManager;
    private SharedPreferences mPreference;
    private Gson mGson;
    private SharedPreferences.Editor mEditor;

    private SharedManager(Context mContext) {
        mPreference = mContext.getSharedPreferences(APP_PREFERENCE, Context.MODE_PRIVATE);
        mEditor = mPreference.edit();
        mGson = new Gson();
    }

    public static SharedManager newInstance() {
        if (mSharedManager == null) {
            return mSharedManager = new SharedManager(APP.context);
        } else {
            return mSharedManager;
        }
    }



    public String getCashValue(String key) {
        return mPreference.getString(key, null);
    }

    public void CashValue(String key, String value) {
        mEditor.putString(key, value).commit();
    }


    public void setInt(String key,int value){
        /*openConnection();
        editor.putInt(key,value);
        editor.commit();
        closeConnection();*/
        mEditor.putInt(key, value).commit();
    }


    public int getInt(String key,int defValue)
    {
        /*int result = defValue;
        openConnection();

        if (AbdallahSettings.contains(key)) {
            result = AbdallahSettings.getInt(key, defValue);
        }

        closeConnection();*/
        return mPreference.getInt(key, defValue);
    }


    public void setBoolean(String key,boolean value){
        mEditor.putBoolean(key, value).commit();
    }


    public Boolean getBoolean(String key,boolean defValue) {
        return mPreference.getBoolean(key, defValue);
    }


    public <T> void saveObject(T object, String key) {
        String fromObject = mGson.toJson(object);
        Boolean status= mEditor.putString(key, fromObject).commit();
    }

    public <T> T getObject(String key, Class<T> Clazz) {
        String fromString = mPreference.getString(key, null);
        T t;
        if (fromString != null) {
            t = mGson.fromJson(fromString, Clazz);
            return t;
        }
        return null;
    }


    public <T> void saveTypedList(List<T> list, Type listType, String key){
        JsonElement element = new Gson().toJsonTree(list, listType);
        JsonArray jsonArray = element.getAsJsonArray();
        mEditor.putString(key, jsonArray.toString()).commit();
    }

    public <T> ArrayList<T> getTypedList(String key , Type typeList){
        String itemsList = mPreference.getString(key, "[]");
        return new Gson().fromJson(itemsList, typeList);
    }

}
