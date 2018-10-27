package me.samlss.lighter.interfaces;

/**
 * @author SamLeung
 * @e-mail samlssplus@gmail.com
 * @github https://github.com/samlss
 * @description To listen the action of {@link me.samlss.lighter.Lighter}
 */
public interface OnLighterListener  {
    /**
     * When the highlight is displayed, this method will be called back.
     *
     * @param index index of the number of highlights you configured.
     * */
    void onShow(int index);

    /**
     * Call back this method when the all highlights has been displayed.
     * */
    void onDismiss();
}
