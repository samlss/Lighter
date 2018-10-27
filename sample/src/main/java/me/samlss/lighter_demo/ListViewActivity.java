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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import me.samlss.lighter.Lighter;
import me.samlss.lighter.parameter.Direction;
import me.samlss.lighter.parameter.LighterParameter;
import me.samlss.lighter.shape.OvalShape;

/**
 * @author SamLeung
 * @e-mail samlssplus@gmail.com
 * @github https://github.com/samlss
 * @description Demo for {@link android.widget.ListView}
 */
public class ListViewActivity extends AppCompatActivity {
    private ListView mListView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimary));
        }

        setContentView(R.layout.activity_listview);

        LighterHelper.setupToolBarBackAction(this, (Toolbar) findViewById(R.id.toolbar));
        initListViews();
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();

        Toast.makeText(this, "Click to show highlight(点击显示高亮)~", Toast.LENGTH_LONG).show();
    }

    private void initListViews(){
        mListView = findViewById(R.id.listview);
        mListView.setDividerHeight(20);
        mListView.setAdapter(new Adapter());
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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

        OvalShape ovalShape = new OvalShape();
        ovalShape.setPaint(LighterHelper.getDashPaint());

        Lighter.with(this)
                .addHighlight(new LighterParameter.Builder()
                        .setHighlightedView(highlightedView)
                        .setTipLayoutId(R.layout.layout_tip_5)
                        .setTipViewRelativeDirection(Direction.TOP)
                        .setLighterShape(ovalShape)
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
