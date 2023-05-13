package controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Fornecedor;

public class FornecedorDAO {

    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private List<Fornecedor> fornecedores;
    private Fornecedor fornecedor;

    private final String CONSULTAR = "SELECT * FROM cvendas.fornecedor;";
    private final String CONSULTAR_POR_NOME = "SELECT * FROM cvendas.fornecedor WHERE fornecedor.NOME LIKE ?";
    private final String INSERIR = "INSERT INTO cvendas.fornecedor (NOME, EMAIL, ENDERECO, BAIRRO, CIDADE, UF, CEP, TELEFONE) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private final String ALTERAR = "UPDATE cvendas.fornecedor SET NOME = ?, EMAIL = ?, ENDERECO = ?, BAIRRO = ?, CIDADE = ?, UF = ?, CEP = ?, TELEFONE = ? WHERE fornecedor.ID = ?";
    private final String EXCLUIR = "DELETE FROM cvendas.fornecedor WHERE fornecedor.ID = ?";

    public List<Fornecedor> consultarFornecedores() {
        fornecedores = new ArrayList<>();
        try {
            preparedStatement = ConexaoBD.conectar().prepareStatement(CONSULTAR);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                fornecedores.add(new Fornecedor(
                        resultSet.getInt("ID"),
                        resultSet.getString("NOME"),
                        resultSet.getString("EMAIL"),
                        resultSet.getString("ENDERECO"),
                        resultSet.getString("BAIRRO"),
                        resultSet.getString("CIDADE"),
                        resultSet.getString("UF"),
                        resultSet.getString("CEP"),
                        resultSet.getString("TELEFONE")
                ));
            }
            ConexaoBD.desconectar(preparedStatement.getConnection());
        } catch (SQLException e) {
        }
        return (!fornecedores.isEmpty()) ? fornecedores : null;
    }

    public List<Fornecedor> consultarFornecedores(String nome) {
        fornecedores = new ArrayList<>();
        try {
            preparedStatement = ConexaoBD.conectar().prepareStatement(CONSULTAR_POR_NOME);
            preparedStatement.setString(1, nome);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                fornecedores.add(new Fornecedor(
                        resultSet.getInt("ID"),
                        resultSet.getString("NOME"),
                        resultSet.getString("EMAIL"),
                        resultSet.getString("ENDERECO"),
                        resultSet.getString("BAIRRO"),
                        resultSet.getString("CIDADE"),
                        resultSet.getString("UF"),
                        resultSet.getString("CEP"),
                        resultSet.getString("TELEFONE")
                ));
            }
            ConexaoBD.desconectar(preparedStatement.getConnection());
        } catch (SQLException e) {
        }
        return (!fornecedores.isEmpty()) ? fornecedores : null;
    }

    public boolean incluirFornecedor(Fornecedor fornecedor) {
        try {
            preparedStatement = ConexaoBD.conectar().prepareStatement(INSERIR);
            preparedStatement.setString(1, fornecedor.getNome());
            preparedStatement.setString(2, fornecedor.getEmail());
            preparedStatement.setString(3, fornecedor.getEndereco());
            preparedStatement.setString(4, fornecedor.getBairro());
            preparedStatement.setString(5, fornecedor.getCidade());
            preparedStatement.setString(6, fornecedor.getUf());
            preparedStatement.setString(7, fornecedor.getCep());
            preparedStatement.setString(8, fornecedor.getTelefone());
            preparedStatement.executeUpdate();
            ConexaoBD.desconectar(preparedStatement.getConnection());
            return true;
        } catch (SQLException e) {
        }
        return false;
    }

    public boolean alterarFornecedor(Fornecedor fornecedor) {
        try {
            preparedStatement = ConexaoBD.conectar().prepareStatement(ALTERAR);
            preparedStatement.setString(1, fornecedor.getNome());
            preparedStatement.setString(2, fornecedor.getEmail());
            preparedStatement.setString(3, fornecedor.getEndereco());
            preparedStatement.setString(4, fornecedor.getBairro());
            preparedStatement.setString(5, fornecedor.getCidade());
            preparedStatement.setString(6, fornecedor.getUf());
            preparedStatement.setString(7, fornecedor.getCep());
            preparedStatement.setString(8, fornecedor.getTelefone());
            preparedStatement.setInt(9, fornecedor.getId());
            preparedStatement.executeUpdate();
            ConexaoBD.desconectar(preparedStatement.getConnection());
            return true;
        } catch (SQLException e) {
        }
        return false;
    }

    public boolean excluirFornecedor(Fornecedor fornecedor) {
        try {
            preparedStatement = ConexaoBD.conectar().prepareStatement(EXCLUIR);
            preparedStatement.setInt(1, fornecedor.getId());
            preparedStatement.executeUpdate();
            ConexaoBD.desconectar(preparedStatement.getConnection());
            return true;
        } catch (SQLException e) {
        }
        return false;
    }

}
