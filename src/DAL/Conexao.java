package DAL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    public Connection con;
    private String mensagem;

    public Connection conectar() {
        this.mensagem = "";
        try {
            if (con == null || con.isClosed()) {
                con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=CrudCarro", "sa", "unip");
            }
        } catch (SQLException e) {
            this.mensagem = e.getMessage();
        }

        return con;
    }

    public void desconectar() {
        try {
            if (!con.isClosed()) {
                con.close();
            }
        } catch (SQLException e) {
            this.mensagem = "Não foi possivel desconectar do banco de dados.";
        }

    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

}
