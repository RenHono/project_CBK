package xyz.renhono.project_cbk.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import xyz.renhono.project_cbk.MainActivity;
import xyz.renhono.project_cbk.R;
import xyz.renhono.project_cbk.adapter.ListAdapter;
import xyz.renhono.project_cbk.asynctask.ListStrAST;
import xyz.renhono.project_cbk.domain.ListData;
import xyz.renhono.project_cbk.interfaces.ListRC;

public class SearchActivity extends AppCompatActivity {

    private String gjz;
    private String urlx = "http://sns.maimaicha.com/api?apikey=b4f4ee31a8b9acc866ef2afb754c33e6&format=json&method=news.searcListByTitle&rows=10&page=1&search=";
    private ListView listView;
    private ListAdapter listAdapter;
    private List<ListData> listDataList = new ArrayList<>();
    private TextView textView;
    private ImageView imageView;
    private ImageView ivbackhome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);


        gjz = getIntent().getStringExtra("gjz");

        listView = (ListView) findViewById(R.id.lvsearch);
        textView = (TextView) findViewById(R.id.tvheadgjz);
        imageView = (ImageView) findViewById(R.id.ivback);
        ivbackhome = (ImageView) findViewById(R.id.ivbackhome);

        textView.setText(gjz);

        listAdapter = new ListAdapter(listDataList, this);

        ivbackhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.setAction("xyz.renhono.project_cbk.mainhome");
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);            }
        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();

            }
        });

        new ListStrAST(new ListRC() {
            @Override
            public void DataListRC(List<ListData> listL) {

                listDataList.clear();

                listDataList.addAll(listL);

                listAdapter.notifyDataSetChanged();

                listView.setAdapter(listAdapter);


            }
        }).execute(urlx + gjz);

    }
}
