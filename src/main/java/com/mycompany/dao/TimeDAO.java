/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dao;

import com.mycompany.exception.TacticAllException;
import com.mycompany.model.Time;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author CS
 */
public class TimeDAO implements GenericoDAO<Time> {

    @Override
    public List<Time> listar() throws TacticAllException {
        List<Time> times = new ArrayList<Time>();
        String sql = "SELECT * FROM Time";
        Connection connection = null;
        try {
            connection = Conexao.getInstance().getConnection();
            PreparedStatement pStatement = connection.prepareStatement(sql);
            ResultSet result = pStatement.executeQuery();
            while (result.next()) {
                times.add(new Time(result.getInt("id"), result.getString("nome"), result.getString("sigla"), result.getString("pais"), result.getString("liga"), result.getInt("idUsuario"), result.getString("corUniforme")));
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TimeDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(TimeDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(TimeDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return times;
    }

    @Override
    public void inserir(Time time) throws TacticAllException {
        String sql = "INSERT INTO Time (nome, sigla, pais, liga, idUsuario, corUniforme) VALUES (?, ?, ?, ?, ?, ?)";
        Connection connection = null;
        try {
            connection = Conexao.getInstance().getConnection();
            PreparedStatement pStatement = connection.prepareStatement(sql);
            pStatement.setString(1, time.getNome());
            pStatement.setString(2, time.getSigla());
            pStatement.setString(3, time.getPais());
            pStatement.setString(4, time.getLiga());
            pStatement.setInt(5, time.getIdUsuario());
            pStatement.setString(6, time.getCorUniforme());
            pStatement.execute();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TimeDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new TacticAllException("Não foi possível carregar o driver de conexão com a base de dados");
        } catch (SQLException ex) {
            Logger.getLogger(TimeDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new TacticAllException("Erro ao enviar o comando para a base de dados");
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(TimeDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void alterar(Time time) throws TacticAllException {
        String sql = "UPDATE Time SET nome = ?,  sigla = ?, pais = ?, liga = ?, idUsuario = ?, corUniforme = ? WHERE id = ?";
        Connection connection = null;
        try {
            connection = Conexao.getInstance().getConnection();
            PreparedStatement pStatement = connection.prepareStatement(sql);
            pStatement.setString(1, time.getNome());
            pStatement.setString(2, time.getSigla());
            pStatement.setString(3, time.getPais());
            pStatement.setString(4, time.getLiga());
            pStatement.setInt(5, time.getIdUsuario());
            pStatement.setString(6, time.getCorUniforme());
            pStatement.setInt(7, time.getId());
            pStatement.execute();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TimeDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new TacticAllException("Não foi possível carregar o driver de conexão com a base de dados");
        } catch (SQLException ex) {
            Logger.getLogger(TimeDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new TacticAllException("Erro ao enviar o comando para a base de dados");
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(TimeDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void remover(Time time) throws TacticAllException {
        String sql = "DELETE FROM Time WHERE Id = ?";

        Connection connection = null;
        try {
            connection = Conexao.getInstance().getConnection();
            PreparedStatement pStatement = connection.prepareStatement(sql);
            pStatement.setInt(1, time.getId());
            pStatement.execute();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TimeDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new TacticAllException("Não foi possível conectar à base de dados!");
        } catch (SQLException ex) {
            Logger.getLogger(TimeDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new TacticAllException("Não foi possível enviar o comando para a base de dados!");
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(TimeDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public Time listarPorID(int ID) throws TacticAllException {
        Time time = new Time();
        Connection connection = null;
        String sql = "SELECT * FROM Time WHERE Id = ?";

        try {
            connection = Conexao.getInstance().getConnection();
            PreparedStatement pStatement = connection.prepareStatement(sql);
            pStatement.setInt(1, ID);
            ResultSet result = pStatement.executeQuery();
            while (result.next()) {
                time = new Time(result.getInt("id"), result.getString("nome"), result.getString("sigla"), result.getString("pais"), result.getString("liga"), result.getInt("idUsuario"), result.getString("corUniforme"));
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TimeDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(TimeDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(TimeDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return time;
    }

    public int IdMaisRecente() throws TacticAllException {
        int id = -1;
        Connection connection = null;
        String sql = "SELECT Id FROM Time ORDER BY Id DESC FETCH FIRST 1 ROW ONLY;";

        try {
            connection = Conexao.getInstance().getConnection();
            PreparedStatement pStatement = connection.prepareStatement(sql);
            ResultSet result = pStatement.executeQuery();
            while (result.next()) {
                id = result.getInt("id");
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TimeDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(TimeDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(TimeDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return id;
    }

}
