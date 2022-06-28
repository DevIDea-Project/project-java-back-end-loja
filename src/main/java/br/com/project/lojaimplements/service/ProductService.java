package br.com.project.lojaimplements.service;

import java.util.List;

import br.com.project.lojaimplements.domain.Product;
import br.com.project.lojaimplements.form.ProductForm;
import org.springframework.stereotype.Service;

@Service
public interface ProductService {

	public ProductForm removeProduct(Long id) throws Exception;
    
    public ProductForm updateProduct(Long id, ProductForm productForm);

    public ProductForm saveProduct(ProductForm productForm);

    public Product findByIdProduct(Long id);

    public List<ProductForm> findAllByOrderByNomeProduct();

}
