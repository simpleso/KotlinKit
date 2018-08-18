package top.andnux.library.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import top.andnux.library.R;

public class TitleBarView extends RelativeLayout {

    private LinearLayout leftLayout;
    private LinearLayout titleLayout;
    private LinearLayout rightLayout;
    private LinearLayout contentLayout;
    private View lineView;

    private Drawable leftImage;
    private Drawable titleImage;
    private Drawable rightImage;

    private String leftText;
    private String titleText;
    private String rightText;

    private int leftModle = 1;
    private int titleModle = 0;
    private int rightModle = 1;

    private int leftColor = Color.BLACK;
    private int titleColor = Color.BLACK;
    private int rightColor = Color.BLACK;

    private float leftSize = sp2px(10);
    private float titleSize = sp2px(14);
    private float rightSize = sp2px(10);

    private int leftMagin = dip2px(15);
    private int rightMagin = dip2px(15);

    private int lineHeight = dip2px(1);
    private int lineColor = Color.parseColor("#80808080");


    private TextView leftTextView;
    private ImageView leftImageView;

    private TextView titleTextView;
    private ImageView titleImageView;

    private TextView rightTextView;
    private ImageView rightImageView;

    private OnClickListener leftOnClickListener;
    private OnClickListener rightOnClickListener;
    private OnClickListener titleOnClickListener;

    public TitleBarView(Context context) {
        super(context);
        init(context, null, 0);
    }

