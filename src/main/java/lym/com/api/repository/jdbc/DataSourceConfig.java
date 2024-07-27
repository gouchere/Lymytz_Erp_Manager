package lym.com.api.repository.jdbc;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class DataSourceConfig {
	@Value("${spring.datasource.driverClassName}")
    private String driverClass;
    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;
	
	@Bean(name = "dataSource")
	@ConfigurationProperties(prefix = "app.datasource")
	public DataSource getDataSource() {
		return  DataSourceBuilder.create().build();
	}
	
	@Bean(name = "postgresJdbcTemplate")
	 public JdbcTemplate postgresJdbcTemplate(@Qualifier("dataSource")
	                                          DataSource dsPostgres) {
	    return new JdbcTemplate(dsPostgres);
	 }
	
//	@Bean
//	@Primary
//	@ConfigurationProperties("app.datasource")
//	public DataSourceProperties dataSourceProperties() {
//	    return new DataSourceProperties();
//	}
//
//	@Bean
//	@ConfigurationProperties("app.datasource")
//	public DataSource dataSource(DataSourceProperties properties) {
//		System.err.println("Initialized datasource"+properties.initializeDataSourceBuilder().build() );
//	    return properties.initializeDataSourceBuilder().
//	        build();
//	}
	
//	@Bean
//    public DataSource dataSource(){
//        System.out.println(driverClass+" "+ url+" "+username+" "+password);
//        DriverManagerDataSource source = new DriverManagerDataSource();
//        source.setDriverClassName(driverClass);
//        source.setUrl(url);
//        source.setUsername(username);
//        source.setPassword(password);
//        try {
//			System.err.println("ds= "+source.getConnection());
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} 
//        return source;
//    }
}
