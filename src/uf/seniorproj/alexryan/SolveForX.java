package uf.seniorproj.alexryan;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

public class SolveForX extends Activity {
	
	ArrayList<Term> LHS, RHS;
	Term selectedTerm;
	boolean isTermLeft;
	boolean combinePressed;
	LinearLayout eqn;
	TextView t0;//"="
	TextView tz;//"0"
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_solve_for_x);
		
		eqn = (LinearLayout)findViewById(R.id.solveXEqn);
		
		final Context context = this;
		combinePressed = false;

		LHS = new ArrayList<Term>(5);
		RHS = new ArrayList<Term>(5);
		
		OnClickListener myClicker = new OnClickListener() {
			@Override
			public void onClick(View v) {
				Term t = (Term)v;
				if (combinePressed) {
					Boolean combineLeft = false;
					for (int i = 0; i < LHS.size(); i++)
						if (LHS.get(i) == t)
							combineLeft = true;
					combineTerm(t, combineLeft);
				}
				else {
				selectedTerm = t;
				
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
				}
				
			}			
		};
		
		t0 = new TextView(this);
		t0.setText(" = ");
		t0.setTextSize(50);
		t0.setTextColor(Color.BLACK);	
		
		tz = new TextView(this);
		tz.setText("0");
		tz.setTextSize(50);
		tz.setTextColor(Color.BLACK);
		
		Term t1 = new Term(this, 2, 1, 'x');
		t1.setTextSize(50);
		t1.setTextColor(Color.BLACK);
		
		
	
		Term t2 = new Term(this, -1, 1, '1');
		t2.setTextSize(50);
		t2.setTextColor(Color.BLACK);
		
		
		Term t3 = new Term(this, 2, 1, '1');
		t3.setTextSize(50);
		t3.setTextColor(Color.BLACK);
		
		
		Term t4 = new Term(this, -1, 1, 'x');
		t4.setTextSize(50);
		t4.setTextColor(Color.BLACK);
		
		
		LHS.add(t1);
		LHS.add(t2);
		RHS.add(t3);
		RHS.add(t4);

		
		
		t1.setOnClickListener(myClicker);
		t2.setOnClickListener(myClicker);
		t3.setOnClickListener(myClicker);
		t4.setOnClickListener(myClicker);
		

		
		drawEqn();
		
		
		
	}
	
	public void addTerm(View v) {
		if (selectedTerm == null) {
			Toast.makeText(this, "Select a term by tapping on it.", Toast.LENGTH_SHORT).show();
			return;
		}
		if (isTermLeft) {
			isTermLeft = false;	
			selectedTerm.coeff = -1 * selectedTerm.coeff;
			selectedTerm.setMyText();
			LHS.remove(selectedTerm);
			RHS.add(selectedTerm);
		}
		else {
			isTermLeft = true;
			selectedTerm.coeff = -1 * selectedTerm.coeff;
			selectedTerm.setMyText();
			RHS.remove(selectedTerm);
			LHS.add(selectedTerm);
		}
		drawEqn();
		checkForCorrect();
	}
	
	public void selectCombine(View v) {
		if (selectedTerm == null) {
			Toast.makeText(this, "Select a term first.", Toast.LENGTH_SHORT).show();
			return;
		}
		Toast.makeText(this, "Select a term to combine with.", Toast.LENGTH_SHORT).show();
		combinePressed = true;//combine was pressed, listen for click on term
	}
	
	public void combineTerm(Term combineTerm, Boolean combineLeft) {
		combinePressed = false;
		if (combineLeft != isTermLeft) {//terms should be on same side to combine
			Toast.makeText(this, "Move terms to the same side first.", Toast.LENGTH_SHORT).show();
			return;
		}
		if (selectedTerm.var != combineTerm.var) {//can't combine var and constant
			Toast.makeText(this, "Can't combine these terms.", Toast.LENGTH_SHORT).show();
			return;
		}
		selectedTerm.coeff = combineTerm.denom*selectedTerm.coeff + selectedTerm.denom*combineTerm.coeff;
		selectedTerm.denom = selectedTerm.denom*combineTerm.denom;
		selectedTerm.reduce();
		if (isTermLeft)
			LHS.remove(combineTerm);
		else
			RHS.remove(combineTerm);
		drawEqn();
		checkForCorrect();
	}
	
	public void divide(View v) {
		if (selectedTerm == null){
			Toast.makeText(this, "Select a term first.", Toast.LENGTH_SHORT).show();
			return;
		}
		int c = selectedTerm.coeff;
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
		drawEqn();
		checkForCorrect();
	}
	
	public void multiply(View v) {
		if (selectedTerm == null) {
			Toast.makeText(this, "Select a term first.", Toast.LENGTH_SHORT).show();
			return;
		}
		int c = selectedTerm.denom;
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
		drawEqn();
		checkForCorrect();
	}
	
	public void checkForCorrect() {
		if (LHS.size() != 1 || RHS.size() != 1)
			return;//not done yet
		//one side should have a term which is 1x
		//the other side should be just a constant
		if (LHS.get(0).var == 'x' && LHS.get(0).coeff == 1 && LHS.get(0).denom == 1 && RHS.get(0).var == '1')
			Toast.makeText(this, "You solved it!", Toast.LENGTH_SHORT).show();
		if (RHS.get(0).var == 'x' && RHS.get(0).coeff == 1 && RHS.get(0).denom == 1 && LHS.get(0).var == '1')
			Toast.makeText(this, "You solved it!", Toast.LENGTH_SHORT).show();
	}
	
	
	public void drawEqn() {
		eqn.removeAllViews();

		for (int i = 0; i < LHS.size(); i++) {
			Term t = LHS.get(i);
			if (i == 0) {
				if (t.coeff < 0)
					eqn.addView(t.sign);
//				else
//					t.setText(Html.fromHtml("<tt>" + LHS.get(i).text + "<//tt>"));
			}
//			else if (t.coeff > 0)
//				t.setText(Html.fromHtml("<tt>" + "+" + t.text + "<//tt>"));
			else
//				t.setText(Html.fromHtml("<tt>" + "-" + t.text + "<//tt>"));
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
//					t.setText(Html.fromHtml("<tt>" + "-" + t.text + "<//tt>"));
					eqn.addView(t.sign);
//				else
//					t.setText(Html.fromHtml("<tt>" + t.text + "<//tt>"));
			}
//			else if (t.coeff > 0)
//				t.setText(Html.fromHtml("<tt>" + "+" + t.text + "<//tt>"));
			else
//				t.setText(Html.fromHtml("<tt>" + "-" + t.text + "<//tt>"));
				eqn.addView(t.sign);
			
			eqn.addView(t);
		}
		if(RHS.size() == 0)
			eqn.addView(tz); //"0"
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.solve_for_x, menu);
		return true;
	}

}
