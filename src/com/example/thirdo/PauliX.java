package com.example.thirdo;

import android.content.Context;

public class PauliX extends QGate {

	public PauliX(Context context) {
		super(context);
		this.setImageResource(R.drawable.x);
		this.setGateType('X');
	}

}
