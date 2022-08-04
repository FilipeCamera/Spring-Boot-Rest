package com.estudo.filipe.estudo.MVC;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
@EnableSpringDataWebSupport // permite fazer a questao da paginacao
@EnableCaching // habilita uso de cache na aplicacao
public class EstudoMvcApplication {

	public static void main(String[] args) {
		SpringApplication.run(EstudoMvcApplication.class, args);
	}

}
