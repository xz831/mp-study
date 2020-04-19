package com.xz.mpstudy.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * @Package: com.xz.mpstudy.entity
 * @ClassName: User
 * @Author: xz
 * @Date: 2020/4/18 19:35
 * @Version: 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class User extends Model<User> {

    private Long id;

    private String name;

    private Integer age;

    private String email;

    private Long managerId;

    private LocalDateTime createTime;
}
