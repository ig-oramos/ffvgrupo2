package com.fatec.scel2.controller;

import javax.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.fatec.scel2.model.Livro;
import com.fatec.scel2.model.Usuario;
import com.fatec.scel2.servico.UsuarioServico;

@Controller
@RequestMapping(path = "/usuarios")
public class UsuarioController {
    Logger logger = LogManager.getLogger(UsuarioController.class);
    @Autowired
    UsuarioServico servico;

    @GetMapping("/consultar")
    public ModelAndView retornaFormConsultaDeTodosOsUsuarios() {
        ModelAndView mv = new ModelAndView("/usuarios/consultarUsuario");
        mv.addObject("usuarios", servico.findAll());
        return mv;
    }

    @GetMapping("/cadastrar")
    public ModelAndView retornaFormCadastrarUsuario(Usuario usuario) {
        ModelAndView mv = new ModelAndView("usuarios/cadastrarUsuario");
        mv.addObject("usuario", usuario);
        return mv;
    }
     // diz respeito ao método que irá responder a uma requisição do tipo get
    @GetMapping("/edit/{ra}")
    public ModelAndView retornaFormParaEditarUsuario(@PathVariable("ra") String ra) {
        ModelAndView mv = new ModelAndView("usuarios/atualizarUsuario");
        mv.addObject("usuario", servico.findByRa(ra)); // o repositório é injetado no controller
        return mv; // addObject adiciona objetos para a view
    }

    @GetMapping("/delete/{id}")
    public ModelAndView excluirNoFormDeConsultaTodosOsUsuarios(@PathVariable("id") Long id) {
        servico.deleteById(id);
        ModelAndView mv = new ModelAndView("/usuarios");
        mv.addObject("usuarios", servico.findAll());
        return mv;
    }

    @PostMapping("/save")
    public ModelAndView save(@Valid Usuario usuario, BindingResult result) {
        ModelAndView mv = new ModelAndView("usuarios/consultarUsuario");

        if (result.hasErrors())
            mv.setViewName("usuarios/cadastrarUsuario");
        else
            mv = servico.verificaRaExiste(usuario);

        return mv;
    }

    @PostMapping("/update/{id}")
    public ModelAndView atualizaUsuario(@PathVariable("id") Long id, @Valid Usuario usuario,
        BindingResult result) {
        if (result.hasErrors()) {
            usuario.setId(id);
            return new ModelAndView("usuarios/atualizarLivro");
        }

        Usuario umUsuario = servico.findById(id);
        umUsuario.setRa(usuario.getRa());
        umUsuario.setNome(usuario.getNome());
        umUsuario.setEmail(usuario.getEmail());
        servico.save(umUsuario);
        ModelAndView mv = new ModelAndView("/usuarios/atualizarUsuario");
        mv.addObject("usuarios", servico.findAll());
        return mv;
    }
}