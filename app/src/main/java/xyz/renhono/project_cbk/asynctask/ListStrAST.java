package xyz.renhono.project_cbk.asynctask;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import xyz.renhono.project_cbk.domain.ListData;
import xyz.renhono.project_cbk.interfaces.ListRC;
import xyz.renhono.project_cbk.utils.OkHttpUtil;

/**
 * Created by GT70 on 2016/10/18 0018.
 */

public class ListStrAST extends AsyncTask<String, Void, List<ListData>> {

    private ListRC listRC;

    public ListStrAST(ListRC listRC) {
        this.listRC = listRC;
    }

    @Override
    protected List<ListData> doInBackground(String... params) {


        String jsonStr = new OkHttpUtil().urlJx(params[0]);

        List<ListData> listDataList = new ArrayList<>();

        if (!jsonStr.isEmpty()) {
            try {
                JSONObject jsonObject = new JSONObject(jsonStr);

                JSONArray jsonArray = jsonObject.optJSONArray("data");

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jdata = jsonArray.optJSONObject(i);

                    ListData listData = new ListData();

                    listData.setId(jdata.optString("id"));
                    listData.setTitle(jdata.optString("title"));
                    listData.setDescription(jdata.optString("description"));
                    listData.setSource(jdata.optString("source"));
                    listData.setWap_thumb(jdata.optString("wap_thumb"));
                    listData.setCreate_time(jdata.optString("create_time"));
                    listData.setNickname(jdata.optString("nickname"));

                    listDataList.add(listData);

                }


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return listDataList;
    }

    @Override
    protected void onPostExecute(List<ListData> listDatas) {
        super.onPostExecute(listDatas);

        listRC.DataListRC(listDatas);

    }
}
