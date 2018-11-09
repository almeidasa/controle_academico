package controller;

import Util.Exibir;
import entities.Aluno;
import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Scanner;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
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
    private String endereco;
    private String telefone;
    private Part file;

    public AlunoBean() {
    }

    private String fileContent;
 
  public void save() {
    try {
      fileContent = new Scanner(file.getInputStream())
          .useDelimiter("/img").next();
    } catch (Exception e) {
      // Error handling
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
}
