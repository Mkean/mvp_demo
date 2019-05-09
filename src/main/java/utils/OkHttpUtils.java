package utils;

import android.os.Handler;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * 作者：王庆
 * 时间：2017/12/7
 */

public class OkHttpUtils {
    private static Handler handler = null;
    private static OkHttpUtils mOkHttpUtils = null;
    private static OkHttpClient mClient = null;

    public static Handler getHandler() {
        synchronized (OkHttpUtils.class) {
            if (handler == null) {
                handler = new Handler();
            }
        }
        return handler;
    }

    public static OkHttpUtils initOkHttpUtils() {
        synchronized (OkHttpUtils.class) {
            if (mOkHttpUtils == null) {
                mOkHttpUtils = new OkHttpUtils();
            }
        }
        return mOkHttpUtils;
    }

    public static OkHttpClient getInstance() {
        synchronized (OkHttpUtils.class) {
            if (mClient == null) {
                mClient = new OkHttpClient.Builder().readTimeout(20, TimeUnit.SECONDS)
                        .writeTimeout(20, TimeUnit.SECONDS)
                        .connectTimeout(20, TimeUnit.SECONDS)
                        .addInterceptor(new LoggingInterceptor())
                        .build();
            }
        }
        return mClient;
    }

    public static void doGet(String url, Callback callback) {
        getInstance();
        Request request = new Request.Builder().url(url).build();
        Call call = mClient.newCall(request);
        call.enqueue(callback);
    }

    public static void doPost(String url, Map<String, String> map, Callback callback) {
        getInstance();
        FormBody.Builder formbody = new FormBody.Builder();
        for (String key : map.keySet()) {
            formbody.add(key, map.get(key));
        }
        Request request = new Request.Builder().post(formbody.build()).url(url).build();
        Call call = mClient.newCall(request);
        call.enqueue(callback);
    }
}
