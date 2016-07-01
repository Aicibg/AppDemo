package com.app.appdemo.activity;

import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import com.app.appdemo.MainActivity;
import com.app.appdemo.R;
import com.jude.rollviewpager.RollPagerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GuideActivity extends BaseActivity implements GestureDetector.OnGestureListener{
    @BindView(R.id.guide_view_flipper)
    ViewFlipper mViewFlipper;
    @BindView(R.id.bt_guide_startmain)
    Button btStartMain;
    private GestureDetector mGestureDetector;
    private int[] dots={R.id.guide_iv_dots0,R.id.guide_iv_dots1,R.id.guide_iv_dots2,R.id.guide_iv_dots3};
    private int[] imgs={R.mipmap.guide1,R.mipmap.guide2,R.mipmap.guide3,R.mipmap.guide4};
    private int count=0;
    private int current;
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);

         initViewFilpper();
         mGestureDetector=new GestureDetector(this,this);
    }

    /**
     * 初始化ViewFilpper
     */
    private void initViewFilpper() {
        mViewFlipper.addView(getImageView(imgs[0]));
        mViewFlipper.addView(getImageView(imgs[1]));
        mViewFlipper.addView(getImageView(imgs[2]));
        mViewFlipper.addView(getImageView(imgs[3]));
        Animation inAnim= AnimationUtils.loadAnimation(this,R.anim.anim_right_in);
        Animation outAnim=AnimationUtils.loadAnimation(this,R.anim.anim_left_out);
        mViewFlipper.setInAnimation(inAnim);
        mViewFlipper.setOutAnimation(outAnim);
        mViewFlipper.setAutoStart(true);
        mViewFlipper.setFlipInterval(3000);
        initDots();//初始化圆点
        if(mViewFlipper.isAutoStart()&!mViewFlipper.isFlipping()){
            mViewFlipper.startFlipping();
            autoStop();
        }
    }

    /**
     * 初始化圆点
     */
    private void initDots() {
        for(int i=0;i<dots.length;i++){
            ButterKnife.findById(GuideActivity.this,dots[i]).setEnabled(false);
        }
        current=0;
        ButterKnife.findById(GuideActivity.this,dots[current]).setEnabled(true);
    }

    /**
     * 监听自动播放，进入最后一页时，显示Button
     */
    private void autoStop() {
        Animation anim=mViewFlipper.getInAnimation();
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                count++;
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                  setCurrentDots(count);
                 if(count>imgs.length-2){
                     mViewFlipper.stopFlipping();
                     mViewFlipper.setAutoStart(false);
                     btStartMain.setVisibility(View.VISIBLE);
                 }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });

    }

    /**
     * 下方圆点的联动
     * @param count
     */
    private void setCurrentDots(int count) {
        for(int i=0;i<dots.length;i++){
            ButterKnife.findById(GuideActivity.this,dots[i]).setEnabled(false);
        }
        ButterKnife.findById(GuideActivity.this,dots[count]).setEnabled(true);
        current=count;
    }

    @OnClick(R.id.bt_guide_startmain)
    public void startMain(){
        MainActivity.start(GuideActivity.this);
        finish();
    }

    public View getImageView(int resid){
          imageView=new ImageView(this);
          imageView.setId(resid);
          imageView.setImageResource(resid);
          imageView.setScaleType(ImageView.ScaleType.FIT_XY);
          imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
          return imageView;
      }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return mGestureDetector.onTouchEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        mViewFlipper.stopFlipping();
        mViewFlipper.setAutoStart(false);
        if(motionEvent.getX()-motionEvent1.getX()>120&current!=dots.length-1){
            //从右向左滑动，进入下一页
            Animation inAnim=AnimationUtils.loadAnimation(GuideActivity.this,R.anim.anim_right_in);
            Animation outAnim=AnimationUtils.loadAnimation(GuideActivity.this,R.anim.anim_left_out);
            mViewFlipper.setInAnimation(inAnim);
            mViewFlipper.setOutAnimation(outAnim);
            mViewFlipper.showNext();
            current++;
            if(current==dots.length-1){
                btStartMain.setVisibility(View.VISIBLE);
            }
            setCurrentDots(current);
            return true;
        }else if(motionEvent1.getX()-motionEvent.getX()>120&current!=0){
            //从左向右滑动，返回上一页
            Animation inAnim=AnimationUtils.loadAnimation(GuideActivity.this,R.anim.anim_left_in);
            Animation outAnim=AnimationUtils.loadAnimation(GuideActivity.this,R.anim.anim_right_out);
            mViewFlipper.setInAnimation(inAnim);
            mViewFlipper.setOutAnimation(outAnim);
            mViewFlipper.showPrevious();
            current--;
            if(current!=dots.length-1){
                btStartMain.setVisibility(View.GONE);
            }
            setCurrentDots(current);
            return true;
        }
        return true;
    }
}
