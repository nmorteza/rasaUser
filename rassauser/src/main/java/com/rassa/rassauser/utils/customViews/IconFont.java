package com.rassa.rassauser.utils.customViews;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;



/**
 * Created by Mehdi on 6/29/2017 AD.
 */

public class IconFont extends android.support.v7.widget.AppCompatTextView {
    public  Typeface FONT_ICON;

    public IconFont(Context context) {
        super(context);
        if (FONT_ICON == null) {
            FONT_ICON=Typeface.createFromAsset(context.getAssets(), "fonts/icon_font.ttf");
        }
        this.setTypeface(FONT_ICON);

    }

    public IconFont(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (FONT_ICON == null) {
            FONT_ICON=Typeface.createFromAsset(context.getAssets(), "fonts/icon_font.ttf");
        }
        this.setTypeface(FONT_ICON);
    }

    public IconFont(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (FONT_ICON == null) {
            FONT_ICON=Typeface.createFromAsset(context.getAssets(), "fonts/icon_font.ttf");
        }
        this.setTypeface(FONT_ICON);
    }
}
