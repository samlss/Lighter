package me.samlss.lighter_demo;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Toast;

import me.samlss.lighter.Lighter;
import me.samlss.lighter.parameter.Direction;
import me.samlss.lighter.parameter.LighterParameter;

/**
 * @author SamLeung
 * @e-mail samlssplus@gmail.com
 * @github https://github.com/samlss
 * @description Demo for {@link android.support.v7.widget.RecyclerView}
 */
public class RecyclerViewActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimary));
        }

        setContentView(R.layout.activity_recyclerview);
        LighterHelper.setupToolBarBackAction(this, (Toolbar) findViewById(R.id.toolbar));
        initListView();
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();

        Toast.makeText(this, "Click to show highlight(点击显示高亮)~", Toast.LENGTH_LONG).show();
    }

    private void initListView(){
        mRecyclerView = findViewById(R.id.recyclerview);
        Adapter adapter = new Adapter();
        adapter.setItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showGuide(view);
            }
        });
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    }

    private void showGuide(View highlightedView){
        if (highlightedView == null){
            return;
        }

        Lighter.with((ViewGroup) findViewById(R.id.root))
                .addHighlight(new LighterParameter.Builder()
                        .setHighlightedView(highlightedView)
                        .setTipLayoutId(R.layout.layout_tip_5)
                        .setTipViewRelativeDirection(Direction.TOP)
                        .build())
                .show();
    }

    private class Adapter extends RecyclerView.Adapter<ViewHolder>{
        private AdapterView.OnItemClickListener itemClickListener;
        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view = getLayoutInflater().inflate(R.layout.layout_item_list, viewGroup, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
            viewHolder.imageView.setImageResource(LighterHelper.sPictureList.get(i));
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (itemClickListener != null){
                        itemClickListener.onItemClick(null, v, i, v.getId());
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return LighterHelper.sPictureList.size();
        }

        public void setItemClickListener(AdapterView.OnItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }
    }

    private class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.iv_image);
        }
    }
}
