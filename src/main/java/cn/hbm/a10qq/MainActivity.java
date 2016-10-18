package cn.hbm.a10qq;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SlidingPaneLayout;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;

/**
 * Author hbm
 * IDE Android Studio
 * development time 2016/10/15
 */
public class MainActivity extends BaseActivity implements View.OnClickListener {
    private SlidingPaneLayout mSlidingPane;
    private LinearLayout mMenu, mMain;
    private Toast mToast;
    private SimpleDraweeView mSimpleDraweeView;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        setListener();
    }

    //以下设置不影响侧边栏能否滑动
    private void setListener() {
        //设置菜单滑动时渐变色
        mSlidingPane.setCoveredFadeColor(getResources().getColor(R.color.menu));
        //设置主界面滑动渐变色
        mSlidingPane.setSliderFadeColor(getResources().getColor(R.color.main));
        //设置滑动视差
        mSlidingPane.setParallaxDistance(100);
        //设置滑动监听
        mSlidingPane.setPanelSlideListener(new SlidingPaneLayout.PanelSlideListener() {
            @Override
            public void onPanelSlide(View view, float slideOffset) {
                //view 代表主视图
                //slideOffset默认=0.0，值随拖动的距离变化,停止拖动值=1.0
                mMenu.setScaleY(slideOffset / 2 + 0.5f);
                mMenu.setScaleX(slideOffset / 2 + 0.5f);
                mMain.setScaleY(1 - slideOffset / 9);
            }

            @Override
            public void onPanelOpened(View panel) {

            }

            @Override
            public void onPanelClosed(View panel) {

            }
        });


    }

    private void initView() {
        mSlidingPane = (SlidingPaneLayout) findViewById(R.id.sliding);
        mMenu = (LinearLayout) findViewById(R.id.menu);
        mMain = (LinearLayout) findViewById(R.id.main);
        mSimpleDraweeView = (SimpleDraweeView) findViewById(R.id.simple);

        findViewById(R.id.but1).setOnClickListener(this);
        findViewById(R.id.but2).setOnClickListener(this);
        findViewById(R.id.but3).setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.but1:
                toast(this, "别点我点B", R.drawable.f1);
                break;
            case R.id.but2:
                toast(this, "你还是点A吧", R.drawable.f2);
                break;
            case R.id.but3:
                toast(this, "别摸我走开", R.drawable.f3);
                break;
        }
    }


    public void toast(final Context context, String string, int resId) {
        View view = View.inflate(context, R.layout.toast_layout, null);
        TextView toast_Tv = (TextView) view.findViewById(R.id.toast_tv);
        ImageView toast_Iv = (ImageView) view.findViewById(R.id.toast_iv);
        toast_Iv.setImageResource(resId);
        toast_Tv.setText(string);
        if (mToast == null) {
            mToast = new Toast(context);
        } else {
            mToast.setGravity(Gravity.CENTER_VERTICAL, 120, 0);
            mToast.setDuration(Toast.LENGTH_LONG);
            mToast.setView(view);
            mToast.show();
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mToast.cancel();
            }
        }, 800);
    }
}
