package DAO;

import Util.Exibir;
import entities.Curso;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

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
            pstm.close();
            System.out.println("Inserido com sucesso!");
        } catch (Exception ex) {
            Exibir.Mensagem("Erro ao inserir curso: " + ex);
        }
    }
    
    public ArrayList<Curso> obterCursos() {

        ArrayList<Curso> cursos = new ArrayList<>();

        String SQL = "SELECT cod, nome_curso, fk_Funcionario_id FROM curso";
        try {
            PreparedStatement pstm = BD.getConexao().prepareStatement(SQL);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                Curso novo = new Curso(
                        rs.getInt("cod"),
                        rs.getString("nome_curso"),
                        rs.getInt("fk_Funcionario_id")
                );
                cursos.add(novo);
            }
            pstm.close();
            rs.close();
            System.out.println("Cursos obtidos com sucesso!");
        } catch (Exception ex) {
            Exibir.Mensagem("Erro ao obter Cursos!: \n" + ex);
        }

        return cursos;
    }
    
    public void editarCurso(Curso c) {
        String SQL = "UPDATE curso SET cod = ?, nome_curso = ?, fk_Funcionario_id = ? WHERE cod = ?";
        try (PreparedStatement pstm = BD.getConexao().prepareStatement(SQL)) {
            System.out.println("cod " + c.getCod());
            pstm.setInt(1, c.getCod());
            pstm.setString(2, c.getNome_curso());
            pstm.setInt(3, c.getFk_Funcionario_id());
            pstm.setInt(4, c.getCod_antigo());
            System.out.println(c.getCod_antigo());
            System.out.println(SQL);
            pstm.executeUpdate();

            pstm.close();
            BD.getConexao().close();
            System.out.println("Alteração efetuada!");
        } catch (Exception ex) {
            Exibir.Mensagem("Erro ao Alterar Curso!:\n" + ex);
        }
    }

    public void removerCurso(Curso c) {
        String SQL = "DELETE FROM curso WHERE cod = (?)";
        try (PreparedStatement pstm = BD.getConexao().prepareStatement(SQL)) {
            pstm.setInt(1, c.getCod());

            pstm.execute();

            BD.getConexao().close();
            pstm.close();
            System.out.println("Removido com sucesso!");
        } catch (Exception ex) {
            Exibir.Mensagem("\nErro ao remover curso: " + ex);
        }
    }
}
