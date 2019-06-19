package com.app.camera.cameraapp.utils;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * Created by vit-vetal- on 19.06.19.
 */

public class Converter {
    public static int dpToPx(int dp, Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        int px = Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
        return px;
    }
}
