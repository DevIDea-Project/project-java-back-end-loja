package br.com.project.lojaimplements.domain;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.Date;

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

	private Integer quantityProduct;

	@NotBlank
	private String description;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_date")
	private Date createDate;

	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modify_date")
	private Date modifyDate;

	private Float priceProduct;

	public Product() {

	}

	public Product(String nome) {
		this(null, nome);
	}

	public Product(Long id, String nome) {
		this.id = id;
		this.nome = nome;
	}

}
