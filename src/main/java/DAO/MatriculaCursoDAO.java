package DAO;

import Util.Exibir;
import entities.MatriculaCurso;
import java.sql.PreparedStatement;

/**
 *
 * @author Alexandre Almeida
 */
public class MatriculaCursoDAO {

    public void inserirDisciplina(MatriculaCurso matriculaCurso) {
        String SQL = "INSERT INTO matriculacurso(matricula, situacao, data_inicio, duracao_curso, fk_aluno_cpf, fk_curso_cod) VALUES (?,?,?,?,?,?)";
        try (PreparedStatement pstm = BD.getConexao().prepareStatement(SQL)) {
            pstm.setInt(1, matriculaCurso.getMatricula());
            pstm.setString(2, matriculaCurso.getSituacao());
            pstm.setDate(3, matriculaCurso.getData_inicio());
            pstm.setString(4, matriculaCurso.getDuracao_curso());
            pstm.setString(5, matriculaCurso.getFk_Aluno());
            pstm.setInt(6, matriculaCurso.getFk_Curso_cod());

            pstm.execute();

            BD.getConexao().close();
            System.out.println("Inserido com sucesso!");
        } catch (Exception ex) {
            Exibir.Mensagem("Erro ao inserir Disciplina: " + ex);
        }
    }
}
