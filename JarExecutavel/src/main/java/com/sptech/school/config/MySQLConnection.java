package com.sptech.school.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MySQLConnection {
    private static final String USER = "root";
    private static final String PASSWORD = "";
    private static final String URL = "jdbc:mysql://localhost:3306/horus_db2";

    public static Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public List<String> buscarDados(String usuario) throws SQLException {
        List<String> dadosUsuario = new ArrayList<>();

        String mysql = """
                SELECT f.nome,
                		f.cpf,
                        f.funcao,
                        f.email,
                        e.razao_social,
                        s.hostname,
                        s.mac_address,
                        s.status_servidor,
                        c.tipo,
                        sc.limite,
                        sc.unidade_medida,
                        ra.data_alerta,
                        ra.criticidade,
                        ra.status_alerta
                        FROM funcionario AS f
                        JOIN empresa AS e
                             ON e.id_empresa = f.fk_empresa
                        JOIN servidor AS s
                             ON s.fk_empresa = e.id_empresa
                        JOIN servidor_componente AS sc
                             ON sc.fk_servidor = s.id_servidor
                        JOIN componente AS c
                             ON c.id_componente = sc.fk_componente
                        JOIN registro_alerta AS ra
                             ON ra.fk_componente = c.id_componente
                        WHERE f.email = ?;
                """;

        try (Connection conexao = MySQLConnection.conectar();
             PreparedStatement ps = conexao.prepareStatement(mysql);
        ){
            ps.setString(1, usuario);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                dadosUsuario.add(rs);
            }
            return dadosUsuario;
        } catch (SQLException e) {
            throw new SQLException("Erro ao capturar os dados do MySQL:" + e);
        }
    }
}
