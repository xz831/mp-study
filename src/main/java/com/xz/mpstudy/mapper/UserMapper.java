package com.xz.mpstudy.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xz.mpstudy.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Package: com.xz.mpstudy.mapper
 * @ClassName: UserMapper
 * @Author: xz
 * @Date: 2020/4/18 19:34
 * @Version: 1.0
 */
@Repository
@Mapper
public interface UserMapper extends BaseMapper<User> {

    @Select("select * from user ${ew.customSqlSegment}")
    List<User> selectAll(@Param(Constants.WRAPPER)Wrapper<User> wrapper);

    @Select("select * from user ${ew.customSqlSegment}")
    IPage<User> selectMyPage(Page<User> page, @Param(Constants.WRAPPER)Wrapper<User> wrapper);
}
