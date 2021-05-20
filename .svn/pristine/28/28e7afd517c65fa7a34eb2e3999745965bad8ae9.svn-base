package com.example.thirdo;

import java.text.DecimalFormat;
import java.util.ArrayList;

import org.apache.commons.math3.complex.Complex;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.DragShadowBuilder;
import android.view.View.OnDragListener;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.Spinner;

public class MainActivity extends Activity implements OnTouchListener,
		OnDragListener {

	final Context context = this;
	static int activeQbits = 1;
	static QuBit[] qBitVect;
	GateContainer topCont;
	char[][] gates = new char[6][20];
	int targetId = 0;
	static GateContainer[] gateCont = new GateContainer[6];
	View[] qBut = new View[6];
	static LayoutParams params = new LayoutParams(86, 86);

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initGateCont();
		initButtons();
		topCont = new GateContainer(getBaseContext(), null);
		topCont = (GateContainer) findViewById(R.id.topCont);
		topCont.setContId(9);
		topCont.setOnDragListener(this);
		topCont.addView(new PauliX(context), 0, params);
		topCont.addView(new PauliY(context), 1, params);
		topCont.addView(new PauliZ(context), 2, params);
		topCont.addView(new Hadamard(context), 3, params);
		topCont.addView(new Cnot(getBaseContext()), 4, params);
		topCont.addView(new Swap(getBaseContext()), 5, params);

		topCont.getChildAt(0).setOnTouchListener(this);
		topCont.getChildAt(1).setOnTouchListener(this);
		topCont.getChildAt(2).setOnTouchListener(this);
		topCont.getChildAt(3).setOnTouchListener(this);
		topCont.getChildAt(4).setOnTouchListener(this);
		topCont.getChildAt(5).setOnTouchListener(this);

		Spinner spinner = (Spinner) findViewById(R.id.spinner1);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				this, R.array.qubits, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);
		spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view,
					int pos, long id) {
				activeQbits = pos + 1;
				for (int c = 1; c <= pos; c++) {
					gateCont[c].setVisibility(View.VISIBLE);
					qBut[c].setVisibility(View.VISIBLE);
				}
				for (int x = 5; x > pos; x--) {
					gateCont[x].setVisibility(View.INVISIBLE);
					qBut[x].setVisibility(View.INVISIBLE);
				}
			}

			public void onNothingSelected(AdapterView<?> parent) {
			}
		});
	}

	public void compile(View v) {
		ImageView compiled = new ImageView(context); // Add Compiled Gate Icon
		compiled.setImageResource(R.drawable.c); //
		LinearLayout compiledLayout = (LinearLayout) findViewById(R.id.compiledGates); //
		compiledLayout.addView(compiled, 0, params); //
		ShowToast compiledToast = new ShowToast("Compiled",context);
		compiledToast.show();
		GateContainer testCont = new GateContainer(context, null);
		testCont = (GateContainer) findViewById(R.id.bottom_container);
		testCont.setContId(11);
		testCont.addView(new PauliX(getBaseContext()), 0, params);

	}

	public void evaluate(View v) {
		ArrayList<String> results = new ArrayList<String>();
		results.clear();
		QuBit.initGates();
		QuBit[] qBitVect = new QuBit[activeQbits];
		for (int i = 0; i < activeQbits; i++) {
			qBitVect[i] = new QuBit(new Complex(1, 0), new Complex(0, 0));
		}
		for (int i = 0; i < activeQbits; i++) {
			if (qBut[i].getTag() == "qubit0") {
				qBitVect[i] = new QuBit(new Complex(1, 0), new Complex(0, 0));
			} else if (qBut[i].getTag() == "qubit1") {
				qBitVect[i] = new QuBit(new Complex(0, 0), new Complex(1, 0));
			}
		}
		for (int j = 0; j < 20; j++) {
			for (int i = 0; i < activeQbits; i++) {
				switch ((gateCont[i].qGate[j]).getGateType()) {
				case ' ':
					break;
				case 'X':
					qBitVect[i] = qBitVect[i].pauliX();
					break;
				case 'Z':
					qBitVect[i] = qBitVect[i].pauliZ();
					break;
				case 'Y':
					qBitVect[i] = qBitVect[i].pauliY();
					break;
				case 'C':
					qBitVect[i] = qBitVect[i].cNot(qBitVect[i + 1]);
					i++;
					break;
				case 'H':
					qBitVect[i] = qBitVect[i].hadamard();
					break;
				case 'S':
					qBitVect[i] = qBitVect[i].swap(qBitVect[i + 1]);
					i++;
					break;
				}
			}
		}
		String resultStr = "0";
		double real;
		double img;
		double c;
		QuBit.finalResult(qBitVect, activeQbits);

		for (int i = 0; i < (int) Math.floor(Math.pow(2, activeQbits)); i++) {
			resultStr = "";
			real = QuBit.measuredStates[i].getReal();
			img = QuBit.measuredStates[i].getImaginary();
			c = ((real * real) + (img * img)) * 100;

			for (int k = activeQbits; k > 0; k--)
				resultStr += String.valueOf((i / (int) Math.floor(Math.pow(2,
						k - 1))) % 2);

			results.add(i, "        (" + roundTwoDecimals(real) + " + "
					+ roundTwoDecimals(img) + "i" + ")   *   " + "|"
					+ resultStr + ">" + "            " + roundTwoDecimals(c)
					+ "% ");
		}
		printGates();

		ResultDialogFragment myDialog = new ResultDialogFragment();
		myDialog.results = results;
		myDialog.show(getFragmentManager(), "ResultDialogFragment");
	}

	@Override
	public boolean onTouch(View v, MotionEvent e) {
		if (e.getAction() == MotionEvent.ACTION_DOWN) {
			DragShadowBuilder shadowBuilder = null;
			if (((QGate) v).getGateType() == 'C') {

				shadowBuilder = new GateDragShadowBuilder(context,
						R.drawable.cnot);
			} else if (((QGate) v).getGateType() == 'S') {
				shadowBuilder = new GateDragShadowBuilder(context,
						R.drawable.swap);
			} else {
				shadowBuilder = new View.DragShadowBuilder(v);
			}
			v.startDrag(null, shadowBuilder, v, 0);

			return true;
		} else {
			return false;
		}
	}

	public void buttonClick(View myBut) {
		if (myBut.getTag() == "qubit0") {
			((ImageView) myBut).setImageResource(R.drawable.q1);
			myBut.setTag("qubit1");
		} else if (myBut.getTag() == "qubit1") {
			((ImageView) myBut).setImageResource(R.drawable.q0);
			myBut.setTag("qubit0");
		}
	}

	@Override
	public boolean onDrag(View targetView, DragEvent e) {
		Object gateDragged = e.getLocalState();
		GateContainer sourceCont = ((GateContainer) ((QGate) gateDragged)
				.getParent());
		GateContainer targetCont = ((GateContainer) targetView);
		if (e.getAction() == DragEvent.ACTION_DROP) {
			int gateIndex = (int) (e.getX() / 86);
			if (sourceCont.getContId() == 9) { // source = top
				if (targetCont.getContId() != 9) { // source = top & target =
													// container
					targetCont.removeGate(gateIndex);
					targetCont.addGate(gateIndex, gateDragged, context);
				} else {
					return true; // source = top & target = top
				}
			} else if (sourceCont.getContId() < 7) { // source = container
				if (targetCont.getContId() != 9) { // source = container &
													// target = container
					GateContainer
							.swapGates(
									((QGate) gateDragged),
									(QGate) (gateCont[targetCont.getContId()]).getChildAt(gateIndex),
									context);
				}
				
			}
		}
		return true; // Drag Successful
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		// getMenuInflater().inflate(R.menu.main, menu);
		// ActionBar actionBar = getActionBar();
		// actionBar.hide();
		return true;
	}

	public void printGates() {
		for (int d = 0; d < 6; d++) {
			for (int f = 0; f < 18; f++) {
				System.out.print(gates[d][f]);
			}
			System.out.println();
		}
	}

	public double roundTwoDecimals(double d) {
		DecimalFormat twoDForm = new DecimalFormat("#.##");
		if (Double.valueOf(twoDForm.format(d)) == 0.49) {
			return 0.05;
		} else
			return Double.valueOf(twoDForm.format(d));
	}

	public void initGateCont() {

		Drawable lineBg = getResources().getDrawable(R.drawable.line);
		QGate[] bg = new QGate[20];

		for (int d = 0; d < 6; d++) {
			gateCont[d] = new GateContainer(getBaseContext(), null);
			gateCont[d] = (GateContainer) findViewById(R.id.bottom_container
					+ d);
			gateCont[d].setContId(d);
			gateCont[d].setBackground(lineBg);
			gateCont[d].setOnDragListener(this);

			for (int f = 0; f < 20; f++) {
				bg[f] = new QGate(getBaseContext());
				bg[f].setImageResource(R.drawable.tp);
				bg[f].setIndex(f);
				gateCont[d].addView(bg[f], f, params);
				gates[d][f] = ' ';
			}
		}
	}

	public void initButtons() {
		qBut[0] = findViewById(R.id.qubitButton1);
		qBut[1] = findViewById(R.id.qubitButton2);
		qBut[2] = findViewById(R.id.qubitButton3);
		qBut[3] = findViewById(R.id.qubitButton4);
		qBut[4] = findViewById(R.id.qubitButton5);
		qBut[5] = findViewById(R.id.qubitButton6);
		for (int i = 0; i < 6; i++) {
			qBut[i].setTag("qubit0");
		}
	}

	
}
