package DAO;

import Util.Exibir;
import Util.Obter;
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

        String SQL = "SELECT c.nome_curso, a.nome AS nomealuno, mc.matricula, d.nome AS nomedisciplina, md.ano, md.semestre, md.conceito FROM aluno a\n"
                + "INNER JOIN MatriculaCurso mc on(mc.fk_Aluno_cpf = a.cpf)\n"
                + "INNER JOIN Curso c ON(c.cod = mc.fk_Curso_cod)\n"
                + "INNER JOIN MatriculaDisciplina md on(md.fk_Aluno_cpf = a.cpf)\n"
                + "INNER JOIN Disciplina d ON(d.codigo = md.fk_Disciplina_codigo AND d.fk_Curso_cod = ?)\n"
                + "WHERE a.cpf = ? AND mc.fk_Curso_cod = ? ORDER BY md.ano, md.semestre";
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
                        rs.getString("conceito") != null &&  !rs.getString("conceito").equals("") ? (rs.getString("conceito").equals("Insuficiente") ? "Reprovado" : "Aprovado") : "M"
                );
                historico.add(ha);
            }
            pstm.close();
            Exibir.Mensagem("Erro ao obter Histórico!");
            System.out.println("Histórico obtido com sucesso!");
        } catch (Exception ex) {
            System.out.println("Erro" + ex);
            Exibir.Mensagem("Erro ao obter Histórico!: \n" + ex);
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
