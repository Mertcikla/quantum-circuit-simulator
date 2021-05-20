package com.example.thirdo;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

public class GateContainer extends LinearLayout {

	int contId;

	static QGate tempGate;
	static QGate tempGate2;
	QGate theWire;
	static GateContainer tempCont;
	static GateContainer tempCont2;
	static int tempIndex;
	static int tempIndex2;
	QGate[] qGate;
	Context myContext;

	public GateContainer(Context context, AttributeSet attrs) {
		super(context, attrs);
		theWire = new QGate(getContext());
		qGate = new QGate[20];
		for (int i = 0; i < 20; i++) {
			qGate[i] = new QGate(getContext());
		}

	}

	public void addGate(int gateIndex, Object gateToAdd, Context context) {
		if (((QGate) gateToAdd).getGateType() == 'X')
			tempGate = new PauliX(context);
		else if (((QGate) gateToAdd).getGateType() == 'Y')
			tempGate = new PauliY(context);
		else if (((QGate) gateToAdd).getGateType() == 'Z')
			tempGate = new PauliZ(context);
		else if (((QGate) gateToAdd).getGateType() == 'H')
			tempGate = new Hadamard(context);
		else if (((QGate) gateToAdd).getGateType() == 'S') {
			tempGate = new Swap(context);
			tempGate2 = ((Swap) tempGate).parts[0];
			tempGate2.setIndex(gateIndex);
			(MainActivity.gateCont[this.contId + 1]).removeViewAt(gateIndex);
			(MainActivity.gateCont[this.contId + 1]).addView(tempGate2,
					gateIndex, MainActivity.params);
			(MainActivity.gateCont[this.contId + 1]).qGate[gateIndex] = tempGate2;
			

			tempGate2.setOnTouchListener(new OnTouchListener() {
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					if (event.getAction() == MotionEvent.ACTION_DOWN) {
						DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(
								v);
						v.startDrag(null, shadowBuilder, v, 0);
						return true;
					} else {
						return false;
					}
				}
			});

		} else if (((QGate) gateToAdd).getGateType() == 'C') {
			tempGate = new Cnot(context);
			tempGate2 = ((Cnot) tempGate).parts[0];
			tempGate2.setIndex(gateIndex);
			(MainActivity.gateCont[this.contId + 1]).removeViewAt(gateIndex);
			(MainActivity.gateCont[this.contId + 1]).addView(tempGate2,
					gateIndex, MainActivity.params);
			(MainActivity.gateCont[this.contId + 1]).qGate[gateIndex] = tempGate2;

			tempGate2.setOnTouchListener(new OnTouchListener() {
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					if (event.getAction() == MotionEvent.ACTION_DOWN) {
						DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(
								v);
						v.startDrag(null, shadowBuilder, v, 0);
						return true;
					} else {
						return false;
					}
				}
			});

		}
		tempGate.setIndex(gateIndex);
		addView(tempGate, gateIndex, MainActivity.params);
		qGate[gateIndex] = tempGate;
		

		tempGate.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(
							v);
					v.startDrag(null, shadowBuilder, v, 0);
					return true;
				} else {
					return false;
				}
			}
		});

	}

	public static boolean swapGates(QGate gate1, QGate gate2, Context context) {
		if (gate1.getGateType() == 'S' || gate1.getGateType() == 'C'
				|| gate1.getGateType() == 's' || gate1.getGateType() == 'c'
				|| gate2.getGateType() == 'S' || gate2.getGateType() == 'C'
				|| gate2.getGateType() == 's' || gate2.getGateType() == 'c') {
			ShowToast immovableGateToast = new ShowToast(
					"Can't replace this gate, try removing and adding it to desired place",
					context);
			immovableGateToast.show();
			return false;
		}

		tempGate = new QGate(context);

		tempCont = (GateContainer) gate1.getParent();
		tempCont2 = (GateContainer) gate2.getParent();

		tempIndex = gate1.getIndex();
		tempIndex2 = gate2.getIndex();

		tempCont.removeView(gate1);
		tempCont2.removeView(gate2);

		tempCont2.addView(gate1, tempIndex2, MainActivity.params);
		tempCont.addView(gate2, tempIndex, MainActivity.params);
		gate1.setIndex(tempIndex2);
		gate2.setIndex(tempIndex);
		tempCont.qGate[tempIndex] = gate2;
		tempCont2.qGate[tempIndex2] = gate1;
		return true;

	}

	public void removeGate(int gateIndex) {
		removeViewAt(gateIndex);
		tempGate = new QGate(getContext());
		tempGate.setIndex(gateIndex);
		tempGate.setGateType(' ');
		qGate[gateIndex] = tempGate;
	}

	public void removeGate(QGate gate) {
		removeViewAt(gate.index);
		tempGate = new QGate(getContext());
		tempGate.setIndex(gate.index);
		tempGate.setGateType(' ');
		qGate[gate.index] = tempGate;
	}

	/**
	 * @return the contId
	 */
	public int getContId() {
		return contId;
	}

	/**
	 * @param contId
	 *            the contId to set
	 */
	public void setContId(int contId) {
		this.contId = contId;
	}

}
