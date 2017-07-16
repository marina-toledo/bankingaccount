package com.code.bankingaccount.controller;

import com.code.bankingaccount.entity.UserRepository;
import com.code.bankingaccount.form.LoginForm;
import com.code.bankingaccount.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
    public String init(@ModelAttribute LoginForm loginForm) {
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

    @PostMapping("/")
    public ModelAndView entrar(LoginForm loginForm) throws IOException {
        Logger.getAnonymousLogger().info("Entrando no Internet Banking.");

        ModelMap model = new ModelMap();
        try{
            service.login(loginForm.getUsuario(), loginForm.getSenha());
        }catch(Exception e){
            Logger.getAnonymousLogger().info("Exception: " + e);
            loginForm.setMsgLogin("Usuário ou senha inválido(s).");
            return new ModelAndView("/home", model);
        }

        model.addAttribute("usuario", loginForm.getUsuario());
        return new ModelAndView("/abrir-conta", model);
//        return new ModelAndView("redirect:/", model);
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
//    @PostMapping("/abrir-conta")
//    public String abrir( String usuario) {
//        Logger.getAnonymousLogger().info("Abrindo conta bancária.");
//
//        ModelMap model = new ModelMap();
//
//        model.addAttribute("usuario", userRepository.findByEmail(usuario));
//        return "abrir-conta";
//    }

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
