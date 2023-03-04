package com.zkm.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReadedModel {

    private Integer id;

    private  Integer userId;

    private Integer friendId;

    private Timestamp lastReadTime;

    private Integer type;
}
