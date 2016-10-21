package xyz.renhono.project_cbk.utils;

import android.widget.Toast;

import java.io.IOException;
import java.security.PrivateKey;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by GT70 on 2016/10/18 0018.
 */

public class OkHttpUtil {

    private String sdata;


    public String urlJx(String s) {


        OkHttpClient okHttpClient = new OkHttpClient();


        Request request = new Request.Builder().url(s).build();


        try {
            Response response = okHttpClient.newCall(request).execute();

            if (response.isSuccessful()) {

                sdata = response.body().string();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


        return sdata;
    }

}
