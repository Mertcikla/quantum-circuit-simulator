package com.example.thirdo;

import org.apache.commons.math3.complex.Complex;

public class QuBit{

	Complex[] vect;
	Complex[] result;
	Complex[] result4;
	Complex[][] gate;
	
	static Complex[] measuredStates;

	static final Complex[][] PauliX = new Complex[2][2];	
	static final Complex[][] PauliZ = new Complex[2][2];
	static final Complex[][] PauliY = new Complex[2][2];
	static final Complex[][] Hadamard = new Complex[2][2];	
	static final Complex[][] CNot = new Complex[4][4];
	static final Complex[][] Swap = new Complex[4][4];

	public static void finalResult(QuBit[] qubits,int activeGates){
		int j=(int)Math.floor(Math.pow(2, activeGates));
		measuredStates = new Complex[j];
		
		for(int i = 0; i < j; i++){
		 switch (activeGates){		
		 
		 case 1:
			 measuredStates[i] = 		    qubits[0].vect[i % 2];
			 break;
		 case 2:
			 measuredStates[i] = multiply(  qubits[0].vect[(i / 2) % 2], 
				    						qubits[1].vect[i % 2]);
			 break;
		 case 3:
			 measuredStates[i] = multiply(  qubits[0].vect[(i / 4) % 2], 
									    	qubits[1].vect[(i / 2) % 2], 
											qubits[2].vect[i % 2]);
			 break;	 
		 case 4:
			 measuredStates[i] = multiply(  qubits[0].vect[(i / 8) % 2], 
									    	qubits[1].vect[(i / 4) % 2], 
											qubits[2].vect[(i / 2) % 2], 
											qubits[3].vect[ i  % 2]);
			 break;
		 case 5:
			 measuredStates[i] = multiply(  qubits[0].vect[(i / 16) % 2], 
									    	qubits[1].vect[(i / 8) % 2], 
											qubits[2].vect[(i / 4) % 2], 
											qubits[3].vect[(i / 2) % 2], 
											qubits[4].vect[ i  % 2]);
			 break;
		 case 6:
		     measuredStates[i] = multiply(  qubits[0].vect[(i / 32) % 2], 
									    	qubits[1].vect[(i / 16) % 2], 
											qubits[2].vect[(i / 8) % 2], 
											qubits[3].vect[(i / 4) % 2], 
											qubits[4].vect[(i / 2) % 2], 
											qubits[5].vect[i % 2]);
		     break;		 
		 	}	
		}
	}
	public static Complex multiply(Complex a,Complex b,Complex c,Complex d,Complex e,Complex f){
		return ((((a.multiply(b)).multiply(c)).multiply(d)).multiply(e)).multiply(f);
	}
	public static Complex multiply(Complex a,Complex b,Complex c,Complex d,Complex e){
		return (((a.multiply(b)).multiply(c)).multiply(d)).multiply(e);
	}
	public static Complex multiply(Complex a,Complex b,Complex c,Complex d){
		return (((a.multiply(b)).multiply(c)).multiply(d));
	}
	public static Complex multiply(Complex a,Complex b,Complex c){
		return ((a.multiply(b)).multiply(c));
	}
	public static Complex multiply(Complex a,Complex b){
		return (a.multiply(b));
	}
	public static void initGates (){
		PauliX[0][0] = new Complex(0,0);			//  	Pauli X  		quantum equivalent of a NOT gate
		PauliX[0][1] = new Complex(1,0);			//      | 0  1 |
		PauliX[1][0] = new Complex(1,0);			//      | 1  0 |
		PauliX[1][1] = new Complex(0,0);	
		
		PauliZ[0][0] = new Complex(1,0);			//      Pauli Z 	 rotation around the Z-axis of the Bloch Sphere by pi radians
		PauliZ[0][1] = new Complex(0,0);			//      | 1  0 |
		PauliZ[1][0] = new Complex(0,0);			//      | 0 -1 |
		PauliZ[1][1] = new Complex(-1,0);

		PauliY[0][0] = new Complex(0,0);			//      Pauli Y    	 rotation around the Y-axis of the Bloch Sphere by pi radians
		PauliY[0][1] = new Complex(0,-1);			//      | 0 -i |
		PauliY[1][0] = new Complex(0,1);			//      | i  0 |
		PauliY[1][1] = new Complex(0,0);
		
		Hadamard[0][0] = new Complex(1/Math.sqrt(2),0);			//        
		Hadamard[0][1] = new Complex(1/Math.sqrt(2),0);			//		  | 1/sqrt(2)  1/sqrt(2) |
		Hadamard[1][0] = new Complex(1/Math.sqrt(2),0);			//		  | 1/sqrt(2) -1/sqrt(2) |
		Hadamard[1][1] = new Complex((-1)/Math.sqrt(2),0);		//		 	
		
		CNot[0][0] = new Complex(1,0);		/* ---------------------------------- */
		CNot[0][1] = new Complex(0,0);
		CNot[0][2] = new Complex(0,0);
		CNot[0][3] = new Complex(0,0);		
		CNot[1][0] = new Complex(0,0);		
		CNot[1][1] = new Complex(1,0);		//		    Controlled NOT
		CNot[1][2] = new Complex(0,0);		//			 | 1 0 0 0 |
		CNot[1][3] = new Complex(0,0);		//			 | 0 1 0 0 |
		CNot[2][0] = new Complex(0,0);		//			 | 0 0 0 1 |
		CNot[2][1] = new Complex(0,0);		//			 | 0 0 1 0 |	
		CNot[2][2] = new Complex(0,0);	
		CNot[2][3] = new Complex(1,0);		
		CNot[3][0] = new Complex(0,0);
		CNot[3][1] = new Complex(0,0);
		CNot[3][2] = new Complex(1,0);
		CNot[3][3] = new Complex(0,0); 		/* ---------------------------------- */
		
		Swap[0][0] = new Complex(1,0);		/* ---------------------------------- */
		Swap[0][1] = new Complex(0,0);
		Swap[0][2] = new Complex(0,0);
		Swap[0][3] = new Complex(0,0);		
		Swap[1][0] = new Complex(0,0);		
		Swap[1][1] = new Complex(0,0);		//		    	Swap
		Swap[1][2] = new Complex(1,0);		//			 | 1 0 0 0 |
		Swap[1][3] = new Complex(0,0);		//			 | 0 0 1 0 |
		Swap[2][0] = new Complex(0,0);		//			 | 0 1 0 0 |
		Swap[2][1] = new Complex(1,0);		//			 | 0 0 0 1 |	
		Swap[2][2] = new Complex(0,0);
		Swap[2][3] = new Complex(0,0);		
		Swap[3][0] = new Complex(0,0);
		Swap[3][1] = new Complex(0,0);
		Swap[3][2] = new Complex(0,0);
		Swap[3][3] = new Complex(1,0); 		/* ---------------------------------- */		
	}

