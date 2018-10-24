package DAO;

import entities.Aluno;
import java.sql.PreparedStatement;

/**
 *
 * @author Alexandre Almeida
 */
public class AlunoDAO {

    public void inserirAluno(Aluno aluno) {
        String SQL = "INSERT INTO aluno(cpf, nome, data_nascimento, sexo, foto, endereco, telefone) VALUES (?,?,?,?,?,?,?)";
        try (PreparedStatement pstm = BD.getConexao().prepareStatement(SQL)) {
            pstm.setString(1, aluno.getCpf());
            pstm.setString(2, aluno.getNome());
            pstm.setDate(3, aluno.getData_nascimento());
            pstm.setString(4, aluno.getSexo());
            pstm.setString(5, aluno.getFoto());
            pstm.setString(6, aluno.getEndereco());
            pstm.setString(7, aluno.getTelefone());

            pstm.execute();

            BD.getConexao().close();
            System.out.println("Inserido com sucesso!");
        } catch (Exception ex) {
            System.out.println("\nErro ao inserir Aluno: " + ex);
        }
    }
}
