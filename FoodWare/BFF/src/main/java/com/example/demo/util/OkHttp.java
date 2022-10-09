package com.example.demo.util;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import java.io.IOException;

public class OkHttp {
  public static String getRequest(String url) throws IOException {
    OkHttpClient client = new OkHttpClient();
    Request request = new Request.Builder().url(url).build();
    Response response = client.newCall(request).execute();
    return response.body().string();
  }
}
