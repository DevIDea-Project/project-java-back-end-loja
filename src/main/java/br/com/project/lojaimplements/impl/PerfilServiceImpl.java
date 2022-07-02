package br.com.project.lojaimplements.impl;

import br.com.project.lojaimplements.domain.Perfil;
import br.com.project.lojaimplements.form.PerfilForm;
import br.com.project.lojaimplements.repository.PerfilRepository;
import br.com.project.lojaimplements.service.PerfilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PerfilServiceImpl implements PerfilService {

    @Autowired
    private PerfilRepository perfilRepository;

    @Autowired
    private PerfilForm perfilForm;

    @Override
    public PerfilForm savePerfil(PerfilForm perfilForm) {
        Perfil perfilSave = perfilRepository.save(perfilForm.convertTypeToDomain(perfilForm));
        return perfilForm.convertDomainToType(perfilSave);

    }
}
