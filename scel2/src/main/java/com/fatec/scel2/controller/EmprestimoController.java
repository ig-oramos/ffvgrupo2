package com.fatec.scel2.controller;

import javax.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.fatec.scel2.model.Emprestimo;
import com.fatec.scel2.model.EmprestimoRepository;
import com.fatec.scel2.servico.EmprestimoServico;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;


@RestController
@RequestMapping(path = "/emprestimos")
public class EmprestimoController {
    @Autowired
    private EmprestimoServico servico;
    @Autowired
    EmprestimoRepository emprestimoRepository;

    Logger logger = LogManager.getLogger(EmprestimoController.class);

    @GetMapping("/cadastrar")
    public ModelAndView registrarEmprestimo(Emprestimo emprestimo) {
        ModelAndView mv = new ModelAndView("registrarEmprestimo");
        mv.addObject("emprestimo", emprestimo);
        return mv;
    }

    @PostMapping("/save")
    public ModelAndView save(@Valid Emprestimo emprestimo, BindingResult result) {
        logger.info("emprestimo ==> " + emprestimo.getRa());
        ModelAndView modelAndView = new ModelAndView("consultarEmprestimo");
        if (result.hasErrors()) {
            System.out.println(result.getAllErrors().get(0));
            modelAndView.setViewName("registrarEmprestimo");
        }
        try {
            String message = servico.registraEmprestimo(emprestimo);
            modelAndView.addObject("emprestimos", servico.findAll());
            if (message.equals("fail")) {
                modelAndView.setViewName("registrarEmprestimo");
                modelAndView.addObject("message", "Livro e/ou usuario nao cadastrado(s)."); // fail nao eh nulo a msg aparece na tela
            } else {
                modelAndView.setViewName("consultarEmprestimo");
                modelAndView.addObject("emprestimo", emprestimo);
            }
        } catch (Exception e) {
            logger.error("========> Exceçao nao prevista - save() cadastra emprestimo");
            modelAndView.setViewName("registrarEmprestimo");
            modelAndView.addObject("fail", "Exceçao nao prevista - consulte o administrador =>" + e.getMessage());
        }
        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("atualizarEmprestimo");
        modelAndView.addObject("emprestimo", servico.findById(id));
        return modelAndView;
    }

    @PostMapping("/update/{id}")
    public ModelAndView update(@PathVariable("id") Long id, @Valid Emprestimo emprestimo, BindingResult result) {
        if (result.hasErrors()) {
            emprestimo.setId(id);
            return new ModelAndView("atualizarEmprestimo");
        }
        
        Emprestimo umEmprestimo = servico.findById(id);
        umEmprestimo.setDataDevolucao(emprestimo.getDataDevolucao());
        emprestimoRepository.save(umEmprestimo);
        ModelAndView modelAndView = new ModelAndView("consultarEmprestimo");
        modelAndView.addObject("emprestimos", servico.findAll());
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") Long id) {
        servico.deleteById(id);
        ModelAndView modelAndView = new ModelAndView("consultarEmprestimo");
        modelAndView.addObject("emprestimos", servico.findAll());
        return modelAndView;
    }
}