package br.com.project.lojaimplements.repository;

import br.com.project.lojaimplements.domain.Usuario;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;


@Configuration
@EnableAutoConfiguration
@ComponentScan("br.com.caelum.carangobom.repository")
public interface AuthRepository extends JpaRepository<Usuario, Long> {
	
	
}