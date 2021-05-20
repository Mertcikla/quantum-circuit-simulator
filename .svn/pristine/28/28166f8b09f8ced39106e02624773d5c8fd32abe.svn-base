package com.example.thirdo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.view.View;

public class GateDragShadowBuilder extends View.DragShadowBuilder {
	private Drawable shadow;

	GateDragShadowBuilder(Context context, int drawableId) {
		super();

		this.shadow = context.getResources().getDrawable(drawableId);
		if (this.shadow == null) {
			throw new NullPointerException("Drawable from id is null");
		}

		this.shadow.setBounds(0, 0, 150, 300);

	}

	@Override
	public void onDrawShadow(Canvas canvas) {
		canvas.scale((float) 0.5, (float) 0.5);
		shadow.draw(canvas);
	}

	@Override
	public void onProvideShadowMetrics(Point shadowSize, Point shadowTouchPoint) {
		shadowSize.x = shadow.getMinimumWidth();
		shadowSize.y = shadow.getMinimumHeight();

		shadowTouchPoint.x = (int) (shadowSize.x / 4) + 50;
		shadowTouchPoint.y = (int) (shadowSize.y / 6) + 50;
	}
}