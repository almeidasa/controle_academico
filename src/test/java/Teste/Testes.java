package Teste;

import DAO.AlunoDAO;
import DAO.CursoDAO;
import DAO.DisciplinaDAO;
import DAO.FuncionarioDAO;
import DAO.MatriculaDisciplinaDAO;
import DAO.UsuariosDAO;
import entities.Aluno;
import entities.Curso;
import entities.Disciplina;
import entities.Funcionario;
import entities.MatriculaDisciplina;
import entities.Usuarios;
import java.sql.Date;

/**
 *
 * @author Alexandre Almeida
 */
public class Testes {
    public static void main(String[] args) {
//        UsuariosDAO us = new UsuariosDAO();
//        Usuarios user = new Usuarios();        
//        user.setLogin("Admin");
//        user.setSenha("1");
//        user.setTipo("admin");        
//        us.inserirUsuario(user);
//        
// 
        FuncionarioDAO f = new FuncionarioDAO();
        Funcionario func = new Funcionario();
        func.setNome("Administrador");
        func.setEmail("asa-008@hotmail.com");
        func.setTelefone("62982121212");
        func.setFk_Usuarios_id_user(3);
        f.inserirFuncionario(func);
//
//       
//        CursoDAO c = new CursoDAO();
//        Curso curso = new Curso();
//        curso.setCod(1);
//        curso.setNome_curso("Dev");
//        curso.setFk_Funcionario_id(2);
//        c.inserirCurso(curso);
//
//
//        Date d = new Date(27031991);
//        AlunoDAO a = new AlunoDAO();
//        Aluno al = new Aluno();
//        al.setCpf("03011498105");
//        al.setNome("Alexandre");
//        al.setData_nascimento(d);
//        al.setSexo("M");
//        al.setCod_foto(123456);
//        al.setEndereco("Rua aculá");
//        al.setTelefone("623333333");
//        a.inserirAluno(al);
//
//
//        DisciplinaDAO dis = new DisciplinaDAO();
//        Disciplina disc = new Disciplina();
//        disc.setCodigo("12345");
//        disc.setNome("GTI");
//        disc.setSituacao("Aberto");
//        disc.setFk_Curso_cod(1);
//        dis.inserirDisciplina(disc);
//        
//        MatriculaDisciplina md = new MatriculaDisciplina();
//        MatriculaDisciplinaDAO mdd = new MatriculaDisciplinaDAO();
//        md.setConceito("A");
//        md.setSemestre("2");
//        md.setAno(2018);
//        md.setFk_Aluno_cpf("03011498105");
        
    }
}
