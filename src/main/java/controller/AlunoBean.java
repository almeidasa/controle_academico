package controller;

import DAO.AlunoDAO;
import Util.Exibir;
import Util.Obter;
import entities.Aluno;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
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
    private int fk_foto_id_foto;
    private Part file;
    private ArrayList<Aluno> alunos = new ArrayList<>();
    Aluno aluno;
    AlunoDAO alunoDao;
    private boolean editar;
    private String fotoUsuario = "resources/img/usrFoto.jpg";

    public AlunoBean() {
        aluno = new Aluno();
        alunoDao = new AlunoDAO();
        obter();
    }

    public void save() {
        try {
            Path path = Paths.get(Obter.CaminhoArquivo("usrFotoTemp.jpg"));
            Exibir.Mensagem(path.toString());
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            fotoUsuario = "resources/img/usrFotoTemp.jpg";
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
        fk_foto_id_foto = 0;
        return ("cadastrarAluno");
    }

    private void obter() {
        alunos = alunoDao.obterAlunos();
    }

    public void add() {
        aluno = new Aluno(nome, cpf, data_nascimento, sexo, telefone, email, endereco, fk_foto_id_foto);
        alunoDao.inserirAluno(aluno);
        obter();
    }

    public void iniciaEditar(Aluno lista) {

    }

    public void alterar() {

    }

    public void remover(Aluno lista) {

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

    public int getFk_foto_id_foto() {
        return fk_foto_id_foto;
    }

    public void setFk_foto_id_foto(int fk_foto_id_foto) {
        this.fk_foto_id_foto = fk_foto_id_foto;
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