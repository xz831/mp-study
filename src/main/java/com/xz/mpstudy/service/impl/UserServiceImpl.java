package com.xz.mpstudy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xz.mpstudy.entity.User;
import com.xz.mpstudy.mapper.UserMapper;
import com.xz.mpstudy.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @Package: com.xz.mpstudy.service.impl
 * @ClassName: UserServiceImpl
 * @Author: xz
 * @Date: 2020/4/18 19:34
 * @Version: 1.0
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService {
}
