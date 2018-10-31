package me.samlss.lighter_demo;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.view.animation.BounceInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Toast;

import me.samlss.lighter.Lighter;
import me.samlss.lighter.interfaces.OnLighterListener;
import me.samlss.lighter.parameter.Direction;
import me.samlss.lighter.parameter.LighterParameter;
import me.samlss.lighter.parameter.MarginOffset;
import me.samlss.lighter.shape.CircleShape;
import me.samlss.lighter.shape.OvalShape;
import me.samlss.lighter.shape.RectShape;

/**
 * @author SamLeung
 * @e-mail samlssplus@gmail.com
 * @github https://github.com/samlss
 * @description Highlighted demo for normal layout like {@link android.widget.RelativeLayout}...
 */
public class RelativeLayoutActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal_layout);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimary));
        }

        showGuide();
    }

    private void showGuide(){
        TranslateAnimation translateAnimation = new TranslateAnimation(-500, 0, 0, 0);
        translateAnimation.setDuration(500);
        translateAnimation.setInterpolator(new BounceInterpolator());

        CircleShape circleShape = new CircleShape(25);
        circleShape.setPaint(LighterHelper.getDashPaint()); //set custom paint


        RectShape rectShape = new RectShape();
        rectShape.setPaint(LighterHelper.getDiscretePaint());

        Lighter.with(this)
                .setBackgroundColor(0xB3000000)
                .setOnLighterListener(new OnLighterListener() {
                    @Override
                    public void onShow(int index) {
                        Toast.makeText(getApplicationContext(), "正在显示第" + (index+1) + "高亮", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onDismiss() {
                        Toast.makeText(getApplicationContext(), "高亮已全部显示完毕", Toast.LENGTH_SHORT).show();
                    }
                })
                .addHighlight(new LighterParameter.Builder()
                        .setHighlightedViewId(R.id.iv_head_photo)
                        .setTipLayoutId(R.layout.layout_tip_1)
                        .setLighterShape(circleShape)
                        .setTipViewRelativeDirection(Direction.RIGHT)
                        .setTipViewDisplayAnimation(LighterHelper.getScaleAnimation())
                        .setTipViewRelativeOffset(new MarginOffset(30, 0, 80, 0))
                        .build())
                .addHighlight(new LighterParameter.Builder()
                        .setHighlightedViewId(R.id.layout_balance)
                        .setTipLayoutId(R.layout.layout_tip_2)
                        .setLighterShape(rectShape)
                        .setTipViewRelativeDirection(Direction.TOP)
                        .setShapeXOffset(10)
                        .setShapeYOffset(10)
                        .setTipViewDisplayAnimation(translateAnimation)
                        .setTipViewRelativeOffset(new MarginOffset(0, 10, 0, 20))
                        .build())
                .addHighlight(new LighterParameter.Builder()
                        .setHighlightedViewId(R.id.btn_highlight2)
                        .setTipView(LighterHelper.createCommonTipView(this, R.drawable.icon_tip_4, "向左移动"))
                        .setLighterShape(rectShape)
                        .setTipViewRelativeDirection(Direction.LEFT)
                        .setTipViewDisplayAnimation(LighterHelper.getScaleAnimation())
                        .setTipViewRelativeOffset(new MarginOffset(0, 20, 0, 0))
                        .build())
                .addHighlight(new LighterParameter.Builder()
                                .setHighlightedViewId(R.id.btn_highlight1)
                                .setTipLayoutId(R.layout.layout_tip_3)
                                .setLighterShape(new RectShape(0, 0, 25))
                                .setTipViewRelativeDirection(Direction.TOP)
                                .setTipViewDisplayAnimation(LighterHelper.getScaleAnimation())
                                .setTipViewRelativeOffset(new MarginOffset(100, 10, 0, 20))
                                .build(),

                        new LighterParameter.Builder()
                                .setHighlightedViewId(R.id.btn_highlight3)
                                .setTipLayoutId(R.layout.layout_tip_1)
                                .setLighterShape(new OvalShape())
                                .setTipViewRelativeDirection(Direction.BOTTOM)
                                .setTipViewDisplayAnimation(LighterHelper.getScaleAnimation())
                                .setTipViewRelativeOffset(new MarginOffset(300, 0, 0, 0))
                                .build())
                .show();
    }
}
