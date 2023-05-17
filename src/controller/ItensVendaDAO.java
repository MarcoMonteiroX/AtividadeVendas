package controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.ItensVenda;
import model.Venda;

public class ItensVendaDAO {

    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private List<ItensVenda> itensVendas;

    private final String CONSULTAR = "SELECT * FROM cvendas.itens_venda";
    private final String CONSULTAR_PRODUTO_NOME = "SELECT produto.NOME FROM cvendas.itens_venda INNER JOIN produto ON (itens_venda.PRODUTO_ID = produto.ID) WHERE produto.ID = ?";
    private final String INSERIR = "INSERT INTO cvendas.itens_venda (PRODUTO_ID, VENDA_ID, QTDE, VALOR) values (?, ?, ?, ?)";
    private final String EXCLUIR = "DELETE FROM cvendas.itens_venda WHERE itens_venda.ID = ?";

    public List<ItensVenda> consultarItensVenda() {
        itensVendas = new ArrayList<>();
        try {
            preparedStatement = ConexaoBD.conectar().prepareStatement(CONSULTAR);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                itensVendas.add(new ItensVenda(
                        resultSet.getInt("ID"),
                        resultSet.getInt("PRODUTO_ID"),
                        resultSet.getInt("VENDA_ID"),
                        resultSet.getInt("QTDE"),
                        resultSet.getDouble("VALOR")
                ));
            }
            ConexaoBD.desconectar(preparedStatement.getConnection());
        } catch (SQLException e) {
        }
        return (!itensVendas.isEmpty()) ? itensVendas : null;
    }

    public String consultarNomeProduto(Integer produtoId) {
        String produtoNome = "";
        try {
            preparedStatement = ConexaoBD.conectar().prepareStatement(CONSULTAR_PRODUTO_NOME);
            preparedStatement.setInt(1, produtoId);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                produtoNome = resultSet.getString("NOME");
            }
            ConexaoBD.desconectar(preparedStatement.getConnection());
        } catch (SQLException e) {
        }
        return produtoNome;
    }

    public boolean incluirItensVenda(ItensVenda itemVenda) {
        try {
            preparedStatement = ConexaoBD.conectar().prepareStatement(INSERIR);
            preparedStatement.setInt(1, itemVenda.getProdutoId());
            preparedStatement.setInt(2, itemVenda.getVendaId());
            preparedStatement.setInt(3, itemVenda.getQuantidade());
            preparedStatement.setDouble(4, itemVenda.getValor());
            preparedStatement.executeUpdate();
            ConexaoBD.desconectar(preparedStatement.getConnection());
            return true;
        } catch (SQLException e) {
        }
        return false;
    }

    public boolean excluirItemVenda(ItensVenda itensVenda) {
        try {
            preparedStatement = ConexaoBD.conectar().prepareStatement(EXCLUIR);
            preparedStatement.setInt(1, itensVenda.getId());
            preparedStatement.executeUpdate();
            ConexaoBD.desconectar(preparedStatement.getConnection());
            return true;
        } catch (SQLException e) {
        }
        return false;
    }

}
