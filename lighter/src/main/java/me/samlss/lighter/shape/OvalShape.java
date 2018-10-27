package me.samlss.lighter.shape;

import android.graphics.Path;
import android.graphics.RectF;

/**
 * @author SamLeung
 * @e-mail samlssplus@gmail.com
 * @github https://github.com/samlss
 * @description
 */
public class OvalShape extends LighterShape {

    /**
     * Construct a circle shape object.
     *
     * Will call {@link #OvalShape(float)} and pass the parameter is (15);
     * */
    public OvalShape() {
        super(15);
    }

    /**
     * Construct a oval shape object.
     * */
    public OvalShape(float blurRadius){
        super(blurRadius);
    }

    @Override
    public void setViewRect(RectF rect) {
        super.setViewRect(rect);

        if (!isViewRectEmpty()) {
            path.reset();
            path.addOval(rect, Path.Direction.CW);
        }
    }
}
