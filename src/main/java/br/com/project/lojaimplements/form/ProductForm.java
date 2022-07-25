package br.com.project.lojaimplements.form;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;

import br.com.project.lojaimplements.domain.Product;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@Getter
@Setter
public class ProductForm {

	private Long id;
	@NotBlank
	private String nome;
	private Integer quantityProduct;
	@NotBlank
	private String Description;
	private Date createDate;
	private Date modifyDate;
	private Float priceProduct;


	public List<ProductForm> convertDomainToDto(List<Product> marca) {
		List<ProductForm> listaType = new ArrayList<>();
		marca.forEach(productDomain -> listaType.add(convertDomainToType(productDomain)));
		return listaType;
	}

	public Product convertTypeToDomain(ProductForm productType) {
		Product productDomain = new Product();
		productDomain.setId(null);
		productDomain.setNome(productType.getNome());
		productDomain.setQuantityProduct(productType.getQuantityProduct());
		productDomain.setDescription(productType.getDescription());
		productDomain.setCreateDate(productType.getCreateDate());
		productDomain.setModifyDate(productType.getModifyDate());
		productDomain.setPriceProduct(productType.getPriceProduct());
		return productDomain;
	}

	public ProductForm convertDomainToType(Product productDomain) {
		ProductForm productType = new ProductForm();
		productType.setId(productDomain.getId());
		productType.setNome(productDomain.getNome());
		productType.setQuantityProduct(productDomain.getQuantityProduct());
		productType.setDescription(productDomain.getDescription());
		productType.setCreateDate(productDomain.getCreateDate());
		productType.setModifyDate(productDomain.getModifyDate());
		productType.setPriceProduct(productDomain.getPriceProduct());
		return productType;
	}

}
