package com.example.demo.controller;

import com.example.demo.dto.OrderItemTransfer;
import com.example.demo.model.ExtraOrderItem;
import com.example.demo.model.Order;
import com.example.demo.model.OrderItem;
import com.example.demo.model.SubOrder;
import com.google.gson.Gson;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import java.io.IOException;
import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "order")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class OrderController {

  @PostMapping(path = "placeOrder/{userId}")
  public String placeOrder(@RequestBody List<OrderItem> orderItemList, @PathVariable Integer userId) throws IOException {
    Gson gson = new Gson();

    // create order;
    Order order = new Order();
    order.setTableId(99);
    order.setInitiatorId(userId);

    com.squareup.okhttp.RequestBody bodyOrder = com.squareup.okhttp.RequestBody.create(
        MediaType.parse("application/json"), gson.toJson(order));

    Request requestInsertOrder = new Request.Builder()
        .url("http://localhost:8082/shoppingCart/insert")
        .post(bodyOrder)
        .build();

    // de verificat
    OkHttpClient okHttpClient = new OkHttpClient();

    Call callOrder = okHttpClient.newCall(requestInsertOrder);
    Response responseOrder = callOrder.execute();
    Order responseOrderEntity = gson.fromJson(responseOrder.body().string(), Order.class);

    // create subOrder based on the previously generated order;
    SubOrder subOrder = new SubOrder();
    subOrder.setOrder_id(responseOrderEntity.getId());
    subOrder.setUser_id(userId);
    subOrder.setPayment_id(1);
    subOrder.setStatus_id(1);

    com.squareup.okhttp.RequestBody bodySubOrder = com.squareup.okhttp.RequestBody.create(
        MediaType.parse("application/json"), gson.toJson(subOrder));

    Request requestInsertSubOrder = new Request.Builder()
        .url("http://localhost:8082/subOrder/insert")
        .post(bodySubOrder)
        .build();

    Call callSubOrder = okHttpClient.newCall(requestInsertSubOrder);
    Response responseSubOrder = callSubOrder.execute();
    SubOrder responseSubOrderEntity = gson.fromJson(responseSubOrder.body().string(), SubOrder.class);


    // then set subOrder id to all items from suborder


    orderItemList.forEach(
        orderItem -> {
          OrderItemTransfer orderItemTransfer = new OrderItemTransfer();
          orderItemTransfer.setSubOrderId(responseSubOrderEntity.getId());
          orderItemTransfer.setId(0);
          orderItemTransfer.setExtraIds(orderItem.getExtraIds());
          orderItemTransfer.setProductId(orderItem.getProductId());
          orderItemTransfer.setQuantity(orderItem.getQuantity());
          orderItemTransfer.setStatusId(orderItem.getStatusId());
          com.squareup.okhttp.RequestBody bodyOrderItem = com.squareup.okhttp.RequestBody.create(
              MediaType.parse("application/json"), gson.toJson(orderItemTransfer));

          Request requestInsertOrderItem = new Request.Builder()
              .url("http://localhost:8082/shoppingItem/insert")
              .post(bodyOrderItem)
              .build();

          Call callOrderItem = okHttpClient.newCall(requestInsertOrderItem);
          Response responseOrderItem = null;
          try {
            responseOrderItem = callOrderItem.execute();
          } catch (IOException e) {
            e.printStackTrace();
          }
          try {
            OrderItem responseOrderItemEntity = gson.fromJson(responseOrderItem.body().string(), OrderItem.class);
            orderItem.getExtraIds().forEach(extraId -> {
              ExtraOrderItem extraOrderItem = new ExtraOrderItem();
              extraOrderItem.setOrderItemId(responseOrderItemEntity.getId());
              extraOrderItem.setExtraId(extraId);
              com.squareup.okhttp.RequestBody bodyExtraOrderItem = com.squareup.okhttp.RequestBody.create(
                  MediaType.parse("application/json"), gson.toJson(extraOrderItem));

              Request requestInsertExtraOrderItem = new Request.Builder()
                  .url("http://localhost:8082/extraOrderItem/insert")
                  .post(bodyExtraOrderItem)
                  .build();

              Call callExtraOrderItem = okHttpClient.newCall(requestInsertExtraOrderItem);
              try {
                Response responseExtraOrderItem = callExtraOrderItem.execute();
              } catch (IOException e) {
                e.printStackTrace();
              }
            });
          } catch (IOException e) {
            e.printStackTrace();
          }
        }
    );
    return null;
  }

}
