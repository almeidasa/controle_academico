package controller;

import DAO.AlunoDAO;
import Util.Exibir;
import Util.Formatar;
import Util.Obter;
import entities.Aluno;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.servlet.http.Part;

/**
 * @Autor Winder Rezende
 * @Data 09/11/2018, 12:00:15
 */
@ManagedBean
@ViewScoped
public class AlunoBean {

    private String nome;
    private String cpf;
    private String data_nascimento;
    private String sexo;
    private String telefone;
    private String email;
    private String endereco;
    private Part file;
    private ArrayList<Aluno> alunos = new ArrayList<>();
    Aluno aluno;
    AlunoDAO alunoDao;
    private String tempCpf;
    private boolean editar;
    private String fotoUsuario = "resources/img/usrFoto.jpg";
    private InputStream fotoStream = null;

    public AlunoBean() {
        aluno = new Aluno();
        alunoDao = new AlunoDAO();
        obter();
    }

    public void carregarFoto() {
        try {
            if (file != null) {
                fotoStream = file.getInputStream();
                Path path = Paths.get(Obter.CaminhoArquivo("usrFotoTemp.jpg"));
                Exibir.Mensagem(path.toString());
                Files.copy(fotoStream, path, StandardCopyOption.REPLACE_EXISTING);
                fotoUsuario = "resources/img/usrFotoTemp.jpg";
            }
            else{
                Exibir.Mensagem("Selecione a foto primeiro!");
            }
        } catch (Exception e) {
            Exibir.Mensagem("Erro ao Carregar Foto: " + e);
        }
    }

    public String cancelar() {
        nome = null;
        cpf = null;
        data_nascimento = null;
        sexo = null;
        telefone = null;
        email = null;
        endereco = null;
        fotoStream = null;
        return ("cadastrarAluno");
    }

    private void obter() {
        alunos = alunoDao.obterAlunos();
    }

    public void add() {
        aluno = new Aluno(nome, cpf, data_nascimento, sexo, telefone, email, endereco);
        alunoDao.inserirAluno(aluno, fotoStream);
        obter();
    }

    public void iniciaEditar(Aluno lista) {
        editar = true;
        nome = lista.getNome();
        cpf = lista.getCpf();
        tempCpf = lista.getCpf();
        data_nascimento = Formatar.Data(lista.getData_nascimento(), "dd/MM/yyyy", "yyyy-MM-dd");
        sexo = lista.getSexo();
        telefone = lista.getTelefone();
        email = lista.getEmail();
        endereco = lista.getEndereco();
    }

    public void alterar() {
        aluno = new Aluno(nome, cpf, data_nascimento, sexo, telefone, email, endereco);
        alunoDao.alterarUsuario(aluno, tempCpf);
        editar = false;
        obter();
        cancelar();
    }

    public void remover(Aluno lista) {
        alunoDao.apagarUsuario(lista.getCpf());
        obter();
    }

    //Getters e Seters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getData_nascimento() {
        return data_nascimento;
    }

    public void setData_nascimento(String data_nascimento) {
        this.data_nascimento = data_nascimento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Part getFile() {
        return file;
    }

    public void setFile(Part file) {
        this.file = file;
    }

    public ArrayList<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(ArrayList<Aluno> alunos) {
        this.alunos = alunos;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public AlunoDAO getAlunoDao() {
        return alunoDao;
    }

    public void setAlunoDao(AlunoDAO alunoDao) {
        this.alunoDao = alunoDao;
    }

    public boolean isEditar() {
        return editar;
    }

    public void setEditar(boolean editar) {
        this.editar = editar;
    }

    public String getFotoUsuario() {
        return fotoUsuario;
    }

    public void setFotoUsuario(String fotoUsuario) {
        this.fotoUsuario = fotoUsuario;
    }
}
