package uf.seniorproj.alexryan;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class FoilActivity extends Activity {
	
	EditText box1; //x^2
	EditText box2; //x
	EditText box3; //x
	EditText box4; //constant
	
	TextView correctText; //correct or try again
	LinearLayout focusStealer; //to remove focus from editText
	
	int a, b, c, d; //the coefficients for the problem

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_foil);
		
		

		
		//Problem information
		//Will likely be stored in array later
		String problemData1 = "(x + 3)(x - 2)";
		a = 1;
		b = 3;
		c = 1;
		d = -2;
		
		//initialize
		//Set the problem at the top
		TextView problem = (TextView)findViewById(R.id.foilProblem);
		problem.setText(problemData1);
		
		box1 = (EditText)findViewById(R.id.editText1);
		box2 = (EditText)findViewById(R.id.editText2);
		box3 = (EditText)findViewById(R.id.editText3);
		box4 = (EditText)findViewById(R.id.editText4);
		
		correctText = (TextView) findViewById(R.id.foilCorrect);
		focusStealer = (LinearLayout)findViewById(R.id.focusStealer);
		
		
		
		
		//testing arrows and stuff
		//this is all garbage
		int problemWidth = problem.getWidth();
		RelativeLayout arrowLayout = (RelativeLayout)findViewById(R.id.arrowLayout);
		Display display = getWindowManager().getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);
		int screenWidth = size.x;
		int center = screenWidth/2;
		RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) arrowLayout.getLayoutParams();
		int leftMargin = lp.leftMargin;
		center = center - leftMargin;
		//View arrowView = (View)(new DrawArrow(this, center ));
		//arrowLayout.addView(arrowView);
		//end test
		
		
		
		//on focus listeners
		//no longer needed if using check answer button
		
		//F in FOIL, x^2 term
		/*
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
	    */
		
		
		
	}
	
	public void checkAnswer(View v) {
		focusStealer.requestFocus();//remove focus
		String answer1 = box1.getText().toString();
		String answer2 = box2.getText().toString();
		String answer3 = box3.getText().toString();
		String answer4 = box4.getText().toString();
		
		//a box is empty
		if (answer1.equals("") ||
			answer2.equals("") ||
			answer3.equals("") ||
			answer4.equals("")) {
			Toast.makeText(this, "Please finish entering your answer.", Toast.LENGTH_SHORT).show();
			return;
		}
		
		int ans1, ans2, ans3, ans4;
		//check for non-int answers
		try {
			ans1 = Integer.parseInt(answer1);
			ans2 = Integer.parseInt(answer2);
			ans3 = Integer.parseInt(answer3);
			ans4 = Integer.parseInt(answer4);
		}catch(Exception e) {
			Toast.makeText(this, "Please only enter integer answers.", Toast.LENGTH_SHORT).show();
			return;
		}
		
		//all answers correct
		if (ans1 == a*c &&
			ans2 == a*d &&
			ans3 == b*c &&
			ans4 == b*d) {
			correctText.setText("Correct! Well done.");
		}
		else
			correctText.setText("Oops. Try again!");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.foil, menu);
		return true;
	}

}
