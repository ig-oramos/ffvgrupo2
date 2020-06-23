package com.fatec.scel2.servico;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import com.fatec.scel2.model.Livro;
import com.fatec.scel2.model.LivroRepository;

@Service
public class LivroServico {
    Logger logger = LogManager.getLogger(LivroServico.class);

    @Autowired
    private LivroRepository repository;

    public Iterable<Livro> findAll() {
        return repository.findAll();
    }

    public Livro findByIsbn(String isbn) {
        return repository.findByIsbn(isbn);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public Livro findById(Long id) {
        return repository.findById(id).get();
    }

    public void save(Livro livro) {
        repository.save(livro);
    }

    public ModelAndView verificaIsbnJaExiste(Livro livro) {
        ModelAndView mv = new ModelAndView("consultarLivro");

        try {
            Livro jaExiste = null;
            jaExiste = repository.findByIsbn(livro.getIsbn());
            logger.info("=====> Verifica se o livro já existe = " + livro.getIsbn());
            if (jaExiste == null) {
                logger.info("=====> Livro não cadastrado" + livro.getAutor());
                repository.save(livro);
                mv.addObject("livros", repository.findAll());
            } else {
                logger.info("=====> Livro já cadastrado");
                mv.setViewName("cadastrarLivro");
                mv.addObject("message", "Livro já cadastrado");
            }
        } catch (Exception e) {
            logger.error("=====> Exceção não prevista - save() cadastra livro");
            mv.setViewName("cadastrarLivro");
            mv.addObject("message", "Exceção não prevista");
        }
        return mv;
    }
}