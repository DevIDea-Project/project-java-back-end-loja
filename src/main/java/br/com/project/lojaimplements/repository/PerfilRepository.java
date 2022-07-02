package br.com.project.lojaimplements.repository;

import br.com.project.lojaimplements.domain.Perfil;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;


@Configuration
@EnableAutoConfiguration
public interface PerfilRepository extends JpaRepository<Perfil, Long> {}
