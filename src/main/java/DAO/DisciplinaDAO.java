package DAO;

import entities.Disciplina;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

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

    public ArrayList<Disciplina> obterDisciplina() {

        ArrayList<Disciplina> disciplina = new ArrayList<>();

        String SQL = "SELECT id, nome, cargo, email, telefone, fk_Usuarios_id_user FROM funcionario ORDER BY nome";
        try {
            PreparedStatement pstm = BD.getConexao().prepareStatement(SQL);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                Disciplina disc = new Disciplina(
                        rs.getString("codigo"),
                        rs.getString("nome"),
                        rs.getString("situacao"),
                        rs.getInt("fk_curso_cod")
                );
                disciplina.add(disc);
            }
            System.out.println("Disciplinas obtidos com sucesso!");
        } catch (Exception ex) {
            System.out.println("Erro ao obter Disciplinas!: \n" + ex);
        }

        return disciplina;
    }

    public void editarDisciplina(Disciplina d) {
        String SQL = "UPDATE disciplina SET nome = ?, situacao = ?, fk_curso_cod = ? WHERE codigo = ?";
        try (PreparedStatement pstm = BD.getConexao().prepareStatement(SQL)) {
            pstm.setString(1, d.getNome());
            pstm.setString(2, d.getSituacao());
            pstm.setInt(3, d.getFk_Curso_cod());
            pstm.setString(4, d.getCodigo());

            System.out.println(SQL);
            pstm.executeUpdate();

            pstm.close();
            BD.getConexao().close();
            System.out.println("Alteração efetuada!");
        } catch (Exception ex) {
            System.out.println("Erro ao Alterar disciplina!:\n" + ex);
        }
    }

    public void removerDisciplina(Disciplina disciplina) {
        String SQL = "DELETE FROM disciplina WHERE id = (?)";
        try (PreparedStatement pstm = BD.getConexao().prepareStatement(SQL)) {
            pstm.setString(1, disciplina.getCodigo());

            pstm.execute();

            BD.getConexao().close();
            System.out.println("Removido com sucesso!");
        } catch (Exception ex) {
            System.out.println("\nErro ao remover disciplina: " + ex);
        }
    }
}
