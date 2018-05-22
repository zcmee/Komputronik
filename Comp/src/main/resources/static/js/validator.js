// Copyright by Adam Bryksy
// Contact :
//   email: adam.bryksy[AT]orangeretail.pl
var Validator = (function () {
    var errorClassName = "error";
    var errorMessageClassName = "regex-error-message";

    var selectors;
    var regexMap;
    function Validator(idForm) {
        if (idForm === undefined) { idForm = "form";}

        regexMap = getDefaultRegexMap();
        selectors = document.getElementById(idForm).elements;
        for (var i = 0; i < selectors.length; ++i) {
            selectors[i].onblur = this.validation;
        }
    };

    Validator.prototype.setErrorClassName = function (className) {
        errorClassName = className;
    };

    Validator.prototype.addClass = function () {
        if (this.className.indexOf(errorClassName) < 1)
            this.className += " " + errorClassName;
    };

    Validator.prototype.addMessageError = function (message) {
        var tmpNextElement = this.nextSibling;
        if(tmpNextElement.className !== errorMessageClassName) {
            var messageElement = getMessageErrorElement(message);
            this.parentNode.insertBefore(messageElement, this.nextSibling);
        }
    };

    Validator.prototype.addClassAndMessageError = function(message) {
        Validator.prototype.addClass.call(this);
        Validator.prototype.addMessageError.call(this, message);
    };

    Validator.prototype.removeMessageError = function () {
        var tmpNextElement = this.nextSibling;
        if(tmpNextElement.className === errorMessageClassName) {
            tmpNextElement.parentNode.removeChild(tmpNextElement);
        }
    };

    Validator.prototype.removeClass = function () {
        this.className = this.className.replace(errorClassName, '');
    };

    Validator.prototype.removeClassAndMessageError = function() {
        Validator.prototype.removeClass.call(this);
        Validator.prototype.removeMessageError.call(this);
    };

    function validateNIP(nip) {
	var nip_bez_kresek = nip.replace(/-/g,"");
	var reg = /^[0-9]{10}$/;
	if(reg.test(nip_bez_kresek) === false) { return false;}
	else {
            var dig = (""+nip_bez_kresek).split("");
            var kontrola = (6*parseInt(dig[0]) + 5*parseInt(dig[1]) + 7*parseInt(dig[2]) + 2*parseInt(dig[3]) + 3*parseInt(dig[4]) + 4*parseInt(dig[5]) + 5*parseInt(dig[6]) + 6*parseInt(dig[7]) + 7*parseInt(dig[8]))%11;
            if(parseInt(dig[9]) === kontrola)
            return true;
            else
            return false;
	}
    }

    function validatePesel(pesel) {
        var reg = /^[0-9]{11}$/;
        if(reg.test(pesel) === false) { return false;}
        else
        {
            var dig = (""+pesel).split("");
            var kontrola = (1*parseInt(dig[0]) + 3*parseInt(dig[1]) + 7*parseInt(dig[2]) + 9*parseInt(dig[3]) + 1*parseInt(dig[4]) + 3*parseInt(dig[5]) + 7*parseInt(dig[6]) + 9*parseInt(dig[7]) + 1*parseInt(dig[8]) + 3*parseInt(dig[9]))%10;
            if(kontrola === 0) kontrola = 10;
            kontrola = 10 - kontrola;
            if(parseInt(dig[10]) === kontrola)
            return true;
            else
            return false;
        }
    }

    function validateBankAccount(bankAccount) {
        bankAccount = bankAccount.replace(/[^0-9]+/g, '');
        var wagi = new Array(1, 10, 3, 30, 9, 90, 27, 76, 81, 34, 49, 5, 50, 15, 53, 45, 62, 38, 89, 17, 73, 51, 25, 56, 75, 71, 31, 19, 93, 57);

        if (bankAccount.length == 26) {
            bankAccount = bankAccount + "2521";
            bankAccount = bankAccount.substr(2) + bankAccount.substr(0, 2);
            var Z = 0;
            for (var i = 0; i < 30; i++) {
                Z += bankAccount[29 - i] * wagi[i];
            }
            if (Z % 97 == 1) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    Validator.prototype.validation = function () {
        switch (this.type) {
            case 'textarea':
            case 'text':
                var dataRegex = this.getAttribute('data-regex');
                if(dataRegex === null) break;
                if (dataRegex in regexMap)
                    dataRegex = regexMap[dataRegex];
                var pattern = new RegExp(dataRegex);


                if(this.id === "nip" || this.id === "pesel") {
                    var isCorrectNipOrPesel = this.id === "nip" ? validateNIP(this.value) : validatePesel(this.value);

                    if(isCorrectNipOrPesel) {
                        Validator.prototype.removeClassAndMessageError.call(this);
                        return true;
                    } else {
                        var tmpMessage = this.value.length === 0 ? 'Pole wymagane' : 'Pole źle wypełnione';
                        Validator.prototype.removeMessageError.call(this);
                        Validator.prototype.addClassAndMessageError.call(this, tmpMessage);
                        return false;
                    }
                }
                 if(this.id === "bank-account") {
                    var isBankAccountCorrect = validateBankAccount(this.value);
                    if(isBankAccountCorrect) {
                        Validator.prototype.removeClassAndMessageError.call(this);
                        return true;
                    } else {
                        var tmpMessage = this.value.length === 0 ? 'Pole wymagane' : 'Pole źle wypełnione';
                        Validator.prototype.removeMessageError.call(this);
                        Validator.prototype.addClassAndMessageError.call(this, tmpMessage);
                        return false;
                    }
                 }
                 if (pattern.test(this.value)) {
                    Validator.prototype.removeClassAndMessageError.call(this);
                    return true;
                 } else {
                    var tmpMessage = this.value.length === 0 ? 'Pole wymagane' : 'Pole źle wypełnione';
                    Validator.prototype.removeMessageError.call(this);
                    Validator.prototype.addClassAndMessageError.call(this, tmpMessage);
                    return false;
                 }
            break;
            case 'select-one':
                if (this.value.length === 0) {
                    Validator.prototype.addClassAndMessageError.call(this, "Proszę wybrać element z listy");
                    return false;
                } else {
                    Validator.prototype.removeClassAndMessageError.call(this);
                    return true;
                }
            break;
        }

        return true;
    };

    Validator.prototype.valid = function () {
        var status = true;
        for (i = 0; i < selectors.length; ++i)
            if(!Validator.prototype.validation.call(selectors[i])) status = false;

        return status;
    };

    return Validator;
}());

var SelectorAttribute = (function () {
    function SelectorAttribute(name, regex) {
        this.name = name;
        this.regex = regex;
    }

    SelectorAttribute.prototype.toString = function () {
        return "name: " + this.name + ", regex = " + this.regex;
    };

    return SelectorAttribute;
}());

    function getMessageErrorElement(message) {
        var messageElement = document.createElement("span");
        messageElement.innerHTML = message;
        messageElement.className = 'regex-error-message';
        return messageElement;
    }

    function getDefaultRegexMap() {
        var map = {};

        map['zip-code-poland'] = '^[0-9]{2}-[0-9]{3}$';
        map['phone-number-poland'] = '^[0-9]{9}$';
        map['nip'] = '^[0-9]{10}$';
        map['pesel'] = '^[0-9]{11}$';
        map['digits'] = '^[0-9]+$';
        map['alpha'] = '^[a-zA-z]+$';
        map['email'] = '^[-a-z0-9~!$%^&*_=+}{\'?]+(\.[-a-z0-9~!$%^&*_=+}{\'?]+)*@([a-z0-9_][-a-z0-9_]*(\.[-a-z0-9_]+)*\.(aero|arpa|biz|com|coop|edu|gov|info|int|mil|museum|name|net|org|pro|travel|mobi|[a-z][a-z])|([0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}))(:[0-9]{1,5})?';
        map['login'] = '^[a-z0-9\-\.]{3,100}$';
        map['ip-address'] = '^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$';
        map['url-address'] = '^((http[s]?|ftp):\/)?\/?([^:\/\s]+)((\/\w+)*\/)([\w\-\.]+[^#?\s]+)(.*)?(#[\w\-]+)?$';
        map['required'] = '^(\.+)$';
        map['required-with-enter'] = '^((\.|\n)+)$';
        map['full-date'] = '^[1-2][0-9]{3}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$';
        map['id-tel-zamowienia'] = '^(TEL[0-9]{12})$';
        map['bank-account'] = '^([0-9]{26})$';
        map['person-name'] = '^[a-zA-z\-]{3,50}$';

        return map;
    }