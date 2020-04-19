package com.xz.mpstudy.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * @Package: com.xz.mpstudy.entity
 * @ClassName: UserPlus
 * @Author: xz
 * @Date: 2020/4/18 19:35
 * @Version: 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UserPlus extends Model<UserPlus> {

    private Long id;

    private String name;

    private Integer age;

    private String email;

    private Long managerId;
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
    @Version
    private Integer version;
    @TableLogic
    @TableField(select = false)
    private Integer deleted;
}
