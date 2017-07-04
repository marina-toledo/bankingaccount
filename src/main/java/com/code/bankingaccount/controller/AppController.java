package com.code.bankingaccount.controller;

import com.code.bankingaccount.entity.UserRepository;
import com.code.bankingaccount.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
@Controller
public class AppController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AccountService service;

    @RequestMapping("/erro") //TODO
    public String erro(HttpServletRequest request, HttpServletResponse response) throws IOException {
        return "menuFinal";
    }

//    ------------------------------
//    Requests vindo da pagina HOME
//    ------------------------------
    @RequestMapping("/")
    public String init() {
        Logger.getAnonymousLogger().info("Tela Inicial.");
        return "home";
    }

    @RequestMapping("/enviaSenhaPorEmail")
    private void enviaSenhaPorEmail(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Logger.getAnonymousLogger().info("Enviando senha por email.");

        service.sendPasswordEmail( request.getParameter("email") );
    }

    @RequestMapping("/cadastrar")
    public String cadastrar(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Logger.getAnonymousLogger().info("Indo para a tela de cadastro.");
        return "cadastro";
    }

    @RequestMapping("/entrar")
    public String entrar(HttpServletRequest request) throws IOException {
        Logger.getAnonymousLogger().info("Entrando no Internet Banking.");

        String usuario = request.getParameter("usuario");
        String senha = request.getParameter("senha");

//        service.login(usuario, senha);
//
//        response.sendRedirect("abrir-conta?usuario=" + usuario + "&senha=" + senha);
//        return "abrir-conta?usuario=" + usuario + "&senha=" + senha;

//        model.addAttribute(usuario, usuario);
//        model.addAttribute(senha, senha);
//        return new ModelAndView("abrir-conta");
        return "cadastro";
//        //todo entrar na pagina de abrir conta, usar Post e Foward de modo a esconder as informacoes necessarias
    }

//    ------------------------------
//    Requests vindo da pagina CADASTRO
//    ------------------------------
    @RequestMapping("/submitCadastro")
    private void submitCadastro(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Logger.getAnonymousLogger().info("Registrando novo usuário.");

        String email = request.getParameter("email");
        String rg = request.getParameter("rg");
        String cpf = request.getParameter("cpf");
        String nomeCompleto = request.getParameter("nomeCompleto");
        String telefone = request.getParameter("telefone");

        service.registerUser(email, rg, cpf, nomeCompleto, telefone);
    }

//    ------------------------------
//    Requests vindo da pagina ABRIR-CONTA
//    ------------------------------
    @RequestMapping("/abrir-conta")
    public String abrir(@RequestParam(value = "usuario") String usuario, @RequestParam(value = "senha") String senha, Model model) {
        Logger.getAnonymousLogger().info("Abrindo conta bancária.");

        model.addAttribute("usuario", usuario);
        model.addAttribute("senha", senha);
        return "abrir-conta";
    }

    //    ------------------------------
//    Requests vindo da pagina MENUFINAL
//    ------------------------------
    @RequestMapping("/menuFinal")
    public String irMenuFinal() {
        Logger.getAnonymousLogger().info("Indo para o menu final.");

//    public String irMenuFinal(@RequestParam(value="usuario") String usuario, @RequestParam(value="senha") String senha, Model model) {
        return "menuFinal";
    }

}
