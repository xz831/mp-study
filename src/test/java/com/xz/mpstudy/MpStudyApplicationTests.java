package com.xz.mpstudy;

import com.xz.mpstudy.entity.User;
import com.xz.mpstudy.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@SpringBootTest
class MpStudyApplicationTests {

    @Autowired
    private UserService userService;

    @Test
    void contextLoads() {
        User user = userService.getById(1087982257332887553L);
        System.out.println(user);
        LocalDateTime createTime = user.getCreateTime();
        String format = createTime.format(DateTimeFormatter.BASIC_ISO_DATE);
        System.out.println(format);
    }

    public static void main(String[] args) {
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now.format(DateTimeFormatter.BASIC_ISO_DATE));
    }
}
