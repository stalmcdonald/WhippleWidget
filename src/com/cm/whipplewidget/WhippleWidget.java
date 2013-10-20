/*
 * Crystal McDonald
 * MDF3
 * 1310
 * 
 * Widget Application for Whipple
 */
package com.cm.whipplewidget;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.Random;

import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.RemoteViews;

public class WhippleWidget extends Service {
	public static final String RANDOMTIPS = "updateTip";
	public static final String CURRENTTIP = "MyTip";
	
	private String mainClamtips;
	private LinkedList<String> clamtips;
	
	public WhippleWidget(){
		this.clamtips = new LinkedList<String>();
		randomTipList();
	}

	//creating Random tips to display in widget
    private void randomTipList() {
    	this.clamtips.add("Dig clams at low-tide for best results");
    	this.clamtips.add("Clamming Season Begins October.");
    	this.clamtips.add("Don't forget to purchase a liscence!");
    	this.clamtips.add("Digging clams is very messy work.");
    	this.clamtips.add("Small clams are great for chowders");
    	this.clamtips.add("Cockies are used in chowders");
    	this.mainClamtips = "Dig clams at Low Tide";
    }

  //when starting widget im calling methods to display first tip
	@Override
	public void onStart(Intent intent, int beginHere) {
	    handleCommand(intent, beginHere);
		
        Log.i(WidgetProvider.TIP, "onStartCommand");

        getTips(intent);
			
		stopSelf(beginHere);
		
		return;
	}

	private int handleCommand(Intent intent, int startId) {
		// TODO Auto-generated method stub
		return START_STICKY;
	}

	//creating a method that handles random tips by timer
	private String displayTipsAtRandom() {
		Random random = new Random(Calendar.getInstance().getTimeInMillis());//changes based on time 
		int randomlyPlaced = random.nextInt(clamtips.size());
		return clamtips.get(randomlyPlaced);
	}

	//getting the tips to be displayed randomly
	private void getTips(Intent intent) {
        if (intent != null){
    		String requestedAction = intent.getAction();
    		if (requestedAction != null && requestedAction.equals(RANDOMTIPS)){
	            this.mainClamtips = displayTipsAtRandom();
	            	            	 
	            int widgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, 0);

	            Log.i(WidgetProvider.TIP, "Tidal Prediction: " + mainClamtips + " to widget " + widgetId);
	            
	            AppWidgetManager appWidgetMan = AppWidgetManager.getInstance(this);
	            RemoteViews rv = new RemoteViews(this.getPackageName(),R.layout.widget);
	            rv.setTextViewText(R.id.randomClamTips, mainClamtips);
	            appWidgetMan.updateAppWidget(widgetId, rv);
	            
	            Log.i(WidgetProvider.TIP, "Clamming Tips Updated!");
    		}
        }
	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

}