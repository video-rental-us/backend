package com.igorszalas.rentalappus.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;

public class Utils {
  public static Sort getSort(String sort) {
    if (sort == null || sort.isEmpty() || sort.isBlank()) {
      return Sort.unsorted();
    }
    String[] sortFields = sort.split(",");

    List<Order> orders = new ArrayList<>();
    for (String field : sortFields) {
      String[] data = field.split(":");
      switch (data[1]) {
        case "asc":
          orders.add(Order.asc(data[0]));
          break;
        case "desc":
          orders.add(Order.desc(data[0]));
          break;

        default:
          orders.add(Order.by(data[0]));
          break;
      }
    }

    return Sort.by(orders);
  }
}
