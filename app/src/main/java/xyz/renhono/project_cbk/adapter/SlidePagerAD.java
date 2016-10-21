package xyz.renhono.project_cbk.adapter;

import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.List;

import xyz.renhono.project_cbk.activity.WebActivity;
import xyz.renhono.project_cbk.domain.SlideShowData;

/**
 * Created by GT70 on 2016/10/17 0017.
 */

public class SlidePagerAD extends PagerAdapter {
    private List<ImageView> imageViewList;
    private List<SlideShowData> slideShowDataList;

    public SlidePagerAD(List<ImageView> imageViewList, List<SlideShowData> slideShowDataList) {
        this.imageViewList = imageViewList;
        this.slideShowDataList = slideShowDataList;
    }

    public SlidePagerAD(List<ImageView> imageViewList) {
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
    public Object instantiateItem(ViewGroup container, final int position) {

//        container.addView(imageViewList.get(position));


        final View view = imageViewList.get(position);

        view.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

//                Toast.makeText(view.getContext(), "点击了" + (position + 1) + "项", Toast.LENGTH_SHORT).show();


                String idXX = slideShowDataList.get(position).getId();

                if (!idXX.isEmpty()) {
                    Intent intent = new Intent(view.getContext(), WebActivity.class);
                    intent.putExtra("webid", idXX);
                    view.getContext().startActivity(intent);
                } else {

                    Toast.makeText(view.getContext(), "没有可打开页面", Toast.LENGTH_SHORT).show();

                }
            }
        });
        ViewPager viewPager = (ViewPager) container;

        viewPager.addView(view);


        return imageViewList.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(imageViewList.get(position));

    }

}
