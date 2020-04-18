package com.xz.mpstudy.entity;

import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * @Package: com.xz.mpstudy.entity
 * @ClassName: User
 * @Author: xz
 * @Date: 2020/4/18 19:35
 * @Version: 1.0
 */
@Data
public class User {

    private Long id;

    private String name;

    private Integer age;

    private String email;

    private Long managerId;

    private LocalDateTime createTime;
}
