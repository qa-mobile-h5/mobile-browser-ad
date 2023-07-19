package com.group.chat.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Component
@ConfigurationProperties(prefix = "mysql.datasource.druid")
@MapperScan(basePackages = {"com.group.chat.dao"},
        sqlSessionTemplateRef = "sqlSessionTemplate")
public class DatabaseConfig {

    @Value("${mysql.datasource.druid.driverClassName}")
    public String driverClassName;

    @Value("${mysql.datasource.druid.url}")
    public String url;

    @Value("${mysql.datasource.druid.username}")
    public String username;

    @Value("${mysql.datasource.druid.password}")
    public String password;

    @Value("${mysql.datasource.druid.initialSize}")
    public int initialSize;

    @Value("${mysql.datasource.druid.maxActive}")
    public int maxActive;

    @Value("${mysql.datasource.druid.minIdle}")
    public int minIdle;

    @Value("${mysql.datasource.druid.maxWait}")
    public int maxWait;

    @Value("${mysql.datasource.druid.poolPreparedStatements}")
    public boolean poolPreparedStatements;

    @Value("${mysql.datasource.druid.maxPoolPreparedStatementPerConnectionSize}")
    public int maxPoolPreparedStatementPerConnectionSize;

    @Value("${mysql.datasource.druid.validationQuery}")
    public String validationQuery;

    @Value("${mysql.datasource.druid.validationQueryTimeout}")
    public int validationQueryTimeout;

    @Value("${mysql.datasource.druid.testOnBorrow}")
    public boolean testOnBorrow;

    @Value("${mysql.datasource.druid.testOnReturn}")
    public boolean testOnReturn;

    @Value("${mysql.datasource.druid.testWhileIdle}")
    public boolean testWhileIdle;

    @Value("${mysql.datasource.druid.timeBetweenEvictionRunsMillis}")
    public int timeBetweenEvictionRunsMillis;

    @Value("${mysql.datasource.druid.minEvictableIdleTimeMillis}")
    public int minEvictableIdleTimeMillis;

    @Value("${mysql.datasource.druid.connectionInitSqls}")
    public String connectionInitSqls;

    @Value("${mybatis.mapper-locations}")
    public String mapperLocation;

    @Bean(name = "dataSource")
    public DruidDataSource dataSource() {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName(driverClassName);
        druidDataSource.setUrl(url);
        druidDataSource.setUsername(username);
        druidDataSource.setPassword(password);
        druidDataSource.setInitialSize(initialSize);
        druidDataSource.setMaxActive(maxActive);
        druidDataSource.setMinIdle(minIdle);
        druidDataSource.setMaxWait(maxWait);
        druidDataSource.setPoolPreparedStatements(poolPreparedStatements);
        druidDataSource.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);
        druidDataSource.setValidationQuery(validationQuery);
        druidDataSource.setValidationQueryTimeout(validationQueryTimeout);
        druidDataSource.setTestOnBorrow(testOnBorrow);
        druidDataSource.setTestOnReturn(testOnReturn);
        druidDataSource.setTestWhileIdle(testWhileIdle);
        druidDataSource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        druidDataSource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        List<String> list = new ArrayList<>();
        list.add(connectionInitSqls);
        druidDataSource.setConnectionInitSqls(list);
        return druidDataSource;
    }

    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("dataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(mapperLocation));
        return sqlSessionFactoryBean.getObject();
    }

    @Bean(name = "sqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("sqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    /*
     * 解决druid 日志报错：discard long time none received connection:xxx
     * */
    @PostConstruct
    public void setProperties(){
        System.setProperty("druid.mysql.usePingMethod","false");
    }

    @Override
    public String toString() {
        return "DatabaseConfig{" +
                "driverClassName='" + driverClassName + '\'' +
                ", url='" + url + '\'' +
                ", username='" + username + '\'' +
                ", pwd='" + password + '\'' +
                ", initialSize=" + initialSize +
                ", maxActive=" + maxActive +
                ", minIdle=" + minIdle +
                ", maxWait=" + maxWait +
                ", poolPreparedStatements=" + poolPreparedStatements +
                ", maxPoolPreparedStatementPerConnectionSize=" + maxPoolPreparedStatementPerConnectionSize +
                ", validationQuery='" + validationQuery + '\'' +
                ", validationQueryTimeout=" + validationQueryTimeout +
                ", testOnBorrow=" + testOnBorrow +
                ", testOnReturn=" + testOnReturn +
                ", testWhileIdle=" + testWhileIdle +
                ", timeBetweenEvictionRunsMillis=" + timeBetweenEvictionRunsMillis +
                ", minEvictableIdleTimeMillis=" + minEvictableIdleTimeMillis +
                ", mapperLocation='" + mapperLocation + '\'' +
                '}';
    }
}