package com.github.spyhunter99.wikimapia_client;

import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

import java.io.File;

/**
 * created on 1/11/2017.
 *
 * @author Alex O'Ree
 */

public class WikiMapiaDatabase {
    SQLiteDatabase sqLiteDatabase;
    public WikiMapiaDatabase(){
        sqLiteDatabase = SQLiteDatabase.openOrCreateDatabase(new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/wikimapia.db"),null);
        //FIXME sqLiteDatabase.execSQL("create if not exists table categories");
    }
}
