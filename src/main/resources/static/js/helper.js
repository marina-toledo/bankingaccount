
    //TODO: procurar por frameworks ou plugins de validacao

    nomeCompletoEhValido = function (nomeCompleto){
        return /^[A-Za-z\s]+$/.test(nomeCompleto); //returns true if matched, validates for a-z and A-Z and white space
    }

    cpfEhValido = function (cpf){
        return /^[0-9]+$/.test(cpf); // todo procurar validacao melhor na net
    }

    rgEhValido = function (rg){
        return /^[0-9]+$/.test(rg); // todo procurar validacao melhor na net
    }

    telefoneEhValido = function (telefone){
        return /^[0-9]+$/.test(telefone); // todo procurar validacao melhor na net
    }

    emailEhValido = function (email) {
        // TODO : http://stackoverflow.com/questions/46155/validate-email-address-in-javascript
        return /\S+@\S+\.\S+/.test(email); //return true if matches anystring@anystring.anystring
    }

    formataMsgSucesso = function (msg){
        msg.removeClass("msgErro");
        msg.addClass("msgSucesso");
    }

    formataMsgErro = function (msg){
        msg.removeClass("msgSucesso");
        msg.addClass("msgErro");
    }
