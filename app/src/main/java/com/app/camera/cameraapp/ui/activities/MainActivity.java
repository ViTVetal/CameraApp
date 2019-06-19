package com.app.camera.cameraapp.ui.activities;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import com.app.camera.cameraapp.R;
import com.app.camera.cameraapp.ui.adapters.RatioAdapter;
import com.app.camera.cameraapp.ui.views.CameraView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.tvScale) TextView tvScale;
    @BindView(R.id.tvPhoto) TextView tvPhoto;
    @BindView(R.id.tvVideo) TextView tvVideo;

    @BindView(R.id.ivCamera) CameraView ivCamera;

    @BindView(R.id.spRatio) Spinner spRatio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("myLogs", ((int) getResources().getDimension(R.dimen.sp_ratio_width)) + " ");

        ButterKnife.bind(this);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        ivCamera.setTvScaleFactor(tvScale);

        final String[] ratio = {"9:16", "2:3", "3:4", "4:5", "1:1"};

        RatioAdapter adapter = new RatioAdapter(this, ratio);
        spRatio.setAdapter(adapter);
        spRatio.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d("myLogs", ratio[position]);
                switch (ratio[position]) {
                    case "9:16":
                        changeRatio(9, 16);
                        break;
                    case "2:3":
                        changeRatio(2, 3);
                        break;
                    case "3:4":
                        changeRatio(3, 4);
                        break;
                    case "4:5":
                        changeRatio(4, 5);
                        break;
                    case "1:1":
                        changeRatio(1, 1);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void changeRatio(int x, int y) {
        final DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        int height = (int) ((double)metrics.widthPixels / x * y);

        if(height <= metrics.heightPixels) {
            ivCamera.getLayoutParams().height = height;
            ivCamera.getLayoutParams().width = metrics.widthPixels;
        } else {
            int width = (int) ((double) metrics.heightPixels / y * x);
            ivCamera.getLayoutParams().height = metrics.heightPixels;
            ivCamera.getLayoutParams().width = width;
        }

        ivCamera.requestLayout();
    }

    @OnClick(R.id.tvPhoto)
    public void selectPhotoMode() {
        tvPhoto.setTextColor(ContextCompat.getColor(this, R.color.text_selector_color));
        tvVideo.setTextColor(ContextCompat.getColor(this, R.color.white));
    }

    @OnClick(R.id.tvVideo)
    public void selectVideoMode() {
        tvPhoto.setTextColor(ContextCompat.getColor(this, R.color.white));
        tvVideo.setTextColor(ContextCompat.getColor(this, R.color.text_selector_color));
    }
}
