package controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Usuario;

public class UsuarioDAO {

    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private List<Usuario> usuarios;

    private final String CONSULTAR = "select * from cvendas.usuario";
    private final String CONSULTAR_POR_NOME = "SELECT * FROM cvendas.usuario WHERE usuario.NOME LIKE ?";
    private final String INSERIR = "insert into cvendas.usuario (nome, login, senha, email, perfil) values (?, ?, ?, ?, ?)";
    private final String ALTERAR = "update cvendas.usuario set nome = ?, login = ?, senha = ?, email = ?, perfil = ? where cvendas.usuario.ID = ?";
    private final String EXCLUIR = "delete from cvendas.usuario where usuario.id = ?";
    private final String CONSULTAR_POR_LOGIN = "SELECT * FROM cvendas.usuario WHERE usuario.LOGIN = ?";

    public List<Usuario> consultarUsuarios() {
        usuarios = new ArrayList<>();
        try {
            preparedStatement = ConexaoBD.conectar().prepareStatement(CONSULTAR);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                usuarios.add(new Usuario(
                        resultSet.getInt("id"),
                        resultSet.getString("nome"),
                        resultSet.getString("login"),
                        resultSet.getString("senha"),
                        resultSet.getString("email"),
                        resultSet.getString("perfil")
                ));
            }
            ConexaoBD.desconectar(preparedStatement.getConnection());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (!usuarios.isEmpty()) ? usuarios : null;
    }

    public List<Usuario> consultarUsuarios(String nome) {
        try {
            usuarios = new ArrayList<>();
            preparedStatement = ConexaoBD.conectar().prepareStatement(CONSULTAR_POR_NOME);
            preparedStatement.setString(1, nome);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                usuarios.add(new Usuario(
                        resultSet.getInt("id"),
                        resultSet.getString("nome"),
                        resultSet.getString("login"),
                        resultSet.getString("senha"),
                        resultSet.getString("email"),
                        resultSet.getString("perfil")
                ));
            }
            ConexaoBD.desconectar(preparedStatement.getConnection());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (!usuarios.isEmpty()) ? usuarios : null;
    }

    public boolean inserirUsuario(Usuario usuario) {
        try {
            preparedStatement = ConexaoBD.conectar().prepareStatement(INSERIR);
            preparedStatement.setString(1, usuario.getNome());
            preparedStatement.setString(2, usuario.getLogin());
            preparedStatement.setString(3, usuario.getSenha());
            preparedStatement.setString(4, usuario.getEmail());
            preparedStatement.setString(5, usuario.getPerfi());
            preparedStatement.executeUpdate();
            ConexaoBD.desconectar(preparedStatement.getConnection());
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean alterarUsuario(Usuario usuario) {
        try {
            preparedStatement = ConexaoBD.conectar().prepareStatement(ALTERAR);
            preparedStatement.setString(1, usuario.getNome());
            preparedStatement.setString(2, usuario.getLogin());
            preparedStatement.setString(3, usuario.getSenha());
            preparedStatement.setString(4, usuario.getEmail());
            preparedStatement.setString(5, usuario.getPerfi());
            preparedStatement.setInt(6, usuario.getId());
            preparedStatement.executeUpdate();
            ConexaoBD.desconectar(preparedStatement.getConnection());
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean excluirUsuario(Usuario usuario) {
        try {
            preparedStatement = ConexaoBD.conectar().prepareStatement(EXCLUIR);
            preparedStatement.setInt(1, usuario.getId());
            preparedStatement.executeUpdate();
            ConexaoBD.desconectar(preparedStatement.getConnection());
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean autenticarUsuario(String login, String senha) {
        try {
            preparedStatement = ConexaoBD.conectar().prepareStatement(CONSULTAR_POR_LOGIN);
            preparedStatement.setString(1, login);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String senhaBanco = resultSet.getString("senha");
                return senha.equals(senhaBanco);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public String administrador(String login) {
        try {
            preparedStatement = ConexaoBD.conectar().prepareStatement(CONSULTAR_POR_LOGIN);
            preparedStatement.setString(1, login);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String perfil = resultSet.getString("perfil");
                return perfil;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }

}
