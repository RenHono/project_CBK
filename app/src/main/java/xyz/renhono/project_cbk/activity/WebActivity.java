package xyz.renhono.project_cbk.activity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import xyz.renhono.project_cbk.R;
import xyz.renhono.project_cbk.asynctask.HtmlAST;
import xyz.renhono.project_cbk.domain.IdData;
import xyz.renhono.project_cbk.interfaces.IdRC;
import xyz.renhono.project_cbk.utils.OkHttpUtil;
import xyz.renhono.project_cbk.utils.TeaDBHelper;

public class WebActivity extends AppCompatActivity {

    private String urlfav = "http://sns.maimaicha.com/api?apikey=b4f4ee31a8b9acc866ef2afb754c33e6&format=json&method=news.getNewsContent&id=";
    private String urlweb = "http://sns.maimaicha.com/news/detail/";


    private ImageView ivback;
    private WebView webView;
    private String urlid;
    private String urlHtml;


    private TextView tvtitle;
    private TextView tvauthor;
    private TextView tvtime;

    private ImageView ivfavo;
    private TeaDBHelper teaDBHelper;
    private SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        ivback = (ImageView) findViewById(R.id.ivback);
        webView = (WebView) findViewById(R.id.webxxx);
        ivfavo = (ImageView) findViewById(R.id.ivfavo);

        tvtitle = (TextView) findViewById(R.id.tvtitle);
        tvauthor = (TextView) findViewById(R.id.tvauthor);
        tvtime = (TextView) findViewById(R.id.tvtime);

        urlid = getIntent().getStringExtra("webid");


        teaDBHelper = new TeaDBHelper(this);


        new HtmlAST(new IdRC() {
            @Override
            public void IdReCall(final IdData idData) {

                urlHtml = idData.getWap_content();

                Log.i("jjjjjjjjjjj", urlHtml);

//                webView.getSettings().setDefaultTextEncodingName("utf-8");
//                webView.loadData(urlHtml, "text/html", "utf-8");
                tvtitle.setText(idData.getTitle());
                tvauthor.setText(idData.getAuthor());
                tvtime.setText(idData.getCreate_time());
                if (!urlHtml.isEmpty()) {
                    webView.loadDataWithBaseURL(null, urlHtml, "text/html", "utf-8", null);
                }

                ivfavo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        sqLiteDatabase = teaDBHelper.getReadableDatabase();

                        ContentValues contentValues = new ContentValues();

                        contentValues.put("_id", idData.getId());
                        contentValues.put("titlex", idData.getTitle());
                        contentValues.put("authorx", idData.getAuthor());
                        contentValues.put("timex", idData.getCreate_time());

                        long nResult = sqLiteDatabase.insert("favo", null, contentValues);

                        Log.i("shoucang==", "" + nResult);

                        if (nResult > 0) {

                            Toast.makeText(WebActivity.this, "收藏成功", Toast.LENGTH_SHORT).show();
                        }
                        if (nResult == -1) {

                            Toast.makeText(WebActivity.this, "已收藏!", Toast.LENGTH_SHORT).show();
                        }

                        //                   db=helper.getReadableDatabase();
//		ContentValues values=new ContentValues();
//		values.put("title", "小宁宁漂流记");
//		values.put("author", "森哥");
//		values.put("price", 1);
//		long nResult=db.insert("book", null, values);
//		if (nResult>0) {
//			Toast.makeText(this, "添加记录成功", Toast.LENGTH_LONG).show();
//		}

//                        db.execSQL("insert into book(title,author,price) values('三宁流浪记','杨森',100)");

                    }
                });


            }
        }).execute(urlfav + urlid);


//        String jsonStr = new OkHttpUtil().urlJx(urlfav + urlid);

//        try {
//            JSONObject jsonObject = new JSONObject(jsonStr);
//            JSONObject jdata = jsonObject.optJSONObject("data");
//            urlHtml = jdata.optString("wap_content");
//
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }

//        Log.i("jjjjjjjjjjj",urlHtml);

//        webView.loadUrl();


        ivback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();

            }
        });


    }
}
