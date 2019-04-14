package com.qihongfei.simpleresttemplate.model;

import lombok.*;
import org.joda.money.Money;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Coffee {
    private String name;
    private Money price;
}
