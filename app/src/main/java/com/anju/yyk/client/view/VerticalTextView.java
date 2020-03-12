package com.anju.yyk.client.view;

import android.content.Context;
import android.graphics.Camera;
import android.graphics.Color;
import android.graphics.Matrix;
import android.os.Handler;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import java.util.ArrayList;
import java.util.List;

public class VerticalTextView extends TextSwitcher implements ViewSwitcher.ViewFactory {
    private static final int animTime = 300;
    private Context mContext;
    private int index = 0;
    private Handler handler = new Handler();
    private boolean isFlipping = false;
    private List<String> textList = new ArrayList<String>();
    private OnItemClickListener itemClickListener;

    public VerticalTextView(Context context) {
        this(context, null);
    }

    public VerticalTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        init();
    }

    private void init() {
        setFactory(this);
        setInAnimation(createAnim(true, true));
        setOutAnimation(createAnim(false, true));
    }

    private Rotate3dAnimation createAnim(boolean turnIn, boolean turnUp) {
        Rotate3dAnimation rotation = new Rotate3dAnimation(turnIn, turnUp);
        rotation.setDuration(animTime); // 执行动画的时间
        rotation.setFillAfter(false); // 是否保持动画完毕之后的状态
        rotation.setInterpolator(new AccelerateInterpolator()); // 设置加速模式

        return rotation;
    }

    public void setTextList(List<String> textList) {
        this.textList = textList;
        if (textList.size() > 0) {
            this.setText(textList.get(0));
        }
        startFlipping();
    }

    // 开启信息轮播
    public void startFlipping() {
        if (textList.size() > 1) {
            handler.removeCallbacks(runnable);
            isFlipping = true;
            handler.postDelayed(runnable, 3000);
        }
    }

    // 关闭信息轮播
    public void stopFlipping() {
        if (textList.size() > 1) {
            isFlipping = false;
            handler.removeCallbacks(runnable);
        }
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (!isFlipping) {
                return;
            }
            index++;
            VerticalTextView.this.setText(textList.get(index % textList.size()));
            if (index == textList.size()) {
                index = 0;
            }
            startFlipping();
        }
    };

    @Override
    public View makeView() {
        TextView textView = new TextView(mContext);
        textView.setGravity(Gravity.START);
        textView.setTextSize(15);
        textView.setSingleLine(true);
        textView.setEllipsize(TextUtils.TruncateAt.END);
        textView.setTextColor(Color.parseColor("#555"));
        textView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (itemClickListener != null) {
                    itemClickListener.onItemClick(index % textList.size());
                }
            }
        });
        return textView;
    }

    class Rotate3dAnimation extends Animation {
        private float mCenterX;
        private float mCenterY;
        private final boolean mTurnIn;
        private final boolean mTurnUp;
        private Camera mCamera;

        Rotate3dAnimation(boolean turnIn, boolean turnUp) {
            mTurnIn = turnIn;
            mTurnUp = turnUp;
        }

        @Override
        public void initialize(int width, int height, int parentWidth, int parentHeight) {
            super.initialize(width, height, parentWidth, parentHeight);
            mCamera = new Camera();
            mCenterY = getHeight();
            mCenterX = getWidth();
        }

        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t) {

            final float centerX = mCenterX;
            final float centerY = mCenterY;
            final Camera camera = mCamera;
            final int direction = mTurnUp ? 1 : -1;

            final Matrix matrix = t.getMatrix();

            camera.save();
            if (mTurnIn) {
                camera.translate(0.0f, direction * mCenterY * (interpolatedTime - 1.0f), 0.0f);
            } else {
                camera.translate(0.0f, direction * mCenterY * (interpolatedTime), 0.0f);
            }
            camera.getMatrix(matrix);
            camera.restore();

            matrix.preTranslate(-centerX, -centerY);
            matrix.postTranslate(centerX, centerY);
        }
    }

    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }
}
