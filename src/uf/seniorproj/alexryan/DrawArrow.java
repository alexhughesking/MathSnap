/*Class for testing arrow drawing
 * May or may not remain
 */

package uf.seniorproj.alexryan;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

public class DrawArrow extends View {
	
	int x;
	int y;
	int center;
	public DrawArrow(Context context, int center) {
		super(context);
		this.center = center;
		// TODO Auto-generated constructor stub
	}
	
	protected void onDraw(Canvas canvas) {
		Paint paint = new Paint();
		canvas.drawLine(center, 0, center, 300, paint);
	}

}
