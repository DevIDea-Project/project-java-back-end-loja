package br.com.project.lojaimplements.domain;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "tb_product")
public class Product {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long id;

	@NotBlank
	@Size(min = 2, message = "Deve ter {min} ou mais caracteres.")
	private String nome;

	public Product() {

	}

	public Product(String nome) {
		this(null, nome);
	}

	public Product(Long id, String nome) {
		this.id = id;
		this.nome	 = nome;
	}

}
