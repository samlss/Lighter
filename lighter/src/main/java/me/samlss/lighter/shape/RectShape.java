package me.samlss.lighter.shape;

import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;

/**
 * @author SamLeung
 * @e-mail samlssplus@gmail.com
 * @github https://github.com/samlss
 * @description Rectangular shaped frame, you can also round the corners.
 */
public class RectShape extends LighterShape{
    private float mXRadius;
    private float mYRadius;


    /**
     * Construct a rectangular shape object.
     *
     * Will call {@link #RectShape(float, float, float)} and pass the parameters are (5, 5, 15);
     * */
    public RectShape(){
        this(5, 5, 15);
    }

    /**
     * Construct a rectangular shape object.
     *
     * @param rx The x-radius of the oval used to round the corners
     * @param ry The y-radius of the oval used to round the corners
     * @param blurRadius The radius to extend the blur from the original mask.
     * */
    public RectShape(float rx, float ry, float blurRadius){
        super(blurRadius);
        mXRadius = rx;
        mYRadius = ry;
    }


    @Override
    public void setPaint(Paint paint){
        this.paint = paint;
    }

    @Override
    public void setViewRect(RectF rect) {
        super.setViewRect(rect);

        if (!isViewRectEmpty()) {
            path.reset();
            path.addRoundRect(highlightedViewRect, mXRadius, mYRadius, Path.Direction.CW);
        }
    }
}
