package br.com.project.lojaimplements.service;

import java.util.List;

import br.com.project.lojaimplements.domain.Product;
import br.com.project.lojaimplements.domain.Usuario;
import br.com.project.lojaimplements.form.UsuarioForm;
import org.springframework.stereotype.Service;

@Service
public interface UsuarioService {

	
	public UsuarioForm saveUsuario(UsuarioForm u1) throws Exception;
	
	public List<UsuarioForm> findAllByOrderByNomeBrand();

	public Usuario findByIdUsuario(Long id);
	
	public UsuarioForm removeUser(Long id);
}
