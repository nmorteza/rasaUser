package com.rassa.rassauser.utils.customViews;

/**
 * Created by mehdi on 1/19/16 AD.
 */

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;


public class cEditText extends AppCompatEditText {

    public cEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public cEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public cEditText(Context context) {
        super(context);
        init();
    }

    private void init() {
        if (!isInEditMode()) {
            setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/IRANSansMobile(FaNum)_Light.ttf"));
        }
    }
}