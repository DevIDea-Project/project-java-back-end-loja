package controller;

import br.com.project.lojaimplements.ProjectLojaApiApplication;
import br.com.project.lojaimplements.controller.UsuarioController;
import br.com.project.lojaimplements.form.UsuarioForm;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = UsuarioController.class)
@ContextConfiguration(classes = ProjectLojaApiApplication.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class UsuarioControllerTest {

    @Autowired
    private UsuarioController usuarioController;

    @Test
    void deveCadastrarUsuario() throws Exception {

        UsuarioForm newUsuario = new UsuarioForm();
        newUsuario.setName("Priscilla Basto");
        newUsuario.setEmail("priii@gmail.com");
        newUsuario.setPassword("123456");

        ResponseEntity<UsuarioForm> cadastroUsuario = usuarioController.cadastraUsuario(newUsuario);
        boolean result = cadastroUsuario.getBody() != null && cadastroUsuario.getStatusCode() == HttpStatus.CREATED ? true : false;

        assertTrue(result);

    }

    @Test
    void getListaUsuarios() {
        List<UsuarioForm> usuarioType = usuarioController.lista();
        boolean result = usuarioType != null && !usuarioType.isEmpty() ? true : false;
        assertTrue(result);
    }

    @Test
    void deveDeletarUsuarioComSucesso() throws Exception {
        Integer idUsuario = 1;
        ResponseEntity<UsuarioForm> usuarioDel = usuarioController.deleta(idUsuario.longValue());
        assertEquals(HttpStatus.OK, usuarioDel.getStatusCode());
        assertEquals(idUsuario, usuarioDel.getBody().getId().intValue());

    }

    @Test
    void deletarUsuarioInexistente() throws Exception {
        Integer idUsuario = 1000;
        ResponseEntity<UsuarioForm> usuarioDel = usuarioController.deleta(idUsuario.longValue());
        assertEquals(HttpStatus.NOT_FOUND, usuarioDel.getStatusCode());

    }
}
