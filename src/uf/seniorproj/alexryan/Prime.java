package uf.seniorproj.alexryan;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Prime extends Activity {
	
	TextView probNumView, problem, currNum, timesSymbol, selectedFactor, LHS;
	LinearLayout focusStealer;
	int selectedFactorValue;
	EditText factorBox1, factorBox2;
	Button factorButton;
	LinearLayout factorList;
	OnClickListener clicker;
	TextView status;
	int probNum;
	int[] problemData = {20, 49, 32, 100, 105, 189, 154, 510, 990, 1001};
	int[] primeNumberList = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29,
							31, 37, 41, 43, 47, 53, 59, 61, 67,
							71, 73, 79, 83, 89, 97, 101, 103, 107,
							109, 113, 127, 131};
	SharedPreferences sPref;//used for saving problem completion state
	SharedPreferences.Editor sPrefEdit;
	AlphaAnimation fadeStatus, fadeAnswer;
	Button prevButton, nextButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_prime);
		
		
		sPref = getSharedPreferences("shared_preferences", MODE_PRIVATE);
		sPrefEdit = sPref.edit();
		
		Bundle extras = getIntent().getExtras();
		probNum = extras.getInt("probNum");//get the problem number that was clicked
				
		probNumView = (TextView) findViewById(R.id.primeProbNum);
		problem = (TextView) findViewById(R.id.primeProblem);
		factorList = (LinearLayout) findViewById(R.id.primeLinearLayout);
		currNum = (TextView) findViewById(R.id.primeCurrentNum);
		timesSymbol = (TextView) findViewById(R.id.primeTimes);
		timesSymbol.setText(Html.fromHtml(" &times; "));
		factorBox1 = (EditText) findViewById(R.id.primeFactorBox1);
		factorBox2 = (EditText) findViewById(R.id.primeFactorBox2);
		factorButton = (Button) findViewById(R.id.primeFactorButton);
		//LHS = (TextView) findViewById(R.id.primeLHS);
		focusStealer = (LinearLayout) findViewById(R.id.primeFocusStealer);
		status = (TextView) findViewById(R.id.primeStatus);	
		prevButton = (Button) findViewById(R.id.primePrev);
		nextButton = (Button) findViewById(R.id.primeNext);
		clicker = new OnClickListener() {			
			@Override
			public void onClick(View v) {
				selectedFactor = (TextView) v;
				displayFactorZone();
			}
		};
		fadeStatus = new AlphaAnimation(0, 1.0f);
		fadeStatus.setDuration(2500);
		fadeAnswer = new AlphaAnimation(0, 1.0f);
		fadeAnswer.setDuration(2000);
		
		fadeStatus.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationEnd(Animation animation) {
				status.setVisibility(View.VISIBLE);
				animateAnswer();//fade in the answer String after "Well Done."
				
			}

			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub
				
			}		
		});
		
		fadeAnswer.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationEnd(Animation animation) {
				prevButton.setAlpha(1.0f);
				nextButton.setAlpha(1.0f);
				prevButton.setClickable(true);
				nextButton.setClickable(true);			
			}

			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub
				
			}		
		});
		
		
		initialize();
	}
	
	public void initialize() {
		InputMethodManager imm = (InputMethodManager)getSystemService(
			      Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(factorBox1.getWindowToken(), 0);//hide the keyboard
		problem.setText("Factor the number " + problemData[probNum] + ".");
		probNumView.setText(String.valueOf(probNum + 1) + ")");
		
		if(sPref.getString("prime"+String.valueOf(probNum), "false").equals("true")) {
			//already did it, show the answer
			status.setVisibility(View.VISIBLE);
			String ans = sPref.getString("primeAnswer"+String.valueOf(probNum), String.valueOf(problemData[probNum]));
			factorList.removeAllViews();
			TextView answerView = new TextView(this);
			answerView.setTextSize(problem.getTextSize());
			answerView.setText(Html.fromHtml(ans));
			factorList.addView(answerView);
		}
		else {
			status.setVisibility(View.INVISIBLE);
			factorList.removeAllViews();
			TextView t1 = new TextView(this);
			t1.setText(Html.fromHtml(String.valueOf(problemData[probNum]) + " "));
			t1.setTextSize(problem.getTextSize());	
			factorList.addView(t1);		
			t1.setOnClickListener(clicker);
		}
		currNum.setVisibility(View.GONE);
		factorBox1.setVisibility(View.GONE);
		factorBox2.setVisibility(View.GONE);
		timesSymbol.setVisibility(View.GONE);
		factorButton.setVisibility(View.GONE);
	}
	
	public void displayFactorZone() {
		String[] htmlText = selectedFactor.getText().toString().split(" ");//split out the html
		//there should always be a space just after the integer
		//ex. "10 &times; " or "10 " if it's the last factor on the end
		selectedFactorValue = Integer.parseInt(htmlText[0]);//get just the integer
		currNum.setText(String.valueOf(selectedFactorValue) + " = ");//set the number in the factor zone
		currNum.setVisibility(View.VISIBLE);
		factorBox1.setVisibility(View.VISIBLE);
		factorBox2.setVisibility(View.VISIBLE);
		timesSymbol.setVisibility(View.VISIBLE);
		factorButton.setVisibility(View.VISIBLE);
		factorBox1.setText("");
		factorBox2.setText("");
		if(factorBox1.requestFocus()) {
			InputMethodManager imm = (InputMethodManager)getSystemService(
				      Context.INPUT_METHOD_SERVICE);
			imm.showSoftInput(factorBox1, 0);//show the keyboard
		}
	}
	
	public boolean isPrime(int x) {
		for (int i = 0; i < primeNumberList.length; i++) {
			if (primeNumberList[i] == x)
				return true;
			else if (primeNumberList[i] > x)
				return false;//we already passed the number
		}
		return false;
	}
	
	public void prevProb(View v) {
		if (probNum == 0)
			Toast.makeText(this, "Cannot go back any further.", Toast.LENGTH_SHORT).show();
		else {
			probNum--;
			initialize();
		}
	}
	
	public void nextProb(View v) {
		if (probNum == 9)
			Toast.makeText(this, "No more problems remain.", Toast.LENGTH_SHORT).show();
		else {
			probNum++;
			initialize();
		}
	}
	
	public void factorize(View v) {
		focusStealer.requestFocus();
		InputMethodManager imm = (InputMethodManager)getSystemService(
			      Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(factorBox1.getWindowToken(), 0);//hide the keyboard
		if (factorBox1.getText().toString().equals(""))//empty box
			return;
		if (factorBox2.getText().toString().equals(""))//empty box
			return;
		int factor1 = Integer.parseInt(factorBox1.getText().toString());//should be integer
		int factor2 = Integer.parseInt(factorBox2.getText().toString());//should be integer
		if (factor1 * factor2 != selectedFactorValue) {
			Toast.makeText(this, "These factors don't multiply to " + String.valueOf(selectedFactorValue) + "!", Toast.LENGTH_SHORT).show();
			return;
		}
		else if (factor1 == 1 || factor2 == 1) {
			currNum.setVisibility(View.GONE);
			factorBox1.setVisibility(View.GONE);
			factorBox2.setVisibility(View.GONE);
			timesSymbol.setVisibility(View.GONE);
			factorButton.setVisibility(View.GONE);
			return;
		}
		else {
			currNum.setVisibility(View.GONE);
			factorBox1.setVisibility(View.GONE);
			factorBox2.setVisibility(View.GONE);
			timesSymbol.setVisibility(View.GONE);
			factorButton.setVisibility(View.GONE);
			
			int i = factorList.indexOfChild(selectedFactor);
					
			TextView newFactor1 = new TextView(this);//putting this somewhere in the middle guaranteed
			newFactor1.setTextSize(problem.getTextSize());
			if (!isPrime(factor1)) {//not prime
				newFactor1.setText(Html.fromHtml(String.valueOf(factor1) + " &times; "));
				newFactor1.setOnClickListener(clicker);//not prime, able to be factored
			}
			else {
				newFactor1.setText(Html.fromHtml("<font color=\"lime\">" + String.valueOf(factor1) + "</font>" + " &times; "));
			}
			
			TextView newFactor2 = new TextView(this);
			newFactor2.setTextSize(problem.getTextSize());
			if (i == factorList.getChildCount() - 1) {//putting this factor on the end
				if (!isPrime(factor2)) {//not prime
					newFactor2.setText(Html.fromHtml(String.valueOf(factor2) + " "));
					newFactor2.setOnClickListener(clicker);//able to be factored
				}
				else//is prime
					newFactor2.setText(Html.fromHtml("<font color=\"lime\">" + String.valueOf(factor2) + "</font>" +  " "));
			}
			else {//putting this factor somewhere in the middle
				if (!isPrime(factor2)) {//not prime
					newFactor2.setText(Html.fromHtml(String.valueOf(factor2) + " &times; "));
					newFactor2.setOnClickListener(clicker);//able to be factored
				}
				else//is prime
					newFactor2.setText(Html.fromHtml("<font color=\"lime\">" + String.valueOf(factor2) + "</font>" +  " &times; "));
			}
			
			
			factorList.removeView(selectedFactor);
			factorList.addView(newFactor1, i);
			factorList.addView(newFactor2, i+1);
		}
		checkForCorrect();
	}
	
	public void checkForCorrect() {
		int n = factorList.getChildCount();
		for (int i = 0; i < n; i++) {
			TextView t = (TextView)factorList.getChildAt(i);
			String[] htmlText = t.getText().toString().split(" ");
			if (!isPrime(Integer.parseInt(htmlText[0])))
				return;
		}
		//Toast.makeText(this, "All done.", Toast.LENGTH_SHORT).show();
		sPrefEdit.putString("prime"+String.valueOf(probNum), "true");
		sPrefEdit.commit();
		
		int[] power = new int[primeNumberList.length];//the power of each prime
		
		//counting up the powers of each prime
		for (int i = 0; i < n; i++) {
			String htmlText = ((TextView)factorList.getChildAt(i)).getText().toString();//has html
			//<font color="lime">3</font> &times; 
			//String[] s = htmlText.split(">");//splitting away the <font color="lime">
			//String[] s2 = s[1].split("<");//split away the </font>
			//int num = Integer.parseInt(s2[0]);
			String[] test = htmlText.split(" ");
			int num = Integer.parseInt(test[0]);
			for (int j = 0; j < primeNumberList.length; j++) {
				if (primeNumberList[j] == num) {
					power[j]++;
					break;
				}
			}
		}
		
		
		String answerString = String.valueOf(problemData[probNum]) + " = ";
		for (int i = 0; i < primeNumberList.length; i++) {
			if (power[i] == 0)
				continue;
			else if (power[i] == 1)
				answerString = answerString + String.valueOf(primeNumberList[i]) + " &times; ";
			else
				answerString = answerString + String.valueOf(primeNumberList[i]) + "<sup><small>"
											+ String.valueOf(power[i]) + "</small></sup>" + " &times; ";
		}
		answerString = answerString.substring(0, answerString.length() - " &times ".length());//get rid of the last times
		sPrefEdit.putString("primeAnswer"+String.valueOf(probNum), answerString);//store the answer
		sPrefEdit.commit();
		
		
		prevButton.setAlpha(0.5f);
		nextButton.setAlpha(0.5f);
		prevButton.setClickable(false);
		nextButton.setClickable(false);
		status.startAnimation(fadeStatus);//begin animating "Well done."
	}
	
	public void animateAnswer() {
		String answerString = sPref.getString("primeAnswer"+String.valueOf(probNum),String.valueOf(problemData[probNum]));
		factorList.removeAllViews();
		TextView t = new TextView(this);
		t.setTextSize(problem.getTextSize());
		t.setText(Html.fromHtml(answerString));
		factorList.addView(t);
		factorList.startAnimation(fadeAnswer);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.prime, menu);
		return true;
	}

}
