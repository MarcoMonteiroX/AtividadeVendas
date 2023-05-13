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
    private Produto produto;

}
