package DAO;

import Util.Exibir;
import Util.Formatar;
import entities.Aluno;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Alexandre Almeida
 */
public class AlunoDAO {

    public void inserirAluno(Aluno aluno, InputStream fotoStream) {
        String SQL = "INSERT INTO aluno(cpf, nome, data_nascimento, sexo, email, endereco, telefone) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstm = BD.getConexao().prepareStatement(SQL)) {
            pstm.setString(1, aluno.getCpf());
            pstm.setString(2, aluno.getNome());
            pstm.setDate(3, java.sql.Date.valueOf(aluno.getData_nascimento()));
            pstm.setString(4, aluno.getSexo());
            pstm.setString(5, aluno.getEmail());
            pstm.setString(6, aluno.getEndereco());
            pstm.setString(7, aluno.getTelefone());

            pstm.execute();

            BD.getConexao().close();
            if (fotoStream != null) {
                inserirFoto(fotoStream, aluno.getCpf());
            }
            System.out.println("Aluno Inserido com Sucesso!");
        } catch (Exception ex) {
            Exibir.Mensagem("Erro ao inserir Aluno: " + ex);
        }
    }
    
    public void inserirFoto(InputStream fotoStream, String cpf) {
        String SQL = "INSERT INTO foto (bin_foto, fk_Aluno_cpf) VALUES (?, ?)";
        
        try (PreparedStatement pstm = BD.getConexao().prepareStatement(SQL)) {
            pstm.setBinaryStream(1, fotoStream);
            pstm.setString(2, cpf);
            pstm.executeUpdate();
            pstm.close();
            BD.getConexao().close();
            fotoStream.close();
            System.out.println("Foto Gravada com Sucesso!");
        } catch (Exception ex) {
            Exibir.Mensagem("Erro ao Gravar Foto!!!: \n" + ex);
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
                        rs.getString("endereco")
                );
                alunos.add(usr);
            }
            System.out.println("Alunos obtidos com sucesso!");
        } catch (Exception ex) {
            Exibir.Mensagem("Erro ao obter alunos!: \n" + ex);
        }
        return alunos;
    }
    
    public void alterarUsuario(Aluno aluno, String tempCpf) {
        
        String SQL = "UPDATE aluno SET cpf = ?, nome = ?, data_nascimento = ?, sexo = ?, email = ?, endereco = ?, telefone = ? WHERE cpf = ?";
        try (PreparedStatement pstm = BD.getConexao().prepareStatement(SQL)) {
            pstm.setString(1, aluno.getCpf());
            pstm.setString(2, aluno.getNome());
            pstm.setDate(3, java.sql.Date.valueOf(aluno.getData_nascimento()));
            pstm.setString(4, aluno.getSexo());
            pstm.setString(5, aluno.getEmail());
            pstm.setString(6, aluno.getEndereco());
            pstm.setString(7, aluno.getTelefone());
            pstm.setString(8, tempCpf);
            
            pstm.executeUpdate();

            pstm.close();
            BD.getConexao().close();
            System.out.println("Alteração efetuada!");
        } catch (Exception ex) {
            Exibir.Mensagem("Erro ao Alterar Usuário!:\n" + ex);
        }
    }

    public void apagarUsuario(String cpf) {

        String SQL = "DELETE FROM aluno WHERE cpf = (?)";
        try (PreparedStatement pstm = BD.getConexao().prepareStatement(SQL)) {
            pstm.setString(1, cpf);
            pstm.executeUpdate();

            pstm.close();
            BD.getConexao().close();
            System.out.println("Aluno Apagado! ");
        } catch (Exception ex) {
            Exibir.Mensagem("Erro ao Apagar Aluno!:\n" + ex);
        }
    }
}
