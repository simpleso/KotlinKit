package top.andnux.library.widget;

import android.content.Context;
import android.util.AttributeSet;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

public class AutoXRecyclerView extends XRecyclerView {

    public AutoXRecyclerView(Context context) {
        super(context);
        initView();
    }

    public AutoXRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public AutoXRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView();
    }

    void initView() {
        this.setPullRefreshEnabled(false);
        this.setLoadingMoreEnabled(false);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(
                Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