	public QuBit(Complex a0,Complex a1) {
		vect = new Complex[2];
		result = new Complex[2];
		result4 = new Complex[4];		
		
		vect[0]= a0;
		vect[1]= a1;		
	}

	public Complex[] evaluate(char g) {
			
		switch(g) {	
		case ' ':
			 break;
	    case 'X':
			gate = PauliX;
			break;
	    case 'Z':
			gate = PauliZ;
	        break;
	    case 'Y':
			gate = PauliY;
	        break;
	    case 'H':
			gate = Hadamard;
	        break;
		}
	    	result[0]=((vect[0].multiply(gate[0][0])).add(vect[1].multiply(gate[0][1])));
			result[1]=((vect[0].multiply(gate[1][0])).add(vect[1].multiply(gate[1][1])));

		return result;		
	}
	
	public String getVectAsString() {		
		return (String.valueOf((double)Math.round(vect[0].getReal() * 100) / 100)+"|0>"+"  "+String.valueOf((double)Math.round(vect[1].getReal() * 100) / 100)+"|1>");
	}
	// Gate Functions Start
	public QuBit pauliX(){
		gate = PauliX;
		result[0]=((vect[0].multiply(gate[0][0])).add(vect[1].multiply(gate[0][1])));
		result[1]=((vect[0].multiply(gate[1][0])).add(vect[1].multiply(gate[1][1])));
		vect=result;
		return this;
	}
	public QuBit pauliY(){
		gate = PauliY;
		result[0]=((vect[0].multiply(gate[0][0])).add(vect[1].multiply(gate[0][1])));
		result[1]=((vect[0].multiply(gate[1][0])).add(vect[1].multiply(gate[1][1])));
		vect=result;
		return this;
	}
	public QuBit pauliZ(){
		gate = PauliZ;
		result[0]=((vect[0].multiply(gate[0][0])).add(vect[1].multiply(gate[0][1])));
		result[1]=((vect[0].multiply(gate[1][0])).add(vect[1].multiply(gate[1][1])));
		vect=result;
		return this;
	}
	public QuBit hadamard(){
		gate = Hadamard;
		result[0]=((vect[0].multiply(gate[0][0])).add(vect[1].multiply(gate[0][1])));
		result[1]=((vect[0].multiply(gate[1][0])).add(vect[1].multiply(gate[1][1])));
		vect=result;
		return this;
	}
	public QuBit cNot(QuBit second){
		gate = CNot;
		Complex a = new Complex(0,0);
		Complex b = new Complex(0,0);
		Complex c = new Complex(0,0);
		Complex d = new Complex(0,0);
		
		result4[0]=(this.vect[0]).multiply(second.vect[0]);
		result4[1]=(this.vect[0]).multiply(second.vect[1]);
		result4[2]=(this.vect[1]).multiply(second.vect[0]);
		result4[3]=(this.vect[1]).multiply(second.vect[1]);

		for(int i=0;i<4;i++){
			a=result4[i].multiply(gate[i][0]);
			b=result4[i].multiply(gate[i][1]);
			c=result4[i].multiply(gate[i][2]);
			d=result4[i].multiply(gate[i][3]);
			result4[i]=((a.add(b)).add(c)).add(d);
		}	
		vect[0]=result4[0].add(result4[1]);
		vect[1]=result4[2].add(result4[3]);
		second.vect[0]=result4[0].add(result4[2]);
		second.vect[1]=result4[1].add(result4[3]);
		return this;
	}
	
	public QuBit swap(QuBit second){
		gate = Swap;
		Complex a = new Complex(0,0);
		Complex b = new Complex(0,0);
		Complex c = new Complex(0,0);
		Complex d = new Complex(0,0);
		
		result4[0]=(this.vect[0]).multiply(second.vect[0]);
		result4[1]=(this.vect[0]).multiply(second.vect[1]);
		result4[2]=(this.vect[1]).multiply(second.vect[0]);
		result4[3]=(this.vect[1]).multiply(second.vect[1]);

		for(int i=0;i<4;i++){
			a=result4[i].multiply(gate[i][0]);
			b=result4[i].multiply(gate[i][1]);
			c=result4[i].multiply(gate[i][2]);
			d=result4[i].multiply(gate[i][3]);
			result4[i]=((a.add(b)).add(c)).add(d);
		}
		vect[0]=result4[0].add(result4[1]);
		vect[1]=result4[2].add(result4[3]);
		second.vect[0]=result4[0].add(result4[2]);
		second.vect[1]=result4[1].add(result4[3]);
		return this;
	}
	// Gate Functions End
	
}
