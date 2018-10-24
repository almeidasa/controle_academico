package DAO;

import entities.Funcionario;
import java.sql.PreparedStatement;

/**
 *
 * @author Alexandre Almeida
 */
public class FuncionarioDAO {

    public void inserirFuncionario(Funcionario funcionario) {
        String SQL = "INSERT INTO funcionario(nome, telefone, fk_Usuarios_id_user) VALUES (?,?,?)";
        try (PreparedStatement pstm = BD.getConexao().prepareStatement(SQL)) {
            pstm.setString(1, funcionario.getNome());
            pstm.setString(2, funcionario.getTelefone());
            pstm.setInt(3, funcionario.getFk_Usuarios_id_user());

            pstm.execute();

            BD.getConexao().close();
            System.out.println("Inserido com sucesso!");
        } catch (Exception ex) {
            System.out.println("\nErro ao inserir Funcionario: " + ex);
        }
    }
}
