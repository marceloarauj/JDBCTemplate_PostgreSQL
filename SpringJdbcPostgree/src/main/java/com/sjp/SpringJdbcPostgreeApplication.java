package com.sjp;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import com.sjp.models.Usuario;
import com.sjp.services.UsuarioService;

@SpringBootApplication
@ComponentScan("com.sjp.services, "
		+ "com.sjp.dao")

public class SpringJdbcPostgreeApplication {

	public static void main(String[] args) {
		
		ApplicationContext context = SpringApplication.run
				(SpringJdbcPostgreeApplication.class, args);
		
		
		UsuarioService ser = context.getBean(UsuarioService.class);
		
		Usuario user = new Usuario();
		user.setId(1);
		user.setNome("Pessoa 1");
		user.setCpf("999.999.999-99");
		
		ser.insert(user);
		
		user = new Usuario();
		user.setId(2);
		user.setNome("Pessoa 2");
		user.setCpf("555.555.555-55");
		
		ser.insert(user);
	}

}
