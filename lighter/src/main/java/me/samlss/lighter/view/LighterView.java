package me.samlss.lighter.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.graphics.Region;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import java.util.List;

import me.samlss.lighter.parameter.Direction;
import me.samlss.lighter.parameter.LighterParameter;
import me.samlss.lighter.parameter.MarginOffset;
import me.samlss.lighter.shape.LighterShape;
import me.samlss.lighter.util.ViewUtils;

/**
 * @author SamLeung
 * @e-mail samlssplus@gmail.com
 * @github https://github.com/samlss
 * @description The entire highlighted view,will be filled the parent view, like activity, or specify a parent view.
 */
public class LighterView extends FrameLayout {
    private List<LighterParameter> mLighterParameterList;
    private int mBgColor = -1;
    private int mInitWidth;
    private int mInitHeight;

    public LighterView(Context context) {
        this(context, null);
    }

    public LighterView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LighterView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public LighterView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        init();
    }

    private void init(){
        setLayerType(LAYER_TYPE_SOFTWARE, null);
        setWillNotDraw(false);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);

        //need the exactly width & height
        measureChildren(MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY),
                MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY));
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mLighterParameterList = null;
    }

//    @Override
//    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
//        super.onLayout(changed, left, top, right, bottom);
//
//        if (!changed
//                || (mInitWidth == 0 && mInitHeight == 0)
//                || mLighterParameterList == null
//                || mLighterParameterList.isEmpty()
//                || (getWidth() == mInitWidth && getHeight() == mInitHeight)){
//            return;
//        }
//
//        mInitWidth = getWidth();
//        mInitHeight = getHeight();
//
//        reLayout();
//    }

    public void reLayout(){
        for (int i = 0; i < getChildCount(); i++) {
            ViewUtils.calculateHighlightedViewRect(LighterView.this, mLighterParameterList.get(i));
            getChildAt(i).setLayoutParams(calculateLayoutParams(mInitWidth, mInitHeight, mLighterParameterList.get(i), getChildAt(i)));
        }
    }

    @Override
    public void setBackgroundColor(int color) {
        mBgColor = color;
    }

    /**
     * Add highlighted view, at the same time, multiple views can be highlighted
     *
     * @param lighterParameters The parameter of highlighted views.
     * */
    public void addHighlight(List<LighterParameter> lighterParameters){
        for (int i = 0; i < getChildCount(); i++){
            getChildAt(i).clearAnimation();
        }

        removeAllViews();

        if (lighterParameters == null
                || lighterParameters.isEmpty()){
            return;
        }

        mLighterParameterList = lighterParameters;

        for (LighterParameter lighterParameter : lighterParameters){
            //check paramters
            addTipView(lighterParameter);
        }
    }

    /**
     * Add highlighted view
     *
     * @param lighterParameter The parameter of highlighted views.
     * */
    public void addTipView(LighterParameter lighterParameter){
        if (lighterParameter == null){
            return;
        }

        //add tip view
        View tipView = lighterParameter.getTipView();
        LayoutParams layoutParams = calculateLayoutParams(mInitWidth, mInitHeight, lighterParameter, tipView);

        if (lighterParameter.getTipViewDisplayAnimation() != null){
            tipView.startAnimation(lighterParameter.getTipViewDisplayAnimation());
        }

        addView(tipView, layoutParams);
    }


    private LayoutParams calculateLayoutParams(int width, int height, LighterParameter lighterParameter, View tipView){
        RectF highlightedViewRect = lighterParameter.getHighlightedViewRect();

        MarginOffset marginOffset = lighterParameter.getTipViewRelativeMarginOffset();
        LayoutParams layoutParams = (LayoutParams) tipView.getLayoutParams();
        if (layoutParams == null){
            layoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }

        if (highlightedViewRect == null
                || highlightedViewRect.isEmpty()){
            return layoutParams;
        }

        switch (lighterParameter.getTipViewRelativeDirection()){
            default:
            case Direction.LEFT:
                layoutParams.topMargin  = (int) highlightedViewRect.top + marginOffset.getTopOffset();
                layoutParams.rightMargin = (int) (width - highlightedViewRect.right  + marginOffset.getRightOffset() + highlightedViewRect.width());
                break;

            case Direction.RIGHT:
                layoutParams.topMargin  = (int) highlightedViewRect.top + marginOffset.getTopOffset();
                layoutParams.leftMargin = (int) (highlightedViewRect.right + marginOffset.getLeftOffset());
                break;

            case Direction.TOP:
                if (highlightedViewRect.left > width / 2){ //on the right
                    layoutParams.rightMargin  = (int) (width - highlightedViewRect.right  + marginOffset.getRightOffset());
                }else{ //on the left
                    layoutParams.leftMargin  = (int) (highlightedViewRect.left + marginOffset.getLeftOffset());
                }
                layoutParams.bottomMargin = (int) (height - highlightedViewRect.bottom +  highlightedViewRect.height() + marginOffset.getBottomOffset());
                break;

            case Direction.BOTTOM:
                if (highlightedViewRect.left > width / 2){ //on the right
                    layoutParams.rightMargin  = (int) (width - highlightedViewRect.right  + marginOffset.getRightOffset());
                }else{ //on the left
                    layoutParams.leftMargin  = (int) (highlightedViewRect.left + marginOffset.getLeftOffset());
                }
                layoutParams.topMargin = (int) (highlightedViewRect.bottom + marginOffset.getTopOffset());
                break;
        }

        if(layoutParams.rightMargin != 0){
            layoutParams.gravity = Gravity.RIGHT;
        }else {
            layoutParams.gravity = Gravity.LEFT;
        }

        if(layoutParams.bottomMargin != 0){
            layoutParams.gravity |= Gravity.BOTTOM;
        }else {
            layoutParams.gravity |= Gravity.TOP;
        }

        return layoutParams;
    }

    /**
     * Check if the shape is empty.
     * */
    private boolean isShapeEmpty(LighterShape lighterShape){
        return lighterShape == null
                || lighterShape.getViewRect() == null
                || lighterShape.getViewRect().isEmpty();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (mBgColor == -1){
            mBgColor = ViewUtils.DEFAULT_HIGHLIGHT_VIEW_BG_COLOR;
        }

        canvas.save();

        //firstly, clip the rects of all the highlighted views.
        if (mLighterParameterList != null && !mLighterParameterList.isEmpty()) {
            for (LighterParameter lighterParameter : mLighterParameterList) {

                if (lighterParameter.getHighlightedView() == null
                        || lighterParameter.getLighterShape() == null
                        || lighterParameter.getLighterShape().getViewRect() == null
                        || lighterParameter.getLighterShape().getViewRect().isEmpty()){
                    continue;
                }
                canvas.clipPath(lighterParameter.getLighterShape().getShapePath(), Region.Op.DIFFERENCE);
            }
        }

        //then, draw the bg color
        canvas.drawColor(mBgColor);

        //finally, draw the rects of all the highlighted views.
        if (mLighterParameterList != null && !mLighterParameterList.isEmpty()) {
            for (LighterParameter lighterParameter : mLighterParameterList) {

                if (isShapeEmpty(lighterParameter.getLighterShape())){
                    continue;
                }

                lighterParameter.getLighterShape().onDraw(canvas);
            }
        }

        canvas.restore();
    }

    public void setInitHeight(int initHeight) {
        this.mInitHeight = initHeight;
    }

    public void setInitWidth(int initWidth) {
        this.mInitWidth = initWidth;
    }
}
