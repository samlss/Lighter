package me.samlss.lighter.shape;

import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;

/**
 * @author SamLeung
 * @e-mail samlssplus@gmail.com
 * @github https://github.com/samlss
 * @description
 */
public class CircleShape extends LighterShape {
    /**
     * Construct a circle shape object.
     *
     * Will call {@link #CircleShape(float)} and pass the parameter is (15);
     * */
    public CircleShape(){
        super(15);
    }

    /**
     * Construct a circle shape object.
     * */
    public CircleShape(float blurRadius){
        super(blurRadius);
    }

    @Override
    public void setViewRect(RectF rect) {
        super.setViewRect(rect);

        if (!isViewRectEmpty()) {
            path.reset();
            path.addCircle(highlightedViewRect.centerX(), highlightedViewRect.centerY(),
                    Math.max(highlightedViewRect.width(), highlightedViewRect.height()) / 2, Path.Direction.CW);
        }
    }
}
