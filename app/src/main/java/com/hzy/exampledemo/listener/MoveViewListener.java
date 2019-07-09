package com.hzy.exampledemo.listener;

public interface MoveViewListener {
    /**
     * 在MotionEvent.ACTION_DOWN的时候记录点击的点，进行缩放、透明度动画
     *
     * @param RawX
     * @param RawY
     */
    void downAction(int RawX, int RawY);

    /**
     * 移动的时候根据移动的上下左右点算出移动控件的中心点是否在诊断、分析的区域内
     *
     * @param l
     * @param t
     * @param r
     * @param b
     */
    void moveAction(int l, int t, int r, int b);
}
