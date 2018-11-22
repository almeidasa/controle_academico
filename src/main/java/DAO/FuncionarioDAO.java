package DAO;

import Util.Exibir;
import entities.Funcionario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Alexandre Almeida
 */
public class FuncionarioDAO {

    public void inserirFuncionario(Funcionario funcionario) {
        String SQL = "INSERT INTO funcionario(nome, cargo, email, telefone, fk_Usuarios_id_user) VALUES (?,?,?,?,?)";
        try (PreparedStatement pstm = BD.getConexao().prepareStatement(SQL)) {
            pstm.setString(1, funcionario.getNome());
            pstm.setString(2, funcionario.getCargo());
            pstm.setString(3, funcionario.getEmail());
            pstm.setString(4, funcionario.getTelefone());
            if (funcionario.getFk_Usuarios_id_user() != 0) {
                pstm.setInt(5, funcionario.getFk_Usuarios_id_user());
            }else{
                pstm.setNull(5, 0);
            }

            pstm.execute();

            BD.getConexao().close();
            System.out.println("Inserido com sucesso!");
        } catch (Exception ex) {
            Exibir.Mensagem("Erro ao inserir Funcionario: " + ex);
        }
    }

    public ArrayList<Funcionario> obterFuncionarios() {

        ArrayList<Funcionario> funcionario = new ArrayList<>();

        String SQL = "SELECT id, nome, cargo, email, telefone, fk_Usuarios_id_user FROM funcionario ORDER BY nome";
        try {
            PreparedStatement pstm = BD.getConexao().prepareStatement(SQL);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                Funcionario func = new Funcionario(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("cargo"),
                        rs.getString("email"),
                        rs.getString("telefone"),
                        rs.getInt("fk_Usuarios_id_user")
                );
                funcionario.add(func);
            }
            pstm.close();
            rs.close();
            System.out.println("Funcionários obtidos com sucesso!");
        } catch (Exception ex) {
            Exibir.Mensagem("Erro ao obter Funcionários!: \n" + ex);
        }

        return funcionario;
    }

    public int obterFuncionarioPorUsuario(int id_user) {
        int id = 0;

        String SQL = "SELECT id FROM funcionario WHERE fk_Usuarios_id_user = " + id_user;
        try {
            PreparedStatement pstm = BD.getConexao().prepareStatement(SQL);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                id = rs.getInt("id");
            }
            pstm.close();
            rs.close();
            System.out.println("Funcionário obtido com sucesso!");
        } catch (Exception ex) {
            Exibir.Mensagem("Erro ao obter Funcionário por usuário!: \n" + ex);
        }
        return id;
    }

    public void editarFuncionario(Funcionario f) {
        String SQL = "UPDATE funcionario SET nome = ?, cargo = ?, email = ?, telefone = ?, fk_Usuarios_id_user = ? WHERE id = ?";
        try (PreparedStatement pstm = BD.getConexao().prepareStatement(SQL)) {
            pstm.setString(1, f.getNome());
            pstm.setString(2, f.getCargo());
            pstm.setString(3, f.getEmail());
            pstm.setString(4, f.getTelefone());
            pstm.setInt(5, f.getFk_Usuarios_id_user());
            pstm.setInt(6, f.getId());

            System.out.println(SQL);
            pstm.executeUpdate();

            pstm.close();
            BD.getConexao().close();
            System.out.println("Alteração efetuada!");
        } catch (Exception ex) {
            Exibir.Mensagem("Erro ao Alterar Funcionário!:\n" + ex);
        }
    }

    public void removerFuncionario(Funcionario funcionario) {
        String SQL = "DELETE FROM funcionario WHERE id = (?)";
        try (PreparedStatement pstm = BD.getConexao().prepareStatement(SQL)) {
            pstm.setInt(1, funcionario.getId());

            pstm.execute();

            BD.getConexao().close();
            pstm.close();
            System.out.println("Removido com sucesso!");
        } catch (Exception ex) {
            Exibir.Mensagem("Erro ao remover Funcionario: " + ex);
        }
    }
}
