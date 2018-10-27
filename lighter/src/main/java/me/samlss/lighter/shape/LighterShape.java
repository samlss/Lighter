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
 * @description The shape of highlighted view.
 */
public abstract class LighterShape {
    protected RectF highlightedViewRect;
    protected Path path;
    protected Paint paint;

    protected LighterShape(float blurRadius){
        path = new Path();
        paint = new Paint();

        paint.setDither(true);
        paint.setAntiAlias(true);
        paint.setColor(Color.WHITE);

        if (blurRadius > 0) {
            paint.setMaskFilter(new BlurMaskFilter(blurRadius, BlurMaskFilter.Blur.SOLID));
        }
    }

    /**
     * Draw the highlighted view.
     * */
    public void onDraw(Canvas canvas){
        if (path != null
                && paint != null) {
            canvas.drawPath(path, paint);
        }
    }

    /**
     * Set {@link RectF} the highlighted view.
     * */
    public void setViewRect(RectF rect){
        highlightedViewRect = rect;
    }

    /**
     * Get {@link RectF} the highlighted view.
     * */
    public RectF getViewRect() {
        return highlightedViewRect;
    }

    /**
     *  Returns true if the view rect is empty (left >= right or top >= bottom)
     * */
    public boolean isViewRectEmpty(){
        return highlightedViewRect == null
                || highlightedViewRect.isEmpty();
    }

    /**
     * Set a custom paint when draw this shape.
     * */
    public void setPaint(Paint paint){
        this.paint = paint;
    }

    /**
     * Get shape path.
     * */
    public Path getShapePath(){
        return path;
    }
}
