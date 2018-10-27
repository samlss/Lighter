package me.samlss.lighter.interfaces;

import me.samlss.lighter.Lighter;
import me.samlss.lighter.LighterInternalImpl;

/**
 * @author SamLeung
 * @e-mail samlssplus@gmail.com
 * @github https://github.com/samlss
 * @description The action of connecting of {@link Lighter} & {@link LighterInternalImpl}
 */
public interface LighterInternalAction {
    /**
     * Check if there is any next highlight
     * */
    boolean hasNext();

    /**
     * Make the next highlight.
     * */
    void next();

    /**
     * Start to show highlight.
     * */
    void show();

    /**
     * Check if it is showing
     * */
    boolean isShowing();

    /**
     * Dismiss highlight.
     * */
    void dismiss();
}
