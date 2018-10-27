package me.samlss.lighter_demo;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import me.samlss.lighter.Lighter;
import me.samlss.lighter.parameter.Direction;
import me.samlss.lighter.parameter.LighterParameter;
import me.samlss.lighter.parameter.MarginOffset;
import me.samlss.lighter.shape.RectShape;

/**
 * @author SamLeung
 * @e-mail samlssplus@gmail.com
 * @github https://github.com/samlss
 * @description Demo for {@link AlertDialog}
 */
public class CustomDialog extends AlertDialog {
    private View mView;
    protected CustomDialog(@NonNull Context context) {
        super(context);

        if (context instanceof Activity){
            setOwnerActivity((Activity) context);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mView = getLayoutInflater().inflate(R.layout.layout_dialog, null);
        setContentView(mView);
    }

    @Override
    public void show() {
        super.show();

        int screenWidth = getOwnerActivity().getWindowManager().getDefaultDisplay().getWidth();
        int screenHeight = getOwnerActivity().getWindowManager().getDefaultDisplay().getHeight();

        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
        layoutParams.width = (int) (screenWidth * 0.9f);
        layoutParams.height = (int) (screenHeight * 0.8f);
        getWindow().setAttributes(layoutParams);

        showGuide(mView);
    }

    private void showGuide(View view){
        Lighter.with((ViewGroup) view)
                .addHighlight(
                        //Show two at a time
                        new LighterParameter.Builder()
                                .setHighlightedViewId(R.id.vp_btn_1)
                                .setTipLayoutId(R.layout.layout_tip_1)
                                .setLighterShape(new RectShape(5, 5, 30))
                                .setTipViewRelativeDirection(Direction.BOTTOM)
                                .setTipViewRelativeOffset(new MarginOffset(150, 0, 30, 0))
                                .build(),

                        new LighterParameter.Builder()
                                .setHighlightedViewId(R.id.vp_btn_2)
                                .setTipLayoutId(R.layout.layout_tip_2)
                                .setLighterShape(new RectShape(5, 5, 30))
                                .setTipViewRelativeDirection(Direction.TOP)
                                .setTipViewRelativeOffset(new MarginOffset(-400, 0, 0, 30))
                                .build())
                .show();
    }
}
