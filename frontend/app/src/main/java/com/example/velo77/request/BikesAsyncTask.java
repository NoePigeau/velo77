package com.example.velo77.request;

import android.util.Log;

import org.json.JSONException;

import java.lang.ref.WeakReference;

public class BikesAsyncTask extends android.os.AsyncTask<String, Void, String> {

    public interface Listeners {
        void onPreExecute();
        void doInBackground();
        void onPostExecute(String success) throws JSONException;
    }

    private final WeakReference<Listeners> callback;

    public BikesAsyncTask(Listeners callback){
        this.callback = new WeakReference<>(callback);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        this.callback.get().onPreExecute();
        Log.e("TAG", "AsyncTask is started.");
    }

    @Override
    protected void onPostExecute(String success) {
        super.onPostExecute(success);
        try {
            this.callback.get().onPostExecute(success);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.e("TAG", "AsyncTask is finished.");
    }

    @Override
    protected String doInBackground(String... url) {
        this.callback.get().doInBackground();
        Log.e("TAG", "AsyncTask doing some big work...");
        return BikesURLConnection.startHttpRequest(url[0]);
    }
}