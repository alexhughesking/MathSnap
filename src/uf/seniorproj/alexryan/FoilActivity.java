package uf.seniorproj.alexryan;

import android.app.Activity;
import android.graphics.Point;
import android.os.Bundle;
import android.text.Html;
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
		
		box1 = (EditText)findViewById(R.id.foilEditText1);
		box2 = (EditText)findViewById(R.id.foilEditText2);
		box3 = (EditText)findViewById(R.id.foilEditText3);
		box4 = (EditText)findViewById(R.id.foilEditText4);
		
		correctText = (TextView) findViewById(R.id.foilCorrect);
		focusStealer = (LinearLayout)findViewById(R.id.foilFocusStealer);
		
		TextView xSquared = (TextView)findViewById(R.id.foilTextView1);
		xSquared.setText(Html.fromHtml("x <sup><small>2</small></sup> + "));
		
		
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
		else if (ans1 == a*c &&
				 ans2 == b*c &&
				 ans3 == a*d &&
				 ans4 == b*d)
			correctText.setText("Correct! Well done.");
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
