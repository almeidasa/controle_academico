package DAO;

import entities.Disciplina;
import java.sql.PreparedStatement;

/**
 *
 * @author Alexandre Almeida
 */
public class DisciplinaDAO {

    public void inserirDisciplina(Disciplina disciplina) {
        String SQL = "INSERT INTO disciplina(codigo, nome, situacao, fk_curso_cod) VALUES (?,?,?,?)";
        try (PreparedStatement pstm = BD.getConexao().prepareStatement(SQL)) {
            pstm.setString(1, disciplina.getCodigo());
            pstm.setString(2, disciplina.getNome());
            pstm.setString(3, disciplina.getSituacao());
            pstm.setInt(4, disciplina.getFk_Curso_cod());

            pstm.execute();

            BD.getConexao().close();
            System.out.println("Inserido com sucesso!");
        } catch (Exception ex) {
            System.out.println("\nErro ao inserir Disciplina: " + ex);
        }
    }
}
