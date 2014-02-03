package uf.seniorproj.alexryan;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
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
	LinearLayout eqn;
	TextView t0;//"="
	TextView tz;//"0"
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_solve_for_x);
		
		eqn = (LinearLayout)findViewById(R.id.solveXEqn);
		
		final Context context = this;
		

		LHS = new ArrayList<Term>(5);
		RHS = new ArrayList<Term>(5);
		
		OnClickListener myClicker = new OnClickListener() {
			@Override
			public void onClick(View v) {
				Term t = (Term)v;
				selectedTerm = t;
				
				for (int i = 0; i < LHS.size(); i++) {
					LHS.get(i).setTextColor(Color.BLACK);
					if (LHS.get(i) == t) {
						LHS.get(i).setTextColor(Color.GREEN);
						isTermLeft = true;
					}
				}
				for (int i = 0; i < RHS.size(); i++) {
					RHS.get(i).setTextColor(Color.BLACK);
					if (RHS.get(i) == t) {
						RHS.get(i).setTextColor(Color.GREEN);
						isTermLeft = false;
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
		
		Term t1 = new Term(this, 2, 'x');
		t1.setTextSize(50);
		t1.setTextColor(Color.BLACK);
		
		
	
		Term t2 = new Term(this, -1, '1');
		t2.setTextSize(50);
		t2.setTextColor(Color.BLACK);
		
		
		Term t3 = new Term(this, 2, '1');
		t3.setTextSize(50);
		t3.setTextColor(Color.BLACK);
		
		
		Term t4 = new Term(this, -1, 'x');
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
	}
	
	
	public void drawEqn() {		
		eqn.removeAllViews();
		
		for (int i = 0; i < LHS.size(); i++) {
			if (i == 0) {
				LHS.get(i).setText(LHS.get(i).text);
			}
			else if (LHS.get(i).coeff > 0)
				LHS.get(i).setText(" + " + LHS.get(i).text);
			else
				LHS.get(i).setText(" " + LHS.get(i).text);
			
			eqn.addView(LHS.get(i));
		}
		if (LHS.size() == 0)
			eqn.addView(tz);
		
		eqn.addView(t0); //" = "
		
		for (int i = 0; i < RHS.size(); i++) {
			if (i == 0) {
				RHS.get(i).setText(RHS.get(i).text);
			}
			else if (RHS.get(i).coeff > 0)
				RHS.get(i).setText(" + " + RHS.get(i).text);
			else
				RHS.get(i).setText(" " + RHS.get(i).text);
			
			eqn.addView(RHS.get(i));
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
