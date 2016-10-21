package xyz.renhono.project_cbk.fragment;


import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import xyz.renhono.project_cbk.utils.MyViewPager;
import xyz.renhono.project_cbk.R;
import xyz.renhono.project_cbk.activity.WebActivity;
import xyz.renhono.project_cbk.adapter.ListAdapter;
import xyz.renhono.project_cbk.adapter.ShowPagerAD;
import xyz.renhono.project_cbk.adapter.SlidePagerAD;
import xyz.renhono.project_cbk.asynctask.ListStrAST;
import xyz.renhono.project_cbk.asynctask.ShowStrAST;
import xyz.renhono.project_cbk.domain.ListData;
import xyz.renhono.project_cbk.domain.SlideShowData;
import xyz.renhono.project_cbk.interfaces.ListRC;
import xyz.renhono.project_cbk.interfaces.ShowRC;

/**
 * A simple {@link Fragment} subclass.
 */
public class Main_Fragment extends Fragment {

    private MyViewPager myViewPager;

    private ListView listView;
    private ViewPager viewPager;
    private SlidePagerAD slidePagerAD;
    private ShowPagerAD showPagerAD;

    private RelativeLayout relativeLayout;

    //    private int[] picids = {R.mipmap.slide1, R.mipmap.slide2, R.mipmap.slide3};
    private List<ImageView> picsList = new ArrayList<>();

//    private String urlxx = "http://sns.maimaicha.com/api?apikey=b4f4ee31a8b9acc866ef2afb754c33e6&format=json&method=news.getSlideshow";

    private List<SlideShowData> slideShowDataList = new ArrayList<>();
    private ListAdapter listAdapter;
    private List<ListData> listDataList = new ArrayList<>();

    private int adSize;

    private String listUrl;
    private LinearLayout linearLayout;
    private TextView textView;

    private ImageView[] ivImgs;
    private ImageView[] ivdots;
    private LinearLayout footerView;
    private int pageL = 2;
    private String urlMore = "&rows=10&page=";
    private boolean isLvBottom;
    private View headerView;

    public Main_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_main_, container, false);

        /*
        不带viewpager滑动
         */

//        viewPager = (ViewPager) view.findViewById(R.id.fmvpxx);
//        relativeLayout = (RelativeLayout) view.findViewById(R.id.fmrl);


//        myViewPager= (MyViewPager) view.findViewById(R.id.fmvpxx);


        linearLayout = new LinearLayout(getActivity());

        listView = (ListView) view.findViewById(R.id.fmlvxx);


        slidePagerAD = new SlidePagerAD(picsList, slideShowDataList);

//        showPagerAD = new ShowPagerAD(slideShowDataList);

        headerView = (LinearLayout) getActivity().getLayoutInflater().inflate(R.layout.view_pager, listView, false);

//        headerView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,210));

        footerView = (LinearLayout) getActivity().getLayoutInflater().inflate(R.layout.list_footer, null);

//        viewPager = new ViewPager(getActivity());
//        viewPager.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT));


        viewPager = (ViewPager) headerView.findViewById(R.id.fmvpxx);

        relativeLayout = (RelativeLayout) headerView.findViewById(R.id.fmrl);

//        relativeLayout.addView(viewPager);


