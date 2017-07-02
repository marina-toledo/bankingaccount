$(document).ready(function () {

    // validar campos sequencialmente, submeter form somente se todos os campos forem validos
    $('#frmCadastro').submit(
            function (event) {
                var msgCadastro = $('#msgCadastro');

                var nomeCompleto = $('#nomeCompleto').val();
                var cpf = $('#cpf').val();
                var rg = $('#rg').val();
                var email = $('#email').val();
                var telefone = $('#telefone').val();

                if( !nomeCompletoEhValido(nomeCompleto) || !cpfEhValido(cpf) || !rgEhValido(rg)
                    || !emailEhValido(email) || !telefoneEhValido(telefone)){
                    msgCadastro.text("Campo(s) inválido(s).");
                    formataMsgErro(msgCadastro);
                    event.preventDefault();
                    return false;
                }

                $.ajax({
                    url: "submitCadastro",
                    data: {
                        nomeCompleto: nomeCompleto,
                        cpf: cpf,
                        rg: rg,
                        email: email,
                        telefone: telefone
                    }
                })
                .done(function (msg) {
                    msgCadastro.text("Cadastro feito! Uma senha temporária foi enviada ao seu e-mail.");
                    formataMsgSucesso(msgCadastro);
                })
                .fail(function() {
                    msgCadastro.text("Erro de cadastro. Tente novamente.");
                    formataMsgErro(msgCadastro);
                })
                ;
                return false;
            });

}); // fecha $(document).ready
