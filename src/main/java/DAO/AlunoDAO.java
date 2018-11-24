package DAO;

import Util.Exibir;
import Util.Formatar;
import Util.Obter;
import entities.Aluno;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Alexandre Almeida
 */
public class AlunoDAO {

    public void inserirAluno(Aluno aluno) {
        String SQL;
        InputStream fis;
        SQL = "INSERT INTO aluno(cpf, nome, data_nascimento, sexo, email, endereco, telefone, bin_foto) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstm = BD.getConexao().prepareStatement(SQL)) {
            pstm.setString(1, aluno.getCpf());
            pstm.setString(2, aluno.getNome());
            pstm.setDate(3, java.sql.Date.valueOf(aluno.getData_nascimento()));
            pstm.setString(4, aluno.getSexo());
            pstm.setString(5, aluno.getEmail());
            pstm.setString(6, aluno.getEndereco());
            pstm.setString(7, aluno.getTelefone().replace(" (__) _____-____ (__) _____-____", "").replace(" (__) _____-____", ""));
            if (aluno.getCaminhoFoto() != null) {
                File file = new File(aluno.getCaminhoFoto());
                fis = new FileInputStream(file);
                pstm.setBinaryStream(8, fis, (int) file.length());
            } else {
                File file = new File(Obter.CaminhoArquivo("usrFoto.jpg"));
                fis = new FileInputStream(file);
                pstm.setBinaryStream(8, fis, (int) file.length());
            }
            pstm.execute();
            pstm.close();
            BD.getConexao().close();
            fis.close();

            System.out.println("Aluno Inserido com Sucesso!");
        } catch (Exception ex) {
            Exibir.Mensagem("Erro ao inserir Aluno: " + ex);
        }
    }

    public ArrayList<Aluno> obterAlunos() {

        ArrayList<Aluno> alunos = new ArrayList<>();

        String SQL = "SELECT cpf, nome, data_nascimento, sexo, telefone, email, endereco FROM aluno ORDER BY nome ASC";
        try (PreparedStatement pstm = BD.getConexao().prepareStatement(SQL)) {

            try (ResultSet rs = pstm.executeQuery()) {
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
                pstm.close();
            }
            System.out.println("Alunos obtidos com sucesso!");
        } catch (Exception ex) {
            Exibir.Mensagem("Erro ao obter alunos!: \n" + ex);
        }
        return alunos;
    }
    
    public ArrayList<Aluno> obterAlunosCurso(int codCurso) {
        ArrayList<Aluno> alunos = new ArrayList<>();

        String SQL = "SELECT cpf, nome FROM aluno a INNER JOIN MatriculaCurso m ON(m.fk_Aluno_cpf = a.cpf) WHERE m.fk_Curso_cod = ? ORDER BY nome ASC";
        try (PreparedStatement pstm = BD.getConexao().prepareStatement(SQL)) {
            pstm.setInt(1, codCurso);
            try (ResultSet rs = pstm.executeQuery()) {
                while (rs.next()) {
                    Aluno usr = new Aluno(
                            rs.getString("nome"),
                            rs.getString("cpf")
                    );
                    alunos.add(usr);
                }
                pstm.close();
            }
            System.out.println("Alunos no curso obtidos com sucesso!");
        } catch (Exception ex) {
            Exibir.Mensagem("Erro ao obter alunos no curso!: \n" + ex);
        }
        return alunos;
    }

    public String obterEmail(String cpf) {
        String email = "";
        System.out.println(cpf);
        String SQL = "SELECT email FROM aluno WHERE cpf = '" + cpf + "'";
        try (PreparedStatement pstm = BD.getConexao().prepareStatement(SQL)) {

            try (ResultSet rs = pstm.executeQuery()) {
                while (rs.next()) {
                    email = rs.getString("email");
                }
                pstm.close();
            }
            System.out.println("Email do aluno obtido com sucesso!");
        } catch (Exception ex) {
            Exibir.Mensagem("Erro ao obter email do aluno!: \n" + ex);
            return "";
        }
        return email;
    }

    public void obterFoto(String cpf) {
        String SQL = "SELECT bin_foto FROM aluno WHERE cpf = (?)";

        try (PreparedStatement pstm = BD.getConexao().prepareStatement(SQL)) {
            pstm.setString(1, cpf);

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                if (rs.getBinaryStream("bin_foto") != null) {
                    Path path = Paths.get(Obter.CaminhoArquivo(cpf + ".jpg"));
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

    public void alterarAluno(Aluno aluno, String tempCpf) {
        String SQL;
        InputStream fis = null;

        if (aluno.getCaminhoFoto() != null) {
            SQL = "UPDATE aluno SET cpf = ?, nome = ?, data_nascimento = ?, sexo = ?, email = ?, endereco = ?, telefone = ?, bin_foto = ? WHERE cpf = ?";
        } else {
            SQL = "UPDATE aluno SET cpf = ?, nome = ?, data_nascimento = ?, sexo = ?, email = ?, endereco = ?, telefone = ? WHERE cpf = ?";
        }

        try (PreparedStatement pstm = BD.getConexao().prepareStatement(SQL)) {
            pstm.setString(1, aluno.getCpf());
            pstm.setString(2, aluno.getNome());
            pstm.setDate(3, java.sql.Date.valueOf(aluno.getData_nascimento()));
            pstm.setString(4, aluno.getSexo());
            pstm.setString(5, aluno.getEmail());
            pstm.setString(6, aluno.getEndereco());
            pstm.setString(7, aluno.getTelefone().replace(" (__) _____-____ (__) _____-____", "").replace(" (__) _____-____", ""));
            if (aluno.getCaminhoFoto() != null) {
                File file = new File(aluno.getCaminhoFoto());
                fis = new FileInputStream(file);
                pstm.setBinaryStream(8, fis, (int) file.length());
                pstm.setString(9, tempCpf);
            } else {
                pstm.setString(8, tempCpf);
            }

            pstm.executeUpdate();

            pstm.close();
            BD.getConexao().close();
            if (fis != null) {
                fis.close();
            }
            System.out.println("Alteração efetuada!");
        } catch (Exception ex) {
            Exibir.Mensagem("Erro ao Alterar Usuário!:\n" + ex);
        }
    }

    public void apagarAluno(String cpf) {

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
