package DAO;

import Util.Formatar;
import entities.Curso;
import entities.Funcionario;
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
            System.out.println("Inserido com sucesso!");
        } catch (Exception ex) {
            System.out.println("\nErro ao inserir curso: " + ex);
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
            System.out.println("Cursos obtidos com sucesso!");
        } catch (Exception ex) {
            System.out.println("Erro ao obter Cursos!: \n" + ex);
        }

        return cursos;
    }
    
}
