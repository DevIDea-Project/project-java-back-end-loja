package controller;

import br.com.project.lojaimplements.ProjectLojaApiApplication;
import br.com.project.lojaimplements.controller.AuthController;
import br.com.project.lojaimplements.controller.ProductController;
import br.com.project.lojaimplements.domain.Product;
import br.com.project.lojaimplements.form.ProductForm;
import br.com.project.lojaimplements.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.validation.BindingResult;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.MockitoAnnotations.openMocks;

@SpringBootTest(classes = AuthController.class)
@ContextConfiguration(classes = ProjectLojaApiApplication.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ProductControllerTest {

    @Autowired
    private ProductController productController;

    @Mock
    private ProductRepository productRepository;

    @Mock
    BindingResult bindingResult;

    @BeforeEach
    public void configuraMock() {
        openMocks(this);
    }

    private ProductForm createProductForm(String nome) {
        ProductForm nova = new ProductForm();
        nova.setNome(nome);
        return nova;
    }

    @Test
    void deveRetornarListaQuandoHouverResultados() {
        ResponseEntity<List<ProductForm>> listProduct = productController.list();
        boolean result = listProduct.getBody() != null && !listProduct.getBody().isEmpty() ? true : false;
        assertTrue(result);
    }

    @Test
    void deveRetornarProductPeloId() {
        ResponseEntity<Product> resposta = productController.id(1L);
        assertEquals(HttpStatus.OK, resposta.getStatusCode());
    }

    @Test
    void deveRetornarNotFoundQuandoRecuperarProductComIdInexistente() {
        ResponseEntity<Product> resposta = productController.id(1000L);
        assertEquals(HttpStatus.NOT_FOUND, resposta.getStatusCode());
    }

    @Test
    void deveResponderCreatedELocationQuandoCadastrarMarca() {
        ProductForm nova = createProductForm("Escova");
        ResponseEntity<ProductForm> resposta = productController.create(nova);
        assertEquals(HttpStatus.CREATED, resposta.getStatusCode());
    }

    @Test
    void deveAlterarNomeQuandoMarcaExistir() {
        ProductForm nova = createProductForm("Novo Escova");
        ResponseEntity<ProductForm> resposta = productController.altera(1L, nova);
        assertEquals(HttpStatus.OK, resposta.getStatusCode());
        ProductForm productAlterada = resposta.getBody();
        assert productAlterada != null;
        assertEquals("Novo Escova", productAlterada.getNome());
    }

    @Test
    void naoDeveAlterarMarcaInexistente() {
        ProductForm nova = createProductForm("Novo Escova");
        ResponseEntity<ProductForm> resposta = productController.altera(1000L, nova);
        assertEquals(HttpStatus.NOT_FOUND, resposta.getStatusCode());
    }

    @Test
    void deveDeletarMarcaExistente() throws Exception {
        ResponseEntity<ProductForm> resposta = productController.delete(1L);
        assertEquals(HttpStatus.OK, resposta.getStatusCode());
    }

    @Test
    void naoDeveDeletarMarcaInexistente() throws Exception {
        ResponseEntity<ProductForm> resposta = productController.delete(1000L);
        assertEquals(HttpStatus.NOT_FOUND, resposta.getStatusCode());
    }
}
