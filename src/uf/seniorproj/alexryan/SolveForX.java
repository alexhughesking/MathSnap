package uf.seniorproj.alexryan;

import java.util.ArrayList;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class SolveForX extends Activity {
	
	ArrayList<Term> LHS, RHS;
	ArrayList<Term>[] LHSData, RHSData;
	Term selectedTerm;
	boolean isTermLeft;//which side it's on
	boolean combinePressed;//next selected term should be combined
	LinearLayout eqn;//the equation which contains LHS and RHS
	OnClickListener myClicker;//to select term and change color
	TextView status;//displays the last operation performed
	TextView t0;//"="
	TextView tz;//"0"
	Button moveTermButton, divideByButton,
			multiplyByButton, combineWithButton,
			distributeButton;//the buttons that may be visible or invisible
	int probNum;
	SharedPreferences sPref;//used for saving problem completion state
	SharedPreferences.Editor sPrefEdit;
	TextView probNumView;//the problem number in the corner
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_solve_for_x);
		
		sPref = getSharedPreferences("shared_preferences", MODE_PRIVATE);
		sPrefEdit = sPref.edit();
		
		Bundle extras = getIntent().getExtras();
		probNum = extras.getInt("probNum");//get the problem number that was clicked
		
		eqn = (LinearLayout)findViewById(R.id.solveXEqn);
		status = (TextView)findViewById(R.id.solveXStatus);
		probNumView = (TextView)findViewById(R.id.solveXProbNum);
		
		
		moveTermButton = (Button) findViewById(R.id.solveXMoveTermButton);
		multiplyByButton = (Button) findViewById(R.id.solveXMultiplyButton);
		divideByButton = (Button) findViewById(R.id.solveXDivideButton);
		combineWithButton = (Button) findViewById(R.id.solveXCombineButton);
		distributeButton = (Button) findViewById(R.id.solveXDistributeButton);
		
		combinePressed = false;
		
		LHS = new ArrayList<Term>(15);
		RHS = new ArrayList<Term>(15);
		
		myClicker = new OnClickListener() {
			@Override
			public void onClick(View v) {
				Term t = (Term)v;//what they clicked on
				if (combinePressed) {//user trying to combine
					Boolean combineLeft = false;
					for (int i = 0; i < LHS.size(); i++)
						if (LHS.get(i) == t)
							combineLeft = true;//terms must be on same side to combine
					combineTerm(t, combineLeft);
				}
				else {
				selectedTerm = t;//what they clicked on
				
				for (int i = 0; i < LHS.size(); i++) {
					LHS.get(i).setTextColor(Color.BLACK);
					LHS.get(i).sign.setTextColor(Color.BLACK);
					if (LHS.get(i) == t) {
						LHS.get(i).setTextColor(Color.GREEN);
						LHS.get(i).sign.setTextColor(Color.GREEN);
						isTermLeft = true;
					}
				}
				for (int i = 0; i < RHS.size(); i++) {
					RHS.get(i).setTextColor(Color.BLACK);
					RHS.get(i).sign.setTextColor(Color.BLACK);
					if (RHS.get(i) == t) {
						RHS.get(i).setTextColor(Color.GREEN);
						RHS.get(i).sign.setTextColor(Color.GREEN);
						isTermLeft = false;
					}
				}
				displayButtons();
				}
				
			}			
		};
		
		Term.textSize = status.getTextSize();//set the appropriate text size for terms
		t0 = new TextView(this);
		t0.setText(" = ");
		t0.setTextSize(status.getTextSize());
		t0.setTextColor(Color.BLACK);	
		
		tz = new TextView(this);
		tz.setText("0");
		tz.setTextSize(status.getTextSize());
		tz.setTextColor(Color.BLACK);
		
		Term test1 = new Term(this, 1, 1, 'x', null);
		Term test2 = new Term(this, -2, 1, '1', null);
		
		Term[] testPt = {test1, test2};
		
		Term t1 = new Term(this, 3, 2, '(', testPt);
		//t1.setTextSize(50);
		//t1.setTextColor(Color.BLACK);
		
		
	
		Term t2 = new Term(this, -1, 1, '1', null);
		//t2.setTextSize(50);
		//t2.setTextColor(Color.BLACK);
		
		
		Term t3 = new Term(this, 2, 1, '1', null);
		//t3.setTextSize(50);
		//t3.setTextColor(Color.BLACK);
		
		
		Term t4 = new Term(this, -1, 2, 'x', null);
		//t4.setTextSize(50);
		//t4.setTextColor(Color.BLACK);
		
		
		
		
		LHS.add(t1);
		LHS.add(t2);
		RHS.add(t3);
		RHS.add(t4);

		
		
		t1.setOnClickListener(myClicker);
		t2.setOnClickListener(myClicker);
		t3.setOnClickListener(myClicker);
		t4.setOnClickListener(myClicker);
		
		loadActivity();
	}
	
	public void makeTerms() {
		//make the terms for each problem
		//add them to some problemData array
	}
	
	public void loadActivity() {
		probNumView.setText((probNum + 1) + ")");
		status.setText("Select a term to begin.");
		selectedTerm = null;
		//copy the problemData into LHS and RHS
		drawEqn();
	}
	
	public void displayButtons() {
		if (selectedTerm == null) {
			moveTermButton.setAlpha(0.5f);
			multiplyByButton.setAlpha(0.5f);
			divideByButton.setAlpha(0.5f);
			combineWithButton.setAlpha(0.5f);
			distributeButton.setAlpha(0.5f);
			multiplyByButton.setText("Multiply");
			divideByButton.setText("Divide");
			distributeButton.setText("Distribute");
			moveTermButton.setClickable(false);
			multiplyByButton.setClickable(false);
			divideByButton.setClickable(false);
			combineWithButton.setClickable(false);
			distributeButton.setClickable(false);
		}
		else{
			multiplyByButton.setText("Multiply by " + selectedTerm.denom);
			divideByButton.setText("Divide by " + selectedTerm.coeff);
			moveTermButton.setAlpha(1.0f);//term can always be moved
			moveTermButton.setClickable(true);
			//don't show multiply if denom is 1
			if (selectedTerm.denom == 1) {
				multiplyByButton.setAlpha(0.5f);
				multiplyByButton.setClickable(false);
			}
			else {
				multiplyByButton.setAlpha(1.0f);
				multiplyByButton.setClickable(true);
			}
			//don't show divide if coeff is 1
			if(selectedTerm.coeff == 1) {
				divideByButton.setAlpha(0.5f);
				divideByButton.setClickable(false);
			}
			else {
				divideByButton.setAlpha(1.0f);
				divideByButton.setClickable(true);
			}
			if (selectedTerm.var == '(') {//parenthetical term
				if (selectedTerm.coeff != 1) {
					distributeButton.setAlpha(1.0f);//something to distribute
					distributeButton.setClickable(true);
					distributeButton.setText("Distribute the " + selectedTerm.coeff);
				}
				else {
					distributeButton.setAlpha(0.5f);//nothing to distribute
					distributeButton.setClickable(false);
					distributeButton.setText("Distribute");
				}
				combineWithButton.setAlpha(0.5f);//can't combine parenterms
				combineWithButton.setClickable(false);
			}
			else {//not a parenthetical term
				distributeButton.setAlpha(0.5f);//nothing to distribute
				distributeButton.setClickable(false);
				distributeButton.setText("Distribute");
				combineWithButton.setAlpha(1.0f);//term can be combined
				combineWithButton.setClickable(true);
			}
			
		}
	}
	
	public void addTerm(View v) {//to move a term
		if (selectedTerm == null) {
			Toast.makeText(this, "Select a term by tapping on it.", Toast.LENGTH_SHORT).show();
			return;
		}
			status.setText("Moved a term.");
		if (isTermLeft) {//move left to right
			isTermLeft = false;	
			selectedTerm.coeff = -1 * selectedTerm.coeff;
			selectedTerm.setMyText();
			LHS.remove(selectedTerm);
			RHS.add(selectedTerm);
		}
		else {//move right to left
			isTermLeft = true;
			selectedTerm.coeff = -1 * selectedTerm.coeff;
			selectedTerm.setMyText();
			RHS.remove(selectedTerm);
			LHS.add(selectedTerm);
		}
		drawEqn();
		checkForCorrect();
	}
	
	public void selectCombine(View v) {//combine was pressed
		if (selectedTerm == null) {
			Toast.makeText(this, "Select a term first.", Toast.LENGTH_SHORT).show();
			return;
		}
		status.setText("Select a term to combine with.");
		//Toast.makeText(this, "Select a term to combine with.", Toast.LENGTH_SHORT).show();
		combinePressed = true;//combine was pressed, listen for click on term
	}
	
	public void combineTerm(Term combineTerm, Boolean combineLeft) {//combine two selected terms
		combinePressed = false;
		if (combineLeft != isTermLeft) {//terms should be on same side to combine
			//Toast.makeText(this, "Move terms to the same side first.", Toast.LENGTH_SHORT).show();
			status.setText("Move terms to the same side first.");
			return;
		}
		if (selectedTerm.var != combineTerm.var ||
				selectedTerm.var == '(' ||
				combineTerm.var == '(') {//can't combine var and constant or parenTerm
			//Toast.makeText(this, "Can't combine these terms.", Toast.LENGTH_SHORT).show();
			status.setText("Can't combine these terms.");
			return;
		}
		if (selectedTerm == combineTerm) {//tapped selectedTerm again after combine
			status.setText("Did not combine two terms.");
			return;
		}
		status.setText("Combined two like terms.");
		selectedTerm.coeff = combineTerm.denom*selectedTerm.coeff + selectedTerm.denom*combineTerm.coeff;
		selectedTerm.denom = selectedTerm.denom*combineTerm.denom;//common denominator
		selectedTerm.reduce();
		if (isTermLeft)
			LHS.remove(combineTerm);//this was combined into selectedTerm
		else
			RHS.remove(combineTerm);
		if (selectedTerm.coeff == 0) {
			if (isTermLeft)
				LHS.remove(selectedTerm);
			else
				RHS.remove(selectedTerm);
			selectedTerm = null;
		}
		drawEqn();
		checkForCorrect();
	}
	
	public void divide(View v) {//divide both sides by the coeff of selectedTerm
		if (selectedTerm == null){
			Toast.makeText(this, "Select a term first.", Toast.LENGTH_SHORT).show();
			return;
		}
		int c = selectedTerm.coeff;
		status.setText("Divided both sides by " + c + ".");
		for (int i = 0; i < LHS.size(); i++) {
			Term t = LHS.get(i);
			t.denom = t.denom * c;
			t.reduce();
		}
		for (int i = 0; i < RHS.size(); i++) {
			Term t = RHS.get(i);
			t.denom = t.denom * c;
			t.reduce();
		}
		if (selectedTerm.var == '(' && selectedTerm.coeff == 1 && selectedTerm.denom == 1)
			selectedTerm = null;//a parenTerm was just cleared into new terms
		for (int i = 0; i < LHS.size(); i++) {
			Term t = LHS.get(i);
			if (t.var == '(' && t.coeff == 1 && t.denom == 1) {//check for any parenTerms that were cleared
				int pos = LHS.indexOf(t);
				for (int j = 0; j < t.parenTerms.length; j++) {
					t.parenTerms[j].setOnClickListener(myClicker);
					LHS.add(pos + j, t.parenTerms[j]);//put new parenTerms in correct place
				}
				LHS.remove(t);
			}
		}
		for (int i = 0; i < RHS.size(); i++) {
			Term t = RHS.get(i);
			if (t.var == '(' && t.coeff == 1 && t.denom == 1) {
				int pos = RHS.indexOf(t);
				for (int j = 0; j < t.parenTerms.length; j++) {
					t.parenTerms[j].setOnClickListener(myClicker);
					RHS.add(pos + j, t.parenTerms[j]);
				}
				RHS.remove(t);				
			}
			
		}
		drawEqn();
		checkForCorrect();
	}
	
	public void multiply(View v) {//multiply both sides by the denom of selectedTerm
		if (selectedTerm == null) {
			Toast.makeText(this, "Select a term first.", Toast.LENGTH_SHORT).show();
			return;
		}
		int c = selectedTerm.denom;
		status.setText("Multiplied both sides by " + c + ".");
		if (c == 1)
			return;
		for (int i = 0; i < LHS.size(); i++) {
			Term t = LHS.get(i);
			t.coeff = t.coeff * c;
			t.reduce();				
		}
		for (int i = 0; i < RHS.size(); i++) {
			Term t = RHS.get(i);
			t.coeff = t.coeff * c;
			t.reduce();
		}
		if (selectedTerm.var == '(' && selectedTerm.coeff == 1 && selectedTerm.denom == 1)
			selectedTerm = null;//a parenTerm was cleared into new terms
		for (int i = 0; i < LHS.size(); i++) {
			Term t = LHS.get(i);
			if (t.var == '(' && t.coeff == 1 && t.denom == 1) {//check for cleared parenTerms
				int pos = LHS.indexOf(t);
				for (int j = 0; j < t.parenTerms.length; j++) {
					t.parenTerms[j].setOnClickListener(myClicker);
					LHS.add(pos + j, t.parenTerms[j]);//put parenTerms in correct position
				}
				LHS.remove(t);				
			}
		}
		for (int i = 0; i < RHS.size(); i++) {
			Term t = RHS.get(i);
			if (t.var == '(' && t.coeff == 1 && t.denom == 1) {
				int pos = RHS.indexOf(t);
				for (int j = 0; j < t.parenTerms.length; j++) {
					t.parenTerms[j].setOnClickListener(myClicker);
					RHS.add(pos + j, t.parenTerms[j]);
				}
				RHS.remove(t);				
			}
			
		}
		drawEqn();
		checkForCorrect();
	}
	
	public void distribute(View v) {//distribute the coeff of a parenTerm
		if (selectedTerm == null) {
			Toast.makeText(this, "Select a term first.", Toast.LENGTH_SHORT).show();
			return;
		}
		if (selectedTerm.var != '(') {
			Toast.makeText(this, "Nothing to distribute.", Toast.LENGTH_SHORT).show();
			return;
		}
		status.setText("Distributed the " + selectedTerm.coeff + ".");
		if (selectedTerm.denom != 1) {//is fraction, leave as a parenTerm
			for (int i = 0; i < selectedTerm.parenTerms.length; i++) {
				selectedTerm.parenTerms[i].coeff *= selectedTerm.coeff;
				selectedTerm.parenTerms[i].setMyText();
			}
			selectedTerm.coeff = 1;
			selectedTerm.setMyText();
		}
		else {//is not fraction, need to replace the parenTerm with the sub-terms
			if (isTermLeft) {
				int pos = LHS.indexOf(selectedTerm);
				for (int i = 0; i < selectedTerm.parenTerms.length; i++) {
					selectedTerm.parenTerms[i].coeff *= selectedTerm.coeff;
					selectedTerm.parenTerms[i].setMyText();
					selectedTerm.parenTerms[i].setOnClickListener(myClicker);
					LHS.add(pos + i, selectedTerm.parenTerms[i]);
				}
				LHS.remove(selectedTerm);
			}
			else {
				int pos = RHS.indexOf(selectedTerm);
				for (int i = 0; i < selectedTerm.parenTerms.length; i++) {
					selectedTerm.parenTerms[i].coeff *= selectedTerm.coeff;
					selectedTerm.parenTerms[i].setMyText();
					selectedTerm.parenTerms[i].setOnClickListener(myClicker);
					RHS.add(pos + i, selectedTerm.parenTerms[i]);
				}
				RHS.remove(selectedTerm);
			}
			selectedTerm = null;
		}
		drawEqn();
	}
	
	public void checkForCorrect() {
		if (LHS.size() != 1 || RHS.size() != 1)
			return;//not done yet
		//one side should have a term which is 1x
		//the other side should be just a constant
		if (LHS.get(0).var == 'x' && LHS.get(0).coeff == 1 && LHS.get(0).denom == 1 && RHS.get(0).var == '1')
			status.setText("You solved it! Well done.");
			//Toast.makeText(this, "You solved it!", Toast.LENGTH_SHORT).show();
		if (RHS.get(0).var == 'x' && RHS.get(0).coeff == 1 && RHS.get(0).denom == 1 && LHS.get(0).var == '1')
			status.setText("You solved it! Well done.");
			//Toast.makeText(this, "You solved it!", Toast.LENGTH_SHORT).show();
	}
	
	
	public void drawEqn() {
		eqn.removeAllViews();

		for (int i = 0; i < LHS.size(); i++) {
			Term t = LHS.get(i);
			if (i == 0) {
				if (t.coeff < 0)
					eqn.addView(t.sign);
			}
			else
				eqn.addView(t.sign);
			
			eqn.addView(t);
		}
		if (LHS.size() == 0)
			eqn.addView(tz);
		
		eqn.addView(t0); //" = "
		
		for (int i = 0; i < RHS.size(); i++) {
			Term t = RHS.get(i);
			if (i == 0) {
				if (t.coeff < 0)
					eqn.addView(t.sign);
			}
			else
				eqn.addView(t.sign);
			
			eqn.addView(t);
		}
		if(RHS.size() == 0)
			eqn.addView(tz); //"0"
		displayButtons();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.solve_for_x, menu);
		return true;
	}

}
