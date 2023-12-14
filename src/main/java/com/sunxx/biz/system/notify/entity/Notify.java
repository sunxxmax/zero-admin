package com.sunxx.biz.system.notify.entity;

import lombok.*;

import java.io.Serializable;

@Builder

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Notify implements Serializable {
    private String message;
}
