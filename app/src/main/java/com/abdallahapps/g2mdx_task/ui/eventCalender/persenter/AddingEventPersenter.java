package com.abdallahapps.g2mdx_task.ui.eventCalender.persenter;

import android.content.Intent;
import android.provider.CalendarContract;

import com.abdallahapps.g2mdx_task.APP;
import com.abdallahapps.g2mdx_task.generalUtils.Constants;
import com.abdallahapps.g2mdx_task.model.data.dto.Event;
import com.abdallahapps.g2mdx_task.ui.eventCalender.view.AddingEventView;

import java.util.Calendar;

public class AddingEventPersenter {

    AddingEventView addingEventView;

    public AddingEventPersenter(AddingEventView addingEventView){
        this.addingEventView = addingEventView;
    }

    public void addEventToCalender(Event event){

        if (event!=null){
            if (event.getTITLE()!=null && event.getDESCRIPTION() !=null && event.getLOCATION()!=null
                    && event.getBEGIN_TIME() !=0 &&  event.getEND_TIME() !=0 ){


                Intent intent = new Intent(Intent.ACTION_INSERT);
                intent.setType("vnd.android.cursor.item/event");

                intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, event.getBEGIN_TIME());
                intent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME, event.getEND_TIME());
                intent.putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, true);
                intent.putExtra(CalendarContract.Events.TITLE, event.getTITLE());
                intent.putExtra(CalendarContract.Events.DESCRIPTION, event.getDESCRIPTION());
                intent.putExtra(CalendarContract.Events.EVENT_LOCATION, event.getLOCATION());
                intent.putExtra(CalendarContract.Events.RRULE, "FREQ=YEARLY");
                APP.context.startActivity(intent);

                return;
            }
        }

        addingEventView.onError(Constants.error_event_empty);


    }

}
