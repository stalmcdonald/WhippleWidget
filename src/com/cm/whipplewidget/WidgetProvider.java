package com.cm.whipplewidget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.widget.RemoteViews;

public class WidgetProvider extends AppWidgetProvider {
	public static final String TIP = "Current Tide";
	
	@Override
	public void onUpdate(Context context, AppWidgetManager myWidgetManager,
			int[] widgID) {
		super.onUpdate(context, myWidgetManager, widgID);
		
		//setting pending intent
		
		final int N = widgID.length;
		
		for (int i=0; i<N; i++) {
		    int appWidgetId = widgID[i];
		   
		    //creating remote view
		    RemoteViews rv = new RemoteViews(context.getPackageName(), R.layout.widget);
		    
		    //identifies widget
		    Intent intent = new Intent(context, WhippleWidget.class);
		    intent.setAction(WhippleWidget.RANDOMTIPS);
		    intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
		    PendingIntent pendingIntent = PendingIntent.getService(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT); 
		
		 // Make the pending intent unique...
		    intent.setData(Uri.parse(intent.toUri(Intent.URI_INTENT_SCHEME)));
		 
		    
		    rv.setOnClickPendingIntent(R.id.randomClamTips, pendingIntent);

		    // Tell the AppWidgetManager to perform an update on the current App Widget
		    myWidgetManager.updateAppWidget(widgID, rv);
		    rv.setOnClickPendingIntent(R.id.imgBtn, pendingIntent);
		    
		    // Tell the AppWidgetManager to perform an update 
		    myWidgetManager.updateAppWidget(appWidgetId, rv);
		}
	}	
}