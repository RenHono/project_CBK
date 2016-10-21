package xyz.renhono.project_cbk.asynctask;

import android.os.AsyncTask;
import android.os.WorkSource;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import xyz.renhono.project_cbk.domain.SlideShowData;
import xyz.renhono.project_cbk.interfaces.ShowRC;
import xyz.renhono.project_cbk.utils.OkHttpUtil;

/**
 * Created by GT70 on 2016/10/18 0018.
 */

public class ShowStrAST extends AsyncTask<String, Void, List<SlideShowData>> {

    //    private List<SlideShowData> slideShowDataList;
    private ShowRC showRC;


//    public ShowStrAST(List<SlideShowData> slideShowDataList) {
//        this.slideShowDataList = slideShowDataList;
//    }


    public ShowStrAST(ShowRC showRC) {
        this.showRC = showRC;
    }

    @Override
    protected List<SlideShowData> doInBackground(String... params) {


        String jsonStr = new OkHttpUtil().urlJx(params[0]);

        List<SlideShowData> slideShowDatas = new ArrayList<>();
        if (!jsonStr.isEmpty()) {
            try {
                JSONObject jsonObject = new JSONObject(jsonStr);
                JSONArray jsonArray = jsonObject.optJSONArray("data");

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jdatas = jsonArray.optJSONObject(i);
                    SlideShowData slideShowData = new SlideShowData();

                    slideShowData.setId(jdatas.optString("id"));
                    slideShowData.setTitle(jdatas.optString("title"));
                    slideShowData.setImage(jdatas.optString("image"));
                    slideShowData.setLink(jdatas.optString("link"));
                    slideShowDatas.add(slideShowData);


                    Log.i("jsonAST====", jdatas.optString("image"));
                }
                Log.i("xxxx", slideShowDatas.get(0).getImage());
                Log.i("xxxx", slideShowDatas.get(1).getImage());
                Log.i("xxxx", slideShowDatas.get(2).getImage());


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return slideShowDatas;
    }

    @Override
    protected void onPostExecute(List<SlideShowData> slideShowDatas) {

//        slideShowDataList.addAll(slideShowDatas);


        Log.i("listdddddd", slideShowDatas.get(0).getImage());
        Log.i("listdddddd", slideShowDatas.get(1).getImage());
        Log.i("listdddddd", slideShowDatas.get(2).getImage());

        super.onPostExecute(slideShowDatas);

        showRC.ShowDataListRC(slideShowDatas);

    }
}
