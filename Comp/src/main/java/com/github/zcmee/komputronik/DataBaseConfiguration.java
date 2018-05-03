package com.github.zcmee.komputronik;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@ConfigurationProperties(prefix = "spring.datasource")
public class DataBaseConfiguration {

    private String driverClassName;
    private String jdbcUrl;
    private String username;
    private String password;
    private int maximumPoolSize;
    private int minimumIdle;
    private long idleTimeout;

    @Bean(destroyMethod = "close")
    public DataSource dataSource() {
        final HikariConfig dataSourceConfig = new HikariConfig();
        dataSourceConfig.setDriverClassName(driverClassName);
        dataSourceConfig.setJdbcUrl(jdbcUrl);
        dataSourceConfig.setUsername(username);
        dataSourceConfig.setPassword(password);
        dataSourceConfig.setMaximumPoolSize(maximumPoolSize);
        dataSourceConfig.setMinimumIdle(minimumIdle);
        dataSourceConfig.setIdleTimeout(idleTimeout);

        return new HikariDataSource(dataSourceConfig);
    }

    public String getDriverClassName() {
        return driverClassName;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    public String getJdbcUrl() {
        return jdbcUrl;
    }

    public void setJdbcUrl(String jdbcUrl) {
        this.jdbcUrl = jdbcUrl;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getMaximumPoolSize() {
        return maximumPoolSize;
    }

    public void setMaximumPoolSize(int maximumPoolSize) {
        this.maximumPoolSize = maximumPoolSize;
    }

    public int getMinimumIdle() {
        return minimumIdle;
    }

    public void setMinimumIdle(int minimumIdle) {
        this.minimumIdle = minimumIdle;
    }

    public long getIdleTimeout() {
        return idleTimeout;
    }

    public void setIdleTimeout(long idleTimeout) {
        this.idleTimeout = idleTimeout;
    }

}
