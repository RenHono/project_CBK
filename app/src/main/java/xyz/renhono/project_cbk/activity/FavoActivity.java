package xyz.renhono.project_cbk.activity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import xyz.renhono.project_cbk.R;
import xyz.renhono.project_cbk.adapter.FavoAdapter;
import xyz.renhono.project_cbk.adapter.ListAdapter;
import xyz.renhono.project_cbk.domain.ListData;
import xyz.renhono.project_cbk.utils.TeaDBHelper;

public class FavoActivity extends AppCompatActivity {

    private ImageView imageView;
    private ListView listView;
    private TeaDBHelper teaDBHelper;
    private List<ListData> listDataList;
    private FavoAdapter favoAdapter;
    private SQLiteDatabase db;
    private ImageView ivbackhome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favo);

        ivbackhome = (ImageView) findViewById(R.id.ivbackhome);

        ivbackhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.setAction("xyz.renhono.project_cbk.mainhome");
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });


        teaDBHelper = new TeaDBHelper(this);
//        db = teaDBHelper.getReadableDatabase();


        imageView = (ImageView) findViewById(R.id.ivback);

        listView = (ListView) findViewById(R.id.lvfavo);

//        teaDBHelper = new TeaDBHelper(this);

//        SQLiteDatabase sqLiteDatabase = teaDBHelper.getReadableDatabase();

//        Cursor cursor = sqLiteDatabase.query("favo", null, null, null, null, null, null);

        listDataList = new ArrayList<>();
//
//        while (cursor.moveToNext()) {
//            ListData listData = new ListData();
//
//            listData.setId("" + cursor.getInt(cursor.getColumnIndex("_id")));
//            listData.setTitle(cursor.getString(cursor.getColumnIndex("titlex")));
//            listData.setNickname(cursor.getString(cursor.getColumnIndex("authorx")));
//            listData.setCreate_time(cursor.getString(cursor.getColumnIndex("timex")));
//            listData.setSource(" ");
//            listData.setDescription(" ");
//            listDataList.add(listData);
//
//
//        }
//        cursor.close();

        chaxun();


        favoAdapter = new FavoAdapter(listDataList, this);
        listView.setAdapter(favoAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                Intent intent = new Intent(FavoActivity.this, WebActivity.class);

                intent.putExtra("webid", listDataList.get(position).getId().toString());

                startActivity(intent);


            }
        });


        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {


                Dialog dialog = new AlertDialog.Builder(FavoActivity.this)
                        .setTitle("删除选项")
                        .setMessage("确认删除吗？")
//相当于点击确认按钮
                        .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                String delID = listDataList.get(position).getId().toString();

                                int nResult = delById(delID);

                                if (nResult > 0) {
                                    Toast.makeText(FavoActivity.this, "删除记录成功", Toast.LENGTH_LONG).show();

                                }

                                chaxun();
                                favoAdapter.notifyDataSetChanged();

//                                Toast.makeText(FavoActivity.this, "删除成功", Toast.LENGTH_SHORT).show();
                            }
                        })
//相当于点击取消按钮
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
// TODO Auto-generated method stub
                                Toast.makeText(FavoActivity.this, "取消删除", Toast.LENGTH_SHORT).show();
//                                chaxun();
//                                favoAdapter.notifyDataSetChanged();
                            }
                        })
                        .create();
                dialog.show();


                return true;
            }
        });


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();

            }
        });


    }

    private int delById(String s) {
        db = teaDBHelper.getReadableDatabase();

        int nResult = db.delete("favo", "_id=?", new String[]{s});
//        if (nResult > 0) {
//            Toast.makeText(this, "删除记录成功", Toast.LENGTH_LONG).show();
//        }

        return nResult;
    }


    private void chaxun() {

        db = teaDBHelper.getReadableDatabase();
        Cursor cursor = db.query("favo", null, null, null, null, null, null);
//        List<ListData> listDataListCX = new ArrayList<>();
        listDataList.clear();

        while (cursor.moveToNext()) {
            ListData listData = new ListData();

            listData.setId("" + cursor.getInt(cursor.getColumnIndex("_id")));
            listData.setTitle(cursor.getString(cursor.getColumnIndex("titlex")));
            listData.setNickname(cursor.getString(cursor.getColumnIndex("authorx")));
            listData.setCreate_time(cursor.getString(cursor.getColumnIndex("timex")));
            listData.setSource(" ");
            listData.setDescription(" ");
            listDataList.add(listData);


        }
        cursor.close();

//        return listDataListCX;
    }


}
