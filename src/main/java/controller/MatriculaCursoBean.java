package controller;

import DAO.AlunoDAO;
import DAO.CursoDAO;
import DAO.MatriculaCursoDAO;
import DAO.UsuariosDAO;
import Util.Formatar;
import Util.Gerar;
import Util.JavaMailApp;
import entities.Aluno;
import entities.Curso;
import entities.MatriculaCurso;
import entities.Usuarios;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * @Autor Alexandre Almeida
 * @Data 13/11/2018
 */
@ManagedBean
@ViewScoped
public class MatriculaCursoBean {

    private int matricula;
    private String situacao;
    private String data_inicio;
    private String duracao_curso = "3";
    private String fk_Aluno;
    private int fk_Curso_cod;

    private ArrayList<MatriculaCurso> matriculaCurso;
    private ArrayList<Aluno> alunos;
    private ArrayList<Curso> curso;
    private Map<String, String> ItensBoxAlunos;
    private Map<Integer, String> ItensBoxCurso;

    private String botao = "Incluir";
    private String icone = "plus-circle";

    public MatriculaCursoBean() {
        this.alunos = new ArrayList<>();
        this.curso = new ArrayList<>();
        this.matriculaCurso = new ArrayList<>();
        obter();
        setBoxAlunos();
        setBoxCurso();
    }

    public MatriculaCursoBean(int matricula, String situacao, String data_inicio, String duracao_curso, String fk_Aluno, int fk_Curso_cod) {
        this.matricula = matricula;
        this.situacao = situacao;
        this.data_inicio = data_inicio;
        this.duracao_curso = duracao_curso;
        this.fk_Aluno = fk_Aluno;
        this.fk_Curso_cod = fk_Curso_cod;
    }

    private void obter() {
        matriculaCurso.clear();
        MatriculaCursoDAO matCursoDAO = new MatriculaCursoDAO();
        matriculaCurso = matCursoDAO.obterMatriculaCurso();
    }

    public void limpaTela() {
        matricula = 0;
        situacao = "";
        data_inicio = "";
        duracao_curso = "";
        fk_Curso_cod = 0;
        fk_Aluno = "A";
    }

    public void criarUsuario(int mat_criada, String cpf) {
        String senha = Gerar.Senha();
        String email = new AlunoDAO().obterEmail(cpf);
        Usuarios us = new Usuarios();
        us.setLogin(Integer.toString(mat_criada));
        us.setSenha(senha);
        us.setTipo("Aluno");
        us.setSituacao("true");
        new UsuariosDAO().inserirUsuario(us);
        new JavaMailApp().enviarEmail(email, senha);
    }

    public void add() {
        if (botao.equals("Incluir")) {
            System.out.println("Incluir");
            MatriculaCursoDAO matCursoDAO = new MatriculaCursoDAO();
            int mat_criada = Integer.parseInt(fk_Curso_cod + fk_Aluno.replace(".", "").replace("-", "").substring(0, 5));
            MatriculaCurso matCurso = new MatriculaCurso(mat_criada, situacao, data_inicio, duracao_curso, fk_Aluno, fk_Curso_cod);

            criarUsuario(mat_criada, fk_Aluno);
            
            matCursoDAO.inserirMatriculaCurso(matCurso);
            limpaTela();
            obter();
        } else {
            MatriculaCursoDAO matCursoDAO = new MatriculaCursoDAO();
            MatriculaCurso matCurso = new MatriculaCurso(matricula, situacao, data_inicio, duracao_curso, fk_Aluno, fk_Curso_cod);
            matCursoDAO.editarMatriculaCurso(matCurso);
            limpaTela();
            obter();
            botao = "Incluir";
            icone = "plus-circle";
        }
    }

    public String cancelar() {
        limpaTela();
        return "matriculaCurso.xhtml";
    }

    public void editar(MatriculaCurso mc) {
        matricula = mc.getMatricula();
        situacao = mc.getSituacao();
        data_inicio = Formatar.Data(mc.getData_inicio(), "dd/MM/yyyy", "yyyy-MM-dd");
        duracao_curso = mc.getDuracao_curso();
        fk_Aluno = mc.getFk_Aluno();
        fk_Curso_cod = mc.getFk_Curso_cod();

        botao = "Alterar";
        icone = "fa-refresh";
    }

    public void remover(MatriculaCurso matcurso) {
        MatriculaCursoDAO matCursoDAO = new MatriculaCursoDAO();
        matCursoDAO.removerMatriculaCurso(matcurso);
        obter();
    }

    private void setBoxAlunos() {
        ItensBoxAlunos = new LinkedHashMap<>();
        AlunoDAO al = new AlunoDAO();
        alunos = al.obterAlunos();

        for (Aluno aluno : alunos) {
            ItensBoxAlunos.put("A", "Selecione um Aluno");
            ItensBoxAlunos.put(aluno.getCpf(), aluno.getNome());
        }
    }

    private void setBoxCurso() {
        ItensBoxCurso = new LinkedHashMap<>();
        CursoDAO c = new CursoDAO();
        curso = c.obterCursos();

        for (Curso cursos : curso) {
            ItensBoxCurso.put(0, "Selecione um Curso");
            ItensBoxCurso.put(cursos.getCod(), cursos.getNome_curso());
        }
    }

    //Getters e Seters
    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public String getData_inicio() {
        return data_inicio;
    }

    public void setData_inicio(String data_inicio) {
        this.data_inicio = data_inicio;
    }

    public String getDuracao_curso() {
        return duracao_curso;
    }

    public void setDuracao_curso(String duracao_curso) {
        this.duracao_curso = duracao_curso;
    }

    public String getFk_Aluno() {
        return fk_Aluno;
    }

    public void setFk_Aluno(String fk_Aluno) {
        this.fk_Aluno = fk_Aluno;
    }

    public int getFk_Curso_cod() {
        return fk_Curso_cod;
    }

    public void setFk_Curso_cod(int fk_Curso_cod) {
        this.fk_Curso_cod = fk_Curso_cod;
    }

    public ArrayList<MatriculaCurso> getMatriculaCurso() {
        return matriculaCurso;
    }

    public void setMatriculaCurso(ArrayList<MatriculaCurso> matriculaCurso) {
        this.matriculaCurso = matriculaCurso;
    }

    public ArrayList<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(ArrayList<Aluno> alunos) {
        this.alunos = alunos;
    }

    public ArrayList<Curso> getCurso() {
        return curso;
    }

    public void setCurso(ArrayList<Curso> curso) {
        this.curso = curso;
    }

    public Map<String, String> getItensBoxAlunos() {
        return ItensBoxAlunos;
    }

    public void setItensBoxAlunos(Map<String, String> ItensBoxAlunos) {
        this.ItensBoxAlunos = ItensBoxAlunos;
    }

    public Map<Integer, String> getItensBoxCurso() {
        return ItensBoxCurso;
    }

    public void setItensBoxCurso(Map<Integer, String> ItensBoxCurso) {
        this.ItensBoxCurso = ItensBoxCurso;
    }

    public String getBotao() {
        return botao;
    }

    public void setBotao(String botao) {
        this.botao = botao;
    }

    public String getIcone() {
        return icone;
    }

    public void setIcone(String icone) {
        this.icone = icone;
    }

}
