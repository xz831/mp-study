package com.xz.mpstudy;

import com.baomidou.mybatisplus.core.parser.ISqlParser;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;

@SpringBootApplication
//@MapperScan("com.xz.mpstudy.mapper")
public class MpStudyApplication {

    public static void main(String[] args) {
        SpringApplication.run(MpStudyApplication.class, args);
    }

    @Bean
    public PaginationInterceptor paginationInterceptor(){
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        ArrayList<ISqlParser> iSqlParsers = new ArrayList<>();
//        TenantSqlParser tenantSqlParser = new TenantSqlParser();
//        tenantSqlParser.setTenantHandler(new TenantHandler() {
//            @Override
//            public Expression getTenantId(boolean where) {
//                return new LongValue(1088248166370832385L);
//            }
//
//            @Override
//            public String getTenantIdColumn() {
//                return "manager_id";
//            }
//
//            @Override
//            public boolean doTableFilter(String tableName) {
//                return "role".equals(tableName);
//            }
//        });
//        iSqlParsers.add(tenantSqlParser);
//        paginationInterceptor.setSqlParserFilter(metaObject -> {
//            MappedStatement mappedStatement = SqlParserHelper.getMappedStatement(metaObject);
//            if("com.xz.mpstudy.mapper.UserMapper.selectAll".equals(mappedStatement.getId())){
//                return true;
//            }
//            return false;
//        });
//        DynamicTableNameParser dynamicTableNameParser = new DynamicTableNameParser();
//        Map<String, ITableNameHandler> map = new HashMap<>();
//        map.put("user", (metaObject, sql, tableName) -> tl.get());
//        dynamicTableNameParser.setTableNameHandlerMap(map);
//        iSqlParsers.add(dynamicTableNameParser);
//        paginationInterceptor.setSqlParserList(iSqlParsers);
        return paginationInterceptor;
    }

    public static ThreadLocal<String> tl = new ThreadLocal<>();

    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor(){
        return new OptimisticLockerInterceptor();
    }
}
