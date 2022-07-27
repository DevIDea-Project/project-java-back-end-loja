package br.com.project.lojaimplements.controller;

import br.com.project.lojaimplements.form.ProductForm;
import br.com.project.lojaimplements.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller()
@RequestMapping("product")
public class ProductController {
	
	@Autowired
	private ProductService productService;

	@GetMapping()
    @ResponseBody
    public ResponseEntity<List<ProductForm>> list() {
        return new ResponseEntity<>(productService.findAllByOrderByNomeProduct(), null, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ResponseBody
    @Transactional
    public ResponseEntity<ProductForm> id(@PathVariable Long id){
		ProductForm product = productService.findByIdProduct(id);
    	if(product != null) {
    		return new ResponseEntity<>(product, null, HttpStatus.OK);
    	}else {
    		return new ResponseEntity<>(product, null, HttpStatus.NOT_FOUND);
    	}
    }
    

    @PostMapping()
    @ResponseBody
    public ResponseEntity<ProductForm> create(@Valid @RequestBody ProductForm product) {
    	return new ResponseEntity<>(productService.saveProduct(product), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<ProductForm> altera(@PathVariable Long id, @Valid @RequestBody ProductForm productForm){
    	ProductForm product = productService.updateProduct(id, productForm);
    	if(product != null) {
    		return new ResponseEntity<>(product, null, HttpStatus.OK);
    	}else {
    		return new ResponseEntity<>(product, null, HttpStatus.NOT_FOUND);
    	}
    }

	@PutMapping("/adding/{id}")
	@ResponseBody
	public ResponseEntity<ProductForm> productAddingQuantity(@PathVariable Long id, @Valid @RequestBody ProductForm productForm){
		ProductForm product = productService.updateProductQuantity(id, productForm);
		if(product != null) {
			return new ResponseEntity<>(product, null, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(product, null, HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/{id}")
	@ResponseBody
	public ResponseEntity<ProductForm> delete(@PathVariable Long id) throws Exception  {
		ProductForm productform = productService.removeProduct(id);
		if (productform != null) {
			return new ResponseEntity<>(productform, null, HttpStatus.OK);
		}else {
			return ResponseEntity.notFound().build();			
		}

	}
}