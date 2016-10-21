package xyz.renhono.project_cbk.asynctask;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import xyz.renhono.project_cbk.domain.IdData;
import xyz.renhono.project_cbk.interfaces.IdRC;
import xyz.renhono.project_cbk.utils.OkHttpUtil;

/**
 * Created by GT70 on 2016/10/19 0019.
 */

public class HtmlAST extends AsyncTask<String, Void, IdData> {

    private IdRC idRC;
    private String urlfav = "http://sns.maimaicha.com/api?apikey=b4f4ee31a8b9acc866ef2afb754c33e6&format=json&method=news.getNewsContent&id=";

    public HtmlAST(IdRC idRC) {
        this.idRC = idRC;
    }

    @Override
    protected IdData doInBackground(String... params) {


        String jsonStr = new OkHttpUtil().urlJx(urlfav + params[0]);
        IdData idData = new IdData();

        if (!jsonStr.isEmpty()) {
            try {
                JSONObject jsonObject = new JSONObject(jsonStr);
                JSONObject jdata = jsonObject.optJSONObject("data");


                idData.setWap_content(jdata.optString("wap_content"));

                idData.setId(jdata.optString("id"));
                idData.setTitle(jdata.optString("title"));
                idData.setSource(jdata.optString("source"));
                idData.setAuthor(jdata.optString("author"));
                idData.setCreate_time(jdata.optString("create_time"));
                idData.setWeiboUrl(jdata.optString("weibourl"));

//            urlHtml = jdata.optString("wap_content");


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return idData;
    }

    @Override
    protected void onPostExecute(IdData idData) {
        super.onPostExecute(idData);

        idRC.IdReCall(idData);
    }
}
