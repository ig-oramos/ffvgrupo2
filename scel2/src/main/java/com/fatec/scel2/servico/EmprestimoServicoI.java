package com.fatec.scel2.servico;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fatec.scel2.model.Emprestimo;
import com.fatec.scel2.model.EmprestimoRepository;
import com.fatec.scel2.model.Livro;
import com.fatec.scel2.model.LivroRepository;
import com.fatec.scel2.model.Usuario;
import com.fatec.scel2.model.UsuarioRepository;

@Service
public class EmprestimoServicoI implements EmprestimoServico {
    @Autowired
    EmprestimoRepository emprestimoRepository;
    @Autowired
    LivroRepository livroRepository;
    @Autowired
    UsuarioRepository usuarioRepository;
    Logger logger = LogManager.getLogger(EmprestimoServicoI.class);

    public Iterable<Emprestimo> findAll() {
        return emprestimoRepository.findAll();
    }

    public Emprestimo findById(Long id) {
        return emprestimoRepository.findById(id).get();
    }

    @Override
    public String registraEmprestimo(Emprestimo emprestimo) {
        String message = "";
        Livro livro = null;
        Usuario usuario = null;
        livro = livroRepository.findByIsbn(emprestimo.getIsbn());
        usuario = usuarioRepository.findByRa(emprestimo.getRa());
        if (livro != null && usuario != null) { // verfica se o livro existe e se o usuário existe.
            logger.info("registra emprestimo => " + emprestimo.getRa());
            DateTime dataAtual = new DateTime();
            emprestimo.setDataEmprestimo(dataAtual);
            emprestimoRepository.save(emprestimo);
        } else {
            message = "fail";
        }
        return message;
    }

    @Override
    public String registraDevolucao(String isbn) {
        return null;
    }

    @Override
    public void deleteById(Long id) {
        emprestimoRepository.deleteById(id);
    }
}