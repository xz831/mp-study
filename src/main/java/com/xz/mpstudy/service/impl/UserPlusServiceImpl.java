package com.xz.mpstudy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xz.mpstudy.entity.UserPlus;
import com.xz.mpstudy.mapper.UserPlusMapper;
import com.xz.mpstudy.service.UserPlusService;
import org.springframework.stereotype.Service;

/**
 * @Package: com.xz.mpstudy.service.impl
 * @ClassName: UserServiceImpl
 * @Author: xz
 * @Date: 2020/4/18 19:34
 * @Version: 1.0
 */
@Service
public class UserPlusServiceImpl extends ServiceImpl<UserPlusMapper, UserPlus> implements UserPlusService {
}