    public TitleBarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public TitleBarView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs, defStyle);
    }

    private void init(Context context, AttributeSet attrs, int defStyle) {
        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.TitleBarView, defStyle, 0);

        leftModle = a.getInt(R.styleable.TitleBarView_leftModle, leftModle);
        titleModle = a.getInt(R.styleable.TitleBarView_titleModle, titleModle);
        rightModle = a.getInt(R.styleable.TitleBarView_rightModle, rightModle);

        leftText = a.getString(R.styleable.TitleBarView_leftText);
        titleText = a.getString(R.styleable.TitleBarView_titleText);
        rightText = a.getString(R.styleable.TitleBarView_rightText);

        leftImage = a.getDrawable(R.styleable.TitleBarView_leftImage);
        titleImage = a.getDrawable(R.styleable.TitleBarView_titleImage);
        rightImage = a.getDrawable(R.styleable.TitleBarView_rightImage);


        leftSize = a.getDimension(R.styleable.TitleBarView_leftModle, leftSize);
        titleSize = a.getDimension(R.styleable.TitleBarView_titleSize, titleSize);
        rightSize = a.getDimension(R.styleable.TitleBarView_rightSize, rightSize);


        leftColor = a.getColor(R.styleable.TitleBarView_leftColor, leftColor);
        titleColor = a.getColor(R.styleable.TitleBarView_titleColor, titleColor);
        rightColor = a.getColor(R.styleable.TitleBarView_rightColor, rightColor);

        leftMagin = (int) a.getDimension(R.styleable.TitleBarView_leftMagin, leftMagin);
        rightMagin = (int) a.getDimension(R.styleable.TitleBarView_rightMagin, rightMagin);

        lineColor = a.getColor(R.styleable.TitleBarView_lineColor,lineColor);
        lineHeight = (int) a.getDimension(R.styleable.TitleBarView_lineHeight,lineHeight);

        a.recycle();

        initViews(context);
    }

    private int sp2px(float spValue) {
        final float fontScale = getContext().getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    private int dip2px(float dpValue) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    private void initViews(Context context) {

        this.removeAllViews();
        contentLayout = new LinearLayout(context);
        contentLayout.setOrientation(LinearLayout.HORIZONTAL);

        addView(contentLayout, new LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT));

        lineView = new View(context);
        lineView.setBackgroundColor(lineColor);
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, lineHeight);
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        addView(lineView, params);

        leftLayout = new LinearLayout(context);
        leftLayout.setGravity(Gravity.CENTER_VERTICAL);
        leftLayout.setOrientation(LinearLayout.HORIZONTAL);
        titleLayout = new LinearLayout(context);
        titleLayout.setGravity(Gravity.CENTER_VERTICAL);
        titleLayout.setOrientation(LinearLayout.HORIZONTAL);
        rightLayout = new LinearLayout(context);
        rightLayout.setGravity(Gravity.CENTER_VERTICAL);
        rightLayout.setOrientation(LinearLayout.HORIZONTAL);

        LinearLayout.LayoutParams leftPamars = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
        LinearLayout.LayoutParams titlePamars = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 2);
        LinearLayout.LayoutParams rightPamars = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);

        contentLayout.addView(leftLayout, leftPamars);
        contentLayout.addView(titleLayout, titlePamars);
        contentLayout.addView(rightLayout, rightPamars);

        switch (leftModle) {
            case 0:
                leftTextView = new TextView(context);
                leftTextView.setText(leftText);
                leftTextView.setTextSize(leftSize);
                leftTextView.setTextColor(leftColor);
                leftTextView.setGravity(Gravity.CENTER_VERTICAL);
                leftTextView.setPadding(leftMagin, 0, 0, 0);
                leftLayout.addView(leftTextView, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
                break;
            case 1:
                leftImageView = new ImageView(context);
                leftImageView.setImageDrawable(leftImage);
                leftImageView.setPadding(leftMagin, 0, 0, 0);
                leftLayout.addView(leftImageView, new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
                break;

            case 2:
                leftImageView = new ImageView(context);
                leftImageView.setImageDrawable(leftImage);
                leftImageView.setPadding(leftMagin, 0, 0, 0);
                leftLayout.addView(leftImageView, new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));

                leftTextView = new TextView(context);
                leftTextView.setTextSize(leftSize);
                leftTextView.setText(leftText);
                leftTextView.setGravity(Gravity.CENTER_VERTICAL);
                leftTextView.setTextColor(leftColor);
                leftTextView.setPadding(leftMagin, 0, 0, 0);
                leftLayout.addView(leftTextView, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
                break;
        }

        switch (titleModle) {
            case 0:
                titleTextView = new TextView(context);
                titleTextView.setTextSize(titleSize);
                titleTextView.setText(titleText);
                titleTextView.setTextColor(titleColor);
                titleTextView.setGravity(Gravity.CENTER);
                titleLayout.addView(titleTextView, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
                break;
            case 1:
                titleImageView = new ImageView(context);
                titleImageView.setImageDrawable(titleImage);
                titleLayout.addView(titleImageView, new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
                break;

            case 2:
                titleImageView = new ImageView(context);
                titleImageView.setImageDrawable(titleImage);
                titleLayout.addView(titleImageView, new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));

                titleTextView = new TextView(context);
                titleTextView.setTextSize(titleSize);
                titleTextView.setText(titleText);
                titleTextView.setGravity(Gravity.CENTER);
                titleTextView.setTextColor(titleColor);
                titleLayout.addView(titleTextView, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
                break;
        }

        switch (rightModle) {
            case 0:
                rightTextView = new TextView(context);
                rightTextView.setTextSize(rightSize);
                rightTextView.setText(rightText);
                rightTextView.setTextColor(rightColor);
                rightTextView.setGravity(Gravity.CENTER_VERTICAL | Gravity.RIGHT);
                rightTextView.setPadding(0, 0, rightMagin, 0);
                rightLayout.addView(rightTextView, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
                break;
            case 1:
                rightImageView = new ImageView(context);
                rightImageView.setImageDrawable(rightImage);
                rightImageView.setPadding(rightMagin, 0, 0, 0);
                rightLayout.addView(rightImageView, new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
                break;

            case 2:
                rightImageView = new ImageView(context);
                rightImageView.setImageDrawable(rightImage);
                rightImageView.setPadding(rightMagin, 0, 0, 0);
                rightLayout.addView(rightImageView, new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));

                rightTextView = new TextView(context);
                rightTextView.setTextSize(rightSize);
                rightTextView.setText(rightText);
                rightTextView.setGravity(Gravity.CENTER_VERTICAL | Gravity.RIGHT);
                rightTextView.setTextColor(rightColor);
                rightTextView.setPadding(0, 0, rightMagin, 0);
                rightLayout.addView(rightTextView, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
                break;
        }
    }

    public void setLeftImage(Drawable leftImage) {
        this.leftImage = leftImage;
        invalidate();
    }

    public void setTitleImage(Drawable titleImage) {
        this.titleImage = titleImage;
        invalidate();
    }

    public void setRightImage(Drawable rightImage) {
        this.rightImage = rightImage;
        invalidate();
    }

    public void setLeftColor(int leftColor) {
        this.leftColor = leftColor;
        invalidate();
    }

    public void setTitleColor(int titleColor) {
        this.titleColor = titleColor;
        invalidate();
    }

    public void setRightColor(int rightColor) {
        this.rightColor = rightColor;
        invalidate();
    }

    public void setLeftModle(int leftModle) {
        this.leftModle = leftModle;
        invalidate();
    }

    public void setRightModle(int rightModle) {
        this.rightModle = rightModle;
        invalidate();
    }

    public void setTitleModle(int titleModle) {
        this.titleModle = titleModle;
        invalidate();
    }

    public void setLeftText(String leftText) {
        this.leftText = leftText;
        invalidate();
    }

    public String getLeftText() {
        return leftText;
    }

    public void setTitleText(String titleText) {
        this.titleText = titleText;
        invalidate();
    }

    public String getTitleText() {
        return titleText;
    }

    public void setRightText(String rightText) {
        this.rightText = rightText;
        invalidate();
    }

    public String getRightText() {
        return rightText;
    }

    public void setLeftOnClickListener(OnClickListener leftOnClickListener) {
        this.leftOnClickListener = leftOnClickListener;
    }

    public void setRightOnClickListener(OnClickListener rightOnClickListener) {
        this.rightOnClickListener = rightOnClickListener;
    }

    public void setTitleOnClickListener(OnClickListener titleOnClickListener) {
        this.titleOnClickListener = titleOnClickListener;
    }

    public OnClickListener getLeftOnClickListener() {
        return leftOnClickListener;
    }

    public OnClickListener getRightOnClickListener() {
        return rightOnClickListener;
    }

    public OnClickListener getTitleOnClickListener() {
        return titleOnClickListener;
    }
}
