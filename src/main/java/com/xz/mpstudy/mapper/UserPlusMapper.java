package com.xz.mpstudy.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xz.mpstudy.entity.User;
import com.xz.mpstudy.entity.UserPlus;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

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
