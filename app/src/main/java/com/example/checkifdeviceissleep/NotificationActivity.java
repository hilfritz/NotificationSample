package com.example.checkifdeviceissleep;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class NotificationActivity extends Activity {
	public static final String TAG = "NotificationActivity";
	TextView textView ;
	public static final String EXTRA_KEY1 = "extraKey1";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d(TAG, "onCreate()");
		setContentView(R.layout.notification_1);
		Button button = (Button)findViewById(R.id.button1);
		textView = (TextView)findViewById(R.id.texView);
		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				finish();
			}
		});
		String tag = "";
		if (getIntent()!=null &&
				(getIntent().getStringExtra(NotificationActivity.EXTRA_KEY1)!=null
						&& getIntent().getStringExtra(NotificationActivity.EXTRA_KEY1).equalsIgnoreCase("")==false
				)
				){
			tag = getIntent().getStringExtra(NotificationActivity.EXTRA_KEY1);
			textView.setText("intent extra: [id:"+getIntent().getIntExtra(NotificationUtil.NOTIFICATION_ID, -1)+" key:"+NotificationActivity.EXTRA_KEY1+" value:"+tag+"]");
		}else{
			textView.setText("intent extra: [id:"+getIntent().getIntExtra(NotificationUtil.NOTIFICATION_ID, -1)+" key:"+NotificationActivity.EXTRA_KEY1+" value:null/empty]");
		}
	}
}
