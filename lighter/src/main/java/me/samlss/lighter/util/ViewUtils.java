package me.samlss.lighter.util;

import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;
import android.util.Log;
import android.view.View;

import me.samlss.lighter.parameter.LighterParameter;
import me.samlss.lighter.view.LighterView;

/**
 * @author SamLeung
 * @e-mail samlssplus@gmail.com
 * @github https://github.com/samlss
 * @description The utils of view.
 */
public class ViewUtils {
    public static final int DEFAULT_HIGHLIGHT_VIEW_BG_COLOR = 0xE5000000;

    private ViewUtils(){
        throw new UnsupportedOperationException("Can not be instantiated.");
    }

    public static boolean isAttachedToWindow(View view) {
        if (view == null){
            return false;
        }
        if (Build.VERSION.SDK_INT >= 19) {
            return view.isAttachedToWindow();
        } else {
            return view.getWindowToken() != null;
        }
    }

    /**
     * Get the position of the view relative to the screen.
     * @param view The view.
     * */
    public static RectF getRectOnScreen(View view)
    {
        if (view == null){
            Log.e("ViewUtils", "Please pass non-null referParent and child.");
            return new RectF();
        }

        RectF result = new RectF();

        int[] pos = new int[2];
        view.getLocationOnScreen(pos);

        result.left   = pos[0];
        result.top    = pos[1];
        result.right  = result.left + view.getMeasuredWidth();
        result.bottom = result.top  + view.getMeasuredHeight();

        return result;
    }

    /**
     * Calculate the rect of the highlighted view
     * */
    public static void calculateHighlightedViewRect(LighterView lighterView, LighterParameter lighterParameter){
        if (lighterView == null
                || lighterParameter == null
                || lighterParameter.getHighlightedView() == null){
            return;
        }

        RectF highlightedViewRect = ViewUtils.getRectOnScreen(lighterParameter.getHighlightedView());

        if (highlightedViewRect == null
                || highlightedViewRect.isEmpty()){
            return;
        }

        View parent = (View) lighterView.getParent();
        int[] rootViewPos = new int[2];
        parent.getLocationOnScreen(rootViewPos);

        highlightedViewRect.left -= rootViewPos[0] ;
        highlightedViewRect.right -= rootViewPos[0];
        highlightedViewRect.top -= rootViewPos[1];
        highlightedViewRect.bottom -= rootViewPos[1];

        highlightedViewRect.left -= parent.getPaddingLeft();
        highlightedViewRect.right -= parent.getPaddingLeft();
        highlightedViewRect.top -= parent.getPaddingTop();
        highlightedViewRect.bottom -= parent.getPaddingTop();

        lighterParameter.setHighlightedViewRect(highlightedViewRect);
        lighterParameter.getLighterShape().setViewRect(new RectF(
                highlightedViewRect.left - lighterParameter.getShapeXOffset(),
                highlightedViewRect.top - lighterParameter.getShapeYOffset(),
                highlightedViewRect.right + lighterParameter.getShapeXOffset(),
                highlightedViewRect.bottom + lighterParameter.getShapeYOffset()
        ));
    }
}
