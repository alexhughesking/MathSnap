package uf.seniorproj.alexryan;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class FoilActivity extends Activity {
	
	EditText box1; //x^2
	EditText box2; //x
	EditText box3; //x
	EditText box4; //constant
	
	TextView problem; //the problem at the top
	TextView correctText; //correct or try again
	LinearLayout focusStealer; //to remove focus from editText
	
	TextView answer1, answer2, answer3; //the faded in answer
	
	int a, b, c, d; //the coefficients for the problem
	int[] aData = {1, 1, 1, 1, 1, 2, 2, 1, 2, 3};
	int[] bData = {-1, 2, -2, 4, 1, 1, -1, -5, 7, -1};
	int[] cData = {1, 1, 1, 1, 1, 1, 2, 2, 1, 2};
	int[] dData = {2, 2, -3, -2, -1, 2, -3, 3, 4, -2};
	public int probNum;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_foil);
		
		Bundle extras = getIntent().getExtras();
		probNum = extras.getInt("probNum");//get the problem number that was clicked
		
		

		
		//Problem information
		//Will likely be stored in array later
		a = aData[probNum];
		b = bData[probNum];
		c = cData[probNum];
		d = dData[probNum];
		
		//initialize
		//Set the problem at the top
		problem = (TextView)findViewById(R.id.foilProblem);
		setProblemText(0);//set the text at the top
		
		box1 = (EditText)findViewById(R.id.foilEditText1);
		box2 = (EditText)findViewById(R.id.foilEditText2);
		box3 = (EditText)findViewById(R.id.foilEditText3);
		box4 = (EditText)findViewById(R.id.foilEditText4);
		
		correctText = (TextView) findViewById(R.id.foilCorrect);
		focusStealer = (LinearLayout)findViewById(R.id.foilFocusStealer);
		answer1 = (TextView) findViewById(R.id.foilDispAnswer1);
		answer2 = (TextView) findViewById(R.id.foilDispAnswer2);
		answer3 = (TextView) findViewById(R.id.foilDispAnswer3);
		setAnswerText();//setProblemText(0) first!
		
		TextView xSquared = (TextView)findViewById(R.id.foilTextView1);
		xSquared.setText(Html.fromHtml("x<sup><small>2</small></sup> + "));
		
		//set focus listeners for changing text color
		box1.setOnFocusChangeListener(new OnFocusChangeListener() {
			
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if (hasFocus) {
					setProblemText(1);
				}
				
			}
		});
		
		box2.setOnFocusChangeListener(new OnFocusChangeListener() {
			
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if (hasFocus) {
					setProblemText(2);
				}
				
			}
		});
		
		box3.setOnFocusChangeListener(new OnFocusChangeListener() {
			
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if (hasFocus) {
					setProblemText(3);
				}
				
			}
		});
		
		box4.setOnFocusChangeListener(new OnFocusChangeListener() {
			
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if (hasFocus) {
					setProblemText(4);
				}
				
			}
		});
		
		
	}
	
	public void setAnswerText() {
		answer1.setText(problem.getText().toString());
		String text2 = " = ";
		if (a * c == 1)
			text2 = text2 + "x<sup><small>2</small></sup> ";
		else
			text2 = text2 + (a*c) + "x<sup><small>2</small></sup> ";
		String text3 = new String(text2);
		if (a * d < 0) {
			if (a*d == -1)
				text2 = text2 + "- " + "x ";
			else
				text2 = text2 + "- " + (-1*a*d) + "x ";
		}
		else {
			if (a*d == 1)
				text2 = text2 + "+ " + "x ";
			else
				text2 = text2 + "+ " + (a*d) + "x ";
		}
		if (b * c < 0) {
			if (b*c == -1)
				text2 = text2 + "- " + "x ";
			else
				text2 = text2 + "- " + (-1*b*c) + "x ";
		}
		else {
			if (b*c == 1)
				text2 = text2 + "+ " + "x ";
			else
				text2 = text2 + "+ "  + (b*c) + "x ";
		}
		if (b * d < 0)
			text2 = text2 + "- " + (-1*b*d);
		else
			text2 = text2 + "+ " + (b*d);
		
		
		if (a*d + b*c < 0) {
			if (a*d + b*c == -1)
				text3 = text3 + "- " + "x ";
			else
				text3 = text3 + "- " + (-1*(a*d + b*c)) + "x ";
		}
		else if (a*d + b*c > 0) {
			if (a*d + b*c == 1)
				text3 = text3 + "+ " + "x ";
			else
				text3 = text3 + "+ " + (a*d + b*c) + "x ";
		}
		if (b*d < 0)
			text3 = text3 + "- " + (-1*b*d);
		else
			text3 = text3 + "+ " + (b*d);
		
		answer2.setText(Html.fromHtml(text2));
		answer3.setText(Html.fromHtml(text3));
	}
	
	public void setProblemText(int boxNo) {
		//boxNo is the editText with focus
		//boxNo == 0 means initialize problem
		String text = "(";
		if (boxNo == 1) {
			text = text + "<font color=\"lime\">";
			if (a == 1)
				text = text + "x ";
			else
				text = text + a + "x ";
			text = text + "</font>";
			if (b < 0)
				text = text + "- " + (-1*b);
			else
				text = text + "+ " + b;
			text = text + ")(";
			text = text + "<font color=\"lime\">";
			if (c == 1)
				text = text + "x ";
			else
				text = text + c + "x ";
			text = text + "</font>";
			if (d < 0)
				text = text + "- " + (-1*d);
			else
				text = text + "+ " + d;
			text = text + ")";
		}
		else if (boxNo == 2) {
			text = text + "<font color=\"lime\">";
			if (a == 1)
				text = text + "x ";
			else
				text = text + a + "x ";
			text = text + "</font>";
			if (b < 0)
				text = text + "- " + (-1*b);
			else
				text = text + "+ " + b;
			text = text + ")(";
			if (c == 1)
				text = text + "x ";
			else
				text = text + c + "x ";
			text = text + "<font color=\"lime\">";
			if (d < 0)
				text = text + "- " + (-1*d);
			else
				text = text + "+ " + d;
			text = text + "</font>";
			text = text + ")";
		}
		else if(boxNo == 3) {
			if (a == 1)
				text = text + "x ";
			else
				text = text + a + "x ";
			text = text + "<font color=\"lime\">";
			if (b < 0)
				text = text + "- " + (-1*b);
			else
				text = text + "+ " + b;
			text = text + "</font>";
			text = text + ")(";
			text = text + "<font color=\"lime\">";
			if (c == 1)
				text = text + "x ";
			else
				text = text + c + "x ";
			text = text + "</font>";
			if (d < 0)
				text = text + "- " + (-1*d);
			else
				text = text + "+ " + d;
			text = text + ")";
		}
		else if (boxNo == 4) {
			if (a == 1)
				text = text + "x ";
			else
				text = text + a + "x ";
			text = text + "<font color=\"lime\">";
			if (b < 0)
				text = text + "- " + (-1*b);
			else
				text = text + "+ " + b;
			text = text + "</font>";
			text = text + ")(";
			if (c == 1)
				text = text + "x ";
			else
				text = text + c + "x ";
			text = text + "<font color=\"lime\">";
			if (d < 0)
				text = text + "- " + (-1*d);
			else
				text = text + "+ " + d;
			text = text + "</font>";
			text = text + ")";
		}
		else {
			if (a == 1)
				text = text + "x ";
			else
				text = text + a + "x ";
			if (b < 0)
				text = text + "- " + (-1*b);
			else
				text = text + "+ " + b;
			text = text + ")(";
			if (c == 1)
				text = text + "x ";
			else
				text = text + c + "x ";
			if (d < 0)
				text = text + "- " + (-1*d);
			else
				text = text + "+ " + d;
			text = text + ")";
			
		}
		problem.setText(Html.fromHtml(text));
	}
	
	public void checkAnswer(View v) {
		focusStealer.requestFocus();//remove focus
		setProblemText(0);//reset original problem
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
			animateAnswer(true);
		}
		else if (ans1 == a*c &&
				 ans2 == b*c &&
				 ans3 == a*d &&
				 ans4 == b*d)
			animateAnswer(true);
		else
			animateAnswer(false);
	}
	
	public void animateAnswer(boolean correct) {
		if (correct) {
			correctText.setText("Correct! Well done.");
			correctText.setVisibility(View.VISIBLE);
			answer1.setVisibility(View.VISIBLE);
			answer2.setVisibility(View.VISIBLE);
			answer3.setVisibility(View.VISIBLE);
		}
		else {
			correctText.setText("Oops. Try again!");
			correctText.setVisibility(View.VISIBLE);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.foil, menu);
		return true;
	}

}
