package me.samlss.lighter.parameter;

/**
 * @author SamLeung
 * @e-mail samlssplus@gmail.com
 * @github https://github.com/samlss
 * @description The tip view's margin offset that relative of highlighted view.
 */
public class MarginOffset {
    private int leftOffset;
    private int rightOffset;
    private int topOffset;
    private int bottomOffset;

    public MarginOffset(){

    }

    public MarginOffset(int leftOffset, int rightOffset, int topOffset, int bottomOffset) {
        this.leftOffset = leftOffset;
        this.rightOffset = rightOffset;
        this.topOffset = topOffset;
        this.bottomOffset = bottomOffset;
    }

    public int getLeftOffset() {
        return leftOffset;
    }

    public void setLeftOffset(int leftOffset) {
        this.leftOffset = leftOffset;
    }

    public int getRightOffset() {
        return rightOffset;
    }

    public void setRightOffset(int rightOffset) {
        this.rightOffset = rightOffset;
    }

    public int getTopOffset() {
        return topOffset;
    }

    public void setTopOffset(int topOffset) {
        this.topOffset = topOffset;
    }

    public int getBottomOffset() {
        return bottomOffset;
    }

    public void setBottomOffset(int bottomOffset) {
        this.bottomOffset = bottomOffset;
    }
}
