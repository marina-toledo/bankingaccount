$(document).ready(function () {

//    ------------------------------
//    Logar na tela inicial
//    ------------------------------
    $('#frmLogin').submit(
            function () {
                $.ajax({
                    url: "entrar",
                    data: {
                        usuario: $('#usuario').val(),
                        senha: $('#senha').val()
                    }
                })
                .done(function (msg) {
                    var msgErroLogin = $('#msgErroLogin');
                    msgErroLogin.text("Usuário ou senha inválido");
                    msgErroLogin.addClass("msgErro");
                })
                ;

                return false;
            }
    );


//    ------------------------------
//    Recuperar senha
//    ------------------------------
    limparCamposModalSenha = function () {
        $('#msgEnviouSenha').text('');
        $('#email').val('');
    };

    $("#email").keyup(
        function() {
          var msgEnviouSenha = $('#msgEnviouSenha');

          if ( emailEhValido($("#email").val()) ) {
              msgEnviouSenha.text("Email válido");
              formataMsgSucesso(msgEnviouSenha);
          } else {
              msgEnviouSenha.text("Email inválido");
              formataMsgErro(msgEnviouSenha);
          }
        }
    );

    $('#enviarSenha').click(
            function () {
                var email = $("#email").val();

                if ( emailEhValido(email) ) {
                    enviaSenhaPorEmail();

                } else {
                    var msgEnviouSenha = $('#msgEnviouSenha');
                    msgEnviouSenha.text("Digite um e-mail valido");
                    formataMsgErro(msgEnviouSenha);
                }
                return false;
            }
    );

    enviaSenhaPorEmail = function () {
        $.ajax({
            url: "enviaSenhaPorEmail",
            data: {
                email: $('#email').val()
            }
        })
        .done(function (msg) {
            var msgEnviouSenha = $('#msgEnviouSenha');
            msgEnviouSenha.text("Uma senha temporária foi enviada ao seu e-mail.");
            formataMsgSucesso(msgEnviouSenha);
        })
        .fail(function() {
            var msgEnviouSenha = $('#msgEnviouSenha');
            msgEnviouSenha.text("Erro ao enviar email. Tente novamente.");
            formataMsgErro(msgEnviouSenha);
          })
        ;
    };

});
