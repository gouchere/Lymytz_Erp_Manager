package lym.com;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import lym.com.api.Initialisation;
import lym.com.api.model.base.LtzCtlAbonnements;
import lym.com.api.repository.jdbc.CustomJdbcRepo;

@SpringBootApplication
@EnableScheduling
public class LymytzErpManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(LymytzErpManagerApplication.class, args);
		Initialisation.contextInitialized(); 
		CustomJdbcRepo test=new CustomJdbcRepo();
		test.fillDataWithJdbc(new LtzCtlAbonnements(), null);
		
	} 
} 
