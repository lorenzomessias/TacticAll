/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dao;

/**
 *
 * @author Everymind
 */
import com.mycompany.exception.TacticAllException;
import com.mycompany.model.RelacionamentoJogadorEsquema;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.mycompany.exception.TacticAllException;
import com.mycompany.model.EsquemaTatico;
import com.mycompany.model.Jogador;
import java.util.List;

public class RelacionamentoJogadorEsquemaDAO implements GenericoDAO<RelacionamentoJogadorEsquema> {

    @Override
    public List<RelacionamentoJogadorEsquema> listar() throws TacticAllException {
        List<RelacionamentoJogadorEsquema> relacionamentos = new ArrayList<>();
        String sql = "SELECT * FROM RelacionamentoJogadorEsquema";
        Connection connection = null;
        try {
            connection = Conexao.getInstance().getConnection();
            PreparedStatement pStatement = connection.prepareStatement(sql);
            ResultSet result = pStatement.executeQuery();
            while (result.next()) {
                RelacionamentoJogadorEsquema relacionamento = new RelacionamentoJogadorEsquema(
                        result.getInt("Id"),
                        result.getInt("IdJogador"),
                        result.getInt("IdEsquema"),
                        result.getString("Posicao")
                );
                relacionamentos.add(relacionamento);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(RelacionamentoJogadorEsquemaDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new TacticAllException("Erro ao buscar dados na base de dados");
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(RelacionamentoJogadorEsquemaDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return relacionamentos;
    }

    @Override
    public void inserir(RelacionamentoJogadorEsquema relacionamento) throws TacticAllException {
        String sql = "INSERT INTO RelacionamentoJogadorEsquema (IdJogador, IdEsquema, Posicao) VALUES (?, ?, ?)";
        Connection connection = null;
        try {
            connection = Conexao.getInstance().getConnection();
            PreparedStatement pStatement = connection.prepareStatement(sql);
            pStatement.setInt(1, relacionamento.getIdJogador());
            pStatement.setInt(2, relacionamento.getIdEsquema());
            pStatement.setString(3, relacionamento.getPosicao());
            pStatement.execute();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(RelacionamentoJogadorEsquemaDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new TacticAllException("Erro ao inserir dados na base de dados");
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(RelacionamentoJogadorEsquemaDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void alterar(RelacionamentoJogadorEsquema relacionamento) throws TacticAllException {
        String sql = "UPDATE RelacionamentoJogadorEsquema SET IdJogador = ?, IdEsquema = ?, Posicao = ? WHERE Id = ?";
        Connection connection = null;
        try {
            connection = Conexao.getInstance().getConnection();
            PreparedStatement pStatement = connection.prepareStatement(sql);
            pStatement.setInt(1, relacionamento.getIdJogador());
            pStatement.setInt(2, relacionamento.getIdEsquema());
            pStatement.setString(3, relacionamento.getPosicao());
            pStatement.setInt(4, relacionamento.getId());
            pStatement.execute();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(RelacionamentoJogadorEsquemaDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new TacticAllException("Erro ao alterar dados na base de dados");
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(RelacionamentoJogadorEsquemaDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void remover(RelacionamentoJogadorEsquema relacionamento) throws TacticAllException {
        String sql = "DELETE FROM RelacionamentoJogadorEsquema WHERE Id = ?";
        Connection connection = null;
        try {
            connection = Conexao.getInstance().getConnection();
            PreparedStatement pStatement = connection.prepareStatement(sql);
            pStatement.setInt(1, relacionamento.getId());
            pStatement.execute();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(RelacionamentoJogadorEsquemaDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new TacticAllException("Erro ao remover dados na base de dados");
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(RelacionamentoJogadorEsquemaDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public RelacionamentoJogadorEsquema listarPorID(int ID) throws TacticAllException {
        RelacionamentoJogadorEsquema relacionamento = new RelacionamentoJogadorEsquema();
        Connection connection = null;
        String sql = "SELECT * FROM RelacionamentoJogadorEsquema WHERE Id = ?";
        try {
            connection = Conexao.getInstance().getConnection();
            PreparedStatement pStatement = connection.prepareStatement(sql);
            pStatement.setInt(1, ID);
            ResultSet result = pStatement.executeQuery();
            while (result.next()) {
                relacionamento = new RelacionamentoJogadorEsquema(
                        result.getInt("Id"),
                        result.getInt("IdJogador"),
                        result.getInt("IdEsquema"),
                        result.getString("Posicao")
                );
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(RelacionamentoJogadorEsquemaDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new TacticAllException("Erro ao buscar dados na base de dados");
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(RelacionamentoJogadorEsquemaDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return relacionamento;
    }

    public List<Jogador> listarJogadoresPorEsquema(int idEsquema) throws TacticAllException {
        List<Jogador> jogadores = new ArrayList<>();
        String sql = "SELECT *  FROM Jogador "
                + "INNER JOIN RelacionamentoJogadorEsquema  ON Jogador.Id = RelacionamentoJogadorEsquema.IdJogador "
                + "INNER JOIN Profissional ON Jogador.IdProfissional = Profissional.Id "
                + "WHERE RelacionamentoJogadorEsquema.IdEsquema = ?";
        Connection connection = null;

        try {
            connection = Conexao.getInstance().getConnection();
            PreparedStatement pStatement = connection.prepareStatement(sql);
            pStatement.setInt(1, idEsquema);
            ResultSet result = pStatement.executeQuery();

            while (result.next()) {
                Jogador jogador = new Jogador(
                        result.getInt("Id"), result.getString("Nome"), result.getDate("DataDeNascimento").toLocalDate(),
                        result.getString("Nacionalidade"), result.getInt("NotaGeral"), result.getString("Posicao"),
                        result.getInt("IdProfissional"), result.getString("Imagem"));

                jogadores.add(jogador);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(RelacionamentoJogadorEsquemaDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new TacticAllException("Erro ao buscar jogadores por esquema");
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(RelacionamentoJogadorEsquemaDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return jogadores;
    }

    public void removerDoTime(int id) throws TacticAllException {
        String sql = "DELETE FROM RelacionamentoJogadorEsquema WHERE IdTime = ?";
        Connection connection = null;
        try {
            connection = Conexao.getInstance().getConnection();
            PreparedStatement pStatement = connection.prepareStatement(sql);
            pStatement.setInt(1, id);
            pStatement.execute();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(RelacionamentoJogadorEsquemaDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new TacticAllException("Erro ao remover dados na base de dados");
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(RelacionamentoJogadorEsquemaDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
        public void removerDoEsquema(int id) throws TacticAllException {
        String sql = "DELETE FROM RelacionamentoJogadorEsquema WHERE IdEsquema = ?";
        Connection connection = null;
        try {
            connection = Conexao.getInstance().getConnection();
            PreparedStatement pStatement = connection.prepareStatement(sql);
            pStatement.setInt(1, id);
            pStatement.execute();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(RelacionamentoJogadorEsquemaDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new TacticAllException("Erro ao remover dados na base de dados");
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(RelacionamentoJogadorEsquemaDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public List<RelacionamentoJogadorEsquema> listarPorEsquema(EsquemaTatico e) throws TacticAllException {
        List<RelacionamentoJogadorEsquema> relacionamentos = new ArrayList<>();
        String sql = "SELECT * FROM RelacionamentoJogadorEsquema WHERE IDESQUEMA = ?";
        Connection connection = null;
        try {
            connection = Conexao.getInstance().getConnection();
            PreparedStatement pStatement = connection.prepareStatement(sql);
            pStatement.setInt(1, e.getId());
            ResultSet result = pStatement.executeQuery();
            while (result.next()) {
                RelacionamentoJogadorEsquema relacionamento = new RelacionamentoJogadorEsquema(
                        result.getInt("Id"),
                        result.getInt("IdJogador"),
                        result.getInt("IdEsquema"),
                        result.getString("Posicao")
                );
                relacionamentos.add(relacionamento);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(RelacionamentoJogadorEsquemaDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new TacticAllException("Erro ao buscar dados na base de dados");
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(RelacionamentoJogadorEsquemaDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return relacionamentos;
    }

}
