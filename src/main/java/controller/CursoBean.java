
package controller;

import DAO.CursoDAO;
import DAO.FuncionarioDAO;
import entities.Curso;
import entities.Funcionario;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author l-s-t
 */
@ManagedBean
@RequestScoped
public class CursoBean {

    private int cod;
    private String nome_curso;
    private int fk_Funcionario_id;
    private ArrayList<Curso> cursos = new ArrayList<>();
    private ArrayList<Funcionario> func = new ArrayList<>();
    private Map<Integer, String> ItensBoxFuncionarios;
    
    public CursoBean() {
        obter();
        setBoxFuncionarios();
    }
    
    private void obter(){
        CursoDAO us = new CursoDAO();
        cursos = us.obterCursos();        
    }
    
    public void add() {
        CursoDAO novo = new CursoDAO();
        Curso cs = new Curso(cod, nome_curso,fk_Funcionario_id);
        novo.inserirCurso(cs);
        obter();
    }
    
    private void setBoxFuncionarios() {
        ItensBoxFuncionarios = new LinkedHashMap<>();
        FuncionarioDAO us = new FuncionarioDAO();
        func = us.obterFuncionarios();

        for (Funcionario funcionarios : func) {
            ItensBoxFuncionarios.put(0, "Selecione um Curso");
            ItensBoxFuncionarios.put(funcionarios.getId(), funcionarios.getNome());
            System.out.println(funcionarios.getId());
        }
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public String getNome_curso() {
        return nome_curso;
    }

    public void setNome_curso(String nome_curso) {
        this.nome_curso = nome_curso;
    }

    public int getFk_Funcionario_id() {
        return fk_Funcionario_id;
    }

    public void setFk_Funcionario_id(int fk_Funcionario_id) {
        this.fk_Funcionario_id = fk_Funcionario_id;
    }

    public ArrayList<Curso> getCursos() {
        return cursos;
    }

    public void setCursos(ArrayList<Curso> cursos) {
        this.cursos = cursos;
    }

    public ArrayList<Funcionario> getFunc() {
        return func;
    }

    public void setFunc(ArrayList<Funcionario> func) {
        this.func = func;
    }

    public Map<Integer, String> getItensBoxFuncionarios() {
        return ItensBoxFuncionarios;
    }

    public void setItensBoxFuncionarios(Map<Integer, String> ItensBoxFuncionarios) {
        this.ItensBoxFuncionarios = ItensBoxFuncionarios;
    }
    
    
    
}
