package br.com.project.lojaimplements.service;

import br.com.project.lojaimplements.form.PerfilForm;
import org.springframework.stereotype.Service;

@Service
public interface PerfilService {

    public PerfilForm savePerfil(PerfilForm perfilForm);

}
