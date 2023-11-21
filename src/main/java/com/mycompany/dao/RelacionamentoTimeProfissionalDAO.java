package com.mycompany.dao;

import com.mycompany.exception.TacticAllException;
import com.mycompany.model.Jogador;
import com.mycompany.model.RelacionamentoTimeProfissional;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RelacionamentoTimeProfissionalDAO implements GenericoDAO<RelacionamentoTimeProfissional> {

    @Override
    public List<RelacionamentoTimeProfissional> listar() throws TacticAllException {
        List<RelacionamentoTimeProfissional> relacionamentos = new ArrayList<>();
        String sql = "SELECT * FROM RelacionamentoTimeProfissional";
        Connection connection = null;
        try {
            connection = Conexao.getInstance().getConnection();
            PreparedStatement pStatement = connection.prepareStatement(sql);
            ResultSet result = pStatement.executeQuery();
            while (result.next()) {
                RelacionamentoTimeProfissional relacionamento = new RelacionamentoTimeProfissional(
                        result.getInt("Id"), result.getInt("IdProfissional"), result.getInt("IdTime"));
                relacionamentos.add(relacionamento);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RelacionamentoTimeProfissionalDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(RelacionamentoTimeProfissionalDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(RelacionamentoTimeProfissionalDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return relacionamentos;
    }

    @Override
    public void inserir(RelacionamentoTimeProfissional relacionamento) throws TacticAllException {
        String sql = "INSERT INTO RelacionamentoTimeProfissional (IdProfissional, IdTime) VALUES (?, ?)";
        Connection connection = null;
        try {
            connection = Conexao.getInstance().getConnection();
            PreparedStatement pStatement = connection.prepareStatement(sql);
            pStatement.setInt(1, relacionamento.getIdProfissional());
            pStatement.setInt(2, relacionamento.getIdTime());
            pStatement.execute();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RelacionamentoTimeProfissionalDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new TacticAllException("Não foi possível carregar o driver de conexão com a base de dados");
        } catch (SQLException ex) {
            Logger.getLogger(RelacionamentoTimeProfissionalDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new TacticAllException("Erro ao enviar o comando para a base de dados");
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(RelacionamentoTimeProfissionalDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void alterar(RelacionamentoTimeProfissional relacionamento) throws TacticAllException {
        String sql = "UPDATE RelacionamentoTimeProfissional SET IdProfissional = ?, IdTime = ? WHERE Id = ?";
        Connection connection = null;
        try {
            connection = Conexao.getInstance().getConnection();
            PreparedStatement pStatement = connection.prepareStatement(sql);
            pStatement.setInt(1, relacionamento.getIdProfissional());
            pStatement.setInt(2, relacionamento.getIdTime());
            pStatement.setInt(3, relacionamento.getId());
            pStatement.execute();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RelacionamentoTimeProfissionalDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new TacticAllException("Não foi possível carregar o driver de conexão com a base de dados");
        } catch (SQLException ex) {
            Logger.getLogger(RelacionamentoTimeProfissionalDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new TacticAllException("Erro ao enviar o comando para a base de dados");
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(RelacionamentoTimeProfissionalDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void remover(RelacionamentoTimeProfissional relacionamento) throws TacticAllException {
        String sql = "DELETE FROM RelacionamentoTimeProfissional WHERE Id = ?";

        Connection connection = null;
        try {
            connection = Conexao.getInstance().getConnection();
            PreparedStatement pStatement = connection.prepareStatement(sql);
            pStatement.setInt(1, relacionamento.getId());
            pStatement.execute();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RelacionamentoTimeProfissionalDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new TacticAllException("Não foi possível conectar à base de dados!");
        } catch (SQLException ex) {
            Logger.getLogger(RelacionamentoTimeProfissionalDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new TacticAllException("Não foi possível enviar o comando para a base de dados!");
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(RelacionamentoTimeProfissionalDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public RelacionamentoTimeProfissional listarPorID(int ID) throws TacticAllException {
        RelacionamentoTimeProfissional relacionamento = new RelacionamentoTimeProfissional();
        Connection connection = null;
        String sql = "SELECT * FROM RelacionamentoTimeProfissional WHERE Id = ?";

        try {
            connection = Conexao.getInstance().getConnection();
            PreparedStatement pStatement = connection.prepareStatement(sql);
            pStatement.setInt(1, ID);
            ResultSet result = pStatement.executeQuery();
            while (result.next()) {
                relacionamento = new RelacionamentoTimeProfissional(result.getInt("Id"),
                        result.getInt("IdProfissional"), result.getInt("IdTime"));
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RelacionamentoTimeProfissionalDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(RelacionamentoTimeProfissionalDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(RelacionamentoTimeProfissionalDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return relacionamento;

    }

    public List<Jogador> listarJogadoresDoTime(int idTime) throws TacticAllException {
        List<Jogador> jogadores = new ArrayList<>();
        String sql = "SELECT j.*, p.* "
                + "FROM Jogador j "
                + "INNER JOIN Profissional p ON j.IdProfissional = p.Id "
                + "INNER JOIN RelacionamentoProfissionalTime r ON p.Id = r.IdProfissional "
                + "WHERE r.IdTime = ?";
        Connection connection = null;
        try {
            connection = Conexao.getInstance().getConnection();
            PreparedStatement pStatement = connection.prepareStatement(sql);
            pStatement.setInt(1, idTime);
            ResultSet result = pStatement.executeQuery();
            while (result.next()) {
                Jogador jogador = new Jogador(
                        result.getInt("j.Id"), result.getString("p.Nome"), result.getDate("p.DataNascimento").toLocalDate(),
                        result.getString("p.Nacionalidade"), result.getInt("p.NotaGeral"), result.getString("j.Posicao"),
                        result.getInt("j.IdProfissional"), result.getString("p.Imagem"));

                jogadores.add(jogador);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(RelacionamentoTimeProfissionalDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(RelacionamentoTimeProfissionalDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return jogadores;
    }
}
