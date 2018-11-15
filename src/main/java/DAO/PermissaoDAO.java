package DAO;

import Util.Exibir;
import controller.PermissaoBean;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * @Autor Winder Rezende
 * @Data 15/11/2018
 */
public class PermissaoDAO {

    public void inserirPermissao(String nomePermissao) {

        String SQL = "INSERT INTO Permissao(nome, admin, diret, coord, func, aluno) VALUES (?, false, false, false, false, false)";
        try (PreparedStatement pstm = BD.getConexao().prepareStatement(SQL)) {
            pstm.setString(1, nomePermissao);
            pstm.execute();

            BD.getConexao().close();
            pstm.close();
            System.out.println("Inserido com sucesso!");
        } catch (Exception ex) {
            Exibir.Mensagem("Erro ao inserir usuário: " + ex);
        }
    }

    public ArrayList<PermissaoBean> obterPermissoes() {

        ArrayList<PermissaoBean> permissoes = new ArrayList<>();

        String SQL = "SELECT * FROM permissao";
        try (PreparedStatement pstm = BD.getConexao().prepareStatement(SQL)) {

            try (ResultSet rs = pstm.executeQuery()) {
                while (rs.next()) {
                    PermissaoBean perm = new PermissaoBean(
                            rs.getString("nome"),
                            rs.getBoolean("admin"),
                            rs.getBoolean("diret"),
                            rs.getBoolean("coord"),
                            rs.getBoolean("func"),
                            rs.getBoolean("aluno")
                    );
                    permissoes.add(perm);
                }
                pstm.close();
            }
            System.out.println("Permissões obtidas com sucesso!");
        } catch (Exception ex) {
            Exibir.Mensagem("Erro ao obter usuários!: \n" + ex);
        }
        return permissoes;
    }

    public void alterarPermissao(String usr, boolean situacao, String nomeAcesso) {

        String SQL = "UPDATE permissao SET " + usr + " = ? WHERE nome = ?";
        System.out.println(SQL);
        try (PreparedStatement pstm = BD.getConexao().prepareStatement(SQL)) {
            pstm.setBoolean(1, situacao);
            pstm.setString(2, nomeAcesso);
            pstm.executeUpdate();

            pstm.close();
            BD.getConexao().close();
            System.out.println("Permissão Alterada!");
        } catch (Exception ex) {
            Exibir.Mensagem("Erro ao Alterar Usuário!:\n" + ex);
        }
    }
}
