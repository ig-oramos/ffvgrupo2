package com.fatec.scel2.servico;

import com.fatec.scel2.model.Emprestimo;

public interface EmprestimoServico {
    public Iterable<Emprestimo> findAll();

    public Emprestimo findById(Long id);

    public String registraEmprestimo(Emprestimo emprestimo);

    public String registraDevolucao(String isbn); // supoe que isbn refere-se ao tombo

    public void deleteById(Long id);
}