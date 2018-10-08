package com.bwie.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bwie.view.WaveView;
import com.bwie.wang.R;

/**
 * Created by wangbingjun on 2018/10/8.
 */

public class FragmentThree extends Fragment {

    private WaveView wave;
    private ImageView img_cur;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_three,container,false);
        wave = view.findViewById(R.id.wave);
        img_cur = view.findViewById(R.id.img_cur);

        WaveView.OnWaveViewListener listener = new WaveView.OnWaveViewListener() {
            @Override
            public void onChanged(float y) {
                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) img_cur.getLayoutParams();
                layoutParams.setMargins(0,0,0, (int) y);
                img_cur.setLayoutParams(layoutParams);
            }

        };
        wave.setOnWaveViewListener(listener);
        return view;
    }
}
