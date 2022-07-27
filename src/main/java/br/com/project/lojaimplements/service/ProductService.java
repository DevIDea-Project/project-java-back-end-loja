package br.com.project.lojaimplements.service;

import br.com.project.lojaimplements.form.ProductForm;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {

	public ProductForm removeProduct(Long id) throws Exception;
    
    public ProductForm updateProduct(Long id, ProductForm productForm);


    public ProductForm updateProductQuantity(Long id, ProductForm productForm);

    public ProductForm saveProduct(ProductForm productForm);

    public ProductForm findByIdProduct(Long id);

    public List<ProductForm> findAllByOrderByNomeProduct();

}
