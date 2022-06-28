package br.com.project.lojaimplements.form;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;

import br.com.project.lojaimplements.domain.Product;
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

	public List<ProductForm> convertDomainToDto(List<Product> marca) {
		List<ProductForm> listaType = new ArrayList<>();
		marca.forEach(productDomain -> listaType.add(convertDomainToType(productDomain)));
		return listaType;
	}

	public Product convertTypeToDomain(ProductForm productType) {
		Product productDomain = new Product();
		productDomain.setId(null);
		productDomain.setNome(productType.getNome());
		return productDomain;
	}

	public ProductForm convertDomainToType(Product productDomain) {
		ProductForm productType = new ProductForm();
		productType.setId(productDomain.getId());
		productType.setNome(productDomain.getNome());
		return productType;
	}

}
