package com.nyapplication.ui.article_details.viewpager;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.nyapplication.R;
import com.nyapplication.data_models.MediaMetaData;

import java.util.ArrayList;

/**
 * @author Sino K D
 * @since 8/4/18.
 * Viewpager image slide animation
 */
public class SlidingImage_Adapter extends PagerAdapter {


    private ArrayList<MediaMetaData> mediaMetaDatas;
    private LayoutInflater inflater;
    private Context context;


    public SlidingImage_Adapter(Context context, ArrayList<MediaMetaData> mediaMetaDatas) {
        this.context = context;
        this.mediaMetaDatas = mediaMetaDatas;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((FrameLayout) object);
    }

    @Override
    public int getCount() {
        return mediaMetaDatas.size();
    }

    @Override
    public Object instantiateItem(ViewGroup view, int position) {
        View imageLayout = inflater.inflate(R.layout.slide_image_layout, view, false);
        MediaMetaData mediaMetaData = mediaMetaDatas.get(position);

        ImageView imageView = imageLayout.findViewById(R.id.image);

        Glide.with(context)
                .load(mediaMetaData.getUrl())
                .into(imageView);

        view.addView(imageLayout);

        return imageLayout;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((FrameLayout) object);
    }


}