package com.example.velo77.request;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.ref.WeakReference;

public class LoginAsyncTask extends android.os.AsyncTask<String, Void, String> {

    private JSONObject inputs;

    public interface Listeners {
        void onPreExecute();
        void doInBackground();
        void onPostExecute(String success) throws JSONException;
    }

    private final WeakReference<Listeners> callback;

    public LoginAsyncTask(Listeners callback, JSONObject inputs){
        this.callback = new WeakReference<>(callback);
        this.inputs = inputs;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        this.callback.get().onPreExecute();
        Log.e("TAG", "LoginAsyncTask is started.");
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

        try {
            return LoginURLConnection.startHttpRequest(url[0], this.inputs);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

}