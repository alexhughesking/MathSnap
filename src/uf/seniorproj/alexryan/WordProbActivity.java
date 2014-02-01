package uf.seniorproj.alexryan;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

public class WordProbActivity extends Activity {

	String[] question = {"A passenger plane made a trip to Las Vegas and back. On the trip there it flew 432 mph and on the return trip it went 480 mph. How long did the trip there take if the return trip took nine hours?",
			              "qusetion2",
			              "question3",
			              "question4",
			              "question5",
			              "question6",
			              "question7",
			              "question8",
			              "question9",
			              "question10"};
	
	String[] a = {"a1", "a2", "a3", "a4", "a5", "a6", "a7", "a8", "a9", "a10"};
	String[] b = {"b1", "b2", "b3", "b4", "b5", "b6", "b7", "b8", "b9", "b10"};
	String[] c = {"c1", "c2", "c3", "c4", "c5", "c6", "c7", "c8", "c9", "c10"};
	String[] d = {"d1", "d2", "d3", "d4", "d5", "d6", "d7", "d8", "d9", "d10"};
	
	

	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_word_prob);
		
		TextView problem = (TextView)findViewById(R.id.wordProblem);
		problem.setText(question[0]);
		
		TextView aRow = (TextView)findViewById(R.id.a);
		aRow.setText(a[0]);
		
		TextView bRow = (TextView)findViewById(R.id.b);
		bRow.setText(b[0]);
	
		TextView cRow = (TextView)findViewById(R.id.c);
		cRow.setText(c[0]);
		
		TextView dRow = (TextView)findViewById(R.id.d);
		dRow.setText(d[0]);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.word_prob, menu);
		return true;
	}

}
