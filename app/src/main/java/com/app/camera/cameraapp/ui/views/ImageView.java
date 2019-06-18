package com.app.camera.cameraapp.ui.views;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.widget.TextView;

import java.text.DecimalFormat;

/**
 * Created by vit-vetal- on 18.06.19.
 */

public class ImageView extends android.support.v7.widget.AppCompatImageView {
    private ScaleGestureDetector mScaleDetector;
    private float mScaleFactor = 1.f;

    private TextView tvScaleFactor;

    public ImageView(Context context) {
        super(context);

        mScaleDetector = new ScaleGestureDetector(context, new ScaleListener());
    }

    public ImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        mScaleDetector = new ScaleGestureDetector(context, new ScaleListener());
    }

    public ImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mScaleDetector = new ScaleGestureDetector(context, new ScaleListener());
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        // Let the ScaleGestureDetector inspect all events.
        mScaleDetector.onTouchEvent(ev);
        return true;
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.save();
        canvas.scale(mScaleFactor, mScaleFactor);

        // onDraw() code goes here

        canvas.restore();
    }

    private class ScaleListener
            extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            mScaleFactor *= detector.getScaleFactor();

            // Don't let the object get too small or too large.
            mScaleFactor = Math.max(1f, Math.min(mScaleFactor, 10.0f));

            if(tvScaleFactor != null)
                tvScaleFactor.setText(String.format("%s x", new DecimalFormat("#.#").format(mScaleFactor)));

            invalidate();
            return true;
        }
    }

    public void setTvScaleFactor(TextView tvScaleFactor) {
        this.tvScaleFactor = tvScaleFactor;
    }
}
