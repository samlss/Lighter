package me.samlss.lighter_demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startNormalLayout(View view) {
        startActivity(new Intent(this, RelativeLayoutActivity.class));
    }

    public void startRecyclerView(View view) {
        startActivity(new Intent(this, RecyclerViewActivity.class));
    }

    public void startListView(View view) {
        startActivity(new Intent(this, ListViewActivity.class));
    }

    public void startScrollView(View view) {
        startActivity(new Intent(this, ScrollViewActivity.class));
    }

    public void startGridView(View view) {
        startActivity(new Intent(this, GridViewActivity.class));
    }

    public void startViewPager(View view) {
        startActivity(new Intent(this, ViewPagerActivity.class));
    }

    public void startDialog(View view) {
        startActivity(new Intent(this, DialogActivity.class));
    }

    public void startFragment(View view) {
        startActivity(new Intent(this, FragmentActivity.class));
    }
}
