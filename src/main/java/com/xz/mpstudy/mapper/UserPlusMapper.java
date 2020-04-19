package com.xz.mpstudy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xz.mpstudy.entity.UserPlus;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @Package: com.xz.mpstudy.mapper
 * @ClassName: UserPlusMapper
 * @Author: xz
 * @Date: 2020/4/18 19:34
 * @Version: 1.0
 */
@Repository
@Mapper
public interface UserPlusMapper extends BaseMapper<UserPlus> {

    int deleteAll();
}
