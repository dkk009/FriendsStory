package com.example.deepak.myfriends.networks;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.Volley;
import com.example.deepak.myfriends.MyFriendsApp;
import com.example.deepak.myfriends.interfaces.IDataModel;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.HashMap;

/**
 *
 */
public class RequestHandler {

    private static final int INITIAL_TIMEOUT_MS = 30000;
    private static Context mContext;
    private static RequestQueue sRequestQueue;
    private static RequestHandler sRequestHandler;
    private static RequestQueue sImageRequestQueue;

    public interface RequestCompletedCallback {
        void onSuccess(IDataModel response);

        void onFailure(VolleyError error);

    }

    public interface ImageRequestCompletedCallback {
        void onSuccess(Bitmap bitmap, String url);

        void onFailure(VolleyError error);
    }


    public static RequestHandler getInstance() {
        if (sRequestQueue == null) {
            sRequestQueue = Volley.newRequestQueue(MyFriendsApp.getInstance(), new HurlStack());

        }

        if (sRequestHandler == null)
            sRequestHandler = new RequestHandler();
        return sRequestHandler;
    }

    public static RequestQueue getImageRequestQueue() {

        if (sImageRequestQueue == null) {
            sImageRequestQueue =Volley.newRequestQueue(MyFriendsApp.getInstance(),new HurlStack());
        }
        return sImageRequestQueue;
    }

    public static RequestQueue getRequestQueue() {

        if (sRequestQueue != null) {
            return sRequestQueue;
        } else {
            throw new IllegalStateException("RequestQueue not initialized");
        }
    }

    public static void cancelRequest(String tag) {

        if (sRequestQueue != null) {
            sRequestQueue.cancelAll(tag);
        }
    }

}
