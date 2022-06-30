package com.example.demo.controller;

import com.example.demo.model.Order;
import com.example.demo.model.OrderItem;
import com.google.gson.Gson;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;
import java.io.IOException;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import util.OkHttp;

@RestController
@RequestMapping(path = "order")
public class OrderController {
  @GetMapping(path = "placeOrder/{userId}")
  public String placeOrder(@RequestBody List<OrderItem> orderItemList, @PathVariable Integer userId) throws IOException {
    Gson gson = new Gson();

    // create order;
    Order order = new Order();
    order.setTable_id(99);
    order.setInitiator_id(userId);

    com.squareup.okhttp.RequestBody body = com.squareup.okhttp.RequestBody.create(
        MediaType.parse("application/json"), gson.toJson(order));

    Request request = new Request.Builder()
        .url("http://localhost:8085/shoppingCart/insert")
        .post(body)
        .build();

    // de verificat
    OkHttpClient okHttpClient = new OkHttpClient();

    Call call = okHttpClient.newCall(request);
    Response response = call.execute();
    Order responseOrder = gson.fromJson(response.toString(), Order.class);

    // create subOrder based on the previously generated order;


    // then set subOrder id to all items from suborder
    orderItemList.forEach(
        orderItem -> {
          orderItem.setSub_order_id(subOrder.getId());
        }
    );



    try {
      return OkHttp.getRequest("http://localhost:8082/category");
    } catch (IOException e) {
      System.out.println("could not parse the response");
    }
    return null;
  }
}
