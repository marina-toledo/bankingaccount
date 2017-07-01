package com.code.bankingaccount.controller;

import com.code.bankingaccount.email.EmailService;
import com.code.bankingaccount.entity.User;
import com.code.bankingaccount.entity.UserDAO;
import com.code.bankingaccount.helper.Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.Logger;

import static javax.servlet.http.HttpServletResponse.SC_OK;

/**
 *
 * @author User
 */
@Controller
public class AppController {

    @Autowired
    UserDAO dao;

    @Autowired
    EmailService service;

    @RequestMapping("/erro") //TODO
    public String erro(HttpServletRequest request, HttpServletResponse response) throws IOException {
        return "menuFinal";
    }

//    ------------------------------
//    Requests vindo da pagina HOME
//    ------------------------------
    @RequestMapping("/")
    public String init() {
        return "home";
    }

    @RequestMapping("/enviaSenhaPorEmail")
    private void enviaSenhaPorEmail(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Logger.getAnonymousLogger().info("Enviando senha por email.");

//        // TODO:: apagar, codigo temporario enquanto o BD ainda nao estah completo
//        User u = new User(request.getParameter("email"), "senha");
//        dao.save(u);

        service.sendPasswordEmail( request.getParameter("email") );
    }

    @RequestMapping("/cadastrar")
    public String cadastrar(HttpServletRequest request, HttpServletResponse response) throws IOException {
        return "cadastro";
    }

    @RequestMapping("/entrar")
    public void entrar(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String usuario = request.getParameter("usuario");
        String senha = request.getParameter("senha");

        if (Helper.autenticar(usuario, senha)) {
            response.setStatus(SC_OK);
//            response.sendRedirect("greeting?name=bla");
            response.sendRedirect("abrir-conta?usuario=" + usuario + "&senha=" + senha);
        }
//            response.sendError(SC_UNAUTHORIZED, "   Erro de autenticacao de usuario.");
    }

//    ------------------------------
//    Requests vindo da pagina CADASTRO
//    ------------------------------
    @RequestMapping("/submitCadastro")
    private void submitCadastro(HttpServletRequest request, HttpServletResponse response) throws IOException {
        OutputStream os = response.getOutputStream();

        String email = request.getParameter("email");
        String rg = request.getParameter("rg");
        String cpf = request.getParameter("cpf");
        String nomeCompleto = request.getParameter("nomeCompleto");
        String telefone = request.getParameter("telefone");

        String msg = "parameters: "
                + nomeCompleto + cpf + rg + email + telefone;

        String senha = Helper.gerarSenha();
        //Boolean status = Helper.criarUsuario(/* parametros de cadastro aqui*/);
        User u = new User( email, senha, rg, cpf, nomeCompleto, telefone);
        dao.save(u);
        //msg = status.toString();
        msg = Boolean.TRUE.toString();

        os.write(msg.getBytes());
        os.close();
        os.flush();
    }

//    ------------------------------
//    Requests vindo da pagina ABRIR-CONTA
//    ------------------------------
    @RequestMapping("/abrir-conta")
    public String abrir(@RequestParam(value = "usuario") String usuario, @RequestParam(value = "senha") String senha, Model model) {
        model.addAttribute("usuario", usuario);
        model.addAttribute("senha", senha);
        return "abrir-conta";
    }

    //    ------------------------------
//    Requests vindo da pagina MENUFINAL
//    ------------------------------
    @RequestMapping("/menuFinal")
    public String irMenuFinal() {
//    public String irMenuFinal(@RequestParam(value="usuario") String usuario, @RequestParam(value="senha") String senha, Model model) {
        return "menuFinal";
    }

}
