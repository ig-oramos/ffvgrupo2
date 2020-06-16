package com.fatec.scel2.model;

import javax.persistence.Id;
import javax.persistence.*;
import javax.validation.constraints.*;

@Entity(name = "Livro")
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    @Size(min = 4, max = 4, message = "ISBN deve ter 4 caracteres.")
    private String isbn;
    @NotNull
    @Size(min = 1, max = 50, message = "Título deve ter entre 1 e 50 caracteres.")
    private String titulo;
    @NotNull
    @Size(min = 1, max = 50, message = "Autor deve ter entre 1 e 50 caracteres.")
    private String autor;

    public Livro() {

    }

    public Livro(String isbn, String titulo, String autor) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }
}