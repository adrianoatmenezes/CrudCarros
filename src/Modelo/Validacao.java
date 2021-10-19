package Modelo;

import java.text.DecimalFormat;
import java.util.List;

public class Validacao {

    private String mensagem;
    private Integer id;

    public void validarDadosCarro(List<String> dadosCarro) {
        this.mensagem = "";

        this.validarIdCarro(dadosCarro.get(0));

        this.validarFabricante(dadosCarro.get(1));

        if (dadosCarro.get(4).length() < 3 || dadosCarro.get(4).length() > 4) {
            this.mensagem += "Ano inválido\n";
        }
    }

    public void validarIdCarro(String numeroId) {
        this.mensagem = "";

        try {
            this.id = Integer.parseInt(numeroId);

        } catch (Exception e) {
            if (numeroId.equals("") || numeroId == null) {
                this.mensagem += "ID vazio\n";
            } else {
                this.mensagem += "ID inválido \n";
            }

        }
    }

    public void validarFabricante(String fabricante) {
        this.mensagem = "";
        if (fabricante.length() < 3 || fabricante.length() > 40) {
            this.mensagem = "Nome deve ter de 3 a 50 caracteres \n";
            if (fabricante == "") {
                this.mensagem = "";
            }
        }
    }

    public String getMensagem() {
        return mensagem;
    }

    public Integer getId() {
        return id;
    }
}
