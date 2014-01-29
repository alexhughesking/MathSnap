package uf.seniorproj.alexryan;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

public class Term extends TextView {
	
	int coeff;
	char var;
	Term[] parenTerms;
	Term numerator, denominator;
	String text;

	public Term(Context context, int c, char v) {
		super(context);
		coeff = c;
		var = v;
		setMyText();
	}
	public Term(Context context) {
		super(context);
		
	}
	
	public Term(Context context, AttributeSet attrs) {
	    super(context, attrs);
	}


	public Term(Context context, AttributeSet attrs, int defStyle) {
	    super(context, attrs, defStyle);

	}
	
	public void setMyText() {
		if (coeff == 1) {
			text = new String("" + var);
		}
		else if (coeff == -1) {
			text = new String("-" + var);
		}
		else if (var == '1') {
			text = new String("" + coeff);
		}
		else
			text = new String("" + coeff + var);
		super.setText(text);
	}

}
