package com.android.androidTesting.widgets;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import androidx.annotation.NonNull;
import android.content.Intent;
import android.net.Uri;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.android.androidTesting.MainActivity;
import com.android.androidTesting.NoteActivity;
import com.android.androidTesting.R;


//Extend from the AppWidgetProvider class//

public class CollectionWidget extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

//Load the layout resource file into a RemoteViews object//

        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_main);
        setRemoteAdapter(context, views);

//Inform AppWidgetManager about the RemoteViews object//
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        RemoteViews views = null;
        for (int appWidgetId : appWidgetIds) {
//            updateAppWidget(context, appWidgetManager, appWidgetId);
            views = new RemoteViews(context.getPackageName(), R.layout.widget_main);
            setRemoteAdapter(context, views);

            //Inform AppWidgetManager about the RemoteViews object//
            appWidgetManager.updateAppWidget(appWidgetId, views);
        }
//        updateAppWidget(context, appWidgetManager, appWidgetIds);
        super.onUpdate(context, appWidgetManager, appWidgetIds);

        Intent configIntent = new Intent(context, MainActivity.class);
        PendingIntent configPendingIntent = PendingIntent.getActivity(context, 0, configIntent, 0);
        views.setOnClickPendingIntent(R.id.dreamsHeader, configPendingIntent);  // clicking the header takes you to main screen
        views.setOnClickPendingIntent(R.id.widgetID, configPendingIntent);  // clicking somewhere undefined takes you to main screen

//        configIntent = new Intent(context, NoteActivity.class);
//        configIntent.putExtra("noteID", -1);
        configIntent = new Intent(context, MainActivity.class);
//        newConfigIntent.putExtra("addNewNote", true);
        configIntent.setData(Uri.parse("addNewNote"));
        configPendingIntent = PendingIntent.getActivity(context, 0, configIntent, 0);
        views.setOnClickPendingIntent(R.id.addDream, configPendingIntent);  // clicking the header takes you to main screen

        appWidgetManager.updateAppWidget(appWidgetIds, views);

    }

    @Override
    public void onEnabled(Context context) {
        Toast.makeText(context,"onEnabled called", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onDisabled(Context context) {
        Toast.makeText(context,"onDisabled called", Toast.LENGTH_LONG).show();
    }

    private static void setRemoteAdapter(Context context, @NonNull final RemoteViews views) {
        views.setRemoteAdapter(R.id.listView,
                new Intent(context, WidgetService.class));
    }

}