package me.samlss.lighter_demo;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import me.samlss.lighter.Lighter;
import me.samlss.lighter.parameter.Direction;
import me.samlss.lighter.parameter.LighterParameter;
import me.samlss.lighter.parameter.MarginOffset;
import me.samlss.lighter.shape.RectShape;

/**
 * @author SamLeung
 * @e-mail samlssplus@gmail.com
 * @github https://github.com/samlss
 * @description Demo for {@link android.widget.GridView}
 */
public class GridViewActivity extends AppCompatActivity {
    private GridView mGridView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimary));
        }

        setContentView(R.layout.activity_gridview);
        LighterHelper.setupToolBarBackAction(this, (Toolbar) findViewById(R.id.toolbar));

        initGridView();
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();

        Toast.makeText(this, "Click to show highlight(点击显示高亮)~", Toast.LENGTH_LONG).show();
    }

    private void initGridView(){
        mGridView = findViewById(R.id.gridview);
        mGridView.setNumColumns(3);
        mGridView.setAdapter(new Adapter());
        mGridView.setVerticalSpacing(10);
        mGridView.setHorizontalSpacing(10);

        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showGuide(view);
            }
        });
    }

    private void showGuide(View highlightedView){
        if (highlightedView == null){
            return;
        }

        RectShape rectShape = new RectShape();
        rectShape.setPaint(LighterHelper.getDashPaint());

        Lighter.with(this)
                .addHighlight(new LighterParameter.Builder()
                        .setHighlightedView(highlightedView)
                        .setTipLayoutId(R.layout.layout_tip_6)
                        .setTipViewRelativeDirection(Direction.TOP)
                        .setLighterShape(rectShape)
                        .setShapeXOffset(10)
                        .setShapeYOffset(10)
                        .setTipViewRelativeOffset(new MarginOffset(0, 0, 0, 20))
                        .build())
                .show();
    }


    private class Adapter extends BaseAdapter{

        @Override
        public int getCount() {
            return LighterHelper.sPictureList.size();
        }

        @Override
        public Object getItem(int position) {
            return LighterHelper.sPictureList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
           ViewHolder viewHolder;

            if (convertView == null){
                convertView = getLayoutInflater().inflate(R.layout.layout_item_list, parent, false);
                viewHolder = new ViewHolder();
                viewHolder.imageView = convertView.findViewById(R.id.iv_image);
                convertView.setTag(viewHolder);
            }

            viewHolder = (ViewHolder)convertView.getTag();
            viewHolder.imageView.setImageResource(LighterHelper.sPictureList.get(position));
            return convertView;
        }
    }

    private class ViewHolder{
        ImageView imageView;
    }
}
