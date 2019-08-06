package com.huwei.calculate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final String FIRST_BOOT = "first_boot";
    private TextView mTextView;
    private int mResult;
    private boolean mIsFirstShow = true;
    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        long tick = System.currentTimeMillis();

        int var1 = 8;
        int var2 = 5;
        mResult = jiafa(var1, var2);

        mTextView = findViewById(R.id.tv_result);
        Button button = findViewById(R.id.btn_click);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("MainActivity1", "click a button");
                mTextView.setText("结果=" + mResult);
            }
        });

        mImageView = findViewById(R.id.iv_adv);

        final SharedPreferences preferences = getSharedPreferences("config", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = preferences.edit();
        mIsFirstShow = preferences.getBoolean(FIRST_BOOT, true);

        if (mIsFirstShow) {
            mImageView.setVisibility(View.VISIBLE);
        } else {
            Log.e("MainActivity1", "set img gone");
            mImageView.setVisibility(View.GONE);
        }
        mImageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                editor.putBoolean(FIRST_BOOT, false);
                editor.commit();

                mImageView.setVisibility(View.GONE);
                return false;
            }
        });

        long ellapseTime = System.currentTimeMillis() - tick;
        Log.e("MainActivity1", String.format("time = %d", ellapseTime));
    }

    private int jiafa(int i, int j) {
        return (i * j);
    }
}
