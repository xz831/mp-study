package com.xz.mpstudy;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.additional.query.impl.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.additional.update.impl.LambdaUpdateChainWrapper;
import com.xz.mpstudy.entity.User;
import com.xz.mpstudy.entity.UserPlus;
import com.xz.mpstudy.mapper.UserMapper;
import com.xz.mpstudy.mapper.UserPlusMapper;
import com.xz.mpstudy.service.UserPlusService;
import com.xz.mpstudy.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.*;

@SpringBootTest
class MpStudyApplicationTests {

    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserPlusService userPlusService;

    @Test
    void selectAll() {
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);
    }

    @Test
    void insert() {
        User user = new User();
        user.setName("刘翔");
        user.setAge(34);
        user.setCreateTime(LocalDateTime.now());
        user.setEmail("lx@xxx.com");
        user.setManagerId(1087982257332887553L);
        int insert = userMapper.insert(user);
        System.out.println(insert);
    }

    @Test
    void selectById() {
        User user = userMapper.selectById(1087982257332887553L);
        System.out.println(user);
    }

    @Test
    void selectByIds() {
        List<User> users = userMapper.selectBatchIds(Arrays.asList(1087982257332887553L, 1088248166370832385L));
        users.forEach(System.out::println);
    }

    @Test
    void selectByMap() {
        //用map的时候，key是数据库的列名，也就是不支持驼峰
        List<User> users = userMapper.selectByMap(new HashMap<String, Object>(1) {{
            put("name", "王天风");
        }});
        users.forEach(System.out::println);
    }

    /**
     * 名字包含雨且年龄小于40
     */
    @Test
    void selectByWrapper() {
        List<User> users = userMapper.selectList(new QueryWrapper<User>().like("name", "雨").lt("age", 40));
        users.forEach(System.out::println);
    }

    /**
     * 名字包含雨且年龄大于等于20小于等于40且email不为空
     */
    @Test
    void selectByWrapper2() {
        List<User> users = userMapper.selectList(new QueryWrapper<User>().like("name", "雨")
                .between("age", 20, 40).isNotNull("email"));
        users.forEach(System.out::println);
    }

    /**
     * 姓王或者年龄大于等于25，年龄降序，id升序
     */
    @Test
    void selectByWrapper3() {
        List<User> users = userMapper.selectList(new QueryWrapper<User>().likeRight("name", "王").
                or().ge("age", 25).orderByDesc("age").orderByAsc("id"));
        users.forEach(System.out::println);
    }

    /**
     * 直属上级为王姓且创建日期是2019年2月14日
     */
    @Test
    void selectByWrapper4() {
        List<User> users = userMapper.selectList(new QueryWrapper<User>()
                .apply("date_format(create_time,'%Y-%m-%d') = {0}", "2019-02-14")
                .inSql("manager_id", "select id from user where name like '王%'"));
        users.forEach(System.out::println);
    }

    /**
     * 姓王且（年龄小于40或邮箱不为空）
     */
    @Test
    void selectByWrapper5() {
        List<User> users = userMapper.selectList(new QueryWrapper<User>()
                .likeRight("name", "王").and(sub -> sub.lt("age", 40).or().isNotNull("email")));
        users.forEach(System.out::println);
    }

    /**
     * 姓王或者（年龄20-40且邮箱不为空）
     */
    @Test
    void selectByWrapper6() {
        List<User> users = userMapper.selectList(new QueryWrapper<User>()
                .likeRight("name", "王").or(sub -> sub.between("age", 20, 40).isNotNull("email")));
        users.forEach(System.out::println);
    }

    /**
     * （年龄小于40或邮箱不为空）且姓王
     */
    @Test
    void selectByWrapper7() {
        List<User> users = userMapper.selectList(new QueryWrapper<User>()
                .nested(sub -> sub.lt("age", 40).or().isNotNull("email")).likeRight("name", "王"));
        users.forEach(System.out::println);
    }

    /**
     * 年龄为30、31、34、35
     */
    @Test
    void selectByWrapper8() {
        List<User> users = userMapper.selectList(new QueryWrapper<User>()
                .in("age", 30, 31, 34, 35));
        users.forEach(System.out::println);
    }

    /**
     * 年龄为30、31、34、35,limit1
     */
    @Test
    void selectByWrapper9() {
        List<User> users = userMapper.selectList(new QueryWrapper<User>()
                .in("age", 30, 31, 34, 35).last("limit 1"));
        users.forEach(System.out::println);
    }

    /**
     * 只查id，name
     */
    @Test
    void selectByWrapper10() {
        List<User> users = userMapper.selectList(new QueryWrapper<User>().select("id", "name")
                .in("age", 30, 31, 34, 35).last("limit 1"));
        users.forEach(System.out::println);
    }

    /**
     * 不查managerId
     */
    @Test
    void selectByWrapper11() {
        List<User> users = userMapper.selectList(new QueryWrapper<User>().select(User.class, item -> !item.getColumn().equals("manager_id"))
                .in("age", 30, 31, 34, 35).last("limit 1"));
        users.forEach(System.out::println);
    }

    /**
     * condition
     */
    @Test
    void selectByWrapper12() {
        int num = new Random().nextInt(100);
        List<Integer> list;
        if (num % 2 == 0) {
            list = Arrays.asList(30, 31, 34, 35);
        } else {
            list = Collections.emptyList();
        }
        List<User> users = userMapper.selectList(new QueryWrapper<User>()
                .in(!list.isEmpty(), "age", list).last("limit 1"));
        users.forEach(System.out::println);
    }

    /**
     * 条件构造器传入实体
     */
    @Test
    void selectByWrapper13() {
        User user = new User();
        //默认等值，如果设置模糊查，@TableField(condition = SqlCondition.LIKE)
        user.setName("刘翔");
        List<User> users = userMapper.selectList(new QueryWrapper<>(user));
        users.forEach(System.out::println);
    }

    /**
     * allEq
     */
    @Test
    void selectByWrapperAllEq() {
        List<User> users = userMapper.selectList(new QueryWrapper<User>()
                .allEq(new HashMap<String, Object>() {{
                    put("name", "刘翔");
                    put("email", null);
                }}));
        users.forEach(System.out::println);
    }

    /**
     * maps
     */
    @Test
    void selectMaps() {
        List<Map<String, Object>> users = userMapper.selectMaps(new QueryWrapper<User>().select("id", "name", "email").eq("name", "刘翔"));
        users.forEach(System.out::println);
    }

    /**
     * select avg(age),min(age),max(age) from user group by manager_id having sum(age)<500
     */
    @Test
    void selectMaps2() {
        List<Map<String, Object>> users = userMapper.selectMaps(new QueryWrapper<User>().select("avg(age)", "min(age)", "max(age)")
                .groupBy("manager_id").having("sum(age)<{0}", 500));
        users.forEach(System.out::println);
    }

    /**
     * objs，只返回一列的时候
     */
    @Test
    void selectObjs() {
        List<Object> users = userMapper.selectObjs(new QueryWrapper<User>().select("id"));
        users.forEach(System.out::println);
    }

    /**
     * count，查询记录数
     */
    @Test
    void selectCount() {
        Integer count = userMapper.selectCount(new QueryWrapper<User>().lt("age", 35));
        System.out.println(count);
    }

    /**
     * one
     */
    @Test
    void selectOne() {
        User user = userMapper.selectOne(new QueryWrapper<User>().eq("name", "刘翔"));
        System.out.println(user);
    }

    /**
     * lambda
     */
    @Test
    void selectLambda() {
        List<User> users = userMapper.selectList(new QueryWrapper<User>().lambda().like(User::getName, "王"));
        users.forEach(System.out::println);
    }

    /**
     * lambdaChain
     */
    @Test
    void selectLambdaChain() {
        List<User> users = new LambdaQueryChainWrapper<>(userMapper).like(User::getName, "雨").list();
        users.forEach(System.out::println);
    }

    /**
     * 自定义sql
     */
    @Test
    void selectMySql() {
        List<User> users = userMapper.selectAll(new QueryWrapper<User>().eq("name", "刘翔"));
        users.forEach(System.out::println);
    }

    /**
     * 分页
     */
    @Test
    void selectPage() {
        //第三个参数为是否查询count
        Page<User> page = new Page<>(2, 2);
//        Page<User> page = new Page<>(2, 2,false);
        IPage<User> userIPage = userMapper.selectPage(page, new LambdaQueryWrapper<User>()
                .ge(User::getAge, 26));
        System.out.println(userIPage.getCurrent());//当前页
        System.out.println(userIPage.getPages());//总页数
        System.out.println(userIPage.getSize());//每页条数
        System.out.println(userIPage.getTotal());//总记录数
        System.out.println(userIPage.getRecords());//记录
    }

    /**
     * 分页，自定义sql
     */
    @Test
    void selectMyPage() {
        //第三个参数为是否查询count
        Page<User> page = new Page<>(2, 2);
//        Page<User> page = new Page<>(2, 2,false);
        IPage<User> userIPage = userMapper.selectMyPage(page, new LambdaQueryWrapper<User>()
                .ge(User::getAge, 26));
        System.out.println(userIPage.getCurrent());//当前页
        System.out.println(userIPage.getPages());//总页数
        System.out.println(userIPage.getSize());//每页条数
        System.out.println(userIPage.getTotal());//总记录数
        System.out.println(userIPage.getRecords());//记录
    }

    /**
     * 更新byId
     */
    @Test
    void updateById() {
        User user = new User();
        user.setId(1251699549527920642L);
        user.setAge(35);
        int i = userMapper.updateById(user);
        System.out.println(i);
    }

    /**
     * 更新byWrapper
     */
    @Test
    void updateWrapper() {
        User user = new User();
        user.setEmail("lyw2@baomidou.com");
        int i = userMapper.update(user, new UpdateWrapper<User>().lambda().eq(User::getName, "李艺伟"));
        System.out.println(i);
    }

    /**
     * 更新set
     */
    @Test
    void updateSet() {
        int i = userMapper.update(null, new UpdateWrapper<User>().lambda().eq(User::getName, "李艺伟").set(User::getAge, 30));
        System.out.println(i);
    }

    /**
     * 更新chain
     */
    @Test
    void updateChain() {
        boolean flg = new LambdaUpdateChainWrapper<>(userMapper).eq(User::getName, "李艺伟").set(User::getAge, 29).update();
        System.out.println(flg);
    }

    /**
     * 删除byId
     */
    @Test
    void delete() {
        int i = userMapper.deleteById(1251699549527920642L);
        System.out.println(i);
    }

    /**
     * 删除byMap
     */
    @Test
    void deleteByMap() {
        int i = userMapper.deleteByMap(new HashMap<String, Object>(1) {{
            put("name", "刘翔");
        }});
        System.out.println(i);
    }

    /**
     * 删除batch
     */
    @Test
    void deleteBatch() {
        int i = userMapper.deleteBatchIds(Arrays.asList(1L, 2L));
        System.out.println(i);
    }

    /**
     * 删除byWrapper
     */
    @Test
    void deleteBtWrapper() {
        int i = userMapper.delete(new LambdaQueryWrapper<User>().eq(User::getName, "刘翔"));
        System.out.println(i);
    }

    /**
     * AR模式，通过实体类直接实现CRUD操作
     */
    @Test
    void ar() {
        User user = new User();
        user.setName("李强");
        user.setAge(25);
        user.setEmail("lq@xxx.com");
        user.setCreateTime(LocalDateTime.now());
//        user.insert();
    }

    /**
     * service getOne
     */
    @Test
    void serviceGetOne() {
        User user = userService.getOne(new LambdaQueryWrapper<User>().eq(User::getName, "李强"));
        System.out.println(user);
    }

    /**
     * service batch
     */
    @Test
    void serviceBatch() {
        User user1 = new User();
        user1.setName("张伟");
        user1.setAge(32);
        user1.setCreateTime(LocalDateTime.now());
        user1.setEmail("zw@xxx.com");
        user1.setManagerId(1087982257332887553L);

        User user2 = new User();
        user2.setName("张大炮");
        user2.setAge(33);
        user2.setCreateTime(LocalDateTime.now());
        user2.setEmail("zdp@xxx.com");
        user2.setManagerId(1087982257332887553L);
        boolean b = userService.saveBatch(Arrays.asList(user1, user2));
        System.out.println(b);
    }

    /**
     * chain
     */
    @Test
    void serviceChain() {
        User user = userService.lambdaQuery().eq(User::getName, "张大炮").one();
        System.out.println(user);
    }

    /**
     * 分页
     */
    @Test
    void servicePage() {
//        MpStudyApplication.tl.set("user_2020");
        IPage<User> page = userService.lambdaQuery().gt(User::getAge, 30).page(new Page<>(2, 2));
        System.out.println(page.getRecords());
        System.out.println(page.getTotal());
        System.out.println(page.getPages());
    }


    //-------------------------进阶分隔符-----------------------------

    /**
     * 逻辑删除
     */
    @Test
    void softDelete() {
        Long id = 1087982257332887553L;
        boolean b = userPlusService.removeById(id);
        System.out.println(b);
        UserPlus byId = userPlusService.getById(id);
        System.out.println(byId);
    }

    /**
     * 自动填充 新增
     */
    @Test
    void testInsert() {
        UserPlus userPlus = new UserPlus();
        userPlus.setName("苏大强");
        userPlus.setAge(45);
        userPlus.setEmail("sdq@xxx.com");
        userPlusService.save(userPlus);
    }

    /**
     * 自动填充 更新
     */
    @Test
    void testUpdate() {
        UserPlus userPlus = new UserPlus();
        userPlus.setId(1251803585799278593L);
        userPlus.setName("苏大强1");
        userPlus.setAge(43);
        userPlus.setEmail("sdq1@xxx.com");
        userPlusService.updateById(userPlus);
    }

    /**
     * 乐观锁
     */
    @Test
    void testUpdate2() {
        UserPlus userPlus = new UserPlus();
        userPlus.setId(1251803585799278593L);
        userPlus.setName("苏大强2");
        userPlus.setAge(50);
        userPlus.setEmail("sdq2@xxx.com");
        userPlus.setVersion(1);
        userPlusService.updateById(userPlus);
    }

    @Autowired
    private UserPlusMapper userPlusMapper;

    /**
     * 乐观锁
     */
    @Test
    void testDeleteAll() {
        int i = userPlusMapper.deleteAll();
        System.out.println(i);
    }
}
