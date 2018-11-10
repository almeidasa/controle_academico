package controller;

import Util.Exibir;
import Util.Obter;
import entities.Aluno;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import sun.misc.IOUtils;

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
    private String endereco;
    private String telefone;
    private Part file;
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

    public String getFotoUsuario() {
        return fotoUsuario;
    }

    public void setFotoUsuario(String fotoUsuario) {
        this.fotoUsuario = fotoUsuario;
    }
}
