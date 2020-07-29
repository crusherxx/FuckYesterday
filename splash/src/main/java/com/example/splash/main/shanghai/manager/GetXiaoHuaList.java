package com.example.splash.main.shanghai.manager;

import android.os.AsyncTask;
import android.util.Log;

import com.example.splash.main.shanghai.module.ShanghaiDetailHttpTask;

import java.io.IOException;

import okhttp3.Response;

public class GetXiaoHuaList extends AsyncTask<Object,Object,Object> {
    @Override
    protected Object doInBackground(Object... objects) {
        Object desc = new ShanghaiDetailHttpTask ().getXiaohuaList ((String)objects[0], (String)objects[1], (String)objects[2]);
        return desc;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute (o);
        Response response = (Response)o;
        try {
            Log.e ( "GetXiaoHuaList","onResponse"+response.body ().string () );
        } catch (IOException e) {
            e.printStackTrace ();
        }
    }
}
