package com.fatec.scel2.model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

@Entity
public class Emprestimo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    @Size(min = 4, max = 4, message = "ISBN deve ter 4 caracteres")
    private String isbn;
    @NotNull
    @Size(min = 4, max = 4, message = "RA deve ter 4 caracteres")
    private String ra;
    private String dataEmprestimo;
    private String dataDevolucaoPrevista;
    private String dataDevolucao;

    public Emprestimo(String isbn, String ra) {
        this.isbn = isbn;
        this.ra = ra;
        DateTime dataAtual = new DateTime();
        setDataEmprestimo(dataAtual);
    }

    public Emprestimo() {
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

    public String getRa() {
        return ra;
    }

    public void setRa(String ra) {
        this.ra = ra;
    }

    public String getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(DateTime dataAtual) {
        DateTimeFormatter fmt = DateTimeFormat.forPattern("YYYY/MM/dd");
        this.dataEmprestimo = dataAtual.toString(fmt);
        setDataDevolucaoPrevista();
    }

    public String getDataDevolucaoPrevista() {
        return dataDevolucaoPrevista;
    }

    private void setDataDevolucaoPrevista() {
        DateTimeFormatter fmt = DateTimeFormat.forPattern("YYYY/MM/dd");
        DateTime data = fmt.parseDateTime(getDataEmprestimo());
        this.dataDevolucaoPrevista = data.plusDays(8).toString(fmt);
    }

    public String getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(String dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public boolean ehDomingo(String data) {
        boolean isValida = false;
        DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy/MM/dd");
        if (validaData(data)) {
            DateTime umaData = fmt.parseDateTime(data);
            if (umaData.dayOfWeek().getAsText().equals("Domingo")) {
                isValida = true;
            }
        }
        return isValida;
    }

    public boolean validaData(String data) {
        DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
        df.setLenient(false); //
        try {
            df.parse(data); // data válida
            return true;
        } catch (ParseException ex) {
            return false;
        }
    }

    public Integer verificaAtraso() {
        DateTimeFormatter fmt = DateTimeFormat.forPattern("YYYY/MM/dd");
        DateTime dataAtual = fmt.parseDateTime(new DateTime().toString(fmt));
        DateTime dataDevolucaoPrevista = fmt.parseDateTime(getDataDevolucaoPrevista());
        int dias = Days.daysBetween(dataAtual, dataDevolucaoPrevista).getDays();
        return dias;
    }
}