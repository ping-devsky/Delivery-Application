package com.daiict.winterInternship.streetshoppingdelivery.streetshoppingdelivery.IntroSlider;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.daiict.winterInternship.streetshoppingdelivery.R;
import com.daiict.winterInternship.streetshoppingdelivery.streetshoppingdelivery.SignupSignIn.Signin;

public class introSlider extends AppCompatActivity {
    ViewPager viewPager;
    LinearLayout layout_linear;

    //The class that we created
    SliderAdapter sliderAdapter;

    TextView[] dots_tv;

    int position_viewpager;

    Animation btn_animation;

    Button btn_letsstart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro_slider);
        //Removing Status Bar from The top
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        viewPager = findViewById(R.id.viewpager);
        layout_linear = findViewById(R.id.layout_linear_dots);

        //assigning values to the class constructor
        sliderAdapter = new SliderAdapter(this);
        viewPager.setAdapter(sliderAdapter);

        //bind button
        btn_letsstart = findViewById(R.id.btn_start_slider);

        addDots(0);
        viewPager.addOnPageChangeListener(onPageChangeListener);
    }

    private void addDots(int position) {
        dots_tv = new TextView[3];
        layout_linear.removeAllViews();

        for (int i = 0; i < dots_tv.length; i++) {
            dots_tv[i] = new TextView(this);
            dots_tv[i].setText(Html.fromHtml("&#8226;"));
            dots_tv[i].setTextSize(35);

            layout_linear.addView(dots_tv[i]);
        }
        if (dots_tv.length > 0) {
            dots_tv[position].setTextColor(getResources().getColor(R.color.Primary));
        }


    }
    ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addDots(position);

            position_viewpager = position;
            if (position >= 0 && position <= 1) {
                btn_letsstart.setVisibility(View.INVISIBLE);
            } else {
                btn_animation = AnimationUtils.loadAnimation(introSlider.this, R.anim.side_animation);
                btn_letsstart.setAnimation(btn_animation);
                btn_letsstart.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    public void introSkip(View view) {
        startActivity(new Intent(this, Signin.class));
        finish();
    }

    public void letsStartClick(View view) {
        startActivity(new Intent(this, Signin.class));
        finish();
    }

    public void introNext(View view) {
        viewPager.setCurrentItem(position_viewpager + 1);
    }
}