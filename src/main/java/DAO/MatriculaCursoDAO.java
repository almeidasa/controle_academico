package DAO;

import Util.Exibir;
import Util.Formatar;
import entities.MatriculaCurso;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Alexandre Almeida
 */
public class MatriculaCursoDAO {

    public void inserirMatriculaCurso(MatriculaCurso matriculaCurso) {
        String SQL = "INSERT INTO matriculacurso(matricula, situacao, data_inicio, duracao_curso, fk_aluno_cpf, fk_curso_cod) VALUES (?,?,?,?,?,?)";
        try (PreparedStatement pstm = BD.getConexao().prepareStatement(SQL)) {
            pstm.setInt(1, matriculaCurso.getMatricula());
            pstm.setString(2, matriculaCurso.getSituacao());
            pstm.setDate(3, java.sql.Date.valueOf(matriculaCurso.getData_inicio()));
            pstm.setString(4, matriculaCurso.getDuracao_curso());
            pstm.setString(5, matriculaCurso.getFk_Aluno());
            pstm.setInt(6, matriculaCurso.getFk_Curso_cod());

            pstm.execute();

            BD.getConexao().close();
            pstm.close();
            System.out.println("Inserido com sucesso!");
        } catch (Exception ex) {
            if (ex.toString().contains("duplicate key value violates unique constraint")) {
                Exibir.Mensagem("ERRO! Matricula já existente! Verifique se o aluno já não está cadastrado neste curso!");
            } else {
                Exibir.Mensagem("Erro inserir MatriculaCurso!: \n" + ex);
            }
        }
    }

    public ArrayList<MatriculaCurso> obterMatriculaCurso() {

        ArrayList<MatriculaCurso> matcurso = new ArrayList<>();

        String SQL = "SELECT * FROM matriculacurso";
        try (PreparedStatement pstm = BD.getConexao().prepareStatement(SQL)) {

            try (ResultSet rs = pstm.executeQuery()) {
                while (rs.next()) {
                    MatriculaCurso mat = new MatriculaCurso(
                            rs.getInt("matricula"),
                            rs.getString("situacao"),
                            Formatar.data(rs.getDate("data_inicio"), "dd/MM/yyyy"),
                            rs.getString("duracao_curso"),
                            rs.getString("fk_aluno_cpf"),
                            rs.getInt("fk_curso_cod")
                    );
                    matcurso.add(mat);
                }
                pstm.close();
            }
            System.out.println("MatriculaCurso obtidas com sucesso!");
        } catch (Exception ex) {
            Exibir.Mensagem("Erro ao obter MatriculaCurso!: \n" + ex);
        }
        return matcurso;
    }

    public ArrayList<MatriculaCurso> obterCursoDoAluno(String cpf) {
        
        ArrayList<MatriculaCurso> matcurso = new ArrayList<>();
        
        String SQL = "SELECT fk_curso_cod FROM matriculacurso WHERE fk_aluno_cpf = '" + cpf + "'";
        try (PreparedStatement pstm = BD.getConexao().prepareStatement(SQL)) {

            try (ResultSet rs = pstm.executeQuery()) {
                while (rs.next()) {
                    MatriculaCurso mat = new MatriculaCurso(
                            rs.getInt("matricula"),
                            rs.getString("situacao"),
                            Formatar.data(rs.getDate("data_inicio"), "dd/MM/yyyy"),
                            rs.getString("duracao_curso"),
                            rs.getString("fk_aluno_cpf"),
                            rs.getInt("fk_curso_cod")
                    );
                    matcurso.add(mat);
                }
                pstm.close();
            }
            System.out.println("MatriculaCurso do aluno obtidas com sucesso!");
        } catch (Exception ex) {
            Exibir.Mensagem("Erro ao obter MatriculaCurso do aluno!: \n" + ex);
        }
        return matcurso;
    }

    public void editarMatriculaCurso(MatriculaCurso mc) {
        String SQL = "UPDATE matriculacurso SET situacao = ?, data_inicio = ?, duracao_curso = ?, fk_aluno_cpf = ?, fk_curso_cod = ? WHERE matricula = ?";
        try (PreparedStatement pstm = BD.getConexao().prepareStatement(SQL)) {
            pstm.setString(1, mc.getSituacao());
            pstm.setDate(2, java.sql.Date.valueOf(mc.getData_inicio()));
            pstm.setString(3, mc.getDuracao_curso());
            pstm.setString(4, mc.getFk_Aluno());
            pstm.setInt(5, mc.getFk_Curso_cod());
            pstm.setInt(6, mc.getMatricula());

            System.out.println(SQL);
            pstm.executeUpdate();

            pstm.close();
            BD.getConexao().close();
            System.out.println("Alteração efetuada!");
        } catch (Exception ex) {
            Exibir.Mensagem("Erro ao Alterar MatriculaCurso!:\n" + ex);
        }
    }

    public void removerMatriculaCurso(MatriculaCurso mc) {
        String SQL = "DELETE FROM matriculacurso WHERE matricula = ?";
        try (PreparedStatement pstm = BD.getConexao().prepareStatement(SQL)) {
            pstm.setInt(1, mc.getMatricula());

            pstm.execute();

            BD.getConexao().close();
            System.out.println("Removido com sucesso!");
        } catch (Exception ex) {
            Exibir.Mensagem("\nErro ao remover MatriculaCurso: " + ex);
        }
    }
}
