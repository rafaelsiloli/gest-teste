package br.com.gestiona.apiconsulta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass = true)
@ComponentScan("br.com.gestiona.apiconsulta")
@EntityScan("br.com.gestiona.apiconsulta")
public class ApiConsultaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiConsultaApplication.class, args);
	}

}
