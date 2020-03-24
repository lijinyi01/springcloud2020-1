package com.atguigu.springcloud.entities;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.XSlf4j;

import java.io.Serializable;

@Data
@Accessors(chain=true)
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
@TableName("payment")
public class Payment implements Serializable {

    @TableId(value = "id", type = IdType.UUID)
    private Long id;
    private String serial;


}
