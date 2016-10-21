package xyz.renhono.project_cbk.utils;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v4.app.NavUtils;

/**
 * Created by GT70 on 2016/10/19 0019.
 */

public class TeaDBHelper extends SQLiteOpenHelper {

    private final static String DBNAME = "teawiki";
    private final static int VERSION = 1;


    public TeaDBHelper(Context context) {
        super(context, DBNAME, null, VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "create table if not exists favo(_id integer primary key,urlx text,titlex text,authorx text,timex text)";

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


        String sql = "drop table favo";
        onCreate(db);

    }
}
