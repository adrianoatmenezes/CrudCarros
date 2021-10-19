package Modelo;

import DAL.CarroDAO;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Controle {

    private String mensagem;

    public void cadastrarCarro(List<String> dadosCarro) {
        this.mensagem = "";

        Validacao validacao = new Validacao();
        validacao.validarDadosCarro(dadosCarro);

        if (validacao.getMensagem().equals("")) {
            Carros carro = new Carros();
            carro.setId(validacao.getId());
            carro.setFabricante(dadosCarro.get(1));
            carro.setModelo(dadosCarro.get(2));
            carro.setCor(dadosCarro.get(3));
            carro.setAno_fabricado(Integer.parseInt(dadosCarro.get(4)));
            carro.setValor(Float.parseFloat(dadosCarro.get(5)));

            CarroDAO carroDAO = new CarroDAO();
            carroDAO.cadastrarCarro(carro);
            this.mensagem = carroDAO.getMensagem();
        } else {
            this.mensagem = validacao.getMensagem();
        }
    }

    public void editarCarro(List<String> dadosCarro) {
        this.mensagem = "";

        Validacao validacao = new Validacao();
        validacao.validarDadosCarro(dadosCarro);

        if (validacao.getMensagem().equals("")) {
            Carros carro = new Carros();
            carro.setId(validacao.getId());
            carro.setFabricante(dadosCarro.get(1));
            carro.setModelo(dadosCarro.get(2));
            carro.setCor(dadosCarro.get(3));
            carro.setAno_fabricado(Integer.parseInt(dadosCarro.get(4)));
            carro.setValor(Float.parseFloat(dadosCarro.get(5)));

            CarroDAO carroDAO = new CarroDAO();
            carroDAO.editarCarro(carro);
            this.mensagem = carroDAO.getMensagem();
        } else {
            this.mensagem = validacao.getMensagem();
        }
    }

    public void excluirCarro(String numeroId) {
        this.mensagem = "";

        Validacao validacao = new Validacao();
        validacao.validarIdCarro(numeroId);
        Carros carros = new Carros();

        if (validacao.getMensagem().equals("")) {
            CarroDAO carrosDAO = new CarroDAO();
            carros.setId(validacao.getId());
            carrosDAO.excluirCarro(carros);
            this.mensagem = carrosDAO.getMensagem();
        } else {
            this.mensagem = validacao.getMensagem();
        }
    }

    public Carros pesquisarCarroPorId(String numeroId) {
        this.mensagem = "";
        Validacao validacao = new Validacao();
        validacao.validarIdCarro(numeroId);
        Carros carro = new Carros();

        if (validacao.getMensagem().equals("")) {
            carro.setId(validacao.getId());
            CarroDAO carrodao = new CarroDAO();
            carro = carrodao.pesquisarCarroPorId(carro);

            this.mensagem = carrodao.getMensagem();

        } else {
            this.mensagem = validacao.getMensagem();
        }
        return carro;
    }

    public List<Carros> pesquisarCarroPorFabricante(String fabricante) {
        this.mensagem = "";
        Validacao validacao = new Validacao();
        validacao.validarFabricante(fabricante);
        Carros carro = new Carros();
        List<Carros> listaCarros = new ArrayList<>();
        if (validacao.getMensagem().equals("")) {
            carro.setFabricante(fabricante);
            CarroDAO carroDAO = new CarroDAO();
            listaCarros = carroDAO.pesquisarCarroPorFabricante(carro);
            this.mensagem = carroDAO.getMensagem();

        } else {
            this.mensagem = validacao.getMensagem();
        }
        return listaCarros;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
