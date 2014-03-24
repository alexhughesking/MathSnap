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
	ArrayList<ArrayList<Term>> LHSList, RHSList;//store state of problem after each step
	ArrayList<ArrayList<Term>> problemsLHS, problemsRHS;//the initial state for each problem
	Term selectedTerm;
	boolean isTermLeft;//which side selectedTerm is on
	boolean combinePressed;//next selected term should combine with selectedTerm
	LinearLayout eqn;//the equation which contains LHS and RHS
	OnClickListener myClicker;//to select term and change color
	TextView status;//displays the last operation performed
	TextView t0;//"="
	TextView tz;//"0"
	Button moveTermButton, divideByButton,
			multiplyByButton, combineWithButton,
			distributeButton, undoButton;//the buttons that may be visible or invisible
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
		undoButton = (Button) findViewById(R.id.solveXUndoButton);
		
		

		
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
		
		makeTerms();//set up the terms for each problem		
		loadActivity();
	}
	
	public void makeTerms() {
		//make the terms for each problem
		//add them to problemLHS, problemRHS
		problemsLHS = new ArrayList<ArrayList<Term>>(10);
		problemsRHS = new ArrayList<ArrayList<Term>>(10);
		Term.textSize = status.getTextSize();//set the appropriate text size for terms
		t0 = new TextView(this);
		t0.setText(" = ");
		t0.setTextSize(status.getTextSize());
		t0.setTextColor(Color.BLACK);	
		
		tz = new TextView(this);
		tz.setText("0");
		tz.setTextSize(status.getTextSize());
		tz.setTextColor(Color.BLACK);
		
		Term p1t1 = new Term(this, 2, 1, 'x', null);
		Term p1t2 = new Term(this, -3, 1, '1', null);
		Term p1t3 = new Term(this, 1, 1, 'x', null);
		Term p1t4 = new Term(this, 2, 1, '1', null);
		ArrayList<Term> prob1LHS = new ArrayList<Term>();
		ArrayList<Term> prob1RHS = new ArrayList<Term>();
		prob1LHS.add(p1t1);
		prob1LHS.add(p1t2);
		prob1RHS.add(p1t3);
		prob1RHS.add(p1t4);		
		problemsLHS.add(prob1LHS);
		problemsRHS.add(prob1RHS);
		
		Term p2t1 = new Term(this, -5, 1, '1', null);
		Term p2t2 = new Term(this, 3, 1, 'x', null);
		Term p2t3 = new Term(this, 1, 1, 'x', null);
		Term p2t4 = new Term(this, -1, 1, '1', null);
		ArrayList<Term> prob2LHS = new ArrayList<Term>();
		ArrayList<Term> prob2RHS = new ArrayList<Term>();
		prob2LHS.add(p2t1);
		prob2LHS.add(p2t2);
		prob2RHS.add(p2t3);
		prob2RHS.add(p2t4);
		problemsLHS.add(prob2LHS);
		problemsRHS.add(prob2RHS);
		
		Term p3t1 = new Term(this, 1, 1, 'x', null);
		Term p3t2 = new Term(this, 1, 3, 'x', null);
		Term p3t3 = new Term(this, -6, 1, '1', null);
		ArrayList<Term> prob3LHS = new ArrayList<Term>();
		ArrayList<Term> prob3RHS = new ArrayList<Term>();
		prob3LHS.add(p3t1);
		prob3RHS.add(p3t2);
		prob3RHS.add(p3t3);
		problemsLHS.add(prob3LHS);
		problemsRHS.add(prob3RHS);
		
		Term p4inner1 = new Term(this, 2, 1, 'x', null);
		Term p4inner2 = new Term(this, -1, 1, '1', null);
		Term[] p4paren1 = {p4inner1, p4inner2};
		Term p4t1 = new Term(this, 3, 1, '(', p4paren1);
		Term p4t2 = new Term(this, 2, 1, 'x', null);
		Term p4t3 = new Term(this, 1, 1, '1', null);
		ArrayList<Term> prob4LHS = new ArrayList<Term>();
		ArrayList<Term> prob4RHS = new ArrayList<Term>();
		prob4LHS.add(p4t1);
		prob4RHS.add(p4t2);
		prob4RHS.add(p4t3);
		problemsLHS.add(prob4LHS);
		problemsRHS.add(prob4RHS);
		
		Term p5t1 = new Term(this, -1, 1, 'x', null);
		Term p5inner1 = new Term(this, 1, 1, 'x', null);
		Term p5inner2 = new Term(this, 1, 1, '1', null);
		Term[] p5paren1 = {p5inner1, p5inner2};
		Term p5t2 = new Term(this, -2, 1, '(', p5paren1);
		Term p5t3 = new Term(this, 3, 1, '1', null);
		Term p5inner3 = new Term(this, 2, 1, '1', null);
		Term p5inner4 = new Term(this, 1, 1, 'x', null);
		Term[] p5paren2 = {p5inner3, p5inner4};
		Term p5t4 = new Term(this, -2, 1, '(', p5paren2);
		ArrayList<Term> prob5LHS = new ArrayList<Term>();
		ArrayList<Term> prob5RHS = new ArrayList<Term>();
		prob5LHS.add(p5t1);
		prob5LHS.add(p5t2);
		prob5RHS.add(p5t3);
		prob5RHS.add(p5t4);
		problemsLHS.add(prob5LHS);
		problemsRHS.add(prob5RHS);
		
		
		
		Term p6inner1 = new Term(this, 1, 1, 'x', null);
		Term p6inner2 = new Term(this, -2, 1, '1', null);		
		Term[] p6paren1 = {p6inner1, p6inner2};		
		Term p6t1 = new Term(this, 3, 2, '(', p6paren1);	
		Term p6t2 = new Term(this, -1, 1, '1', null);	
		Term p6t3 = new Term(this, 2, 1, '1', null);	
		Term p6t4 = new Term(this, -1, 2, 'x', null);
		
		ArrayList<Term> prob6LHS = new ArrayList<Term>();
		ArrayList<Term> prob6RHS = new ArrayList<Term>();
		prob6LHS.add(p6t1);
		prob6LHS.add(p6t2);
		prob6RHS.add(p6t3);
		prob6RHS.add(p6t4);
		
		problemsLHS.add(prob6LHS);
		problemsRHS.add(prob6RHS);
		
		
		Term p7inner1 = new Term(this, 1, 1, 'x', null);
		Term p7inner2 = new Term(this, -1, 1, '1', null);
		Term[] p7paren1 = {p7inner1, p7inner2};
		Term p7t1 = new Term(this, 1, 6, '(', p7paren1);
		Term p7inner3 = new Term(this, 2, 1, 'x', null);
		Term p7inner4 = new Term(this, 1, 1, '1', null);
		Term[] p7paren2 = {p7inner3, p7inner4};
		Term p7t2 = new Term(this, 1, 3, '(', p7paren2);
		Term p7t3 = new Term(this, -1, 2, '1', null);
		ArrayList<Term> prob7LHS = new ArrayList<Term>();
		ArrayList<Term> prob7RHS = new ArrayList<Term>();
		prob7LHS.add(p7t1);
		prob7RHS.add(p7t2);
		prob7RHS.add(p7t3);
		problemsLHS.add(prob7LHS);
		problemsRHS.add(prob7RHS);
		
		Term p8t1 = new Term(this, 1, 1, 'x', null);
		Term p8inner1 = new Term(this, 1, 1, 'x', null);
		Term p8inner2 = new Term(this, 1, 1, '1', null);
		Term[] p8paren1 = {p8inner1, p8inner2};
		Term p8t2 = new Term(this, -1, 1, '(', p8paren1);
		Term p8t3 = new Term(this, 2, 1, 'x', null);
		Term p8inner3 = new Term(this, 1, 1, 'x', null);
		Term p8inner4 = new Term(this, 2, 1, '1', null);
		Term[] p8paren2 = {p8inner3, p8inner4};
		Term p8t4 = new Term(this, -1, 1, '(', p8paren2);
		Term p8t5 = new Term(this, -3, 1, '1', null);
		ArrayList<Term> prob8LHS = new ArrayList<Term>();
		ArrayList<Term> prob8RHS = new ArrayList<Term>();
		prob8LHS.add(p8t1);
		prob8LHS.add(p8t2);
		prob8RHS.add(p8t3);
		prob8RHS.add(p8t4);
		prob8RHS.add(p8t5);
		problemsLHS.add(prob8LHS);
		problemsRHS.add(prob8RHS);
		
		Term p9t1 = new Term(this, 5, 1, '1', null);
		Term p9inner1 = new Term(this, 2, 1, 'x', null);
		Term p9inner2 = new Term(this, 1, 1, '1', null);
		Term[] p9paren1 = {p9inner1, p9inner2};
		Term p9t2 = new Term(this, 3, 1, '(', p9paren1);
		Term p9inner3 = new Term(this, 1, 1, '1', null);
		Term p9inner4 = new Term(this, -1, 1, 'x', null);
		Term[] p9paren2 = {p9inner3, p9inner4};
		Term p9t3 = new Term(this, -2, 1, '(', p9paren2);
		Term p9t4 = new Term(this, 4, 1, 'x', null);
		Term p9t5 = new Term(this, -2, 1, '1', null);
		ArrayList<Term> prob9LHS = new ArrayList<Term>();
		ArrayList<Term> prob9RHS = new ArrayList<Term>();
		prob9LHS.add(p9t1);
		prob9LHS.add(p9t2);
		prob9LHS.add(p9t3);
		prob9RHS.add(p9t4);
		prob9RHS.add(p9t5);
		problemsLHS.add(prob9LHS);
		problemsRHS.add(prob9RHS);
		
		Term p10inner1 = new Term(this, 2, 1, 'x', null);
		Term p10inner2 = new Term(this, -4, 1, '1', null);
		Term[] p10paren1 = {p10inner1, p10inner2};
		Term p10t1 = new Term(this, 1, 5, '(', p10paren1);
		Term p10t2 = new Term(this, 1, 1, 'x', null);
		Term p10inner3 = new Term(this, 1, 1, '1', null);
		Term p10inner4 = new Term(this, 1, 1, 'x', null);
		Term[] p10paren2 = {p10inner3, p10inner4};
		Term p10t3 = new Term(this, 3, 8, '(', p10paren2);
		Term p10t4 = new Term(this, 1, 1, 'x', null);
		Term p10t5 = new Term(this, -1, 1, '1', null);
		ArrayList<Term> prob10LHS = new ArrayList<Term>();
		ArrayList<Term> prob10RHS = new ArrayList<Term>();
		prob10LHS.add(p10t1);
		prob10LHS.add(p10t2);
		prob10RHS.add(p10t3);
		prob10RHS.add(p10t4);
		prob10RHS.add(p10t5);
		problemsLHS.add(prob10LHS);
		problemsRHS.add(prob10RHS);
	}
	
	public void loadActivity() {
		probNumView.setText((probNum + 1) + ")");
		status.setText("Select a term to begin.");
		selectedTerm = null;
		combinePressed = false;
		//copy the problemsLHS and problemsRHS into LHS and RHS
		LHS = new ArrayList<Term>(15);
		RHS = new ArrayList<Term>(15);
		ArrayList<Term> probLHS = problemsLHS.get(probNum);//the data for current prob's LHS
		ArrayList<Term> probRHS = problemsRHS.get(probNum);//the data for current prob's RHS
		LHSList = new ArrayList<ArrayList<Term>>(30);//no steps taken yet
		RHSList = new ArrayList<ArrayList<Term>>(30);//no steps taken yet
		for (int i = 0; i < probLHS.size(); i++) {
			Term t = new Term(probLHS.get(i));//copy terms from the problem
			t.setOnClickListener(myClicker);
			LHS.add(t);//insert copied term into current problem
		}
		for (int i = 0; i < probRHS.size(); i ++) {
			Term t = new Term(probRHS.get(i));
			t.setOnClickListener(myClicker);
			RHS.add(t);
		}
		//saveState();
		drawEqn();
	}
	
	public void saveState() {
		ArrayList<Term> currLHS = new ArrayList<Term>(15);//current Terms on LHS
		ArrayList<Term> currRHS = new ArrayList<Term>(15);//current Terms on RHS
		
		for (int i = 0; i < LHS.size(); i++) {
			Term t = new Term(LHS.get(i));//copy LHS to be stored
			currLHS.add(t);
		}	
		for (int i = 0; i < RHS.size(); i++) {
			Term t = new Term(RHS.get(i));//copy RHS to be stored
			currRHS.add(t);
		}
		
		LHSList.add(currLHS);//store LHS at end of list
		RHSList.add(currRHS);//store RHS at end of list
	}
	
	public void undo(View v) {
		if (LHSList.size() == 0) {
			Toast.makeText(this, "Nothing to undo!", Toast.LENGTH_SHORT).show();
			return;
		}
		LHS = LHSList.remove(LHSList.size() - 1);//remove LHS from end of list
		RHS = RHSList.remove(RHSList.size() - 1);//remove RHS from end of list
		
		for (int i = 0; i < LHS.size(); i++) {
			LHS.get(i).setOnClickListener(myClicker);
		}
		for (int i = 0; i < RHS.size(); i++) {
			RHS.get(i).setOnClickListener(myClicker);
		}
		selectedTerm = null;
		combinePressed = false;
		status.setText("Undid a step.");
		drawEqn();
	}
	
	public void displayButtons() {
		if (LHSList.size() == 0) {//no steps taken yet
			undoButton.setAlpha(0.5f);//setAlpha 0.5f for undo button
			undoButton.setClickable(false);//setClickable false for undo button
		}
		else {
			undoButton.setAlpha(1.0f);//setAlpha to 1 for undo
			undoButton.setClickable(true);//setClickable true for undo
		}
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
		saveState();
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
		saveState();
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
		saveState();
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
		saveState();
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
		saveState();
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
		if (LHS.size() > 1 || RHS.size() > 1)
			return;//not done yet
		
		//explicit check for x = 0 or 0 = x
		else if (LHS.size() == 0) {//0 on the LHS, RHS should not also be empty
			if (RHS.get(0).var == 'x' && RHS.get(0).coeff == 1 && RHS.get(0).denom == 1)
				status.setText("You solved it! Well done.");
		}
		else if (RHS.size() == 0) {//0 on RHS, LHS should not also be empty
			if (LHS.get(0).var == 'x' && LHS.get(0).coeff == 1 && LHS.get(0).denom == 1)
				status.setText("You solved it! Well done.");
		}
		
		//usually, one side should have a term which is 1x
		//the other side should be just a constant
		else if (LHS.get(0).var == 'x' && LHS.get(0).coeff == 1 && LHS.get(0).denom == 1 && RHS.get(0).var == '1')
			status.setText("You solved it! Well done.");
		else if (RHS.get(0).var == 'x' && RHS.get(0).coeff == 1 && RHS.get(0).denom == 1 && LHS.get(0).var == '1')
			status.setText("You solved it! Well done.");
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
