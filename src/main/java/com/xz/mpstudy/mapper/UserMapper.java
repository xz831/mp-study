package com.xz.mpstudy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xz.mpstudy.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Package: com.xz.mpstudy.mapper
 * @ClassName: UserMapper
 * @Author: xz
 * @Date: 2020/4/18 19:34
 * @Version: 1.0
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
