package com.lhf.exam.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Desk {
    private int id;
    private String name;
    private int content;
    private int state;
    private String ordertime;
}
