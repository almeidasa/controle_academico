package DAO;

import Util.Exibir;
import Util.Formatar;
import controller.LoginBean;
import entities.Usuarios;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Alexandre Almeida
 */
public class UsuariosDAO {

    public void inserirUsuario(Usuarios usuarios) {

        String SQL = "INSERT INTO usuarios(login, senha, tipo, situacao, data_cad) VALUES (?, md5(?), ?, ?, '" + new Date() + "')";
        try (PreparedStatement pstm = BD.getConexao().prepareStatement(SQL)) {
            pstm.setString(1, usuarios.getLogin());
            pstm.setString(2, usuarios.getSenha());
            pstm.setString(3, usuarios.getTipo());
            pstm.setBoolean(4, Boolean.parseBoolean(usuarios.getSituacao()));
            pstm.execute();

            BD.getConexao().close();
            pstm.close();
            System.out.println("Inserido com sucesso!");
        } catch (Exception ex) {
            Exibir.Mensagem("Erro ao inserir usuário: " + ex);
        }
    }

    public ArrayList<Usuarios> obterUsuarios() {

        ArrayList<Usuarios> usuarios = new ArrayList<>();

        String SQL = "SELECT id_user, login, senha, tipo, situacao, data_cad FROM usuarios ORDER BY login ASC";
        try (PreparedStatement pstm = BD.getConexao().prepareStatement(SQL)) {

            try (ResultSet rs = pstm.executeQuery()) {
                while (rs.next()) {
                    Usuarios usr = new Usuarios(
                            rs.getInt("id_user"),
                            rs.getString("login"),
                            rs.getString("senha").equals("e8d95a51f3af4a3b134bf6bb680a213a") ? "Padrão" : "Privada",
                            rs.getString("tipo"),
                            rs.getBoolean("situacao") ? "Ativo" : "Inativo",
                            Formatar.data(rs.getDate("data_cad"), "dd/MM/yyyy")
                    );
                    usuarios.add(usr);
                }
                pstm.close();
            }
            System.out.println("Usuários obtidos com sucesso!");
        } catch (Exception ex) {
            Exibir.Mensagem("Erro ao obter usuários!: \n" + ex);
        }
        return usuarios;
    }

    public void alterarUsuario(Usuarios usuarios) {
        String SQL;
        if (!usuarios.getSenha().equals("")) {
            SQL = "UPDATE usuarios SET login = (?), senha = md5(?), tipo = (?), situacao = (?) WHERE id_user = (?)";
        } else {
            SQL = "UPDATE usuarios SET login = (?), tipo = (?), situacao = (?) WHERE id_user = (?)";
        }

        try (PreparedStatement pstm = BD.getConexao().prepareStatement(SQL)) {
            pstm.setString(1, usuarios.getLogin());
            if (!usuarios.getSenha().equals("")) {
                pstm.setString(2, usuarios.getSenha());
                pstm.setString(3, usuarios.getTipo());
                pstm.setBoolean(4, Boolean.parseBoolean(usuarios.getSituacao()));
                pstm.setInt(5, usuarios.getId_user());
            } else {
                pstm.setString(2, usuarios.getTipo());
                pstm.setBoolean(3, Boolean.parseBoolean(usuarios.getSituacao()));
                pstm.setInt(4, usuarios.getId_user());
            }
            pstm.executeUpdate();

            pstm.close();
            BD.getConexao().close();
            System.out.println("Alteração efetuada!");
        } catch (Exception ex) {
            Exibir.Mensagem("Erro ao Alterar Usuário!:\n" + ex);
        }
    }

    public void apagarUsuario(int id_user) {

        String SQL = "DELETE FROM usuarios WHERE id_user = (?)";
        try (PreparedStatement pstm = BD.getConexao().prepareStatement(SQL)) {
            pstm.setInt(1, id_user);
            pstm.executeUpdate();

            pstm.close();
            BD.getConexao().close();
            System.out.println("Usuário Apagado! ");
        } catch (Exception ex) {
            Exibir.Mensagem("Erro ao Apagar Usuário!:\n" + ex);
        }
    }

    public boolean verificaUsuarioSenha(String usuario, String senha) {

        boolean result = false;
        String SQL = "SELECT login, senha, situacao FROM usuarios WHERE UPPER(login) = UPPER(?) AND senha = md5(?)";
        try (PreparedStatement pstm = BD.getConexao().prepareStatement(SQL)) {
            pstm.setString(1, usuario);
            pstm.setString(2, senha);

            try (ResultSet rs = pstm.executeQuery()) {

                if (rs.next()) {
                    if (!rs.getBoolean("situacao")) {
                        Exibir.Mensagem("Usuário desabilitado contate o administrador do sistema!");
                        return false;
                    }
                    result = true;
                    pstm.close();
                    BD.getConexao().close();
                } else {
                    BD.getConexao().close();
                    result = false;
                    Exibir.Mensagem("Usuário ou senha inválidos!");
                }
                pstm.close();
                rs.close();
            }
        } catch (Exception ex) {
            Exibir.Mensagem("Erro ao Verificar Usuário e Senha!: \n" + ex);
        }
        return result;
    }

