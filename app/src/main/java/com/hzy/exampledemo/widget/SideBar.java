package com.hzy.exampledemo.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.hzy.exampledemo.R;
import com.hzy.exampledemo.utils.DipUtil;

/**
 * @author hzy
 * @description:右侧的字母索引View
 * @date :2019/3/28 18:24
 */
public class SideBar extends View {

    /**
     * 触摸事件
     */
	private OnTouchingLetterChangedListener onTouchingLetterChangedListener;

    /**
     * 26个字母
     */
    public static String[] b = { "A", "B", "C", "D", "E", "F", "G", "H", "I",  
            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",  
            "W", "X", "Y", "Z", "#" };
    /**
     * 选中
     */
    private int choose = -1;
	
    private Paint paint = new Paint();
    
    private TextView mTextDialog;
    
    /**
     * 为SideBar显示字母的TextView
     * 
     * @param mTextDialog
     */
    public void setTextView(TextView mTextDialog){
    	this.mTextDialog = mTextDialog;
    }
    

	public SideBar(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	public SideBar(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public SideBar(Context context) {
		super(context);
	}
	/**
	 * 
	 * 重写的onDraw的方法
	 * 
	 */
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		//获取对应的高度
		int height = getHeight();
		//获取对应的宽度
		int width = getWidth();
		//获取每一个字母的高度
		int singleHeight = height/b.length;
		for (int i = 0; i < b.length; i++) {
			paint.setColor(Color.rgb(33, 65, 98));
			paint.setTypeface(Typeface.DEFAULT_BOLD);
			paint.setAntiAlias(true);
			paint.setTextSize(DipUtil.sp2px(getContext(),14));
			//选中的状态
			if (i == choose) {
				paint.setColor(Color.parseColor("#3399ff"));
				//设置是否为粗体文字
				paint.setFakeBoldText(true);
			}
			//x坐标等于=中间-字符串宽度的一半
			float xPos = width / 2- paint.measureText(b[i])/2;
			float yPos = singleHeight*i + singleHeight;
			canvas.drawText(b[i], xPos, yPos, paint);
			paint.reset();//重置画笔
		}
	}
	
	@Override
	public boolean dispatchTouchEvent(MotionEvent event) {
		final int action = event.getAction();
		//点击y坐标
		final float y = event.getY();
		final int oldChoose = choose;
		
		final OnTouchingLetterChangedListener listener = onTouchingLetterChangedListener;

		//点击y坐标所占高度的比例*b数组的长度就等于点击b中的个数
		final int c = (int)((y / getHeight()) * b.length);
		
		switch (action) {
		case MotionEvent.ACTION_UP:
			//设置背景颜色
//			setBackgroundDrawable(new ColorDrawable(0x00000000));
			setBackgroundResource(R.drawable.sidebar_background2);
			choose = -1;
			invalidate();
			if (mTextDialog != null) {
				mTextDialog.setVisibility(View.INVISIBLE);
			}
			break;

		default:
			setBackgroundResource(R.drawable.sidebar_background);
			if (oldChoose != c) {
				if (c>=0 && c<b.length) {
					if (listener != null) {
						listener.onTouchingLetterChanged(b[c]);
					}
					if (mTextDialog != null) {
						mTextDialog.setText(b[c]);
						mTextDialog.setVisibility(View.VISIBLE);
					}
					choose = c;
					invalidate();
				}
			}
			break;
		}
		return true;
	}
	/**
	 * 向外松开的方法
	 * 
	 * @param onTouchingLetterChangedListener
	 */
	public void setOnTouchingLetterChangedListener(
			OnTouchingLetterChangedListener onTouchingLetterChangedListener) {
		this.onTouchingLetterChangedListener = onTouchingLetterChangedListener;
	}
	

	public interface OnTouchingLetterChangedListener {
		/**
		 * 触摸字母变化，为外部提供参数s
		 * @param s
		 */
		void onTouchingLetterChanged(String s);
	}

}