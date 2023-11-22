package com.mycompany.dao;

import com.mycompany.exception.TacticAllException;
import com.mycompany.model.EsquemaTatico;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EsquemaTaticoDAO implements GenericoDAO<EsquemaTatico> {

    @Override
    public List<EsquemaTatico> listar() throws TacticAllException {
        List<EsquemaTatico> esquemas = new ArrayList<>();
        String sql = "SELECT * FROM EsquemaTatico";
        try (Connection connection = Conexao.getInstance().getConnection(); PreparedStatement pStatement = connection.prepareStatement(sql); ResultSet result = pStatement.executeQuery()) {
            while (result.next()) {
                EsquemaTatico esquema = new EsquemaTatico(
                        result.getInt("Id"), result.getInt("IdTime"), result.getString("Tipo"),
                        result.getString("Nome"), result.getString("Formacao"), result.getString("TaticaEspecifica"));
                esquemas.add(esquema);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(EsquemaTaticoDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new TacticAllException("Erro ao acessar o banco de dados");
        }
        return esquemas;
    }

    @Override
    public void inserir(EsquemaTatico esquema) throws TacticAllException {
        String sql = "INSERT INTO EsquemaTatico (IdTime, Tipo, Nome, Formacao, TaticaEspecifica) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = Conexao.getInstance().getConnection(); PreparedStatement pStatement = connection.prepareStatement(sql)) {
            pStatement.setInt(1, esquema.getIdTime());
            pStatement.setString(2, esquema.getTipo());
            pStatement.setString(3, esquema.getNome());
            pStatement.setString(4, esquema.getFormacao());
            pStatement.setString(5, esquema.getTaticaEspecifica());
            pStatement.execute();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(EsquemaTaticoDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new TacticAllException("Erro ao acessar o banco de dados");
        }
    }

    @Override
    public void alterar(EsquemaTatico esquema) throws TacticAllException {
        String sql = "UPDATE EsquemaTatico SET IdTime = ?, Tipo = ?, Nome = ?, Formacao = ?, TaticaEspecifica = ? WHERE Id = ?";
        try (Connection connection = Conexao.getInstance().getConnection(); PreparedStatement pStatement = connection.prepareStatement(sql)) {
            pStatement.setInt(1, esquema.getIdTime());
            pStatement.setString(2, esquema.getTipo());
            pStatement.setString(3, esquema.getNome());
            pStatement.setString(4, esquema.getFormacao());
            pStatement.setString(5, esquema.getTaticaEspecifica());
            pStatement.setInt(6, esquema.getId());
            pStatement.execute();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(EsquemaTaticoDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new TacticAllException("Erro ao acessar o banco de dados");
        }
    }

    @Override
    public void remover(EsquemaTatico esquema) throws TacticAllException {
        String sql = "DELETE FROM EsquemaTatico WHERE Id = ?";
        try (Connection connection = Conexao.getInstance().getConnection(); PreparedStatement pStatement = connection.prepareStatement(sql)) {
            pStatement.setInt(1, esquema.getId());
            pStatement.execute();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(EsquemaTaticoDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new TacticAllException("Erro ao acessar o banco de dados");
        }
    }

    @Override
    public EsquemaTatico listarPorID(int ID) throws TacticAllException {
        EsquemaTatico esquema = new EsquemaTatico();
        String sql = "SELECT * FROM EsquemaTatico WHERE Id = ?";
        try (Connection connection = Conexao.getInstance().getConnection(); PreparedStatement pStatement = connection.prepareStatement(sql)) {
            pStatement.setInt(1, ID);
            try (ResultSet result = pStatement.executeQuery()) {
                while (result.next()) {
                    esquema = new EsquemaTatico(
                            result.getInt("Id"), result.getInt("IdTime"),
                            result.getString("Tipo"), result.getString("Nome"),
                            result.getString("Formacao"), result.getString("TaticaEspecifica"));
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(EsquemaTaticoDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new TacticAllException("Erro ao acessar o banco de dados");
        }
        return esquema;
    }

    public List<EsquemaTatico> listarEsquemasPorTime(int idTime, String tipo) throws TacticAllException {
        List<EsquemaTatico> esquemas = new ArrayList<>();
        String sql = "SELECT * FROM EsquemaTatico WHERE IdTime = ? AND Tipo = ?";
        try (Connection connection = Conexao.getInstance().getConnection(); PreparedStatement pStatement = connection.prepareStatement(sql)) {
            pStatement.setInt(1, idTime);
            pStatement.setString(2, tipo);

            try (ResultSet result = pStatement.executeQuery()) {
                while (result.next()) {
                    EsquemaTatico esquema = new EsquemaTatico(
                            result.getInt("Id"), result.getInt("IdTime"),
                            result.getString("Tipo"), result.getString("Nome"),
                            result.getString("Formacao"), result.getString("TaticaEspecifica"));
                    esquemas.add(esquema);
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(EsquemaTaticoDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new TacticAllException("Erro ao acessar o banco de dados");
        }
        return esquemas;
    }

    public List<EsquemaTatico> listarPorNome(String pesquisa, int id) throws TacticAllException {
        List<EsquemaTatico> esquemas = new ArrayList<>();
        String sql = "SELECT * FROM EsquemaTatico WHERE Nome LIKE ? AND IDTIME = ?";
        try {
            Connection connection = Conexao.getInstance().getConnection();
            PreparedStatement pStatement = connection.prepareStatement(sql);
            pStatement.setString(1, "%" + pesquisa + "%");
            pStatement.setInt(2, id);
            ResultSet result = pStatement.executeQuery();
            {
                while (result.next()) {
                    EsquemaTatico esquema = new EsquemaTatico(
                            result.getInt("Id"), result.getInt("IdTime"), result.getString("Tipo"),
                            result.getString("Nome"), result.getString("Formacao"), result.getString("TaticaEspecifica"));
                    esquemas.add(esquema);
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(EsquemaTaticoDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new TacticAllException("Erro ao acessar o banco de dados");
        }
        return esquemas;
    }
    
        public List<EsquemaTatico> listarPorNome(String pesquisa, int id, String categ) throws TacticAllException {
        List<EsquemaTatico> esquemas = new ArrayList<>();
        String sql = "SELECT * FROM EsquemaTatico WHERE Nome LIKE ? AND IDTIME = ? AND Tipo = ?";
        try {
            Connection connection = Conexao.getInstance().getConnection();
            PreparedStatement pStatement = connection.prepareStatement(sql);
            pStatement.setString(1, "%" + pesquisa + "%");
            pStatement.setInt(2, id);
            pStatement.setString(3, categ);
            ResultSet result = pStatement.executeQuery();
            {
                while (result.next()) {
                    EsquemaTatico esquema = new EsquemaTatico(
                            result.getInt("Id"), result.getInt("IdTime"), result.getString("Tipo"),
                            result.getString("Nome"), result.getString("Formacao"), result.getString("TaticaEspecifica"));
                    esquemas.add(esquema);
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(EsquemaTaticoDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new TacticAllException("Erro ao acessar o banco de dados");
        }
        return esquemas;
    }
}
