package br.com.alura.comex.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    public Connection criaConexao() {
        String usuario = "admin";
        String senha = "admin";

        try {
            String url = "jdbc:postgresql://localhost:5432/comex"
                    //   + "INIT=runscript from './schema.sql'\\;"
                    //   + "runscript from './data.sql'"
                    ;
            
            return DriverManager.getConnection(url, usuario, senha);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao tentar se conectar no banco de dados.");
        }
    }
}
