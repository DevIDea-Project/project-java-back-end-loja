package br.com.project.lojaimplements.controller;

import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import br.com.project.lojaimplements.domain.Usuario;
import br.com.project.lojaimplements.form.PerfilForm;
import br.com.project.lojaimplements.form.UsuarioForm;
import br.com.project.lojaimplements.service.PerfilService;
import br.com.project.lojaimplements.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("usuario")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private PerfilService perfilService;


	@GetMapping()
    @ResponseBody
    @Transactional
    public List<UsuarioForm> lista() {
        return usuarioService.findAllByOrderByNomeBrand();
    }

	@PostMapping()
    @ResponseBody
    @Transactional
    public ResponseEntity<UsuarioForm> cadastraUsuario(@Valid @RequestBody UsuarioForm usuario) throws Exception {
    	return new ResponseEntity<>(usuarioService.saveUsuario(usuario), null, HttpStatus.CREATED);
    }

	@PostMapping("/perfil")
	@ResponseBody
	@Transactional
	public ResponseEntity<PerfilForm> cadastraPerfil(@Valid @RequestBody PerfilForm perfil) throws Exception {
		return new ResponseEntity<>(perfilService.savePerfil(perfil), null, HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	@ResponseBody
	@Transactional
	@Validated
	public ResponseEntity<Usuario> listById(@PathVariable Long id) {
		Usuario usuarioId = usuarioService.findByIdUsuario(id);
		if (usuarioId != null) {
			return new ResponseEntity<>(usuarioId, null, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(usuarioId, null, HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/{id}")
	@ResponseBody
	@Transactional
	@Validated
	public ResponseEntity<UsuarioForm> deleta(@PathVariable Long id) {
		UsuarioForm usuario = usuarioService.removeUser(id);
		if (usuario != null) {
			return new ResponseEntity<>(usuario, null, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(usuario, null, HttpStatus.NOT_FOUND);			
		}

	}
}
