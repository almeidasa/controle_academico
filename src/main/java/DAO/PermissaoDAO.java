package DAO;

import Util.Exibir;
import controller.PermissaoBean;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

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
            System.out.println("Permissão inserida com sucesso!");
        } catch (Exception ex) {
            Exibir.Mensagem("Erro ao inserir Permissão: " + ex);
        }
    }

    public Map<String, Boolean> obterPermissoesSessao(String tpUsr) {

        switch (tpUsr) {
            case "Administrador":
                tpUsr = "admin";
                break;
            case "Diretor":
                tpUsr = "diret";
                break;
            case "Coordenador":
                tpUsr = "coord";
                break;
            case "Funcionário":
                tpUsr = "func";
                break;
            case "Aluno":
                tpUsr = "aluno";
                break;
            default:
                break;
        }
        
        System.out.println("Tipo de Usuário: " + tpUsr);

        Map<String, Boolean> permissao = new LinkedHashMap<>();

        String SQL = "SELECT nome, " + tpUsr + " FROM permissao";
        try (PreparedStatement pstm = BD.getConexao().prepareStatement(SQL)) {

            try (ResultSet rs = pstm.executeQuery()) {
                while (rs.next()) {
                    permissao.put(rs.getString("nome"), rs.getBoolean(tpUsr));
                    System.out.println(rs.getString("nome"));
                }
                pstm.close();
            }
            System.out.println("Permissões da sessão obtidas com sucesso!");
        } catch (Exception ex) {
            Exibir.Mensagem("Erro ao obter as permissões da sessao!: \n" + ex);
        }
        return permissao;
    }

    public ArrayList<PermissaoBean> obterPermissoes() {

        ArrayList<PermissaoBean> permissoes = new ArrayList<>();

        String SQL = "SELECT * FROM permissao ORDER BY nome";
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
                    if (rs.getString("nome") == null) {
                        inserirPermissoesPadrao();
                    }
                }

                pstm.close();
            }
            System.out.println("Permissões obtidas com sucesso!");
        } catch (Exception ex) {
            Exibir.Mensagem("Erro ao obter Permissões!: \n" + ex);
        }
        return permissoes;
    }

    public void inserirPermissoesPadrao() {
        String SQL = "INSERT INTO Permissao(nome, admin, diret, coord, func, aluno) VALUES \n"
                + "	('Cadastrar Geral', false, false, false, false, false),\n"
                + "	('Cadastrar Usuários', false, false, false, false, false),\n"
                + "	('Cadastrar Funcionários', false, false, false, false, false),\n"
                + "	('Cadastrar Cursos', false, false, false, false, false),\n"
                + "	('Cadastrar Disciplinas', false, false, false, false, false),\n"
                + "	('Cadastrar Alunos', false, false, false, false, false),\n"
                + "	('Gerenciar Alunos', false, false, false, false, false),\n"
                + "	('Gerenciar Matricular', false, false, false, false, false),\n"
                + "	('Gerenciar Matricular Aluno no Curso', false, false, false, false, false),\n"
                + "	('Gerenciar Matricular Aluno nas Disciplinas', false, false, false, false, false),\n"
                + "	('Gerenciar Aprovação', false, false, false, false, false),\n"
                + "	('Gerenciar Histórico Acadêmico', false, false, false, false, false),\n"
                + "	('Relatórios Geral', false, false, false, false, false),\n"
                + "	('Relatórios Relação de Alunos', false, false, false, false, false),\n"
                + "	('Relatórios Relação de Alunos Matriculados', false, false, false, false, false),\n"
                + "	('Relatórios Relação de Alunos Poderão Colar Grau', false, false, false, false, false),\n"
                + "	('Relatórios Relação de Alunos Podem Colar Grau', false, false, false, false, false),\n"
                + "	('Relatórios Relação de Funcionários', false, false, false, false, false),\n"
                + "	('Relatórios Relação de Funcionários Coordenadores', false, false, false, false, false),\n"
                + "	('Relatórios Relação de Funcionários Outros', false, false, false, false, false),\n"
                + "	('Relatórios Outros Geral', false, false, false, false, false)";
        try (PreparedStatement pstm = BD.getConexao().prepareStatement(SQL)) {
            pstm.execute();

            BD.getConexao().close();
            pstm.close();
            System.out.println("Permissões padrão inseridas com sucesso!");
        } catch (Exception ex) {
            Exibir.Mensagem("Erro ao inserir permissões padrão: " + ex);
        }
    }

    public void alterarPermissao(String nomeAcesso, String omeAcessAnterior) {

        String SQL = "UPDATE permissao SET nome = ? WHERE nome = ?";
        try (PreparedStatement pstm = BD.getConexao().prepareStatement(SQL)) {
            pstm.setString(1, nomeAcesso);
            pstm.setString(2, omeAcessAnterior);
            pstm.executeUpdate();

            pstm.close();
            BD.getConexao().close();
            System.out.println("Permissão Alterada!");
        } catch (Exception ex) {
            Exibir.Mensagem("Erro ao Alterar Permissão!:\n" + ex);
        }
    }

    public void alterarPermissaoUsr(String usr, boolean situacao, String nomeAcesso) {

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
            Exibir.Mensagem("Erro ao Alterar Permissão!:\n" + ex);
        }
    }

    public void apagarPermissao(String nomeAcesso) {

        String SQL = "DELETE FROM permissao WHERE nome = (?)";
        try (PreparedStatement pstm = BD.getConexao().prepareStatement(SQL)) {
            pstm.setString(1, nomeAcesso);
            pstm.executeUpdate();

            pstm.close();
            BD.getConexao().close();
            System.out.println("Permissão Apagada! ");
        } catch (Exception ex) {
            Exibir.Mensagem("Erro ao Apagar Permissão!:\n" + ex);
        }
    }
}
