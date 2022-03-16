package br.ufpr.dao;

import br.ufpr.connection.ConnectionFactory;
import br.ufpr.modelo.Agenda;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author ander
 */
public class AgendaDAO {
    public void create(Agenda a){
    Connection con = ConnectionFactory.getConnection();
    PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO agenda (nome, sobrenome,"
            + " telefone) VALUES (?,?,?)");
            stmt.setString(1, a.getNome());
            stmt.setString(2, a.getSobrenome());
            stmt.setString(3, a.getTelefone());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Registro efetuado com sucesso!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar! Mensagem de erro:" + e);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    public List<Agenda> read(){
    Connection con = ConnectionFactory.getConnection();
    PreparedStatement stmt = null;
    ResultSet rs = null;
    List<Agenda> agendas = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM agenda");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Agenda agenda = new Agenda();
                agenda.setId(rs.getInt("id"));
                agenda.setNome(rs.getString("nome"));
                agenda.setSobrenome(rs.getString("sobrenome"));
                agenda.setTelefone(rs.getString("telefone"));
                agendas.add(agenda);
            }

        } catch (SQLException e) {
            Logger.getLogger(AgendaDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
         return agendas;

}

}
