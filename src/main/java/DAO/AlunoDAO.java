package DAO;

import Util.Exibir;
import Util.Formatar;
import entities.Aluno;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Alexandre Almeida
 */
public class AlunoDAO {

    public void inserirAluno(Aluno aluno) {
        String SQL = "INSERT INTO aluno(cpf, nome, data_nascimento, sexo, email, endereco, telefone, fk_foto_id_foto) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstm = BD.getConexao().prepareStatement(SQL)) {
            pstm.setString(1, aluno.getCpf());
            pstm.setString(2, aluno.getNome());
            pstm.setDate(3, java.sql.Date.valueOf(aluno.getData_nascimento()));
            pstm.setString(4, aluno.getSexo());
            pstm.setString(5, aluno.getEmail());
            pstm.setString(6, aluno.getEndereco());
            pstm.setString(7, aluno.getTelefone());
            pstm.setInt(8, aluno.getFk_foto_id_foto());

            pstm.execute();

            BD.getConexao().close();
            System.out.println("Aluno Inserido com Sucesso!");
        } catch (Exception ex) {
            Exibir.Mensagem("Erro ao inserir Aluno: " + ex);
        }
    }
    
    public ArrayList<Aluno> obterAlunos() {

        ArrayList<Aluno> alunos = new ArrayList<>();

        String SQL = "SELECT * FROM aluno ORDER BY nome ASC";
        try {
            PreparedStatement pstm = BD.getConexao().prepareStatement(SQL);

            ResultSet rs = pstm.executeQuery(); //envia o comando ao banco

            while (rs.next()) {
                Aluno usr = new Aluno(
                        rs.getString("nome"),
                        rs.getString("cpf"),
                        Formatar.data(rs.getDate("data_nascimento"), "dd/MM/yyyy"),
                        rs.getString("sexo"),
                        rs.getString("telefone"),
                        rs.getString("email"),
                        rs.getString("endereco"),
                        rs.getInt("fk_foto_id_foto")
                );
                alunos.add(usr);
            }
            System.out.println("Alunos obtidos com sucesso!");
        } catch (Exception ex) {
            Exibir.Mensagem("Erro ao obter alunos!: \n" + ex);
        }
        return alunos;
    }
}
