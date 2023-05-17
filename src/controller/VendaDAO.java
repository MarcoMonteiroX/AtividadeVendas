package controller;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Venda;

public class VendaDAO {

    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private List<Venda> vendas;

    private final String CONSULTAR = "SELECT * FROM cvendas.venda";
    private final String CONSULTAR_ENTRE_DATAS = "SELECT * FROM cvendas.venda WHERE DATA_VENDA BETWEEN ? AND ?";
    private final String CONSULTAR_NOME_CLIENTE = "SELECT cliente.NOME FROM cvendas.venda INNER JOIN cliente on (venda.CLIENTE_ID = cliente.ID) WHERE cliente.ID = ?";
    private final String INSERIR = "INSERT INTO cvendas.venda (cliente_id, data_venda) values (?, ?)";

    public List<Venda> consultarVendas() {
        vendas = new ArrayList<>();
        try {
            preparedStatement = ConexaoBD.conectar().prepareStatement(CONSULTAR);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                vendas.add(new Venda(
                        resultSet.getInt("ID"),
                        resultSet.getInt("CLIENTE_ID"),
                        resultSet.getDate("DATA_VENDA")
                ));
            }
            ConexaoBD.desconectar(preparedStatement.getConnection());
        } catch (SQLException e) {
        }
        return (!vendas.isEmpty()) ? vendas : null;
    }

    public List<Venda> consultarVendas(Date dataInicial, Date dataFinal) {
        vendas = new ArrayList<>();
        try {
            preparedStatement = ConexaoBD.conectar().prepareStatement(CONSULTAR_ENTRE_DATAS);
            preparedStatement.setDate(1, dataInicial);
            preparedStatement.setDate(2, dataFinal);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                vendas.add(new Venda(
                        resultSet.getInt("ID"),
                        resultSet.getInt("CLIENTE_ID"),
                        resultSet.getDate("DATA_VENDA")
                ));
            }
            ConexaoBD.desconectar(preparedStatement.getConnection());
        } catch (SQLException e) {
        }
        return (!vendas.isEmpty()) ? vendas : null;
    }

    public String consultarNomeCliente(Integer clienteId) {
        String clienteNome = "";
        try {
            preparedStatement = ConexaoBD.conectar().prepareStatement(CONSULTAR_NOME_CLIENTE);
            preparedStatement.setInt(1, clienteId);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                clienteNome = resultSet.getString("NOME");
            }
            ConexaoBD.desconectar(preparedStatement.getConnection());
        } catch (SQLException e) {
        }
        return clienteNome;
    }

    public boolean incluirVenda(Venda venda) {
        try {
            preparedStatement = ConexaoBD.conectar().prepareStatement(INSERIR);
            preparedStatement.setInt(1, venda.getClienteId());
            preparedStatement.setDate(2, (Date) venda.getDataVenda());
            preparedStatement.executeUpdate();
            ConexaoBD.desconectar(preparedStatement.getConnection());
            return true;
        } catch (SQLException e) {
        }
        return false;
    }

}
