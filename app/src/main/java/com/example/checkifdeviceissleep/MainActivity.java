package com.example.checkifdeviceissleep;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.app.KeyguardManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.PowerManager;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.Menu;
import android.view.View;

/**
 * @see http://stackoverflow.com/questions/6391870/how-exactly-to-use-notification-builder
 * @author hilfritz
 *
 */
public class MainActivity extends Activity {
	public static final String TAG = "MainActivity";
	int counter =0;
	Handler handler;
	Timer timer;
	TimerTask checkTask;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		timer = new Timer();
		handler = new Handler();
		checkDeviceStatus();
	}
	String str = "";
	private void checkDeviceStatus(){
		Log.d(TAG, "checkDeviceStatus() ");
		timer = new Timer();
		handler = new Handler();
		
		checkTask= new TimerTask() {
	        public void run() {
	                handler.post(new Runnable() {
	                        public void run() {
	                        	str="";
		                         if (isScreenOn())
		                        	 str+=" screen is on";
		                         else
		                        	 str+=" screen is off";
		                         if (isKeyguardLocked())
		                        	 str+=" on lockscreen";
		                         else
		                        	 str+=" not on lockscreen";
		                         Log.d("mytag", str);
	                        }
	               });
	        }};
	    timer.schedule(checkTask, 500, 5000); 
	}
	
	
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		timer.cancel();
		checkTask.cancel();
		Log.d(TAG, "onDestroy()");
	}

	public void createNotificationOnClick(View v){
		if (counter<0){
			counter = 0;
		}
		createNotification("title:"+counter, "description for counter ="+counter, counter, R.drawable.ic_launcher);
		counter++;
	}	
	
	public void removeNotificationOnClick(View v){
		counter--;
		removeNotification(counter);
	}
	
	private void createNotification(String title, String description, int notificationId, int smallIcon){
		Log.d(TAG, "createNotification() creating notification [notificationId:"+notificationId+"]");
		NotificationManager notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
		//CREATE THE INTENT TO START WHEN THE NOTIFICATION IS CLICKED
		Intent intent = new Intent(this, NotificationActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.putExtra(NotificationUtil.NOTIFICATION_ID, notificationId);

		//CREATE THE PENDING INTENT
		PendingIntent pIntent = PendingIntent.getActivity(this, counter, intent, PendingIntent.FLAG_UPDATE_CURRENT);

		// Build notification
		// Actions are just fake
		NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
		        .setContentTitle(title)
		        .setContentText(description)
		        .setSmallIcon(smallIcon)
		        .setContentIntent(pIntent);
		Notification notification = notificationBuilder.build();
		// Hide the notification after its selected
		//noti.flags |= Notification.FLAG_AUTO_CANCEL;
		notification.flags |=Notification.DEFAULT_VIBRATE;
		notification.flags |=Notification.DEFAULT_SOUND;
		notification.flags |=Notification.DEFAULT_LIGHTS;
		notification.flags |=Notification.FLAG_AUTO_CANCEL;
		notificationManager.notify(notificationId, notification);
	}
	
	public void removeNotification(int notificationId){
		 NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);  
		 manager.cancel(notificationId);
	}
	
	/**
	 * This checks if the screen is on
	 * @return boolean isScreenOn
	 */
	private boolean isScreenOn(){
		boolean retVal = false;
		retVal = ((PowerManager) getSystemService(Context.POWER_SERVICE)).isScreenOn();
		return retVal;
	}
	
	/**
	 * 
	 * @return boolean TRUE if device is locked (also if screen is on)
	 */
	private boolean isKeyguardLocked(){
		boolean retVal = false;
		KeyguardManager kgMgr = (KeyguardManager) getSystemService(Context.KEYGUARD_SERVICE);
		retVal = kgMgr.inKeyguardRestrictedInputMode();
		return retVal;
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
