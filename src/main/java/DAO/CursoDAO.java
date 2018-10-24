package DAO;

import entities.Curso;
import java.sql.PreparedStatement;

/**
 *
 * @author Alexandre Almeida
 */
public class CursoDAO {

    public void inserirCurso(Curso curso) {
        String SQL = "INSERT INTO curso(cod, nome_curso, fk_funcionario_id) VALUES (?,?,?)";
        try (PreparedStatement pstm = BD.getConexao().prepareStatement(SQL)) {
            pstm.setInt(1, curso.getCod());
            pstm.setString(2, curso.getNome_curso());
            pstm.setInt(3, curso.getFk_Funcionario_id());

            pstm.execute();

            BD.getConexao().close();
            System.out.println("Inserido com sucesso!");
        } catch (Exception ex) {
            System.out.println("\nErro ao inserir curso: " + ex);
        }
    }
}
