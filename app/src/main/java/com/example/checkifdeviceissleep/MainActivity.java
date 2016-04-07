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
 * @see https://github.com/hilfritz/NotificationSample/
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
		Log.d(TAG, "onCreate() ");
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
		                         Log.d(TAG, "checkDeviceStatus() "+str);
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
		//CREATE THE INTENT WITH INTENt EXTRAS
		Intent intent = new Intent(this, NotificationActivity.class);
		intent.putExtra(NotificationActivity.EXTRA_KEY1, "today is friday");
		PendingIntent pendingIntent = NotificationUtil.getSamplePendingIntent(this, intent, 99);
		NotificationUtil.createNotification(this, counter, "title:" + counter, "description for counter:" + counter, R.drawable.ic_launcher, pendingIntent);
		counter++;
	}	
	
	public void removeNotificationOnClick(View v){
		counter--;
		NotificationUtil.removeNotification(this, counter);
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
