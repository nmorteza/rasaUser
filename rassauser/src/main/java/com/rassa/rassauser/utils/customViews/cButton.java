package com.rassa.rassauser.utils.customViews;


import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;

/**
 * Created by mehdi on 9/20/15 AD.
 */
public class cButton extends AppCompatButton {


    public cButton(Context context) {
        super(context);
    }

    public cButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/IRANSansMobile(FaNum)_Light.ttf"));
    }

    public cButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/IRANSansMobile(FaNum)_Light.ttf"));
    }
}