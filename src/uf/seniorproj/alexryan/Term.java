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

	public Term(Context context, int c, int d, char v, Term[] pt) {
		super(context);
		coeff = c;
		denom = d;
		var = v;
		parenTerms = pt;
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
		text = "<tt>";//monospacing
		if (denom != 1) {
			text = text + "<u>";//underlining aka fraction bar
		}
		if (var == '1') {
			text = text + abs(coeff);//just a constant
		}
		else if (var == 'x') {
			if (abs(coeff) == 1)
				text = text + var;//dont show 1x or -1x
			else
				text = text + abs(coeff) + var;//for example 3x
		}
		else if (var == '(') {//parenTerm
			if (abs(coeff) != 1)			
				text = text + abs(coeff) + "(";//for example 3(x+1)/2
			else if (abs(coeff) == 1 && denom == 1)
				text = text + "(";//for example -(x+1)
			if (parenTerms[0].coeff < 0)
				text = text + "-" + parenTerms[0].text;//for example -x
			else
				text = text + parenTerms[0].text;//for example 2x
			for (int i = 1; i < parenTerms.length; i++) {
				if (parenTerms[i].coeff < 0)
					text = text + "-" + parenTerms[i].text;//add the rest of the terms
				else
					text = text + "+" + parenTerms[i].text;
			}
			if (abs(coeff) != 1) 
				text = text + ")";//close parentheses if present
			else if (abs(coeff) == 1 && denom == 1)
				text = text + ")";//close parentheses if present
		}
		if (denom != 1) {
			text = text + "<//u><br>";//finish fraction bar, new line
			if (var == '(') {
				text = text + "&nbsp;";//add a space for (
			if (abs(coeff) != 1)
				text = text + "&nbsp;";//add a space for coeff
			}			
			text = text + denom;//put denominator
		}
		if (coeff < 0)
			sign.setText(Html.fromHtml("<tt>-<//tt>"));//monospaced sign
		else
			sign.setText(Html.fromHtml("<tt>+<//tt>"));//monospaced sign
		super.setText(Html.fromHtml("<//tt>" + text));//end monospacing
	}
	
	public void reduce() {
		if (denom < 0) {//denom should always be considered positive
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
