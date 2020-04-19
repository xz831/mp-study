package com.xz.mpstudy.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @Package: com.xz.mpstudy.handler
 * @ClassName: FieldFillHandler
 * @Author: xz
 * @Date: 2020/4/19 17:12
 * @Version: 1.0
 */
@Component
public class FieldFillHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        boolean has = metaObject.hasSetter("createTime");
        //有这个属性执行填充
        if (has) {
            Object createTime = getFieldValByName("createTime", metaObject);
            //自己没有设定好值
            if(createTime == null){
                System.out.println("插入自动填充执行");
                setInsertFieldValByName("createTime", LocalDateTime.now(), metaObject);
            }
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        boolean has = metaObject.hasSetter("updateTime");
        if (has) {
            Object updateTime = getFieldValByName("updateTime", metaObject);
            if(updateTime == null){
                System.out.println("更新自动填充执行");
                setUpdateFieldValByName("updateTime", LocalDateTime.now(), metaObject);
            }
        }
    }
}
