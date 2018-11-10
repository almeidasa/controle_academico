package DAO;

import Util.Exibir;
import entities.MatriculaDisciplina;
import java.sql.PreparedStatement;

/**
 * @Autor Alexandre Almeida
 * @Data  31/10/2018
 */
public class MatriculaDisciplinaDAO {
    public void inserirMatriculaDisciplina(MatriculaDisciplina ma) {
        String SQL = "INSERT INTO matriculadisciplina(conceito, semestre, ano, situacao, fk_disciplina_codigo, fk_aluno_cpf) VALUES (?,?,?,?,?,?)";
        try (PreparedStatement pstm = BD.getConexao().prepareStatement(SQL)) {
            pstm.setString(1, ma.getConceito());
            pstm.setString(2, ma.getSemestre());
            pstm.setInt(3, ma.getAno());
            pstm.setString(4, ma.getSituacao());
            pstm.setString(5, ma.getFk_Disciplina_codigo());
            pstm.setString(6, ma.getFk_Aluno_cpf());
            
            pstm.execute();

            BD.getConexao().close();
            System.out.println("Inserido com sucesso!");
        } catch (Exception ex) {
            Exibir.Mensagem("Erro ao inserir MatriculaDisciplina: " + ex);
        }
    }
}
