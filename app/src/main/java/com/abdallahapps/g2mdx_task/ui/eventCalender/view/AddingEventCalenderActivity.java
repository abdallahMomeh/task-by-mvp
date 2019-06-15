package com.abdallahapps.g2mdx_task.ui.eventCalender.view;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.abdallahapps.g2mdx_task.R;
import com.abdallahapps.g2mdx_task.generalUtils.Constants;
import com.abdallahapps.g2mdx_task.generalUtils.CustomListenter;
import com.abdallahapps.g2mdx_task.generalUtils.DatePicker;
import com.abdallahapps.g2mdx_task.model.data.dto.Event;
import com.abdallahapps.g2mdx_task.ui.base.view.BaseActivity;
import com.abdallahapps.g2mdx_task.ui.eventCalender.persenter.AddingEventPersenter;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

public class AddingEventCalenderActivity extends BaseActivity implements AddingEventView, View.OnClickListener {

    AddingEventPersenter addingEventPersenter;
    Event event ;
    Toolbar toolbar;
    EditText mEventTitle,mEventDescription,mEventLocation;
    TextView mBegainTime,mEndTime;
    Button mAddEventBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_event);
        initViews();
        event = new Event();
        addingEventPersenter  = new AddingEventPersenter(this);

    }

    void initViews(){
        toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.arrow_back_white_24);
        setSupportActionBar(toolbar);

        mEventTitle = findViewById(R.id.mEventTitle);
        mEventDescription = findViewById(R.id.mEventDescription);
        mEventLocation = findViewById(R.id.mEventLocation);
        mBegainTime = findViewById(R.id.mBegainTime);
        mEndTime = findViewById(R.id.mEndTime);
        mAddEventBtn = findViewById(R.id.mAddEventBtn);

        mAddEventBtn.setOnClickListener(this);
        mBegainTime.setOnClickListener(this);
        mEndTime.setOnClickListener(this);
    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onSuccess() {
        Toast.makeText(this, R.string.success, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError(int type) {

        switch (type){

            case Constants.error_event_empty:{
                Toast.makeText(this, R.string.ensureFromEvent, Toast.LENGTH_SHORT).show();
                break;
            }

        }
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.mAddEventBtn:{
                event.setTITLE(mEventTitle.getText().toString());
                event.setDESCRIPTION(mEventDescription.getText().toString());
                event.setLOCATION(mEventLocation.getText().toString());
                addingEventPersenter.addEventToCalender(event);
                break;
            }

            case R.id.mBegainTime:{
                DatePicker newFragment = new DatePicker();
                newFragment.setOnDatePicked(new CustomListenter.OnDatePicked() {
                    @Override
                    public void onDate(long date ,String dateText) {
                        event.setBEGIN_TIME(date);
                        mBegainTime.setText(dateText);
                    }
                });
                newFragment.show(getSupportFragmentManager(), "date picker");
                break;
            }

            case R.id.mEndTime:{
                DatePicker newFragment = new DatePicker();
                newFragment.setOnDatePicked(new CustomListenter.OnDatePicked() {
                    @Override
                    public void onDate(long date , String dateText) {
                        event.setEND_TIME(date);
                        mEndTime.setText(dateText);
                    }
                });
                newFragment.show(getSupportFragmentManager(), "date picker");
                break;
            }

        }

    }


}
