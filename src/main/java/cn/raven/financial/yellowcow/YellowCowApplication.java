package cn.raven.financial.yellowcow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication
public class YellowCowApplication {

	public static void main(String[] args) {
		SpringApplication.run(YellowCowApplication.class, args);
	}
}
