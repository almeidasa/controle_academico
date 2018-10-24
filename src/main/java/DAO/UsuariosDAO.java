package DAO;

import entities.Usuarios;
import java.sql.PreparedStatement;

/**
 *
 * @author Alexandre Almeida
 */
public class UsuariosDAO {

    public void inserirUsuario(Usuarios usuarios) {
        String SQL = "INSERT INTO usuarios(login, senha, tipo) VALUES (?,md5(?),?)";
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

}
