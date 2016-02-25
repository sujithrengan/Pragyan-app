package org.pragyan.pragyantshirtapp;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;


import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * Implementation of App Widget functionality.
 */


/**
 * Implementation of App Widget functionality.
 *
 */


public class UpcomingWidget extends AppWidgetProvider {

    static Calendar timenow;
    static int a,b,c,d,checkintent=0;


    public static String MY_WIDGET_UPDATE = "MY_OWN_WIDGET_UPDATE";
    private static final String MyOnClick1 = "myOnClickTag1";
    private static final String MyOnClick2 = "myOnClickTag2";

    public static int clickCount = 0;
    static String msg[] = {"cat","dog","rat"};


    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);//add this line

        if (MyOnClick1.equals(intent.getAction())) {
            // your onClick action is here
            //Toast.makeText(context, "Next Intent", Toast.LENGTH_SHORT).show();
            checkintent=1;
            ++StorePresent.pos;
            // Toast.makeText(context, "answer "+ clickCount, Toast.LENGTH_SHORT).show();
        }
        if (MyOnClick2.equals(intent.getAction())) {
            checkintent=1;

            --StorePresent.pos;
            //Toast.makeText(context, "Prev Intent "+ clickCount, Toast.LENGTH_SHORT).show();
        }
        else if (MY_WIDGET_UPDATE.equals(intent.getAction())) {
            //Toast.makeText(context, "Alarm Intent", Toast.LENGTH_SHORT).show();
            checkintent=0;
//            Toast.makeText(context, "Alarm Intent", Toast.LENGTH_LONG).show();

        }
        else {
            //Toast.makeText(context, "Extra Intent", Toast.LENGTH_SHORT).show();
            checkintent = 2;
        }
        //if(checkintent!=2) {
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
        ComponentName thisAppWidget = new ComponentName(context.getPackageName(), UpcomingWidget.class.getName());
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(thisAppWidget);
        //Toast.makeText(context, "Entering onUpdate()", Toast.LENGTH_SHORT).show();
        onUpdate(context, appWidgetManager, appWidgetIds);
        // }
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager,
                         int[] appWidgetIds) {
        final int N = appWidgetIds.length;
        ComponentName thisWidget = new ComponentName(context, UpcomingWidget.class);
        for (int i = 0; i < N; i++) {
            //Toast.makeText(context, "onUpdate()", Toast.LENGTH_SHORT).show();
            // initializing widget layout





            /*RemoteViews remoteViews = new RemoteViews(context.getPackageName(),
                    R.layout.list_single);




            // register for button event
            remoteViews.setOnClickPendingIntent(R.id.describe,
                    buildButtonPendingIntent(context));
            // request for widget update
            pushWidgetUpdate(context, remoteViews);*/

            // updating view with initial data


            /*RemoteViews remoteViews = new RemoteViews(context.getPackageName(),
                R.layout.upcoming_widget);*/
            RemoteViews mviews = new RemoteViews(context.getPackageName(), R.layout.upcoming_widget2);
            // if(checkintent==1) {

            mviews.setOnClickPendingIntent(R.id.Next, getPendingSelfIntentfront(context, MyOnClick1));
            mviews.setOnClickPendingIntent(R.id.Prev, getPendingSelfIntentback(context, MyOnClick2));
            pushWidgetUpdate(context, mviews);
            // }


            /*if(StorePresent.tie==0) {
                RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.no_widget_events);
                views.setOnClickPendingIntent(R.id.Next, getPendingSelfIntentfront(context, MyOnClick1));
                views.setOnClickPendingIntent(R.id.Prev, getPendingSelfIntentback(context, MyOnClick2));
               // pushWidgetUpdate(context, views);
               // appWidgetManager.updateAppWidget(appWidgetIds[i], views);
            }*/

            // if(StorePresent.tie>0){


            StorePresent.showsort();

            thisWidget = new ComponentName(context, UpcomingWidget.class);

            if(StorePresent.tie==0) {
                RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.no_widget_events);
                if(checkintent==1) {
                    views.setOnClickPendingIntent(R.id.Next, getPendingSelfIntentfront(context, MyOnClick1));
                    views.setOnClickPendingIntent(R.id.Prev, getPendingSelfIntentback(context, MyOnClick2));
                }
                pushWidgetUpdate(context, views);
                // appWidgetManager.updateAppWidget(appWidgetIds[i], views);
            }
            // Construct the RemoteViews object

            else {
                if (StorePresent.tie != 0)
                    StorePresent.pos = (StorePresent.pos + StorePresent.tie) % StorePresent.tie;
                else
                    StorePresent.pos = 0;

                // String[] temp=StorePresent.storepresent[widcount];
                RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.upcoming_widget2);
                timenow = new GregorianCalendar(TimeZone.getTimeZone("GMT+5:30"));

                Calendar time5 = new GregorianCalendar(TimeZone.getTimeZone("GMT+5:30"));
                time5.set(timenow.get(Calendar.YEAR), timenow.get(Calendar.MONTH), StorePresent.showtime[StorePresent.pos][0], StorePresent.showtime[StorePresent.pos][1], StorePresent.showtime[StorePresent.pos][2]);

                Calendar time6 = new GregorianCalendar(TimeZone.getTimeZone("GMT+5:30"));
                time6.set(timenow.get(Calendar.YEAR), timenow.get(Calendar.MONTH), StorePresent.showtime[StorePresent.pos][3], StorePresent.showtime[StorePresent.pos][4], StorePresent.showtime[StorePresent.pos][5]);


                //calculates time left for begining an event
                if (time5.after(timenow)) {

                    a = ((int) ((time5.getTimeInMillis() - timenow.getTimeInMillis()) / 3600000));
                    b = ((int) ((time5.getTimeInMillis() - timenow.getTimeInMillis()) / 60000)) % 60;


                    if (a != 0)
                        remoteViews.setTextViewText(R.id.widgetTime, "begins in " + a + " hours " + b + " mins ");
                    else
                        remoteViews.setTextViewText(R.id.widgetTime, "begins in " + b + " mins ");
                }
                //calculates time left for end of an ongoing event
                else if (time6.before(timenow)) {


                    remoteViews.setTextViewText(R.id.widgetTime, "ends in 0 hours 0 mins ");
                } else {

                    c = ((int) ((time6.getTimeInMillis() - timenow.getTimeInMillis()) / 3600000));
                    d = ((int) ((time6.getTimeInMillis() - timenow.getTimeInMillis()) / 60000)) % 60;
                    if (c != 0)
                        remoteViews.setTextViewText(R.id.widgetTime, "ends in " + c + " hours " + d + " mins ");
                    else
                        remoteViews.setTextViewText(R.id.widgetTime, "ends in " + d + " mins ");

                }
                if(checkintent!=1){
                    if (StorePresent.tie != 0)
                        StorePresent.pos = (StorePresent.pos + StorePresent.tie) % StorePresent.tie;
                    else
                        StorePresent.pos = 0;
                }

                remoteViews.setTextViewText(R.id.widgetEvent, StorePresent.showpresent[StorePresent.pos][0]);
                remoteViews.setTextViewText(R.id.widgetLocation, StorePresent.showpresent[StorePresent.pos][1]);
                remoteViews.setTextViewText(R.id.widgetCate, StorePresent.showpresent[StorePresent.pos][2]);
                if(checkintent==1) {
                    remoteViews.setOnClickPendingIntent(R.id.Next, getPendingSelfIntentfront(context, MyOnClick1));
                    remoteViews.setOnClickPendingIntent(R.id.Prev, getPendingSelfIntentback(context, MyOnClick2));
                }
                // Instruct the widget manager to update the widget
                //appWidgetManager.updateAppWidget(appWidgetIds[i], remoteViews);
                pushWidgetUpdate(context.getApplicationContext(), remoteViews);
                //


                // }
            /*else {
                RemoteViews noViews = new RemoteViews(context.getPackageName(), R.layout.no_widget_events);
                thisWidget = new ComponentName(context, UpcomingWidget.class);
                noViews.setOnClickPendingIntent(R.id.Next, getPendingSelfIntentfront(context, MyOnClick1));
                noViews.setOnClickPendingIntent(R.id.Prev, getPendingSelfIntentback(context, MyOnClick2));
                appWidgetManager.updateAppWidget(appWidgetIds[i], noViews);
                // pushWidgetUpdate(context.getApplicationContext(),       noViews);
            }*/


                //pushWidgetUpdate(context, remoteViews);
            }

        }
    }

    protected PendingIntent getPendingSelfIntentfront(Context context, String action) {
        // ++MyWidgetIntentReceiver.clickCount;
        Intent intent = new Intent(context, getClass());
        intent.setAction(action);
        return PendingIntent.getBroadcast(context, 0, intent, 0);
    }

    protected PendingIntent getPendingSelfIntentback(Context context, String action) {
        // --MyWidgetIntentReceiver.clickCount;
        Intent intent = new Intent(context, getClass());
        intent.setAction(action);
        return PendingIntent.getBroadcast(context, 0, intent, 0);
    }

  /*  public static PendingIntent buildButtonPendingIntent(Context context) {
        ++MyWidgetIntentReceiver.clickCount;

        // initiate widget update request
        Intent intent = new Intent();
        intent.setAction(WidgetUtils.WIDGET_UPDATE_ACTION);
        return PendingIntent.getBroadcast(context, 0, intent,
                PendingIntent.FLAG_UPDATE_CURRENT);
    }*/

   /* private static CharSequence getDesc() {
        return "Sync to see some of our funniest joke collections";
    }*/

    private static CharSequence getTitle() {
        return "Funny Jokes";
    }

   /* static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        RemoteViews remoteViews = new RemoteViews(context.getPackageName(),
                R.layout.list_single);

        // updating view

        remoteViews.setTextViewText(R.id.Event, getTitle());
        remoteViews.setTextViewText(R.id.Location, getDesc1(context));

        // re-registering for click listener
        appWidgetManager.updateAppWidget(appWidgetId, remoteViews);

        //updateAppWidget(context, appWidgetManager, appWidgetId);

    }*/

    static String getDesc1(Context context) {
        // some static jokes from xml
        //Toast.makeText(context, "getDesc()", Toast.LENGTH_LONG).show();

        clickCount=(clickCount+msg.length)%msg.length;
        return msg[clickCount];
    }

    public static void pushWidgetUpdate(Context context, RemoteViews remoteViews) {
        ComponentName myWidget = new ComponentName(context,    UpcomingWidget.class);
        AppWidgetManager manager = AppWidgetManager.getInstance(context);
        manager.updateAppWidget(myWidget, remoteViews);
    }
}
