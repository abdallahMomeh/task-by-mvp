package com.abdallahapps.g2mdx_task.model.data.source.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.abdallahapps.g2mdx_task.model.data.dto.Country;
import com.abdallahapps.g2mdx_task.model.data.dto.Note;
import com.abdallahapps.g2mdx_task.model.data.dto.User;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    public static final String UserTable="User";
    public static final String NoteTable="Note";
    public static final String CountryTable="Country";

    public static final String user_id="id";
    public static final String username="username";
    public static final String password="password";
    public static final String isFBLogin="isFBLogin";
    public static final String isRemembered="isRemembered";

    public static final String note_id="id";
    public static final String note_content="content";
    public static final String note_time="time";

    public static final String country_id="id";
    public static final String country_name="name";



    Context context;
    public DBHelper(Context context/*, String name, SQLiteDatabase.CursorFactory factory, int version*/) {
        super(context, "task.db", null, 1);
        this.context = context;
    }


    @Override
    public void onCreate(SQLiteDatabase db ) {

        db.execSQL(
                "create table  " +UserTable+
                        "( "+ user_id+ " integer primary key, "+ username+" text, "+password+" text, "+isFBLogin+" INTEGER , "+isRemembered+" INTEGER  );"
        );

        db.execSQL(
                "create table  " +NoteTable+
                        "( "+note_id+" integer primary key, "+note_content+" text, "+note_time+" long );"
        );

        db.execSQL(
                "create table  "+CountryTable+
                        "( "+country_id+" integer primary key, "+country_name+" text );"
        );

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        db.execSQL("DROP TABLE IF EXISTS "+UserTable);
        db.execSQL("DROP TABLE IF EXISTS "+NoteTable);
        db.execSQL("DROP TABLE IF EXISTS "+CountryTable);

        onCreate(db);
    }




    public boolean insertUser (User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(username, user.getUsername());
        contentValues.put(password, user.getPassword());
        contentValues.put(isFBLogin, user.isLoginFB());
        contentValues.put(isRemembered, user.isRemembered());

        db.insert(UserTable, null, contentValues);
        return true;
    }

    public void dropStudentTable(){
        SQLiteDatabase db = this.getWritableDatabase();
        int count=db.delete("students","1",null);
    }


    /*public List<User> getAllStudent(){
        List<Student>users=new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();
        Cursor res= db.rawQuery("select * from students",null);
        if(res.getCount()>0){
            res.moveToFirst();
            for(int i=0;i<res.getCount();i++){
                Student user=new Student();
                user.setId(res.getString(1));
                user.setArabicName(res.getString(2));
                user.setGroupId(res.getString(3));
                user.setNumBooking(res.getInt(5));
                user.setPhone(res.getString(4));
                user.setFriendnessId(res.getString(6));
                users.add(user);
                res.moveToNext();
            }

            res.close();
            db.close();
            return users;
        }else {
            return users;
        }

    }*/



    public User getUser(String name , String pass){
        //List<Student>users=new ArrayList<>();
        User user = null;
        SQLiteDatabase db = getReadableDatabase();
        Cursor res= db.rawQuery("select * from "+UserTable+" where "+this.username+"='"+name+"' and "+this.password+"='"+pass+"'",null);
        if(res.getCount()>0){
            res.moveToFirst();

                user=new User();
                user.setId(res.getInt(0));
                user.setUsername(res.getString(1));
                user.setPassword(res.getString(2));
                user.setLoginFB(res.getInt(3)==1);
                user.setRemembered(res.getInt(4)==1);

            res.close();
            db.close();

        }
        return  user;

    }



    public boolean insertCountry (Country country) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(country_name, country.getName());
        //contentValues.put(note_time, note.getTime());

        db.insert(CountryTable, null, contentValues);
        return true;
    }



    public List<Country> getCountryByName(String countryName){
        List<Country>countries=new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();
        Cursor res= db.rawQuery("select * from "+CountryTable+" where "+country_name+" like '%"+countryName+"%'",null);
        if(res.getCount()>0){
            res.moveToFirst();
            for(int i=0;i<res.getCount();i++){

                Country country = new Country();

                country.setId(res.getInt(0));
                country.setName(res.getString(1));

                countries.add(country);

                res.moveToNext();
            }

            res.close();
            db.close();

        }
            return countries;

    }


    public boolean insertNote (Note note) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(note_content, note.getContent());
        contentValues.put(note_time, note.getTime());

        db.insert(NoteTable, null, contentValues);
        return true;
    }



    public List<Note> getAllNotes(){
        List<Note>notes=new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();
        Cursor res= db.rawQuery("select * from "+NoteTable,null);
        if(res.getCount()>0){
            res.moveToFirst();
            for(int i=0;i<res.getCount();i++){

                Note note = new Note();

                note.setId(res.getInt(0));
                note.setContent(res.getString(1));
                note.setTime(res.getInt(2));
                notes.add(note);

                res.moveToNext();
            }

            res.close();
            db.close();

        }
        return notes;

    }


}
