package me.samlss.lighter.interfaces;

import android.view.View;

/**
 * @author SamLeung
 * @e-mail samlssplus@gmail.com
 * @github https://github.com/samlss
 * @description The highlighted views click(include {@link me.samlss.lighter.view.LighterView}, TipView)) listener.
 */
public interface OnLighterViewClickListener {
    /**
     * On lighter view {@link me.samlss.lighter.view.LighterView} click.
     * */
    void onClick(View view);
}
