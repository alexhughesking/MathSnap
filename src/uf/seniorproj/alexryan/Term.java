package uf.seniorproj.alexryan;

import android.content.Context;
import android.text.Html;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.TextView;

public class Term extends TextView {
	
	int coeff;
	int denom;//denom == 1 means this is not a fractional term
	char var;//1 means const, x means var, ( means parenthetical
	Term[] parenTerms;//for parenthetical terms
	Term numerator;
	String text;//will not include the sign
	TextView sign;//+ or -

	public Term(Context context, int c, int d, char v) {
		super(context);
		coeff = c;
		denom = d;
		var = v;
		this.setTextSize(50);
		sign = new TextView(context);
		sign.setTextSize(this.getTextSize());
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
		text = "<tt>";
		if (denom != 1) {
			text = text + "<u>";
		}
		if (var == '1') {
			text = text + abs(coeff);
		}
		else if (var == 'x') {
			if (abs(coeff) == 1)
				text = text + var;
			else
				text = text + abs(coeff) + var;
		}
		if (denom != 1) {
			text = text + "<//u><br>" + denom;
		}
		if (coeff < 0)
			sign.setText(Html.fromHtml("<tt>-<//tt>"));
		else
			sign.setText(Html.fromHtml("<tt>+<//tt>"));
		super.setText(Html.fromHtml("<//tt>" + text));
	}
	
	public void reduce() {
		if (denom < 0) {
			denom = denom * -1;
			coeff = coeff * -1;
		}
		int g = gcd(abs(coeff), abs(denom));
		coeff = coeff / g;
		denom = denom / g;
		setMyText();
	}
	
	public int abs(int x) {
		if (x < 0)
			return -1*x;
		return x;
	}
	
	public static int gcd(int a, int b)
	{
	  if(a == 0 || b == 0) return a+b; // base case
	  return gcd(b,a%b);
	}

}
