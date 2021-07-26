package it.operazioni.ms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableFeignClients(basePackages = {"it.fabrick.test.feignclient"})
@ComponentScan(basePackages = {"it.fabrick.test", "it.operazioni.ms"})
@EntityScan({"it.fabrick.test.entity"})
@EnableJpaRepositories({"it.fabrick.test.repository"})
@Configuration
public class OperazioniMsApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(OperazioniMsApplication.class);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(OperazioniMsApplication.class, args);
	}
}
