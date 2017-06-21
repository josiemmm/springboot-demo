package com.wz.config;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * Created by wangzi on 2017/4/19.
 */
@Configuration
@MapperScan(basePackages = {"com.wz.mapper"}, sqlSessionFactoryRef = "defaultSqlSessionFactory")
public class MybatisConfiguration {
    @Autowired
    private DataSource dataSource;

    @Bean(name = "defaultSqlSessionFactory")
    public SqlSessionFactoryBean sqlSessionFactory(
            @Value("${mybatis.mapperLocations}") String mapperLocations,
            @Value("${mybatis.typeAliasesPackage}") String typeAliasesPackage) throws Exception {
        SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);
        sessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(mapperLocations));
        sessionFactoryBean.setTypeAliasesPackage(typeAliasesPackage);
        return sessionFactoryBean;
    }

    @Bean(name = "defaultTransactionManager")
    public DataSourceTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource);
    }
}
