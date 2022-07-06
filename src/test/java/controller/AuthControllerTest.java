package controller;

import br.com.project.lojaimplements.ProjectLojaApiApplication;
import br.com.project.lojaimplements.controller.AuthController;
import br.com.project.lojaimplements.controller.UsuarioController;
import br.com.project.lojaimplements.form.LoginForm;
import br.com.project.lojaimplements.form.TokenForm;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = AuthController.class)
@ContextConfiguration(classes = ProjectLojaApiApplication.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class AuthControllerTest {

    @Autowired
    private AuthController authController;

    @Test
    void deveriaRetornar400CasoDadosDeAutenticacaoEstejamErrados() throws Exception {

        LoginForm login = new LoginForm();
        login.setEmail("invalid@gmail.com");
        login.setPassword("123456");

        ResponseEntity<TokenForm> token = authController.authentication(login);

        assertEquals(HttpStatus.BAD_REQUEST, token.getStatusCode());

    }

    @Test
    void deveriaRetornar200Sucesso() throws Exception{

        LoginForm login = new LoginForm();
        login.setEmail("priii@gmail.com");
        login.setPassword("123456");

        ResponseEntity<TokenForm> token = authController.authentication(login);

        assertEquals(HttpStatus.OK, token.getStatusCode());

    }
}
