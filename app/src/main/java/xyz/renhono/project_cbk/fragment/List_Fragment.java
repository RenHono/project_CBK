package xyz.renhono.project_cbk.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import xyz.renhono.project_cbk.R;
import xyz.renhono.project_cbk.activity.WebActivity;
import xyz.renhono.project_cbk.adapter.ListAdapter;
import xyz.renhono.project_cbk.asynctask.ListStrAST;
import xyz.renhono.project_cbk.domain.ListData;
import xyz.renhono.project_cbk.interfaces.ListRC;

/**
 * A simple {@link Fragment} subclass.
 */
public class List_Fragment extends Fragment {

    private String url;
    private ListView listView;
    private ListAdapter listAdapter;
    private List<ListData> listDataList = new ArrayList<>();
    private boolean isLvBottom;
    private LinearLayout footerView;
    private String urlMore = "&rows=10&page=";
    private int pageL = 2;


    public List_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list_, container, false);

        url = getArguments().getString("url");
        listView = (ListView) view.findViewById(R.id.fllvxx);


        footerView = (LinearLayout) getActivity().getLayoutInflater().inflate(R.layout.list_footer, null);


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
                }).execute(url + urlMore + pageL);


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


        listAdapter = new ListAdapter(listDataList, getActivity());

        new ListStrAST(new ListRC() {
            @Override
            public void DataListRC(List<ListData> listL) {

                listDataList.clear();

                listDataList.addAll(listL);

                listAdapter.notifyDataSetChanged();

                listView.setAdapter(listAdapter);


            }
        }).execute(url);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(getActivity(), WebActivity.class);

                intent.putExtra("webid", listDataList.get(position).getId().toString());

                startActivity(intent);

            }
        });


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


}
