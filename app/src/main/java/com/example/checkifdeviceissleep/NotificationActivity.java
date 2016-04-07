package com.example.checkifdeviceissleep;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
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
<<<<<<< HEAD
			String str = "intent extra: [id:<b>"+getIntent().getIntExtra(NotificationUtil.NOTIFICATION_ID, -1)+"</b> key:<b>"+NotificationActivity.EXTRA_KEY1+"</b> value:<b>"+tag+"</b>]";
			textView.setText(Html.fromHtml(str));
		}else{
			String str = "intent extra: [id:<b>"+getIntent().getIntExtra(NotificationUtil.NOTIFICATION_ID, -1)+"</b> key:<b>"+NotificationActivity.EXTRA_KEY1+"</b> value:<b>null/empty</b>]";
			textView.setText(Html.fromHtml(str));
=======
			textView.setText("intent extra: [key:"+NotificationActivity.EXTRA_KEY1+" value:"+tag+"]");
		}else{
			textView.setText("intent extra: [key:"+NotificationActivity.EXTRA_KEY1+" value:null/empty]");
>>>>>>> master
		}
	}
	
}
