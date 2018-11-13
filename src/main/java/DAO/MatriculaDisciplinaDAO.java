package DAO;

import Util.Exibir;
import entities.MatriculaDisciplina;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * @Autor Alexandre Almeida
 * @Data 31/10/2018
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
            pstm.close();
            System.out.println("Inserido com sucesso!");
        } catch (Exception ex) {
            Exibir.Mensagem("Erro ao inserir MatriculaDisciplina: " + ex);
        }
    }

    public ArrayList<MatriculaDisciplina> obterMatriculaDisciplina() {

        ArrayList<MatriculaDisciplina> matdis = new ArrayList<>();

        String SQL = "SELECT * FROM matriculadisciplina";
        try (PreparedStatement pstm = BD.getConexao().prepareStatement(SQL)) {

            try (ResultSet rs = pstm.executeQuery()) {
                while (rs.next()) {
                    MatriculaDisciplina mat = new MatriculaDisciplina(
                            rs.getString("conceito"),
                            rs.getString("semestre"),
                            rs.getInt("ano"),
                            rs.getString("situacao"),
                            rs.getString("fk_disciplina_codigo"),
                            rs.getString("fk_aluno_cpf")
                    );
                    matdis.add(mat);
                }
                pstm.close();
            }
            System.out.println("Disciplinas obtidos com sucesso!");
        } catch (Exception ex) {
            Exibir.Mensagem("Erro ao obter Disciplinas!: \n" + ex);
        }
        return matdis;
    }

    public void editarMatriculaDisciplina(MatriculaDisciplina md) {
        String SQL = "UPDATE disciplina SET codigo = ?, nome = ?, situacao = ?, fk_curso_cod = ? WHERE codigo = ?";
        try (PreparedStatement pstm = BD.getConexao().prepareStatement(SQL)) {
//            pstm.setString(1, d.getCodigo());
//            pstm.setString(2, d.getNome());
//            pstm.setString(3, d.getSituacao());
//            pstm.setInt(4, d.getFk_Curso_cod());
//            pstm.setString(5, d.getCod_antigo());

            System.out.println(SQL);
            pstm.executeUpdate();

            pstm.close();
            BD.getConexao().close();
            System.out.println("Alteração efetuada!");
        } catch (Exception ex) {
            Exibir.Mensagem("Erro ao Alterar disciplina!:\n" + ex);
        }
    }

    public void removerMatriculaDisciplina(MatriculaDisciplina md) {
        String SQL = "DELETE FROM disciplina WHERE codigo = (?)";
        try (PreparedStatement pstm = BD.getConexao().prepareStatement(SQL)) {
            //pstm.setString(1, );

            pstm.execute();

            BD.getConexao().close();
            System.out.println("Removido com sucesso!");
        } catch (Exception ex) {
            Exibir.Mensagem("\nErro ao remover MatriculaDisciplina: " + ex);
        }
    }
}
