
    //TODO: procurar por frameworks ou plugins de validacao

    //    Validando campo email
    emailEhValido = function (email) {
        // TODO : http://stackoverflow.com/questions/46155/validate-email-address-in-javascript
        var re = /\S+@\S+\.\S+/; //anystring@anystring.anystring
        return re.test(email);
    };

    formataMsgSucesso = function (msg){
        msg.removeClass("msgErro");
        msg.addClass("msgSucesso");
    }

    formataMsgErro = function (msg){
        msg.removeClass("msgSucesso");
        msg.addClass("msgErro");
    }
