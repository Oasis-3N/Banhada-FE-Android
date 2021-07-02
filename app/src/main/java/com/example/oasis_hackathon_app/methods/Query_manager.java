package com.example.oasis_hackathon_app.methods;

import android.text.style.BulletSpan;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.BufferedSource;

public class Query_manager {
    private final OkHttpClient client;

    private BufferedSource run(String url, String method, RequestBody requestBody) throws IOException, NoSuchFieldException, IllegalAccessException {
        Request request;
        if (method.equals("GET")) {
            request = new Request.Builder()
                    .url(url)
                    .method("GET", null)
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Connection", "close")
                    .build();
        }
        else {
            request = new Request.Builder()
                    .url(url)
                    .method("POST", requestBody)
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Connection", "close")
                    .build();
        }

        Response response = client.newCall(request).execute();

        return response.body().source();
    }

    public Query_manager() {
        client = new OkHttpClient().newBuilder().build();
    }

    public JSONArray request_arr(String url, String method, RequestBody requestBody) throws IOException, JSONException, NoSuchFieldException, IllegalAccessException {
        BufferedSource response = run(url, method, requestBody);
        return new JSONArray(response.readByteString().string(StandardCharsets.UTF_8));
        /*
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = response.readUtf8Line()) != null) {
            sb.append(line);
        }
        Log.d("REQUEST_ARR", sb.toString());
        return new JSONArray(sb.toString());

         */
    }

    public JSONObject request_obj(String url, String method, RequestBody requestBody) throws IOException, JSONException, NoSuchFieldException, IllegalAccessException {
        BufferedSource response = run(url, method, requestBody);

        return new JSONObject(response.readByteString().string(StandardCharsets.UTF_8));
        /*
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = response.) != null) {
            sb.append(line);
        }
        Log.d("REQUEST_OBJ", sb.toString());
        return new JSONObject(sb.toString());

         */
    }
}
