package com.mycompany.dao;

import com.mycompany.exception.TacticAllException;
import com.mycompany.model.Treinador;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TreinadorDAO implements GenericoDAO<Treinador> {

    @Override
    public List<Treinador> listar() throws TacticAllException {
        List<Treinador> treinadores = new ArrayList<>();
        String sql = "SELECT * FROM Treinador";
        Connection connection = null;
        try {
            connection = Conexao.getInstance().getConnection();
            PreparedStatement pStatement = connection.prepareStatement(sql);
            ResultSet result = pStatement.executeQuery();
            while (result.next()) {
                Treinador treinador = new Treinador(result.getInt("Id"), result.getInt("IdProfissional"),
                        result.getString("Especialidade"));
                treinadores.add(treinador);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TreinadorDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(TreinadorDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(TreinadorDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return treinadores;
    }

    @Override
    public void inserir(Treinador treinador) throws TacticAllException {
        String sql = "INSERT INTO Treinador (IdProfissional, Especialidade) VALUES (?, ?)";
        Connection connection = null;
        try {
            connection = Conexao.getInstance().getConnection();
            PreparedStatement pStatement = connection.prepareStatement(sql);
            pStatement.setInt(1, treinador.getIdProfissional());
            pStatement.setString(2, treinador.getEspecialidade());
            pStatement.execute();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TreinadorDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new TacticAllException("Não foi possível carregar o driver de conexão com a base de dados");
        } catch (SQLException ex) {
            Logger.getLogger(TreinadorDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new TacticAllException("Erro ao enviar o comando para a base de dados");
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(TreinadorDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void alterar(Treinador treinador) throws TacticAllException {
        String sql = "UPDATE Treinador SET IdProfissional = ?, Especialidade = ? WHERE Id = ?";
        Connection connection = null;
        try {
            connection = Conexao.getInstance().getConnection();
            PreparedStatement pStatement = connection.prepareStatement(sql);
            pStatement.setInt(1, treinador.getIdProfissional());
            pStatement.setString(2, treinador.getEspecialidade());
            pStatement.setInt(3, treinador.getId());
            pStatement.execute();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TreinadorDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new TacticAllException("Não foi possível carregar o driver de conexão com a base de dados");
        } catch (SQLException ex) {
            Logger.getLogger(TreinadorDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new TacticAllException("Erro ao enviar o comando para a base de dados");
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(TreinadorDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void remover(Treinador treinador) throws TacticAllException {
        String sql = "DELETE FROM Treinador WHERE Id = ?";

        Connection connection = null;
        try {
            connection = Conexao.getInstance().getConnection();
            PreparedStatement pStatement = connection.prepareStatement(sql);
            pStatement.setInt(1, treinador.getId());
            pStatement.execute();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TreinadorDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new TacticAllException("Não foi possível conectar à base de dados!");
        } catch (SQLException ex) {
            Logger.getLogger(TreinadorDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new TacticAllException("Não foi possível enviar o comando para a base de dados!");
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(TreinadorDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public Treinador listarPorID(int ID) throws TacticAllException {
        Treinador treinador = new Treinador();
        Connection connection = null;
        String sql = "SELECT * FROM Treinador WHERE Id = ?";

        try {
            connection = Conexao.getInstance().getConnection();
            PreparedStatement pStatement = connection.prepareStatement(sql);
            pStatement.setInt(1, ID);
            ResultSet result = pStatement.executeQuery();
            while (result.next()) {
                treinador = new Treinador(result.getInt("Id"), result.getInt("IdProfissional"),
                        result.getString("Especialidade"));
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TreinadorDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(TreinadorDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(TreinadorDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return treinador;
    }
    
        public List<Treinador> listarPorNome(String pesquisa) throws TacticAllException {
        List<Treinador> treinadores = new ArrayList<>();
        String sql = "SELECT * FROM Treinador INNER JOIN Profissional ON Treinador.IDPROFISSIONAL = Profissional.ID WHERE Nome LIKE '%?%'";
        Connection connection = null;
        try {
            connection = Conexao.getInstance().getConnection();
            PreparedStatement pStatement = connection.prepareStatement(sql);
            pStatement.setString(1, pesquisa);
            ResultSet result = pStatement.executeQuery();
            while (result.next()) {
            Treinador treinador;
                treinador = new Treinador(result.getInt("id"), result.getString("nome"), result.getDate("datadenascimento").toLocalDate(), 
                        result.getString("nacionalidade"), result.getInt("notageral"),
                        result.getInt("idprofissional"), result.getString("image"));
                treinadores.add(treinador);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TreinadorDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(TreinadorDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(TreinadorDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return treinadores;
    }
}
