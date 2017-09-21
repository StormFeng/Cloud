package com.lida.cloud.widght;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lida.cloud.R;
import com.midian.base.util.Func;

/**
 * Created by WeiQingFeng on 2017/5/3.
 */

public class SwitchButton extends LinearLayout{

    private Context context;
    private ImageView iv;
    private TextView tv;

    private static final int DEFAULT_COLOR = 0x000000;// 默认Tag文字颜色

    private String text;
    private float textSize;
    private int textColor;

    private boolean isOpen = true;

    public SwitchButton(Context context) {
        super(context);
        this.context = context;
    }

    public SwitchButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init(attrs);
    }

    public SwitchButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        TypedArray custom = context.obtainStyledAttributes(attrs, R.styleable.SwitchButton);
        text = custom.getString(R.styleable.SwitchButton_text);
        textSize = custom.getDimension(R.styleable.SwitchButton_textSize,14f);
        textColor = custom.getColor(R.styleable.SwitchButton_textColor,DEFAULT_COLOR);

        this.setOrientation(HORIZONTAL);
        this.setGravity(Gravity.CENTER);
        LayoutParams piv = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        LayoutParams ptv = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        ptv.setMargins(0,0, Func.Dp2Px(context,5),0);

        tv=new TextView(context);
        tv.setText(text);
        tv.setTextSize(Func.Px2Dp(context,textSize));
        tv.setTextColor(textColor);
        tv.setLayoutParams(ptv);

        iv = new ImageView(context);
        iv.setLayoutParams(piv);
        iv.setImageResource(R.drawable.icon_arrow_d);

        this.addView(tv);
        this.addView(iv);
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isOpen){
                    isOpen = false;
                    tv.setTextColor(Color.parseColor("#FA2220"));
                    iv.setImageResource(R.drawable.icon_arrow_u);
                }else{
                    isOpen = true;
                    tv.setTextColor(Color.parseColor("#363636"));
                    iv.setImageResource(R.drawable.icon_arrow_d);
                }
            }
        });
    }

    public void setText(String s){
        tv.setText(s);
    }

    public boolean isOpen(){
        return isOpen;
    }
}
