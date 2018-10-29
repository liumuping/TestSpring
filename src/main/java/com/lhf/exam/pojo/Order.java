package com.lhf.exam.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Order {
    private int id;
    private int userId;
    private int deskId;
    private String deskname;
    private double price;
    private double vipprice;
    private String orderdate;
    private String tel;
    private String custom;
    private int state;
}
