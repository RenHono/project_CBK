package xyz.renhono.project_cbk.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import xyz.renhono.project_cbk.asynctask.ShowStrAST;
import xyz.renhono.project_cbk.domain.SlideShowData;
import xyz.renhono.project_cbk.interfaces.ShowRC;

/**
 * Created by GT70 on 2016/10/18 0018.
 */

public class ShowPagerAD extends PagerAdapter {
    private List<ImageView> imageViewList;

    public ShowPagerAD(List<ImageView> imageViewList) {
        this.imageViewList = imageViewList;
    }

    @Override
    public int getCount() {
        return imageViewList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        container.addView(imageViewList.get(position));


        return imageViewList.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(imageViewList.get(position));

    }

}
