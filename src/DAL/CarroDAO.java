package DAL;

import Modelo.Carros;
import Modelo.Estaticos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class CarroDAO {

    private String mensagem;
    Conexao conexao = new Conexao();

    public void cadastrarCarro(Carros carros) {
        this.mensagem = "";

        try {
            Connection con = conexao.conectar();
            if (conexao.getMensagem().equals("")) {
                String comSql = "insert into carros (fabricante, modelo, cor, "
                        + "ano_fabricado, valor)"
                        + "values(?,?,?,?,?)";
                PreparedStatement stmt = con.prepareStatement(comSql);
                stmt.setString(1, carros.getFabricante());
                stmt.setString(2, carros.getModelo());
                stmt.setString(3, carros.getCor());
                stmt.setInt(4, carros.getAno_fabricado());
                stmt.setDouble(5, carros.getValor());

                stmt.execute();
                conexao.desconectar();
                this.mensagem = "Fabricante do carro cadastrado com sucesso!";

            } else {
                this.mensagem = conexao.getMensagem();
            }
        } catch (Exception e) {
            this.mensagem = e.getMessage();
            // this.mensagem = "Erro de gravação do BD";
        }
    }

    public void editarCarro(Carros carro) {
        this.mensagem = "";

        try {
            Connection con = conexao.conectar();
            if (conexao.getMensagem().equals("")) {
                String comSql = "UPDATE carros "
                        + "SET fabricante = ?,"
                        + "modelo = ?,"
                        + "cor = ?,"
                        + "ano_fabricado = ?,"
                        + "valor = ? WHERE id = ?";
                PreparedStatement stmt = con.prepareStatement(comSql);
                stmt.setString(1, carro.getFabricante());
                stmt.setString(2, carro.getModelo());
                stmt.setString(3, carro.getCor());
                stmt.setInt(4, carro.getAno_fabricado());
                stmt.setFloat(5, carro.getValor());
                stmt.setInt(6, carro.getId());

                stmt.execute();
                conexao.desconectar();
                this.mensagem = "Fabricante editado com sucesso!";
            } else {
                this.mensagem = conexao.getMensagem();
            }
        } catch (Exception e) {
            this.mensagem = e.getMessage();
            //this.mensagem = "Erro de gravação do BD";
        }
    }

    public void excluirCarro(Carros carro) {
        this.mensagem = "";

        try {
            carro = this.pesquisarCarroPorId(carro);
            if (carro.getFabricante() == null || carro.getFabricante().equals("")) {
                this.mensagem = "Não existe este fabricante";
            } else {
                Connection con = conexao.conectar();
                if (conexao.getMensagem().equals("")) {

                    if (carro.getFabricante().equals("")) {
                        this.mensagem = "Não existe este carro para excluir";
                    } else {
                        String comSql = "delete from Carros where id = ?";
                        PreparedStatement stmt = con.prepareStatement(comSql);
                        stmt.setInt(1, carro.getId());
                        stmt.execute();
                        conexao.desconectar();
                        this.mensagem = "Carro excluido com sucesso!";
                    }

                } else {
                    this.mensagem = conexao.getMensagem();
                }
            }

        } catch (SQLException e) {
            this.mensagem = e.getMessage();
            //this.mensagem = "Erro de gravação do BD";
        }
    }

    public Carros pesquisarCarroPorId(Carros carro) {
        this.mensagem = "";

        try {
            Connection con = conexao.conectar();
            if (conexao.getMensagem().equals("")) {
                String comSql = "select * from carros where id = ?";

                PreparedStatement stmt = con.prepareStatement(comSql);
                stmt.setInt(1, carro.getId());

                ResultSet resultset = stmt.executeQuery();

                if (resultset.next()) {
                    carro.setFabricante(resultset.getString("fabricante"));
                    carro.setModelo(resultset.getString("modelo"));
                    carro.setCor(resultset.getString("cor"));
                    carro.setAno_fabricado(resultset.getInt("ano_fabricado"));
                    carro.setValor(resultset.getFloat("valor"));
                } else {
                    this.mensagem = "Não existe esse ID";
                }
                conexao.desconectar();
            } else {
                this.mensagem = conexao.getMensagem();
            }
        } catch (Exception e) {
            this.mensagem = e.getMessage();
            //this.mensagem = "Erro de gravação do BD";
        }
        return carro;
    }

    public List<Carros> pesquisarCarroPorFabricante(Carros carro) {
        this.mensagem = "";
        List<Carros> listaCarro = new ArrayList<>();
        try {
            Connection con = conexao.conectar();

            if (conexao.getMensagem().equals("")) {
                String comSql = "select * from Carros WHERE fabricante like ?";

                PreparedStatement stmt = con.prepareStatement(comSql);
                stmt.setString(1, carro.getFabricante() + "%");

                ResultSet resultset = stmt.executeQuery();

                while (resultset.next()) {
                    Carros carroLista = new Carros();

                    carroLista.setId(resultset.getInt("id"));
                    carroLista.setFabricante(resultset.getString("fabricante"));
                    carroLista.setModelo(resultset.getString("modelo"));
                    carroLista.setCor(resultset.getString("cor"));
                    carroLista.setAno_fabricado(resultset.getInt("ano_fabricado"));
                    carroLista.setValor(resultset.getFloat("valor"));
                    listaCarro.add(carroLista);
                }
                conexao.desconectar();
            } else {
                this.mensagem = conexao.getMensagem();
            }
        } catch (Exception e) {
            this.mensagem = e.getMessage();
            //this.mensagem = "Erro de gravação do BD";

        }
        return listaCarro;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

}
