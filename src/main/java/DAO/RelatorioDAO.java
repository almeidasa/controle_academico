package DAO;

import Util.Exibir;
import Util.Obter;
import entities.Aluno;
import entities.HistoricoAluno;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * @Autor Winder Rezende
 * @Data 24/11/2018
 */
public class RelatorioDAO {

    public ArrayList<HistoricoAluno> obterHistorico(String cpf, int codCurso) {

        ArrayList<HistoricoAluno> historico = new ArrayList<>();

        String SQL = "SELECT c.nome_curso, a.nome AS nomealuno, mc.matricula, d.nome AS nomedisciplina, md.ano, md.semestre, md.conceito FROM Disciplina d\n"
                + "INNER JOIN Curso c ON(c.cod = d.fk_Curso_cod)\n"
                + "INNER JOIN matriculadisciplina md ON(md.fk_Disciplina_codigo = d.codigo)\n"
                + "INNER JOIN Aluno a ON(a.cpf = md.fk_Aluno_cpf)\n"
                + "INNER JOIN MatriculaCurso mc on(mc.fk_Aluno_cpf = a.cpf AND mc.fk_Curso_cod = ?)\n"
                + "WHERE md.fk_Aluno_cpf = ? AND d.fk_Curso_cod = ? ORDER BY md.ano, md.semestre";
        try (PreparedStatement pstm = BD.getConexao().prepareStatement(SQL)) {
            pstm.setInt(1, codCurso);
            pstm.setString(2, cpf);
            pstm.setInt(3, codCurso);

            System.out.println(cpf);
            System.out.println(codCurso);

            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                HistoricoAluno ha = new HistoricoAluno(
                        rs.getString("nome_curso"),
                        rs.getString("nomealuno"),
                        cpf,
                        rs.getString("matricula"),
                        rs.getString("nomedisciplina"),
                        rs.getString("ano"),
                        rs.getString("semestre"),
                        rs.getString("conceito") == null || rs.getString("conceito").equals("") ? "-" : rs.getString("conceito"),
                        rs.getString("conceito") != null && !rs.getString("conceito").equals("") ? (rs.getString("conceito").equals("Insuficiente") ? "Reprovado" : "Aprovado") : "M"
                );
                historico.add(ha);
            }
            pstm.close();
            System.out.println("Histórico obtido com sucesso!");
        } catch (Exception ex) {
            System.out.println("Erro ao obter Histórico!" + ex);
        }
        return historico;
    }

    public ArrayList<HistoricoAluno> obterAlunosMatriculados(int codCurso, String codDicplina) {
        ArrayList<HistoricoAluno> matriculados = new ArrayList<>();

        String sqlCodCurso = (codCurso != 0 ? " AND d.fk_Curso_cod = " + codCurso : "");
        String sqlCodDicplina = (!codDicplina.equals("D") ? " AND d.codigo = '" + codDicplina + "'" : "");

        String SQL = "SELECT a.nome AS nome_aluno, cpf, nome_curso FROM aluno a\n"
                + "INNER JOIN MatriculaDisciplina md on(md.fk_Aluno_cpf = a.cpf)\n"
                + "INNER JOIN disciplina d ON(d.codigo = md.fk_Disciplina_codigo)\n"
                + "INNER JOIN curso c ON(c.cod = d.fk_Curso_cod)\n"
                + "INNER JOIN MatriculaCurso mc on(mc.fk_Aluno_cpf = a.cpf)\n"
                + "WHERE mc.matricula IS NOT NULL" + sqlCodDicplina + sqlCodCurso + " GROUP BY a.nome, cpf, d.fk_Curso_cod, c.nome_curso";
        try (PreparedStatement pstm = BD.getConexao().prepareStatement(SQL)) {

            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                HistoricoAluno matric = new HistoricoAluno(
                        rs.getString("nome_curso"),
                        rs.getString("nome_aluno"),
                        rs.getString("cpf")
                );
                matriculados.add(matric);
            }
            pstm.close();
            System.out.println("Alunos Matriculados obtidos com sucesso!");
        } catch (Exception ex) {
            System.out.println("Erro ao Alunos Matriculados!" + ex);
        }

        return matriculados;
    }

    public ArrayList<Aluno> obterPoderaoColarGrau(int codCurso) {

        ArrayList<Aluno> historico = new ArrayList<>();

        String SQL = "SELECT a.nome AS nome_aluno, md.fk_Aluno_cpf AS cpf FROM MatriculaDisciplina md\n"
                + "LEFT JOIN aluno a ON(a.cpf = md.fk_Aluno_cpf)\n"
                + "LEFT JOIN disciplina d ON(d.codigo = fk_Disciplina_codigo)\n"
                + "WHERE md.situacao = 'Concluido' AND d.fk_Curso_cod = ?\n"
                + "GROUP BY md.fk_Aluno_cpf, a.nome HAVING COUNT(md.fk_Aluno_cpf) >= (SELECT ROUND((COUNT(*) - 3) - (COUNT(*) / (SELECT to_number(duracao_curso, '99G999D9S') FROM matriculacurso \n"
                + "WHERE fk_Curso_cod = ? GROUP BY fk_Curso_cod, duracao_curso))) FROM disciplina WHERE fk_Curso_cod = ?)\n"
                + "ORDER BY a.nome";
        try (PreparedStatement pstm = BD.getConexao().prepareStatement(SQL)) {
            pstm.setInt(1, codCurso);
            pstm.setInt(2, codCurso);
            pstm.setInt(3, codCurso);

            try (ResultSet rs = pstm.executeQuery()) {
                while (rs.next()) {
                    Aluno aluno = new Aluno(
                            rs.getString("nome_aluno"),
                            rs.getString("cpf")
                    );
                    historico.add(aluno);
                }
            }
            pstm.close();
            System.out.println("Alunos que poderão colar grau obtidos com sucesso!");
        } catch (Exception ex) {
            System.out.println("Erro ao obter Histórico alunos que poderão colar grau!" + ex);
        }
        return historico;
    }

    public ArrayList<Aluno> obterPodemColarGrau(int codCurso) {

        ArrayList<Aluno> historico = new ArrayList<>();

        String SQL = "SELECT a.nome AS nome_aluno, md.fk_Aluno_cpf AS cpf FROM MatriculaDisciplina md\n"
                + "LEFT JOIN aluno a ON(a.cpf = md.fk_Aluno_cpf)\n"
                + "LEFT JOIN disciplina d ON(d.codigo = fk_Disciplina_codigo)\n"
                + "WHERE md.situacao = 'Concluido' AND d.fk_Curso_cod = ?\n"
                + "GROUP BY md.fk_Aluno_cpf, a.nome HAVING COUNT(md.fk_Aluno_cpf) = (SELECT COUNT(*) FROM disciplina WHERE fk_Curso_cod = ?) \n"
                + "ORDER BY a.nome";
        try (PreparedStatement pstm = BD.getConexao().prepareStatement(SQL)) {
            pstm.setInt(1, codCurso);
            pstm.setInt(2, codCurso);

            try (ResultSet rs = pstm.executeQuery()) {
                while (rs.next()) {
                    Aluno aluno = new Aluno(
                            rs.getString("nome_aluno"),
                            rs.getString("cpf")
                    );
                    historico.add(aluno);
                }
            }
            pstm.close();
            System.out.println("Alunos que podem colar grau obtidos com sucesso!");
        } catch (Exception ex) {
            System.out.println("Erro ao obter Histórico alunos que podem colar grau!" + ex);
        }
        return historico;
    }

    public void obterFoto(String cpf) {
        String SQL = "SELECT bin_foto FROM aluno WHERE cpf = (?)";

        try (PreparedStatement pstm = BD.getConexao().prepareStatement(SQL)) {
            pstm.setString(1, cpf);

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                if (rs.getBinaryStream("bin_foto") != null) {
                    Path path = Paths.get(Obter.CaminhoFotoRel(cpf + ".jpg"));
                    Files.copy(rs.getBinaryStream("bin_foto"), path, StandardCopyOption.REPLACE_EXISTING);
                }
            }

            pstm.close();
            BD.getConexao().close();
            System.out.println("Foto obtida com sucesso!");
        } catch (Exception ex) {
            Exibir.Mensagem("Erro ao Obter Foto!!!: \n" + ex);
        }
    }
}
