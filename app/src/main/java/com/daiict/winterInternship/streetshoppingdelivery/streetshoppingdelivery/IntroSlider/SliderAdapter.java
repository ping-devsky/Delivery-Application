package com.daiict.winterInternship.streetshoppingdelivery.streetshoppingdelivery.IntroSlider;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;

import com.daiict.winterInternship.streetshoppingdelivery.R;

public class SliderAdapter extends PagerAdapter {
    Context context;
    LayoutInflater layoutInflater;

    public SliderAdapter(Context context) {
        this.context = context;
    }

    int[] images = {
            R.drawable.intro_img2,
            R.drawable.intro_img3,
            R.drawable.intro_img1

    };
    int[] headings = {
            R.string.slider_heading_img2,
            R.string.slider_heading_img3,
            R.string.slider_heading_img1

    };

    int[] description = {
            R.string.slider_desc_img2,
            R.string.slider_desc_img3,
            R.string.slider_desc_img1
    };


    @Override
    public int getCount() {
        return headings.length;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //Added a instantiate method
        assert layoutInflater != null;
        View view = layoutInflater.inflate(R.layout.intro_slider_images, container, false);

        ImageView imageView = view.findViewById(R.id.intro_imgview);
        TextView heading = view.findViewById(R.id.intro_tv_heading);
        TextView desc = view.findViewById(R.id.intro_tv_description);

        imageView.setImageResource(images[position]);
        heading.setText(headings[position]);
        desc.setText(description[position]);

        container.addView(view);

        return view;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (ConstraintLayout) object;
        //Object Typecasted to constraint layout
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ConstraintLayout) object);
    }
}