package com.lida.cloud.widght;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.text.method.DigitsKeyListener;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.lida.cloud.R;

/**
 * Created by WeiQingFeng on 2017/5/3.
 */

public class EditTextWithDeleteButton extends LinearLayout implements TextWatcher, View.OnClickListener, View.OnFocusChangeListener {

    private Context context;
    private ImageView iv;
    private EditText et;

    private String textNotice;
    private int inputType;

    public EditTextWithDeleteButton(Context context) {
        super(context);
        this.context = context;
        init(context);
    }

    public EditTextWithDeleteButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.EditTextWithDeleteButton);
        textNotice = typedArray.getString(R.styleable.EditTextWithDeleteButton_textNotice);
        init(context);
    }

    public EditTextWithDeleteButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init(context);
    }

    private void init(Context context) {
        this.setOrientation(HORIZONTAL);
        this.setGravity(Gravity.CENTER);
        LayoutParams piv = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        LayoutParams pet = new LayoutParams(0, LayoutParams.WRAP_CONTENT, 1.0f);

        et = new EditText(context);
        et.setLayoutParams(pet);
        et.setBackgroundColor(Color.TRANSPARENT);

        et.setSingleLine();
        et.setHint(textNotice);
        et.setTextSize(14);
        et.setPadding(0,0,0,0);

        iv = new ImageView(context);
        iv.setLayoutParams(piv);
        iv.setImageResource(R.drawable.icon_clear);
        iv.setOnClickListener(this);
        iv.setVisibility(GONE);

        this.addView(et);
        this.addView(iv);

        et.addTextChangedListener(this);
        et.setOnFocusChangeListener(this);
    }

    public String getText(){
        return et.getText().toString().trim();
    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if(s.length()==0){
            iv.setVisibility(GONE);
        }else{
            iv.setVisibility(VISIBLE);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    @Override
    public void onClick(View v) {
        et.setText("");
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if(hasFocus){
            if("".equals(et.getText().toString())){
                iv.setVisibility(GONE);
            }else{
                iv.setVisibility(VISIBLE);
            }
        }else{
            iv.setVisibility(GONE);
        }
    }

    public void setInputType(int type){
        et.setInputType(type);
    }

    public void setDigits(String s){
        et.setKeyListener(DigitsKeyListener.getInstance(s));
    }

    public void setPassType(){
        et.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
    }
}
