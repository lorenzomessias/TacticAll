
package com.mycompany.dao;

import com.mycompany.exception.TacticAllException;
import com.mycompany.model.Profissional;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ProfissionalDAO implements GenericoDAO<Profissional> {
    public int IdMaisRecente() throws TacticAllException {
        int id = -1;
        Connection connection = null;
        String sql = "SELECT Id FROM Profissional ORDER BY Id DESC FETCH FIRST 1 ROW ONLY;";

        try {
            connection = Conexao.getInstance().getConnection();
            PreparedStatement pStatement = connection.prepareStatement(sql);
            ResultSet result = pStatement.executeQuery();
            while (result.next()) {
                id = result.getInt("Id");
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProfissionalDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ProfissionalDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(TimeDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return id;
    }

    @Override
    public List<Profissional> listar() throws TacticAllException {
        List<Profissional> profissionais = new ArrayList<>();
        String sql = "SELECT * FROM Profissional";
        Connection connection = null;
        try {
            connection = Conexao.getInstance().getConnection();
            PreparedStatement pStatement = connection.prepareStatement(sql);
            ResultSet result = pStatement.executeQuery();
            while (result.next()) {
                profissionais.add(new Profissional(result.getInt("Id"), result.getString("Nome"),
                        result.getDate("DataDeNascimento").toLocalDate(), result.getString("Nacionalidade"),
                        result.getDouble("NotaGeral")));
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProfissionalDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ProfissionalDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(ProfissionalDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return profissionais;
    }

    @Override
    public void inserir(Profissional profissional) throws TacticAllException {
        String sql = "INSERT INTO Profissional (Nome, DataDeNascimento, Nacionalidade, NotaGeral) VALUES (?, ?, ?, ?)";
        Connection connection = null;
        try {
            connection = Conexao.getInstance().getConnection();
            PreparedStatement pStatement = connection.prepareStatement(sql);
            pStatement.setString(1, profissional.getNome());
            pStatement.setDate(2, Date.valueOf(profissional.getDataDeNascimento()));
            pStatement.setString(3, profissional.getNacionalidade());
            pStatement.setDouble(4, profissional.getNotaGeral());
            pStatement.execute();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProfissionalDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new TacticAllException("Não foi possível carregar o driver de conexão com a base de dados");
        } catch (SQLException ex) {
            Logger.getLogger(ProfissionalDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new TacticAllException("Erro ao enviar o comando para a base de dados");
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(ProfissionalDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void alterar(Profissional profissional) throws TacticAllException {
        String sql = "UPDATE Profissional SET Nome = ?, DataDeNascimento = ?, Nacionalidade = ?, NotaGeral = ? WHERE Id = ?";
        Connection connection = null;
        try {
            connection = Conexao.getInstance().getConnection();
            PreparedStatement pStatement = connection.prepareStatement(sql);
            pStatement.setString(1, profissional.getNome());
            pStatement.setDate(2, Date.valueOf(profissional.getDataDeNascimento()));
            pStatement.setString(3, profissional.getNacionalidade());
            pStatement.setDouble(4, profissional.getNotaGeral());
            pStatement.setInt(5, profissional.getId());
            pStatement.execute();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProfissionalDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new TacticAllException("Não foi possível carregar o driver de conexão com a base de dados");
        } catch (SQLException ex) {
            Logger.getLogger(ProfissionalDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new TacticAllException("Erro ao enviar o comando para a base de dados");
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(ProfissionalDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void remover(Profissional profissional) throws TacticAllException {
        String sql = "DELETE FROM Profissional WHERE Id = ?";

        Connection connection = null;
        try {
            connection = Conexao.getInstance().getConnection();
            PreparedStatement pStatement = connection.prepareStatement(sql);
            pStatement.setInt(1, profissional.getId());
            pStatement.execute();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProfissionalDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new TacticAllException("Não foi possível conectar à base de dados!");
        } catch (SQLException ex) {
            Logger.getLogger(ProfissionalDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new TacticAllException("Não foi possível enviar o comando para a base de dados!");
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(ProfissionalDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public Profissional listarPorID(int ID) throws TacticAllException {
        Profissional profissional = new Profissional();
        Connection connection = null;
        String sql = "SELECT * FROM Profissional WHERE Id = ?";

        try {
            connection = Conexao.getInstance().getConnection();
            PreparedStatement pStatement = connection.prepareStatement(sql);
            pStatement.setInt(1, ID);
            ResultSet result = pStatement.executeQuery();
            while (result.next()) {
                profissional = new Profissional(result.getInt("Id"), result.getString("Nome"),
                        result.getDate("DataDeNascimento").toLocalDate(), result.getString("Nacionalidade"),
                        result.getDouble("NotaGeral"));
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProfissionalDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ProfissionalDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(ProfissionalDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return profissional;
    }
}
