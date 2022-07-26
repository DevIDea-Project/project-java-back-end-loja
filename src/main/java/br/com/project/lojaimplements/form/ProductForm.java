package br.com.project.lojaimplements.form;

import java.util.ArrayList;
import java.util.Date;
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
	@NotBlank
	private String medida;
	@NotBlank
	private String etiqueta;
	@NotBlank
	private String categoria = "";
	private Float inQuantityItem;
	private Float outQuantityItem;
	private Float totalResult;
	private Date createDate;
	private Date modifyDate;

	public List<ProductForm> convertDomainToDto(List<Product> marca) {
		List<ProductForm> listaType = new ArrayList<>();
		marca.forEach(productDomain -> listaType.add(convertDomainToType(productDomain)));
		return listaType;
	}

	public Product convertTypeToDomain(ProductForm productType) {
		Product productDomain = new Product();
		productDomain.setId(null);
		productDomain.setNome(productType.getNome());
		productDomain.setMedida(productType.getMedida());
		productDomain.setEtiqueta(productType.getEtiqueta());
		productDomain.setCategoria(productType.getCategoria());
		productDomain.setInQuantityItem(productType.getInQuantityItem());
		productDomain.setOutQuantityItem(productType.getOutQuantityItem());
		Float result = (productType.getInQuantityItem()) - (productType.getOutQuantityItem());
		productDomain.setTotalResult(result);
		productDomain.setCreateDate(productType.getCreateDate());
		productDomain.setModifyDate(productType.getModifyDate());

		return productDomain;
	}

	public ProductForm convertDomainToType(Product productDomain) {
		ProductForm productType = new ProductForm();
		productType.setId(productDomain.getId());
		productType.setNome(productDomain.getNome());
		productType.setMedida(productDomain.getMedida());
		productType.setEtiqueta(productDomain.getEtiqueta());
		productType.setCategoria(productDomain.getCategoria());
		productType.setInQuantityItem(productDomain.getInQuantityItem());
		productType.setOutQuantityItem(productDomain.getOutQuantityItem());
		productType.setTotalResult(productDomain.getTotalResult());
		productType.setCreateDate(productDomain.getCreateDate());
		productType.setModifyDate(productDomain.getModifyDate());
		return productType;
	}

}
