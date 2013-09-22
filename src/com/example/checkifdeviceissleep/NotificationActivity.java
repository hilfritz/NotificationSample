package com.example.checkifdeviceissleep;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class NotificationActivity extends Activity {
	TextView textView ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
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
		try{
			tag = getIntent().getStringExtra("tag");
			textView.setText(tag);
			textView.setText("tag here:"+tag+"-"+getIntent().getExtras().getInt("tag"));
		}catch(Exception e){
			e.printStackTrace();
			textView.setText("error in tag");
		}
		
	}
	
}
