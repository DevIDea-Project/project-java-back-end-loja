package br.com.project.lojaimplements.impl;

import br.com.project.lojaimplements.domain.Usuario;
import br.com.project.lojaimplements.form.UsuarioForm;
import br.com.project.lojaimplements.repository.UsuarioRepository;
import br.com.project.lojaimplements.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private UsuarioForm usuarioForm;

	@Override
	public UsuarioForm saveUsuario(UsuarioForm usuarioType) throws Exception{
		Optional<Usuario> usuarioExistente = usuarioRepository.findByEmail(usuarioType.getEmail());
		if(usuarioExistente.isPresent()) {
			throw new Exception("E-mail já associado a um usuário");
		}
		usuarioType.setPassword(new BCryptPasswordEncoder().encode(usuarioType.getPassword()));
		Usuario usuarioSave = usuarioRepository.save(usuarioForm.convertDtoToDomain(usuarioType));
		return usuarioForm.convertDomainToDto(usuarioSave);
	}

	@Override
	public List<UsuarioForm> findAllByOrderByNomeBrand() {
		List<Usuario> listUsuario = usuarioRepository.findAllByOrderByNomeAsc();
		return usuarioForm.convertListDomainToDto(listUsuario);
	}

	@Override
	public UsuarioForm findByIdUsuario(Long id) {
		Optional<Usuario> m1 = usuarioRepository.findById(id);
		return m1.map(usuario -> usuarioForm.convertDomainToDto((usuario))).orElse(null);
	}

	@Override
	public UsuarioForm removeUser(Long id) {
		Optional<Usuario> u1 = usuarioRepository.findById(id);
		if (u1.isPresent()) {
			Usuario u2 = u1.get();
			usuarioRepository.delete(u2);
			return usuarioForm.convertDomainToDto(u2);
		} else {
			return null;
		}
	}

}