//        listView.addHeaderView(headerView);

        footerView.setVisibility(View.GONE);

        footerView.getChildAt(0).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "马上加载更多。。", Toast.LENGTH_SHORT).show();


                new ListStrAST(new ListRC() {
                    @Override
                    public void DataListRC(List<ListData> listL) {

//                        listDataList.clear();

                        listDataList.addAll(listL);

                        listAdapter.notifyDataSetChanged();

//                        listView.setAdapter(listAdapter);


                    }
                }).execute(listUrl + urlMore + pageL);


                pageL += 1;
            }
        });

        footerView.getChildAt(1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listView.setSelection(0);
            }
        });

        listView.addFooterView(footerView);


        ShowStrAST showStrAST = new ShowStrAST(new ShowRC() {
            @Override
            public void ShowDataListRC(final List<SlideShowData> listS) {

                slideShowDataList.clear();
                slideShowDataList.addAll(listS);


                Log.i("DataList.size()", "" + slideShowDataList.size());
                Log.i("listS.size()", "" + listS.size());

                picsList.clear();

                ivdots = new ImageView[listS.size()];

                ivImgs = new ImageView[listS.size()];


                for (int i = 0; i < listS.size(); i++) {

//                    Log.i("ssssssss", slideShowDataList.get(i).getImage());

                    ImageView imageView = new ImageView(getActivity());


                    RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);

                    layoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);

                    imageView.setLayoutParams(layoutParams);


                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);


                    Picasso.with(getActivity()).load(listS.get(i).getImage()).into(imageView);


                    picsList.add(imageView);

/*

小点
 */
                    ImageView ivdot = new ImageView(getActivity());
                    ivdot.setPadding(30, 10, 30, 10);
                    ivdot.setImageResource(R.drawable.dots);
                    ivdot.setEnabled(true);


                    linearLayout.addView(ivdot);


                }


                textView.setText(listS.get(0).getTitle());
                linearLayout.getChildAt(0).setEnabled(false);
                Log.i("linearC======", linearLayout.getChildCount() + "");
                adSize = linearLayout.getChildCount();


//                viewPager.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT));
//                relativeLayout.addView(viewPager);


                slidePagerAD.notifyDataSetChanged();


                viewPager.setAdapter(slidePagerAD);
                Log.i("adSize=======", "" + adSize);

                for (int i = 0; i < adSize; i++) {

                    ivdots[i] = (ImageView) linearLayout.getChildAt(i);
//                    ivdots[i].setEnabled(true);
                    ivdots[i].setTag(i);


                    linearLayout.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            viewPager.setCurrentItem(Integer.parseInt(v.getTag().toString()));


                        }
                    });


                }


                listView.addHeaderView(headerView);

                viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                    }

                    @Override
                    public void onPageSelected(int position) {

                        for (int i = 0; i < adSize; i++) {

                            linearLayout.getChildAt(i).setEnabled(true);

                        }

                        linearLayout.getChildAt(position).setEnabled(false);


                        textView.setText(listS.get(position).getTitle());


                    }


                    @Override
                    public void onPageScrollStateChanged(int state) {

                    }
                });


            }
        });

        showStrAST.execute(getArguments().getString("slide"));


        listAdapter = new ListAdapter(listDataList, getActivity());

        listUrl = getArguments().getString("url1");
        Log.i("urllllll", listUrl);

        new ListStrAST(new ListRC() {
            @Override
            public void DataListRC(List<ListData> listL) {

                listDataList.clear();

                listDataList.addAll(listL);

                listAdapter.notifyDataSetChanged();

                listView.setAdapter(listAdapter);


            }
        }).execute(listUrl);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(getActivity(), WebActivity.class);

                intent.putExtra("webid", listDataList.get(position - 1).getId().toString());

                startActivity(intent);

            }
        });


//        showPagerAD = new ShowPagerAD(slideShowDataList);
//
//        viewPager.setAdapter(showPagerAD);

        Log.i("linearC", linearLayout.getChildCount() + "");


        Log.i("picsList.size()", "" + picsList.size());
        Log.i("adSize", "" + adSize);


        textView = new TextView(getActivity());

        RelativeLayout.LayoutParams textLayoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);

        textLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);

        textView.setTextColor(Color.WHITE);
        textView.setBackgroundColor(Color.GRAY);
        textView.setAlpha(0.5f);

        textView.setPadding(30, 0, 0, 0);

        textView.setLayoutParams(textLayoutParams);




        /*

         */
        RelativeLayout.LayoutParams linearLayoutParams = new RelativeLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT
                , LinearLayout.LayoutParams.WRAP_CONTENT);

        linearLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);

        linearLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);


        linearLayout.setGravity(Gravity.CENTER);
        linearLayout.setLayoutParams(linearLayoutParams);


        relativeLayout.addView(textView);
        relativeLayout.addView(linearLayout);


        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {


                if (isLvBottom && scrollState == SCROLL_STATE_IDLE) {


                    footerView.setVisibility(View.VISIBLE);


                } else {

                    footerView.setVisibility(View.GONE);

                }


            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {


                isLvBottom = (firstVisibleItem + visibleItemCount == totalItemCount);


            }
        });

        return view;


    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }
}
