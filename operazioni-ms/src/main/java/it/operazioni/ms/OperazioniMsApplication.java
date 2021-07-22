package it.operazioni.ms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@EnableFeignClients(basePackages = {"it.fabrick.test.feignclient"})
@ComponentScan(basePackages = {"it.fabrick.test", "it.operazioni.ms"})
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
