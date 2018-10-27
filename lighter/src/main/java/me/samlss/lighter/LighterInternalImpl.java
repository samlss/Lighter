package me.samlss.lighter;

import android.app.Activity;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import me.samlss.lighter.interfaces.LighterInternalAction;
import me.samlss.lighter.interfaces.OnLighterListener;
import me.samlss.lighter.interfaces.OnLighterViewClickListener;
import me.samlss.lighter.parameter.LighterParameter;
import me.samlss.lighter.parameter.MarginOffset;
import me.samlss.lighter.shape.RectShape;
import me.samlss.lighter.util.Preconditions;
import me.samlss.lighter.util.ViewUtils;
import me.samlss.lighter.view.LighterView;

/**
 * @author SamLeung
 * @e-mail samlssplus@gmail.com
 * @github https://github.com/samlss
 * @description To handle all the function for {@link Lighter}
 */
public class LighterInternalImpl implements LighterInternalAction {
    private List<List<LighterParameter>> mHighlightedParameterList = new ArrayList<>();
    private LighterView mLighterView;
    private ViewGroup mRootView;
    private boolean isReleased = false;
    private boolean isAutoNext = true;
    private boolean isShowing = false;
    private boolean intercept = false;

    private int mShowIndex;
    private OnLighterListener mOnLighterListener;
    private OnLighterViewClickListener mOutSideLighterClickListener;
    public LighterInternalImpl(final Activity activity) {
        mLighterView = new LighterView(activity);
        mRootView = activity.findViewById(android.R.id.content);

        init();
    }

    public LighterInternalImpl(ViewGroup rootView){
        mRootView = rootView;
        mLighterView = new LighterView(rootView.getContext());

        init();
    }

    private void init() {
        mRootView.addView(mLighterView,
                new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
    }

    public void addHighlight(LighterParameter... lighterParameters) {
        if (isReleased){
            return;
        }

        if (lighterParameters != null
                && lighterParameters.length > 0) {
            mHighlightedParameterList.add(Arrays.asList(lighterParameters));
        }
    }

    @Override
    public boolean isShowing() {
        return isShowing;
    }

    @Override
    public boolean hasNext() {
        if (isReleased){
            return false;
        }

        return !mHighlightedParameterList.isEmpty();
    }

    @Override
    public void next() {
        if (isReleased){
            return;
        }

        //To ensure the root view has attached to window
        if (!ViewUtils.isAttachedToWindow(mRootView)) {
            show();
            return;
        }

        if (!hasNext()){
            dismiss();
        }else{
            isShowing = true;
            if (mOnLighterListener != null){
                mOnLighterListener.onShow(mShowIndex);
            }

            mShowIndex++;
            List<LighterParameter> lighterParameters = mHighlightedParameterList.get(0);
            for (LighterParameter lighterParameter : lighterParameters){
                checkLighterParameter(lighterParameter);
            }

            mLighterView.setInitWidth(mRootView.getWidth() - mRootView.getPaddingLeft() - mRootView.getPaddingRight());
            mLighterView.setInitHeight(mRootView.getHeight() - mRootView.getPaddingTop() - mRootView.getPaddingBottom());

            mLighterView.addHighlight(lighterParameters);
            mHighlightedParameterList.remove(0);
        }
    }

    /**
     * Check parameter.
     * */
    private void checkLighterParameter(LighterParameter lighterParameter){
        if (lighterParameter.getLighterShape() == null){
            lighterParameter.setLighterShape(new RectShape());
        }

        if (lighterParameter.getHighlightedView() == null){
            lighterParameter.setHighlightedView(mRootView.findViewById(lighterParameter.getHighlightedViewId()));
        }

        if (lighterParameter.getTipView() == null){
            lighterParameter.setTipView(LayoutInflater.from(mLighterView.getContext()).inflate(lighterParameter.getTipLayoutId(),
                    mLighterView, false));
        }

        if (lighterParameter.getHighlightedView() == null){
            Preconditions.checkNotNull(lighterParameter.getHighlightedView(), "Please pass a highlighted view or an id of highlighted.");
        }

        if (lighterParameter.getTipView() == null){
            Preconditions.checkNotNull(lighterParameter.getTipView(), "Please pass a tip view or a layout id of tip view.");
        }

        if (lighterParameter.getTipViewRelativeMarginOffset() == null){
            lighterParameter.setTipViewRelativeMarginOffset(new MarginOffset()); //use empty offset.
        }

        ViewUtils.calculateHighlightedViewRect(mLighterView, lighterParameter);
    }

    /**
     * Release all when all specified highlights are completed.
     * */
    private void onRelease(){
        if (isReleased){
            return;
        }

        isReleased = true;

        mRootView.removeView(mLighterView);
        mLighterView.removeAllViews();

        mHighlightedParameterList.clear();
        mHighlightedParameterList = null;

        mLighterViewClickListener = null;
        mOnLighterListener = null;
        mRootView = null;
        mLighterView = null;
    }

    @Override
    public void show() {
        if (isReleased){
            return;
        }

        if (!intercept) {
            mLighterView.setOnClickListener(mLighterViewClickListener);
        }

        //To ensure the root view has attached to window
        if ( ViewUtils.isAttachedToWindow(mRootView)) {
            mShowIndex = 0;
            next();
        }else{
            mRootView.getViewTreeObserver().addOnGlobalLayoutListener(mGlobalLayoutListener);
        }
    }

    @Override
    public void dismiss() {
        if (mOnLighterListener != null){
            mOnLighterListener.onDismiss();
        }

        isShowing = false;
        onRelease();
    }

    /**
     * When you call the {@link #show()} method, maybe the {@link #mRootView} has not been initialized yet and the highlighted view property gets failed.
     * Therefore, before {@link #show()}, will invoke {@link ViewUtils#isAttachedToWindow(View)} to check if the {@link #mRootView} has attached to window,
     * if yes, show directly, otherwise will add {@link ViewTreeObserver#addOnGlobalLayoutListener(ViewTreeObserver.OnGlobalLayoutListener)}.
     * */
    private ViewTreeObserver.OnGlobalLayoutListener mGlobalLayoutListener = new ViewTreeObserver.OnGlobalLayoutListener() {
        @Override
        public void onGlobalLayout() {
            /**
             * Guaranteed to be called only once.
             * */
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                mRootView.getViewTreeObserver().removeOnGlobalLayoutListener(mGlobalLayoutListener);
            }

            show();
        }
    };

    public void setBackgroundColor(int color) {
        if (isReleased){
            return;
        }

        mLighterView.setBackgroundColor(color);
    }

    private View.OnClickListener mLighterViewClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (mOutSideLighterClickListener != null){
                mOutSideLighterClickListener.onClick(v);
            }

            if (isAutoNext) {
                next();
            }
        }
    };

    public void setAutoNext(boolean autoNext) {
        this.isAutoNext = autoNext;
    }

    public void setIntercept(boolean intercept) {
        this.intercept = intercept;
    }

    public void setOnClickListener(OnLighterViewClickListener clickListener) {
        mOutSideLighterClickListener = clickListener;
    }


    public void setOnLighterListener(OnLighterListener lighterListener) {
        mOnLighterListener = lighterListener;
    }
}
