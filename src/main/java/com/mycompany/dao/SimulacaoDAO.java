/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dao;
import com.mycompany.exception.TacticAllException;
import com.mycompany.model.Simulacao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author CS
 */
public class SimulacaoDAO implements GenericoDAO<Simulacao> {

    @Override
    public List<Simulacao> listar() throws TacticAllException {
        List<Simulacao> simulacoes = new ArrayList<Simulacao>();
        String sql = "SELECT * FROM Simulacao";
        Connection connection = null;
        try {
            connection = Conexao.getInstance().getConnection();
            PreparedStatement pStatement = connection.prepareStatement(sql);
            ResultSet result = pStatement.executeQuery();
            while (result.next()) {
                simulacoes.add(new Simulacao(result.getInt("id"), result.getInt("idEsquemaOfensivoMandante"), result.getInt("idEsquemaOfensivoVisitante"), result.getInt("idEsquemaDefensivoMandante"), result.getInt("idEsquemaDefensivoVisitante"), result.getInt("idUsuario"), result.getInt("golsTimeVisitante"), result.getInt("golsTimeMandante"), result.getDate("dataSimulacao").toLocalDate()));
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SimulacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(SimulacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(SimulacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return simulacoes;
    }

    @Override
    public void inserir(Simulacao simulacao) throws TacticAllException {
        String sql = "INSERT INTO Simulacao (idEsquemaOfensivoMandante, idEsquemaOfensivoVisitante, idEsquemaDefensivoMandante, idEsquemaDefensivoVisitante, idUsuario, golsTimeVisitante, golsTimeMandante, dataSimulacao) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        Connection connection = null;
        try {
            connection = Conexao.getInstance().getConnection();
            PreparedStatement pStatement = connection.prepareStatement(sql);
            pStatement.setInt(1, simulacao.getIdEsquemaOfensivoMandante());
            pStatement.setInt(2, simulacao.getIdEsquemaOfensivoVisitante());
            pStatement.setInt(3, simulacao.getIdEsquemaDefensivoMandante());
            pStatement.setInt(4, simulacao.getIdEsquemaDefensivoVisitante());
            pStatement.setInt(5, simulacao.getIdUsuario());
            pStatement.setInt(6, simulacao.getGolsTimeVisitante());
            pStatement.setInt(7, simulacao.getGolsTimeMandante());
            pStatement.setDate(8, Date.valueOf(simulacao.getDataSimulacao()));
            pStatement.execute();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SimulacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new TacticAllException("Não foi possível carregar o driver de conexão com a base de dados");
        } catch (SQLException ex) {
            Logger.getLogger(SimulacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new TacticAllException("Erro ao enviar o comando para a base de dados");
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(SimulacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void alterar(Simulacao simulacao) throws TacticAllException {
       String sql = "UPDATE Simulacao SET idEsquemaOfensivoMandante = ?,  idEsquemaOfensivoVisitante = ?, idEsquemaDefensivoMandante = ?, idEsquemaDefensivoVisitante = ?, idUsuario = ?, golsTimeVisitante = ?, golsTimeMandante = ?, dataSimulacao = ? WHERE id = ?";
        Connection connection = null;
        try {
            connection = Conexao.getInstance().getConnection();
            PreparedStatement pStatement = connection.prepareStatement(sql);
            pStatement.setInt(1, simulacao.getIdEsquemaOfensivoMandante());
            pStatement.setInt(2, simulacao.getIdEsquemaOfensivoVisitante());
            pStatement.setInt(3, simulacao.getIdEsquemaDefensivoMandante());
            pStatement.setInt(4, simulacao.getIdEsquemaDefensivoVisitante());
            pStatement.setInt(5, simulacao.getIdUsuario());
            pStatement.setInt(6, simulacao.getGolsTimeVisitante());
            pStatement.setInt(7, simulacao.getGolsTimeMandante());
            pStatement.setDate(8, Date.valueOf(simulacao.getDataSimulacao()));
            pStatement.setInt(9, simulacao.getId());
            pStatement.execute();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SimulacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new TacticAllException("Não foi possível carregar o driver de conexão com a base de dados");
        } catch (SQLException ex) {
            Logger.getLogger(SimulacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new TacticAllException("Erro ao enviar o comando para a base de dados");
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(SimulacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void remover(Simulacao simulacao) throws TacticAllException {
        String sql = "DELETE FROM Simulacao WHERE Id = ?";

        Connection connection = null;
        try {
            connection = Conexao.getInstance().getConnection();
            PreparedStatement pStatement = connection.prepareStatement(sql);
            pStatement.setInt(1, simulacao.getId());
            pStatement.execute();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SimulacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new TacticAllException("Não foi possível conectar à base de dados!");
        } catch (SQLException ex) {
            Logger.getLogger(SimulacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new TacticAllException("Não foi possível enviar o comando para a base de dados!");
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(SimulacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public Simulacao listarPorID(int ID) throws TacticAllException {
        Simulacao simulacao = new Simulacao();
        Connection connection = null;
        String sql = "SELECT * FROM Simulacao WHERE Id = ?";

        try {
            connection = Conexao.getInstance().getConnection();
            PreparedStatement pStatement = connection.prepareStatement(sql);
            pStatement.setInt(1, ID);
            ResultSet result = pStatement.executeQuery();
            while (result.next()) {
                simulacao = new Simulacao(result.getInt("id"), result.getInt("idEsquemaOfensivoMandante"), result.getInt("idEsquemaOfensivoVisitante"), result.getInt("idEsquemaDefensivoMandante"), result.getInt("idEsquemaDefensivoVisitante"), result.getInt("idUsuario"), result.getInt("golsTimeVisitante"), result.getInt("golsTimeMandante"), result.getDate("dataSimulacao").toLocalDate());
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SimulacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(SimulacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(SimulacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return simulacao;
    }
    
}
