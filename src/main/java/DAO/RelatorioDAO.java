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

    public ArrayList<Aluno> obterPodemColarGrau(int codCurso) {

        ArrayList<Aluno> historico = new ArrayList<>();

        String SQL = "SELECT a.nome AS nome_aluno, md.fk_Aluno_cpf AS cpf FROM MatriculaDisciplina md\n"
                + "LEFT JOIN aluno a ON(a.cpf = md.fk_Aluno_cpf)\n"
                + "LEFT JOIN disciplina d ON(d.codigo = fk_Disciplina_codigo)\n"
                + "WHERE md.situacao = 'Concluido' AND d.fk_Curso_cod = ?\n"
                + "GROUP BY md.fk_Aluno_cpf, a.nome HAVING COUNT(md.fk_Aluno_cpf) = (SELECT COUNT(*) FROM disciplina WHERE fk_Curso_cod = ?) \n"
                + "ORDER BY md.fk_Aluno_cpf";
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

    public ArrayList<HistoricoAluno> obterAlunosMatriculados(int codCurso, String situacao) {
        ArrayList<HistoricoAluno> matriculados = new ArrayList<>();

        String sqlCodCurso = (codCurso != 0 ? "AND mc.fk_curso_cod = " + codCurso : "");
        String sqlSituacao = (situacao.equals("") ? "AND mc.situacao = " + situacao : "");

        String SQL = "SELECT nome, matricula, fk_Aluno_cpf, nome_curso, data_inicio, duracao_curso, situacao FROM matriculacurso mc\n"
                + "INNER JOIN aluno a ON(a.cpf = mc.fk_Aluno_cpf)\n"
                + "INNER JOIN curso c ON(c.cod = mc.fk_Curso_cod)\n"
                + "WHERE mc.matricula IS NOT NULL" + sqlCodCurso + sqlSituacao;
        try (PreparedStatement pstm = BD.getConexao().prepareStatement(SQL)) {

            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                HistoricoAluno matric = new HistoricoAluno(
                        rs.getString("nome"),
                        rs.getString("matricula"),
                        rs.getString("fk_Aluno_cpf"),
                        rs.getString("nome_curso"),
                        rs.getString("data_inicio"),
                        rs.getString("duracao_curso"),
                        rs.getString("situacao")
                );
                matriculados.add(matric);
            }
            pstm.close();
            System.out.println("Histórico obtido com sucesso!");
        } catch (Exception ex) {
            System.out.println("Erro ao obter Histórico!" + ex);
        }

        return matriculados;
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
