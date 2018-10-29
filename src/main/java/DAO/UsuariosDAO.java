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

        try {
            String SQL = "SELECT login, senha FROM usuarios WHERE UPPER(login) = UPPER(?) and senha = md5(?)";
            PreparedStatement pstm;
            pstm = BD.getConexao().prepareStatement(SQL);

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
    
    public String obterUsuario(String matricula) {
        String UsrAtivo = "";

        try {
            String SQL = "SELECT login FROM usuarios WHERE UPPER(login) = UPPER('" + matricula + "')";

            PreparedStatement pstm = BD.getConexao().prepareStatement(SQL);

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
        try {
            String SQL = "DELETE FROM usuarios WHERE id = " + id;

            PreparedStatement pstm = BD.getConexao().prepareStatement(SQL);
            pstm.executeUpdate();

            pstm.close();
            BD.getConexao().close();
            System.out.println( "Usuário Apagado! ");
        } catch (Exception ex) {
            System.out.println("Erro ao Apagar Usuário!:\n" + ex);
        }
    }

    public void alterarSenha(String login, String novaSenha) {

        try {
            String SQL = "UPDATE usuarios SET senha = md5('" + novaSenha + "') WHERE UPPER(login) = '" + login.toUpperCase() + "'";

            System.out.println(SQL);

            PreparedStatement pstm = BD.getConexao().prepareStatement(SQL);
            pstm.executeUpdate();

            pstm.close();
            BD.getConexao().close();
            System.out.println("Alteração efetuada!");
        } catch (Exception ex) {
            System.out.println("Erro ao Alterar Usuário!:\n" + ex);
        }
    }
}
