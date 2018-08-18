package top.andnux.library.widget;

import android.content.Context;
import android.os.CountDownTimer;
import android.util.AttributeSet;

/**
 * 倒计时 显示内容
 */
public class CountDownButton extends StateButton {

    private CountDownTimer timer;
    private int timerLenth = 60;

    public CountDownButton(Context context) {
        super(context);
    }

    public CountDownButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CountDownButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setTimerLenth(int timerLenth) {
        this.timerLenth = timerLenth;
    }

    public int getTimerLenth() {
        return timerLenth;
    }

    public void start(){
        if (timer != null){
            timer.cancel();
            timer = null;
        }
        timer = new CountDownTimer(1000 * timerLenth, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                setText((millisUntilFinished / 1000) + "秒后可重发");
            }

            @Override
            public void onFinish() {
                setEnabled(true);
                setText("获取验证码");
            }
        };
        timer.start();
        setEnabled(false);
    }

    public void oncancel() {
        timer.cancel();
    }
}
