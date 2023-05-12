package controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Cliente;

public class ClienteDAO {

    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private List<Cliente> clientes;
    private Cliente cliente;

    private final String consultar = "SELECT * FROM cliente";
    private final String consultarPorNome = "SELECT * FROM cliente WHERE cliente.NOME LIKE ?";
    private final String inserir = "INSERT INTO cliente (NOME, EMAIL, ENDERECO, BAIRRO, CIDADE, UF, CEP, TELEFONE) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private final String alterar = "UPDATE cliente SET NOME = ?, EMAIL = ?, ENDERECO = ?, BAIRRO = ?, CIDADE = ?, UF = ?, CEP = ?, TELEFONE = ? WHERE cliente.ID = ?";
    private final String excluir = "DELETE FROM cliente WHERE cliente.ID = ?";

    public List<Cliente> consultarCliente() {
        clientes = new ArrayList<>();
        try {
            preparedStatement = ConexaoBD.conectar().prepareStatement(consultar);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                clientes.add(new Cliente(resultSet.getInt("ID"), resultSet.getString("NOME"), resultSet.getString("EMAIL"), resultSet.getString("ENDERECO"), resultSet.getString("BAIRRO"), resultSet.getString("CIDADE"), resultSet.getString("UF"), resultSet.getString("CEP"), resultSet.getString("TELEFONE")));
            }
            ConexaoBD.desconectar(preparedStatement.getConnection());
        } catch (SQLException e) {
        }
        return (!clientes.isEmpty()) ? clientes : null;
    }

    public List<Cliente> consultarCliente(String nome) {
        clientes = new ArrayList<>();
        try {
            preparedStatement = ConexaoBD.conectar().prepareStatement(consultarPorNome);
            preparedStatement.setString(1, nome);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                clientes.add(new Cliente(resultSet.getInt("ID"), resultSet.getString("NOME"), resultSet.getString("EMAIL"), resultSet.getString("ENDERECO"), resultSet.getString("BAIRRO"), resultSet.getString("CIDADE"), resultSet.getString("UF"), resultSet.getString("CEP"), resultSet.getString("TELEFONE")));
            }
            ConexaoBD.desconectar(preparedStatement.getConnection());
        } catch (SQLException e) {
        }
        return (!clientes.isEmpty()) ? clientes : null;
    }

    public boolean incluirCliente(Cliente cliente) {
        try {
            preparedStatement = ConexaoBD.conectar().prepareStatement(inserir);
            preparedStatement.setString(1, cliente.getNome());
            preparedStatement.setString(2, cliente.getEmail());
            preparedStatement.setString(3, cliente.getEndereco());
            preparedStatement.setString(4, cliente.getBairro());
            preparedStatement.setString(5, cliente.getCidade());
            preparedStatement.setString(6, cliente.getUf());
            preparedStatement.setString(7, cliente.getCep());
            preparedStatement.setString(8, cliente.getTelefone());
            preparedStatement.executeUpdate();
            ConexaoBD.desconectar(preparedStatement.getConnection());
            return true;
        } catch (SQLException e) {
        }
        return false;
    }

    public boolean alterarCliente(Cliente cliente) {
        try {
            preparedStatement = ConexaoBD.conectar().prepareStatement(alterar);
            preparedStatement.setString(1, cliente.getNome());
            preparedStatement.setString(2, cliente.getEmail());
            preparedStatement.setString(3, cliente.getEndereco());
            preparedStatement.setString(4, cliente.getBairro());
            preparedStatement.setString(5, cliente.getCidade());
            preparedStatement.setString(6, cliente.getUf());
            preparedStatement.setString(7, cliente.getCep());
            preparedStatement.setString(8, cliente.getTelefone());
            preparedStatement.setInt(9, cliente.getId());
            preparedStatement.executeUpdate();
            ConexaoBD.desconectar(preparedStatement.getConnection());
            return true;
        } catch (SQLException e) {
        }
        return false;
    }

    public boolean excluirCliente(Cliente cliente) {
        try {
            preparedStatement = ConexaoBD.conectar().prepareStatement(excluir);
            preparedStatement.setInt(1, cliente.getId());
            preparedStatement.executeUpdate();
            ConexaoBD.desconectar(preparedStatement.getConnection());
            return true;
        } catch (SQLException e) {
        }
        return false;
    }

}
