package DAO;

import Util.Exibir;
import entities.Disciplina;
import entities.MatriculaDisciplina;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * @Autor Alexandre Almeida
 * @Data 31/10/2018
 */
public class MatriculaDisciplinaDAO {

    public void inserirMatriculaDisciplina(MatriculaDisciplina md) {
        String SQL = "INSERT INTO matriculadisciplina(conceito, semestre, ano, situacao, fk_disciplina_codigo, fk_aluno_cpf) VALUES (?,?,?,?,?,?)";
        try (PreparedStatement pstm = BD.getConexao().prepareStatement(SQL)) {
            pstm.setString(1, md.getConceito());
            pstm.setString(2, md.getSemestre());
            pstm.setInt(3, md.getAno());
            pstm.setString(4, md.getSituacao());
            pstm.setString(5, md.getFk_Disciplina_codigo());
            pstm.setString(6, md.getFk_Aluno_cpf());

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

        String SQL = "SELECT a.nome AS nome_aluno, d.nome AS nome_disciplina, c.nome_curso, m.ano, m.situacao, m.semestre, m.id, m.conceito, m.fk_disciplina_codigo, m.fk_aluno_cpf \n"
                + "FROM matriculadisciplina m \n"
                + "INNER JOIN aluno a ON(a.cpf = m.fk_aluno_cpf)\n"
                + "INNER JOIN disciplina d ON(d.codigo = m.fk_disciplina_codigo)\n"
                + "INNER JOIN curso c ON(c.cod = fk_curso_cod)";
        try (PreparedStatement pstm = BD.getConexao().prepareStatement(SQL)) {

            try (ResultSet rs = pstm.executeQuery()) {
                while (rs.next()) {
                    MatriculaDisciplina mat = new MatriculaDisciplina(
                            rs.getInt("id"),
                            rs.getString("conceito"),
                            rs.getString("semestre"),
                            rs.getInt("ano"),
                            rs.getString("situacao"),
                            rs.getString("fk_disciplina_codigo"),
                            rs.getString("fk_aluno_cpf"),
                            rs.getString("nome_aluno"),
                            rs.getString("nome_disciplina"),
                            rs.getString("nome_curso")
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

    public ArrayList<MatriculaDisciplina> obterMatriculaPorDisciplina(String fk_disciplina_codigo) {

        ArrayList<MatriculaDisciplina> matdis = new ArrayList<>();

        String SQL = "SELECT * FROM matriculadisciplina WHERE situacao !='Cancelada' AND fk_disciplina_codigo = '" + fk_disciplina_codigo + "'";
        try (PreparedStatement pstm = BD.getConexao().prepareStatement(SQL)) {

            try (ResultSet rs = pstm.executeQuery()) {
                while (rs.next()) {
                    MatriculaDisciplina mat = new MatriculaDisciplina(
                            rs.getInt("id"),
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
        String SQL = "UPDATE matriculadisciplina SET conceito = ?, semestre = ?, ano = ?, situacao = ? WHERE  id = ?";
        try (PreparedStatement pstm = BD.getConexao().prepareStatement(SQL)) {
            pstm.setString(1, md.getConceito());
            pstm.setString(2, md.getSemestre());
            pstm.setInt(3, md.getAno());
            pstm.setString(4, md.getSituacao());
            pstm.setInt(5, md.getId());

            System.out.println(SQL);
            pstm.executeUpdate();

            pstm.close();
            BD.getConexao().close();
            System.out.println("Alteração efetuada!");
        } catch (Exception ex) {
            Exibir.Mensagem("Erro ao Alterar matriculaDisciplina!:\n" + ex);
        }
    }

    public void alterarSituacaoDisciplina(MatriculaDisciplina md) {
        String SQL = "UPDATE matriculadisciplina SET conceito = ?, situacao = ? WHERE  id = ?";
        try (PreparedStatement pstm = BD.getConexao().prepareStatement(SQL)) {
            pstm.setString(1, md.getConceito());
            pstm.setString(2, md.getSituacao());
            pstm.setInt(3, md.getId());

            System.out.println(SQL);
            pstm.executeUpdate();

            pstm.close();
            BD.getConexao().close();
            System.out.println("Alteração efetuada!");
        } catch (Exception ex) {
            Exibir.Mensagem("Erro ao Alterar matriculaDisciplina!:\n" + ex);
        }
    }

    public void removerMatriculaDisciplina(MatriculaDisciplina md) {
        String SQL = "DELETE FROM matriculadisciplina WHERE id = ?";
        try (PreparedStatement pstm = BD.getConexao().prepareStatement(SQL)) {
            pstm.setInt(1, md.getId());

            pstm.execute();

            BD.getConexao().close();
            System.out.println("Removido com sucesso!");
        } catch (Exception ex) {
            Exibir.Mensagem("\nErro ao remover MatriculaDisciplina: " + ex);
        }
    }
}
