package com.app.camera.cameraapp.ui.adapters;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.app.camera.cameraapp.R;
import com.app.camera.cameraapp.utils.Converter;

/**
 * Created by vit-vetal- on 18.06.19.
 */

public class RatioAdapter extends BaseAdapter implements SpinnerAdapter {
    private Context context;
    private String[] ratio;

    public RatioAdapter(Context context, String[] ratio) {
        this.context = context;
        this.ratio = ratio;
    }

    @Override
    public int getCount() {
        return ratio.length;
    }

    @Override
    public Object getItem(int position) {
        return ratio[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = View.inflate(context, R.layout.ratio_main, null);

        TextView tvMain = (TextView) view.findViewById(R.id.tvMain);
        tvMain.setText(ratio[position]);

        LinearLayout llMain = (LinearLayout) view.findViewById(R.id.llMain);

        llMain.setLayoutParams(getLinearLayoutParams(ratio[position]));

        return view;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        View view = View.inflate(context, R.layout.ratio_dropdown, null);

        final TextView tvDropdown = (TextView) view.findViewById(R.id.tvDropdown);
        tvDropdown.setText(ratio[position]);

        LinearLayout llDropDown = (LinearLayout) view.findViewById(R.id.llDropDown);

        llDropDown.setLayoutParams(getLinearLayoutParams(ratio[position]));

        return view;
    }

    private LinearLayout.LayoutParams getLinearLayoutParams(String ratio) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(Converter.dpToPx(45, context), 0);

        switch (ratio) {
            case "9:16":
                params.height = (int) ((double) params.width / 9 * 16);
                break;
            case "2:3":
                params.height = (int) ((double) params.width / 2 * 3);
                break;
            case "3:4":
                params.height = (int) ((double) params.width / 3 * 4);
                break;
            case "4:5":
                params.height = (int) ((double) params.width / 4 * 5);
                break;
            case "1:1":
                params.height = params.width;
                break;
        }

        return params;
    }
}