package br.com.project.lojaimplements.impl;

import java.util.List;
import java.util.Optional;

import br.com.project.lojaimplements.domain.Product;
import br.com.project.lojaimplements.form.ProductForm;
import br.com.project.lojaimplements.repository.ProductRepository;
import br.com.project.lojaimplements.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ProductForm productForm;


	@Override
	public ProductForm removeProduct(Long id) throws Exception {
		Optional<Product> m1 = productRepository.findById(id);
		if (m1.isPresent()) {
			Product m2 = m1.get();
			productRepository.delete(m2);
			return productForm.convertDomainToType(m2);
		} else {
			return null;
		}
	}

	@Override
	public ProductForm updateProduct(Long id, ProductForm productForm) {
		Optional<Product> productDomain = productRepository.findById(id);
		if (productDomain.isPresent()) {
			Product productSave = productDomain.get();
			productSave.setNome(productForm.getNome());
			return productForm.convertDomainToType(productRepository.saveAndFlush(productSave));
		} else {
			return null;
		}
	}

	@Override
	public ProductForm saveProduct(ProductForm productForm) {
		Product productSave = productRepository.save(productForm.convertTypeToDomain(productForm));
		return productForm.convertDomainToType(productSave);
	}

	@Override
	public Product findByIdProduct(Long id) {
		Optional<Product> m1 = productRepository.findById(id);
		if (m1.isPresent()) {
			return m1.get();
		} else {
			return null;
		}
	}

	@Override
	public List<ProductForm> findAllByOrderByNomeProduct() {
		List<Product> listProduct = productRepository.findAllByOrderByNomeAsc();
		return productForm.convertDomainToDto(listProduct);
	}
}

