package com.example.broccoli;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;

import java.lang.reflect.Field;

public class fontUtil
{
    public static void overrideFont(Context context, String defaultFontNameToOverride, String customFontFileNameInAssets)
    {
        try
        {
            final Typeface customFontTypeface = Typeface.createFromAsset(context.getAssets(), customFontFileNameInAssets);

            final Field defaultFontTypefaceField = Typeface.class.getDeclaredField(defaultFontNameToOverride);
            defaultFontTypefaceField.setAccessible(true);
            defaultFontTypefaceField.set(null, customFontTypeface);
        }
        catch (Exception e)
        {
            Log.e("Checking", "Problem with " + customFontFileNameInAssets + ".");
        }
    }
}
