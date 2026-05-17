package com.sptech.school.service;

import com.sptech.school.config.MySQLConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RelatorioData {
    // funcionário
    private String nome;
    private String cpf;
    private String funcao;
    private String email;

    // empresa
    private String razaoSocial;

    // servidor
    private String hostname;
    private String macAddress;
    private String statusServidor;

    // componente
    private String tipoComponente;
    private Double limite;
    private String unidadeMedida;

    // alerta
    private LocalDateTime dataAlerta;
    private String criticidade;
    private String statusAlerta;

    public RelatorioData() {
    }

    public RelatorioData(String nome,
                         String cpf,
                         String funcao,
                         String email,
                         String razaoSocial,
                         String hostname,
                         String macAddress,
                         String statusServidor,
                         String tipoComponente,
                         Double limite,
                         String unidadeMedida,
                         LocalDateTime dataAlerta,
                         String criticidade,
                         String statusAlerta) {
        this.nome = nome;
        this.cpf = cpf;
        this.funcao = funcao;
        this.email = email;
        this.razaoSocial = razaoSocial;
        this.hostname = hostname;
        this.macAddress = macAddress;
        this.statusServidor = statusServidor;
        this.tipoComponente = tipoComponente;
        this.limite = limite;
        this.unidadeMedida = unidadeMedida;
        this.dataAlerta = dataAlerta;
        this.criticidade = criticidade;
        this.statusAlerta = statusAlerta;
    }

    public List<RelatorioData> buscarDados(String usuario) throws SQLException {
        List<RelatorioData> dadosUsuario = new ArrayList<>();

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
                            AND ra.fk_servidor = s.id_servidor
                        WHERE f.email = ?;
                """;

        try (Connection conexao = MySQLConnection.conectar();
             PreparedStatement ps = conexao.prepareStatement(mysql);
        ){
            ps.setString(1, usuario);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                RelatorioData data = new RelatorioData(
                        rs.getString("nome"),
                        rs.getString("cpf"),
                        rs.getString("funcao"),
                        rs.getString("email"),
                        rs.getString("razao_social"),
                        rs.getString("hostname"),
                        rs.getString("mac_address"),
                        rs.getString("status_servidor"),
                        rs.getString("tipo"),
                        rs.getDouble("limite"),
                        rs.getString("unidade_medida"),
                        rs.getTimestamp("data_alerta").toLocalDateTime(),
                        rs.getString("criticidade"),
                        rs.getString("status_alerta")

                );
                dadosUsuario.add(data);
                System.out.println(data);
            }
            return dadosUsuario;
        } catch (SQLException e) {
            throw new SQLException("Erro ao capturar os dados do MySQL:" + e);
        }
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public String getStatusServidor() {
        return statusServidor;
    }

    public void setStatusServidor(String statusServidor) {
        this.statusServidor = statusServidor;
    }

    public String getTipoComponente() {
        return tipoComponente;
    }

    public void setTipoComponente(String tipoComponente) {
        this.tipoComponente = tipoComponente;
    }

    public Double getLimite() {
        return limite;
    }

    public void setLimite(Double limite) {
        this.limite = limite;
    }

    public String getUnidadeMedida() {
        return unidadeMedida;
    }

    public void setUnidadeMedida(String unidadeMedida) {
        this.unidadeMedida = unidadeMedida;
    }

    public LocalDateTime getDataAlerta() {
        return dataAlerta;
    }

    public void setDataAlerta(LocalDateTime dataAlerta) {
        this.dataAlerta = dataAlerta;
    }

    public String getCriticidade() {
        return criticidade;
    }

    public void setCriticidade(String criticidade) {
        this.criticidade = criticidade;
    }

    public String getStatusAlerta() {
        return statusAlerta;
    }

    public void setStatusAlerta(String statusAlerta) {
        this.statusAlerta = statusAlerta;
    }

    @Override
    public String toString() {
        return "RelatorioData{" +
                "nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", funcao='" + funcao + '\'' +
                ", email='" + email + '\'' +
                ", razaoSocial='" + razaoSocial + '\'' +
                ", hostname='" + hostname + '\'' +
                ", macAddress='" + macAddress + '\'' +
                ", statusServidor='" + statusServidor + '\'' +
                ", tipoComponente='" + tipoComponente + '\'' +
                ", limite=" + limite +
                ", unidadeMedida='" + unidadeMedida + '\'' +
                ", dataAlerta=" + dataAlerta +
                ", criticidade='" + criticidade + '\'' +
                ", statusAlerta='" + statusAlerta + '\'' +
                '}';
    }
}
