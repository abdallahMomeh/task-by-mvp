package com.abdallahapps.g2mdx_task.model;

import com.abdallahapps.g2mdx_task.APP;
import com.abdallahapps.g2mdx_task.generalUtils.Constants;
import com.abdallahapps.g2mdx_task.model.data.dto.Country;
import com.abdallahapps.g2mdx_task.model.data.dto.Note;
import com.abdallahapps.g2mdx_task.model.data.dto.User;
import com.abdallahapps.g2mdx_task.model.data.source.db.DBHelper;
import com.abdallahapps.g2mdx_task.model.data.source.preferences.SharedManager;

import java.util.List;

public class DataManager {

    private static DataManager mInstance=null;
    private SharedManager sharedManager;
    private DBHelper dbHelper;


    private DataManager() {
        sharedManager = SharedManager.newInstance();
        dbHelper = new DBHelper(APP.context);
    }

    public static DataManager getInstance() {
        if (mInstance == null) {
            synchronized (DataManager.class) {

                mInstance = new DataManager();

            }
        }
        else if( mInstance.sharedManager==null || mInstance.dbHelper==null)
        {
            mInstance = new DataManager();
        }
        return mInstance;
    }


    public User login(String username , String password){
        return dbHelper.getUser(username,password);
    }

    public void insertUser(User user){
         dbHelper.insertUser(user);
    }

    public User getUser(){
        return sharedManager.getObject(Constants.userKey,User.class);
    }

    public void saveUser(User user){
        sharedManager.saveObject(user,Constants.userKey);
    }


    public void insertNote(Note  note){
        dbHelper.insertNote(note);
    }

    public List<Note> getAllNotes(){
        return dbHelper.getAllNotes();
    }

    public void insertCountry(Country country){
        dbHelper.insertCountry(country);
    }

    public List<Country> searchAboutCoutry(String countryName){
        return dbHelper.getCountryByName(countryName);
    }

}
