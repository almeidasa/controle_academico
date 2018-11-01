package DAO;

import entities.Usuarios;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Alexandre Almeida
 */
public class UsuariosDAO {

    public void inserirUsuario(Usuarios usuarios) {

        String SQL = "INSERT INTO usuarios(login, senha, tipo) VALUES (?, md5(?), ?)";
        try (PreparedStatement pstm = BD.getConexao().prepareStatement(SQL)) {
            pstm.setString(1, usuarios.getLogin());
            pstm.setString(2, usuarios.getSenha());
            pstm.setString(3, usuarios.getTipo());

            pstm.execute();

            BD.getConexao().close();
            System.out.println("Inserido com sucesso!");
        } catch (Exception ex) {
            System.out.println("\nErro ao inserir usuário: " + ex);
        }
    }

    public boolean verificaUsuarioSenha(String usuario, String senha) {

        boolean result = false;
        String SQL = "SELECT login, senha FROM usuarios WHERE UPPER(login) = UPPER(?) AND senha = md5(?)";
        try {
            PreparedStatement pstm = BD.getConexao().prepareStatement(SQL);
            pstm.setString(1, usuario.toUpperCase());
            pstm.setString(2, senha);

            ResultSet rs = pstm.executeQuery(); //envia o comando ao banco

            if (rs.next()) {
                result = true;
                pstm.close();
                BD.getConexao().close();

            } else {
                pstm.close();
                BD.getConexao().close();
                result = false;
            }
        } catch (Exception ex) {
            System.out.println("Erro ao Verificar Usuário e Senha!: \n" + ex);
        }

        return result;
    }
    
    public boolean verificaUsuarioEmail(String usuario, String email) {

        boolean result = false;
        String SQL = "SELECT u.login, f.email FROM usuarios u INNER JOIN Funcionario f ON(f.fk_usuarios_id_user = u.id_user) WHERE UPPER(login) = UPPER(?) AND UPPER(email) = UPPER(?);";
        try {
            PreparedStatement pstm = BD.getConexao().prepareStatement(SQL);
            pstm.setString(1, usuario.toUpperCase());
            pstm.setString(2, email);

            ResultSet rs = pstm.executeQuery(); //envia o comando ao banco

            if (rs.next()) {
                result = true;
                pstm.close();
                BD.getConexao().close();

            } else {
                pstm.close();
                BD.getConexao().close();
                result = false;
            }
        } catch (Exception ex) {
            System.out.println("Erro ao Verificar Usuário e Senha!: \n" + ex);
        }
        System.out.println(result);
        return result;
    }

    public String obterUsuario(String login) {

        String UsrAtivo = "";
        String SQL = "SELECT login FROM usuarios WHERE UPPER(login) = UPPER(?)";

        try (PreparedStatement pstm = BD.getConexao().prepareStatement(SQL)) {
            pstm.setString(1, login);
            ResultSet result = pstm.executeQuery();

            while (result.next()) {
                UsrAtivo = result.getString("login");
            }

            pstm.close();
            BD.getConexao().close();
            System.out.println("Consulta Realizada na Tabela Usuario!");
        } catch (Exception ex) {
            System.out.println("Erro ao Obter Usuários do Banco de Dados!: \n" + ex);
        }
        System.out.println(UsrAtivo);
        return UsrAtivo;
    }

    public void apagarUsuario(String id) {

        String SQL = "DELETE FROM usuarios WHERE id = " + id;
        try (PreparedStatement pstm = BD.getConexao().prepareStatement(SQL)) {

            pstm.executeUpdate();

            pstm.close();
            BD.getConexao().close();
            System.out.println("Usuário Apagado! ");
        } catch (Exception ex) {
            System.out.println("Erro ao Apagar Usuário!:\n" + ex);
        }
    }

    public void alterarSenha(String login, String novaSenha) {

        String SQL = "UPDATE usuarios SET senha = md5(?) WHERE UPPER(login) = UPPER(?)";
        try (PreparedStatement pstm = BD.getConexao().prepareStatement(SQL)) {
            pstm.setString(1, novaSenha);
            pstm.setString(2, login);
            System.out.println(SQL);
            pstm.executeUpdate();

            pstm.close();
            BD.getConexao().close();
            System.out.println("Alteração efetuada!");
        } catch (Exception ex) {
            System.out.println("Erro ao Alterar Usuário!:\n" + ex);
        }
    }
}
