package me.samlss.lighter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;

import me.samlss.lighter.interfaces.LighterInternalAction;
import me.samlss.lighter.interfaces.OnLighterListener;
import me.samlss.lighter.interfaces.OnLighterViewClickListener;
import me.samlss.lighter.parameter.LighterParameter;
import me.samlss.lighter.util.Preconditions;
import me.samlss.lighter.view.LighterView;

/**
 * @author SamLeung
 * @e-mail samlssplus@gmail.com
 * @github https://github.com/samlss
 * @description A tool for highlighting view.
 */
public class Lighter implements LighterInternalAction {
    private LighterInternalImpl mInternalImpl;
    private Lighter(ViewGroup rootView){
        mInternalImpl = new LighterInternalImpl(rootView);
    }

    private Lighter(Activity activity){
        mInternalImpl = new LighterInternalImpl(activity);
    }

    /**
     * Create a {@link Lighter} with an {@link Activity}. The highlighted view will fill the entire window. <br>
     * Will call to {@link android.view.WindowManager#addView(View, ViewGroup.LayoutParams)} to attached the {@link LighterView}
     * to the window.
     *
     * @param activity The activity that will be attach by the {@link LighterView}.
     * */
    public static Lighter with(Activity activity){
        Preconditions.checkNotNull(activity,
                "You can not show a highlight view on a null activity.");
        return new Lighter(activity);
    }

    /**
     * Create a {@link Lighter} with an {@link Activity} br></>
     * The highlighted view will fill the entire root view.
     *
     * @param rootView The root view that will be attach by the {@link LighterView}.
     * */
    public static Lighter with(ViewGroup rootView){
        Preconditions.checkNotNull(rootView,
                "You can not show a highlight view on a null root view.");
        return new Lighter(rootView);
    }

    /**
     * Add highlight parameter <br></>
     *
     * Can highlight multiple parameters simultaneously.
     *
     * @param lighterParameters The specified highlight parameter
     * */
    public Lighter addHighlight(LighterParameter...lighterParameters){
        mInternalImpl.addHighlight(lighterParameters);
        return this;
    }

    /**
     * Set background color of the highlighted view.
     *
     * @param color The background color, default is .
     * */
    public Lighter setBackgroundColor(int color){
        mInternalImpl.setBackgroundColor(color);
        return this;
    }

    /**
     * Set whether to automatically show the next highlighted view, the default is true.
     * If true, when click the {@link LighterView } & tip view, will show the next highlighted view automatically.
     * Otherwise you need to invoke {@link #next()} manually.
     * */
    public Lighter setAutoNext(boolean autoNext){
        mInternalImpl.setAutoNext(autoNext);
        return this;
    }

    /**
     * Set whether to intercept the click event of the {@link LighterView}. If it's true, it will not process the click event.
     * You need to manually handle the next event, and the view under the {@link LighterView} can be clicked.
     * And the {@link OnLighterViewClickListener} will not be invoked.
     * */
    public Lighter setIntercept(boolean intercept){
        mInternalImpl.setIntercept(intercept);
        return this;
    }

    /**
     * Set click listener of {@link LighterView} & tip view.
     * */
    public Lighter setOnClickListener(OnLighterViewClickListener clickListener){
        mInternalImpl.setOnClickListener(clickListener);
        return this;
    }

    /**
     * Set click listener of {@link LighterView} & tip view.
     * */
    public Lighter setOnLighterListener(OnLighterListener onLighterListener){
        mInternalImpl.setOnLighterListener(onLighterListener);
        return this;
    }

    @Override
    public boolean hasNext() {
        return mInternalImpl.hasNext();
    }

    @Override
    public void next() {
        mInternalImpl.next();
    }

    /**
     * Start to show highlight
     * */
    public void show(){
        mInternalImpl.show();
    }

    @Override
    public boolean isShowing() {
        return mInternalImpl.isShowing();
    }

    @Override
    public void dismiss() {
        mInternalImpl.dismiss();
    }
}
