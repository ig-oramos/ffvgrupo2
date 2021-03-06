package com.fatec.scel2.servico;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import com.fatec.scel2.model.Endereco;
import com.fatec.scel2.model.Usuario;
import com.fatec.scel2.model.UsuarioRepository;

@Service
public class UsuarioServico {;
    Logger logger = LogManager.getLogger(UsuarioServico.class);
    @Autowired
    private UsuarioRepository repository;

    public Iterable<Usuario> findAll() {
        return repository.findAll();
    }

    public Usuario findById(Long id) {
        return repository.findById(id).get();
    }

    public Usuario findByRa(String ra) {
        return repository.findByRa(ra);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public void save(Usuario usuario) {
        repository.save(usuario);
    }

    public String obtemEndereco(String cep) {
        RestTemplate template = new RestTemplate();
        String url = "https://viacep.com.br/ws/" + cep + "/json/";
        Endereco endereco = template.getForObject(url, Endereco.class, cep);
        return endereco.getLogradouro();
    }

    public ModelAndView verificaRaExiste(Usuario usuario) {
        ModelAndView mv = new ModelAndView("usuarios/consultarUsuario");

        try {
            Usuario jaExiste = null;
            jaExiste = repository.findByRa(usuario.getRa());
            logger.info("=======> Verifica se usuário já existe = " + usuario.getRa());

            if (jaExiste == null) {
                logger.info("=======> Usuário não cadastrado");
                usuario.setEndereco(obtemEndereco(usuario.getCep()));
                repository.save(usuario);
                mv.addObject("usuarios", repository.findAll());
            } else {
                logger.info("=======> Usuário cadastrado");
                mv.setViewName("usuarios/cadastrarUsuario");
                mv.addObject("message", "Usuário já cadastrado");
            }
        } catch (Exception e) {
            logger.error("=======> Exceçao não prevista - save() - cadastra usuario\n" +
                e.getMessage());
            mv.setViewName("usuarios/cadastrarUsuario");
            mv.addObject("message", "Exceção não prevista");
        }

        return mv;
    }
}