package br.com.project.lojaimplements.repository;


import br.com.project.lojaimplements.domain.Product;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Configuration
@EnableAutoConfiguration
public interface ProductRepository extends JpaRepository<Product, Long>{
	
	public List<Product> findAllByOrderByNomeAsc();
	
}
