package me.samlss.lighter_demo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import me.samlss.lighter.Lighter;
import me.samlss.lighter.interfaces.OnLighterViewClickListener;
import me.samlss.lighter.parameter.Direction;
import me.samlss.lighter.parameter.LighterParameter;
import me.samlss.lighter.parameter.MarginOffset;
import me.samlss.lighter.shape.OvalShape;

/**
 * @author SamLeung
 * @e-mail samlssplus@gmail.com
 * @github https://github.com/samlss
 * @description Demo for {@link Fragment}
 */
public class LighterFragment extends Fragment {
    private Lighter mLighter;
    private Button mButton1;
    private Button mButton2;
    private Button mButton3;
    private Button mButton4;
    private Button mButton5;

    private List<View> mTipViewList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_viewpager_1, container, false);
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mTipViewList.clear();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mButton1 = view.findViewById(R.id.vp_btn_1);
        mButton2 = view.findViewById(R.id.vp_btn_2);
        mButton3 = view.findViewById(R.id.vp_btn_3);
        mButton4 = view.findViewById(R.id.vp_btn_4);
        mButton5 = view.findViewById(R.id.vp_btn_5);

        mButton1.setOnClickListener(mClickListener);
        mButton2.setOnClickListener(mClickListener);
        mButton3.setOnClickListener(mClickListener);
        mButton4.setOnClickListener(mClickListener);
        mButton5.setOnClickListener(mClickListener);

        View tipView1 = getLayoutInflater().inflate(R.layout.layout_tip_1, null);
        View tipView2 = getLayoutInflater().inflate(R.layout.layout_tip_7, null);
        View tipView3 = getLayoutInflater().inflate(R.layout.layout_tip_2, null);
        View tipView4 = getLayoutInflater().inflate(R.layout.layout_tip_3, null);
        View tipView5 = getLayoutInflater().inflate(R.layout.layout_tip_4, null);

        tipView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "You click tip view 1", Toast.LENGTH_SHORT).show();
                showNext();
            }
        });

        tipView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "You click tip view 2", Toast.LENGTH_SHORT).show();
                showNext();
            }
        });

        tipView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "You click tip view 3", Toast.LENGTH_SHORT).show();
                showNext();
            }
        });

        tipView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "You click tip view 4", Toast.LENGTH_SHORT).show();
                showNext();
            }
        });

        tipView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "You click tip view 5", Toast.LENGTH_SHORT).show();
                showNext();
            }
        });


        mTipViewList.add(tipView1);
        mTipViewList.add(tipView2);
        mTipViewList.add(tipView3);
        mTipViewList.add(tipView4);
        mTipViewList.add(tipView5);
        showGuide();
    }

    private void showGuide(){
        mLighter = Lighter.with((ViewGroup) getView())
                .setIntercept(true)
                //The callback interface will not be called if intercept is true.
                //If intercept is true, you need to call Lighter.next() by yourself
                .setOnClickListener(new OnLighterViewClickListener() {
                    @Override
                    public void onClick(View view) {
//                        Toast.makeText(getActivity(), "No show...", Toast.LENGTH_SHORT).show();
                    }
                })
                .addHighlight(new LighterParameter.Builder()
                        .setHighlightedViewId(R.id.vp_btn_1)
                        .setLighterShape(new OvalShape())
                        .setTipView(mTipViewList.get(0))
                        .setTipViewDisplayAnimation(LighterHelper.getScaleAnimation())
                        .setTipViewRelativeDirection(Direction.RIGHT)
                        .setTipViewRelativeOffset(new MarginOffset(30, 0, 80, 0))
                        .build())
                .addHighlight(new LighterParameter.Builder()
                        .setHighlightedViewId(R.id.vp_btn_2)
                        .setLighterShape(new OvalShape())
                        .setTipView(mTipViewList.get(1))
                        .setTipViewRelativeDirection(Direction.LEFT)
                        .setTipViewDisplayAnimation(LighterHelper.getScaleAnimation())
                        .setTipViewRelativeOffset(new MarginOffset(50, 0, 100, 0))
                        .build())
                .addHighlight(new LighterParameter.Builder()
                        .setHighlightedViewId(R.id.vp_btn_3)
                        .setLighterShape(new OvalShape())
                        .setTipView(mTipViewList.get(2))
                        .setTipViewRelativeDirection(Direction.TOP)
                        .setTipViewDisplayAnimation(LighterHelper.getScaleAnimation())
                        .setTipViewRelativeOffset(new MarginOffset(-400, 0, 0, 30))
                        .build())
                .addHighlight(new LighterParameter.Builder()
                        .setHighlightedViewId(R.id.vp_btn_4)
                        .setLighterShape(new OvalShape())
                        .setTipView(mTipViewList.get(3))
                        .setTipViewRelativeDirection(Direction.TOP)
                        .setTipViewRelativeOffset(new MarginOffset(80, 0, 0, 20))
                        .setTipViewDisplayAnimation(LighterHelper.getScaleAnimation())
                        .build())
                .addHighlight(new LighterParameter.Builder()
                        .setHighlightedViewId(R.id.vp_btn_5)
                        .setLighterShape(new OvalShape())
                        .setTipView(mTipViewList.get(4))
                        .setTipViewRelativeDirection(Direction.TOP)
                        .setTipViewRelativeOffset(new MarginOffset(0, 100, 0, 20))
                        .setTipViewDisplayAnimation(LighterHelper.getScaleAnimation())
                        .build());

        mLighter.show();
    }

    private void showNext(){
        if (mLighter != null){
            mLighter.next();
        }
    }


    private View.OnClickListener mClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.vp_btn_1:
                    showNext();
                    Toast.makeText(getActivity(), "You click button 1", Toast.LENGTH_SHORT).show();
                    break;

                case R.id.vp_btn_2:
                    showNext();
                    Toast.makeText(getActivity(), "You click button 2", Toast.LENGTH_SHORT).show();
                    break;

                case R.id.vp_btn_3:
                    showNext();
                    Toast.makeText(getActivity(), "You click button 3", Toast.LENGTH_SHORT).show();
                    break;

                case R.id.vp_btn_4:
                    showNext();
                    Toast.makeText(getActivity(), "You click button 4", Toast.LENGTH_SHORT).show();
                    break;

                case R.id.vp_btn_5:
                    showNext();
                    Toast.makeText(getActivity(), "You click button 5", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };
}
