package com.xz.mpstudy.injector;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;
import com.baomidou.mybatisplus.extension.injector.methods.InsertBatchSomeColumn;
import com.xz.mpstudy.method.DeleteAllMethod;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Package: com.xz.mpstudy.injector
 * @ClassName: MySqlInjector
 * @Author: xz
 * @Date: 2020/4/19 19:39
 * @Version: 1.0
 */
@Component
public class MySqlInjector extends DefaultSqlInjector {

    @Override
    public List<AbstractMethod> getMethodList(Class<?> mapperClass) {
        List<AbstractMethod> methodList = super.getMethodList(mapperClass);
        methodList.add(new DeleteAllMethod());
        methodList.add(new InsertBatchSomeColumn(t->!t.isLogicDelete()));
        return methodList;
    }
}
