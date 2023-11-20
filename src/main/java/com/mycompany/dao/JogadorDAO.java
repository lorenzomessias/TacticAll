package com.mycompany.dao;
import com.mycompany.exception.TacticAllException;
import com.mycompany.model.Jogador;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JogadorDAO implements GenericoDAO<Jogador> {

    @Override
    public List<Jogador> listar() throws TacticAllException {
        List<Jogador> jogadores = new ArrayList<>();
        String sql = "SELECT * FROM Jogador";
        Connection connection = null;
        try {
            connection = Conexao.getInstance().getConnection();
            PreparedStatement pStatement = connection.prepareStatement(sql);
            ResultSet result = pStatement.executeQuery();
            while (result.next()) {
                Jogador jogador = new Jogador(result.getInt("Id"), 
                        result.getString("Posicao"),
                        result.getInt("IdProfissional"));
                jogadores.add(jogador);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(JogadorDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(JogadorDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(JogadorDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return jogadores;
    }

    @Override
    public void inserir(Jogador jogador) throws TacticAllException {
        String sql = "INSERT INTO Jogador ( Posicao,IdProfissional) VALUES (?, ?)";
        Connection connection = null;
        try {
            connection = Conexao.getInstance().getConnection();
            PreparedStatement pStatement = connection.prepareStatement(sql);
            pStatement.setString(1, jogador.getPosicao());
            pStatement.setInt(2, jogador.getIdProfissional());
            pStatement.execute();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(JogadorDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new TacticAllException("Não foi possível carregar o driver de conexão com a base de dados");
        } catch (SQLException ex) {
            Logger.getLogger(JogadorDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new TacticAllException("Erro ao enviar o comando para a base de dados");
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(JogadorDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void alterar(Jogador jogador) throws TacticAllException {
        String sql = "UPDATE Jogador SET  Posicao = ?, IdProfissional = ? WHERE Id = ?";
        Connection connection = null;
        try {
            connection = Conexao.getInstance().getConnection();
            PreparedStatement pStatement = connection.prepareStatement(sql);
            pStatement.setString(1, jogador.getPosicao());
            pStatement.setInt(2, jogador.getIdProfissional());
            pStatement.setInt(3, jogador.getId());
            pStatement.execute();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(JogadorDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new TacticAllException("Não foi possível carregar o driver de conexão com a base de dados");
        } catch (SQLException ex) {
            Logger.getLogger(JogadorDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new TacticAllException("Erro ao enviar o comando para a base de dados");
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(JogadorDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void remover(Jogador jogador) throws TacticAllException {
        String sql = "DELETE FROM Jogador WHERE Id = ?";

        Connection connection = null;
        try {
            connection = Conexao.getInstance().getConnection();
            PreparedStatement pStatement = connection.prepareStatement(sql);
            pStatement.setInt(1, jogador.getId());
            pStatement.execute();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(JogadorDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new TacticAllException("Não foi possível conectar à base de dados!");
        } catch (SQLException ex) {
            Logger.getLogger(JogadorDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new TacticAllException("Não foi possível enviar o comando para a base de dados!");
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(JogadorDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public Jogador listarPorID(int ID) throws TacticAllException {
        Jogador jogador = new Jogador();
        Connection connection = null;
        String sql = "SELECT * FROM Jogador WHERE Id = ?";

        try {
            connection = Conexao.getInstance().getConnection();
            PreparedStatement pStatement = connection.prepareStatement(sql);
            pStatement.setInt(1, ID);
            ResultSet result = pStatement.executeQuery();
            while (result.next()) {
                jogador = new Jogador(result.getInt("Id"),
                        result.getString("Posicao"),
                        result.getInt("IdProfissional"));
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(JogadorDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(JogadorDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(JogadorDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return jogador;
    }
        public List<Jogador> listarPorNome(String pesquisa) throws TacticAllException {
        List<Jogador> jogadores = new ArrayList<>();
        String sql = "SELECT * FROM Jogador WHERE Nome LIKE %?%";
        Connection connection = null;
        try {
            connection = Conexao.getInstance().getConnection();
            PreparedStatement pStatement = connection.prepareStatement(sql);
            pStatement.setString(1, pesquisa);
            ResultSet result = pStatement.executeQuery();
            while (result.next()) {
                Jogador jogador = new Jogador(result.getInt("Id"), result.getInt("Habilidade"),
                        result.getString("Posicao"), result.getInt("Velocidade"),
                        result.getInt("Resistencia"), result.getInt("Tecnica"),
                        result.getInt("Chute"), result.getInt("Passe"),
                        result.getInt("IdProfissional"));
                jogadores.add(jogador);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(JogadorDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(JogadorDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(JogadorDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return jogadores;
    }
}

