package utils;

import android.os.Handler;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * 作者：王庆
 * 时间：2017/12/7
 */

public abstract class OnUiCallback implements Callback {
    private Handler handler = OkHttpUtils.initOkHttpUtils().getHandler();

    public abstract void onFailed(Call call, IOException e);

    public abstract void onSuccess(String result) throws IOException;

    @Override
    public void onFailure(final Call call, final IOException e) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                onFailed(call, e);
            }
        });
    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {
        final String result = response.body().string();
        handler.post(new Runnable() {
            @Override
            public void run() {
                try {
                    onSuccess(result);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
