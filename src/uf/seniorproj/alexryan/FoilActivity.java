package uf.seniorproj.alexryan;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class FoilActivity extends Activity {
	


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_foil);
		
		final Context context = this;//for displaying Toast

		
		//Problem information
		//Will likely be stored in array later
		String problemData1 = "(x + 3)(x - 2)";
		final int a = 1;
		final int b = 3;
		final int c = 1;
		final int d = -2;
		
		//Set the problem at the top
		TextView problem = (TextView)findViewById(R.id.foilProblem);
		problem.setText(problemData1);
		
		//F in FOIL, x^2 term
		final EditText box1 = (EditText)findViewById(R.id.editText1);
		box1.setOnFocusChangeListener(new OnFocusChangeListener() {//to check the answer          

	        public void onFocusChange(View v, boolean hasFocus) {
	            if(!hasFocus) {//if editText has lost focus
	            	String answerString = box1.getText().toString();
	            	if (answerString.equals(""))//box was empty
	            		return;
	            	int answerInt = Integer.parseInt(answerString);
	                if (answerInt == (a*c))
	                	Toast.makeText(context, "Correct", Toast.LENGTH_LONG).show();
	                else
	                	Toast.makeText(context, "Try again", Toast.LENGTH_LONG).show();
	            }
	        }
	    });
		
		final EditText box2 = (EditText)findViewById(R.id.editText2);
		box2.setOnFocusChangeListener(new OnFocusChangeListener() {          

			public void onFocusChange(View v, boolean hasFocus) {
	            if(!hasFocus) {//if editText has lost focus
	            	String answerString = box2.getText().toString();
	            	if (answerString.equals(""))
	            		return;
	            	int answerInt = Integer.parseInt(answerString);
	                if (answerInt == (a*d))
	                	Toast.makeText(context, "Correct", Toast.LENGTH_LONG).show();
	                else
	                	Toast.makeText(context, "Try again", Toast.LENGTH_LONG).show();
	            }
	        }
	    });
		
		final EditText box3 = (EditText)findViewById(R.id.editText3);
		box3.setOnFocusChangeListener(new OnFocusChangeListener() {          

			public void onFocusChange(View v, boolean hasFocus) {
	            if(!hasFocus) {//if editText has lost focus
	            	String answerString = box3.getText().toString();
	            	if (answerString.equals(""))
	            		return;
	            	int answerInt = Integer.parseInt(answerString);
	                if (answerInt == (b*c))
	                	Toast.makeText(context, "Correct", Toast.LENGTH_LONG).show();
	                else
	                	Toast.makeText(context, "Try again", Toast.LENGTH_LONG).show();
	            }
	        }
	    });
		
		final EditText box4 = (EditText)findViewById(R.id.editText4);
		box4.setOnFocusChangeListener(new OnFocusChangeListener() {          

			public void onFocusChange(View v, boolean hasFocus) {
	            if(!hasFocus) {//if editText has lost focus
	            	String answerString = box4.getText().toString();
	            	if (answerString.equals(""))
	            		return;
	            	int answerInt = Integer.parseInt(answerString);
	                if (answerInt == (b*d))
	                	Toast.makeText(context, "Correct", Toast.LENGTH_LONG).show();
	                else
	                	Toast.makeText(context, "Try again", Toast.LENGTH_LONG).show();
	            }
	        }
	    });
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.foil, menu);
		return true;
	}

}
