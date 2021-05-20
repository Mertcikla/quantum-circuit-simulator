package com.example.thirdo;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

/* Creates a scrollable listview item to display the long
 * list of results.
 *  
 */
public class ResultDialogFragment extends DialogFragment {
	/* @param results the string to be displayed.
	 * @param android.R.layout.simple_list_item_1 resource parameters to be used as template 
	 * @param android.R.id.text1 textview object to be used as parameter reference
	 * @param R.id.listView1 id of the empty listview item
	 * @param namesAA array adapter that acts like a buffer between the data and layout
	 */
	ArrayList<String> results;	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {


		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		LayoutInflater inflater = getActivity().getLayoutInflater();
		LinearLayout resultLayout = (LinearLayout)inflater.inflate(R.layout.result, null);
		ListView myLView =(ListView) resultLayout.findViewById(R.id.listView1);
		ArrayAdapter<String> namesAA = new ArrayAdapter<String> ( getActivity(), android.R.layout.simple_list_item_1, android.R.id.text1, results);

		myLView.setAdapter(namesAA);			
		builder.setView(resultLayout);

		// Create the AlertDialog object and return it
		return builder.create();
	}
}
