package com.example.thirdo;

import android.content.Context;
import android.widget.Toast;
public class ShowToast {
	Toast toast;
	ShowToast(CharSequence text,Context context){
		int duration = Toast.LENGTH_SHORT;
		toast = Toast.makeText(context, text, duration);		
	}
	public void show(){
		toast.show();
	}
}