    public boolean verificaUsuarioEmail(String usuario, String email) {

        String SQL = "";
        boolean result = false;
        if (usuario.matches("[0-9]+")) {
            SQL = "SELECT m.matricula, a.email, (SELECT situacao FROM usuarios WHERE login = ?) FROM aluno a INNER JOIN MatriculaCurso m ON(m.fk_Aluno_cpf = a.cpf) WHERE m.matricula = ? AND UPPER(a.email) = UPPER(?)";
        } else {
            SQL = "SELECT u.login, f.email, u.situacao FROM usuarios u INNER JOIN funcionario f ON(f.fk_usuarios_id_user = u.id_user) WHERE UPPER(login) = UPPER(?) AND UPPER(email) = UPPER(?)";
        }

        try (PreparedStatement pstm = BD.getConexao().prepareStatement(SQL)) {

            if (usuario.matches("[0-9]+")) {
                pstm.setString(1, usuario);
                pstm.setInt(2, Integer.parseInt(usuario));
                pstm.setString(3, email);
            } else {
                pstm.setString(1, usuario);
                pstm.setString(2, email);
            }

            ResultSet rs = pstm.executeQuery();

            if (rs.next()) {
                if (!rs.getBoolean("situacao")) {
                    Exibir.Mensagem("Usuário desabilitado contate o administrador do sistema!");
                    return false;
                }
                result = true;
                pstm.close();
                BD.getConexao().close();
                rs.close();
            } else {
                pstm.close();
                BD.getConexao().close();
                result = false;
                rs.close();
                Exibir.Mensagem("Usuário ou senha inválidos!");
            }
        } catch (Exception ex) {
            Exibir.Mensagem("Erro ao Verificar Usuário e Senha!: \n" + ex);
        }
        System.out.println(result);
        return result;
    }

    public void obterLogin(LoginBean login) {

        String SQL = "SELECT login, tipo, id_user FROM usuarios WHERE UPPER(login) = UPPER(?)";

        try (PreparedStatement pstm = BD.getConexao().prepareStatement(SQL)) {
            pstm.setString(1, login.getUsuario());
            try (ResultSet rs = pstm.executeQuery()) {
                while (rs.next()) {
                    login.setNomeUsr(rs.getString("login").matches("[0-9]+") ? nomeAluno(rs.getString("login"), login) : rs.getString("login"));
                    login.setTipoUsr(rs.getString("tipo"));
                    LoginBean.tipo = rs.getString("tipo");
                    LoginBean.id_logado = rs.getInt("id_user");
                }

                pstm.close();
                BD.getConexao().close();
            }
            System.out.println("Login obtido com sucesso!");
        } catch (Exception ex) {
            Exibir.Mensagem("Erro ao Obter Login do Banco de Dados!: \n" + ex);
        }
    }
    
    public String nomeAluno(String login, LoginBean l){
        String nomeAl = "";
        String SQL = "SELECT a.nome, a.cpf FROM aluno a INNER JOIN MatriculaCurso m ON(m.fk_Aluno_cpf = a.cpf) WHERE m.matricula = " + login;
        
        try (PreparedStatement pstm = BD.getConexao().prepareStatement(SQL)) {
            try (ResultSet rs = pstm.executeQuery()) {
                while (rs.next()) {
                    nomeAl = rs.getString("nome");
                    l.setCpfAluno(rs.getString("cpf"));
                }

                pstm.close();
                BD.getConexao().close();
            }
            System.out.println("Nome do aluno obtido com sucesso!");
        } catch (Exception ex) {
            Exibir.Mensagem("Erro ao obter nome do aluno!: \n" + ex);
        }
        String primeiroNome[] = nomeAl.split(" ");
        return primeiroNome[0];
    }

    public void alterarSenha(String login, String novaSenha) {

        String SQL = "UPDATE usuarios SET senha = md5(?) WHERE UPPER(login) = UPPER(?)";
        try (PreparedStatement pstm = BD.getConexao().prepareStatement(SQL)) {
            pstm.setString(1, novaSenha);
            pstm.setString(2, login);
            pstm.executeUpdate();

            pstm.close();
            BD.getConexao().close();
            System.out.println("Alteração efetuada!");
        } catch (Exception ex) {
            Exibir.Mensagem("Erro ao Alterar Usuário!:\n" + ex);
        }
    }
}
