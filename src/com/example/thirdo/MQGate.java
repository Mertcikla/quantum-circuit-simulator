package com.example.thirdo;

import android.content.Context;
import android.widget.ImageView;

public class MQGate extends QGate{
	int dim;
	QGate[] parts;
	ImageView dragShadow;
	public MQGate(Context context,int d) {
		super(context);
		dim=d-1;
		parts  = new QGate[dim];
		dragShadow = new ImageView(context);
	}
	
	
}
