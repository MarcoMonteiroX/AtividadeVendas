package controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Produto;

public class ProdutoDAO {

    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private List<Produto> produtos;

    private final String CONSULTAR = "SELECT * FROM cvendas.produto";
    private final String CONSULTAR_POR_NOME = "SELECT * FROM cvendas.produto WHERE produto.NOME LIKE ?";
    private final String CONSULTAR_FORNECEDOR_NOME = "SELECT fornecedor.NOME FROM cvendas.produto INNER JOIN fornecedor ON (produto.FORNECEDOR_ID = fornecedor.ID) WHERE fornecedor.ID = ?";
    private final String INSERIR = "INSERT INTO cvendas.produto (FORNECEDOR_ID, NOME, QTD_ESTOQUE, VALOR) VALUES (?, ?, ?, ?)";
    private final String ALTERAR = "UPDATE cvendas.produto SET FORNECEDOR_ID = ?, NOME = ?, QTD_ESTOQUE = ?, VALOR = ? WHERE produto.ID = ?";
    private final String EXCLUIR_ITENS_VENDA = "DELETE FROM cvendas.itens_venda WHERE itens_venda.PRODUTO_ID = ?";
    private final String EXCLUIR_PRODUTO = "DELETE FROM cvendas.produto WHERE produto.ID = ?";

    public List<Produto> consultarProdutos() {
        produtos = new ArrayList<>();
        try {
            preparedStatement = ConexaoBD.conectar().prepareStatement(CONSULTAR);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                produtos.add(new Produto(
                        resultSet.getInt("ID"),
                        resultSet.getInt("FORNECEDOR_ID"),
                        resultSet.getString("NOME"),
                        resultSet.getInt("QTD_ESTOQUE"),
                        resultSet.getDouble("VALOR")
                ));
            }
            ConexaoBD.desconectar(preparedStatement.getConnection());
        } catch (SQLException e) {
        }
        return (!produtos.isEmpty()) ? produtos : null;
    }

    public List<Produto> consultarProdutos(String nome) {
        produtos = new ArrayList<>();
        try {
            preparedStatement = ConexaoBD.conectar().prepareStatement(CONSULTAR_POR_NOME);
            preparedStatement.setString(1, nome);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                produtos.add(new Produto(
                        resultSet.getInt("ID"),
                        resultSet.getInt("FORNECEDOR_ID"),
                        resultSet.getString("NOME"),
                        resultSet.getInt("QTD_ESTOQUE"),
                        resultSet.getDouble("VALOR")
                ));
            }
            ConexaoBD.desconectar(preparedStatement.getConnection());
        } catch (SQLException e) {
        }
        return (!produtos.isEmpty()) ? produtos : null;
    }

    public String consultarNomeFornecedor(Integer fornecedorId) {
        String fornecedorNome = "";
        try {
            preparedStatement = ConexaoBD.conectar().prepareStatement(CONSULTAR_FORNECEDOR_NOME);
            preparedStatement.setInt(1, fornecedorId);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                fornecedorNome = resultSet.getString("NOME");
            }
            ConexaoBD.desconectar(preparedStatement.getConnection());
        } catch (SQLException e) {
        }
        return fornecedorNome;
    }

    public boolean incluirProduto(Produto produto) {
        try {
            preparedStatement = ConexaoBD.conectar().prepareStatement(INSERIR);
            preparedStatement.setInt(1, produto.getFornecedorId());
            preparedStatement.setString(2, produto.getNome());
            preparedStatement.setInt(3, produto.getQuantidadeEstoque());
            preparedStatement.setDouble(4, produto.getValor());
            preparedStatement.executeUpdate();
            ConexaoBD.desconectar(preparedStatement.getConnection());
            return true;
        } catch (SQLException e) {
        }
        return false;
    }

    public boolean alterarProduto(Produto produto) {
        try {
            preparedStatement = ConexaoBD.conectar().prepareStatement(ALTERAR);
            preparedStatement.setInt(1, produto.getFornecedorId());
            preparedStatement.setString(2, produto.getNome());
            preparedStatement.setInt(3, produto.getQuantidadeEstoque());
            preparedStatement.setDouble(4, produto.getValor());
            preparedStatement.setInt(5, produto.getId());
            preparedStatement.executeUpdate();
            ConexaoBD.desconectar(preparedStatement.getConnection());
            return true;
        } catch (SQLException e) {
        }
        return false;
    }

    public boolean excluirProduto(Produto produto) {
        try {
            preparedStatement = ConexaoBD.conectar().prepareStatement(EXCLUIR_ITENS_VENDA);
            preparedStatement.setInt(1, produto.getId());
            preparedStatement.executeUpdate();
            ConexaoBD.desconectar(preparedStatement.getConnection());
            preparedStatement = ConexaoBD.conectar().prepareStatement(EXCLUIR_PRODUTO);
            preparedStatement.setInt(1, produto.getId());
            preparedStatement.executeUpdate();
            ConexaoBD.desconectar(preparedStatement.getConnection());
            return true;
        } catch (SQLException e) {
        }
        return false;
    }
}
