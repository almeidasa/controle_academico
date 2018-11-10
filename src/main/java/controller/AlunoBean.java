package controller;

import Util.Exibir;
import Util.Obter;
import entities.Aluno;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
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

    private String cpf;
    private String nome;
    private String data_nascimento;
    private String sexo;
    private int cod_foto;
    private String email;
    private String endereco;
    private String telefone;
    private Part file;
    private boolean editar;
    private String fotoUsuario = "resources/img/usrFoto.jpg";

    public AlunoBean() {
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
        return ("cadastrarAluno");
    }

    private void obter() {

    }

    public void add() {

    }

    public void iniciaEditar(Aluno lista) {

    }

    public void alterar() {

    }

    public void remover(Aluno lista) {

    }

    //Getters e Seters
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    public int getCod_foto() {
        return cod_foto;
    }

    public void setCod_foto(int cod_foto) {
        this.cod_foto = cod_foto;
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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Part getFile() {
        return file;
    }

    public void setFile(Part file) {
        this.file = file;
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
