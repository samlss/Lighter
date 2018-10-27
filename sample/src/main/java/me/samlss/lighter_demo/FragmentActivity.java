package me.samlss.lighter_demo;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

/**
 * @author SamLeung
 * @e-mail samlssplus@gmail.com
 * @github https://github.com/samlss
 * @description Demo for {@link android.support.v4.app.Fragment}
 */
public class FragmentActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimary));
        }

        setContentView(R.layout.activity_fragment);
        LighterHelper.setupToolBarBackAction(this, (Toolbar) findViewById(R.id.toolbar));

        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_content, new LighterFragment())
                .commit();
    }
}
