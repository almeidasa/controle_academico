package entities;

import java.sql.Date;

/**
 *
 * @author Alexandre Almeida
 */
public class Aluno {

    private String cpf;
    private String nome;
    private Date data_nascimento;
    private String sexo;
    private int cod_foto;
    private String endereco;
    private String telefone;

    public Aluno(String cpf, String nome, Date data_nascimento, String sexo, int cod_foto, String endereco, String telefone) {
        this.cpf = cpf;
        this.nome = nome;
        this.data_nascimento = data_nascimento;
        this.sexo = sexo;
        this.cod_foto = cod_foto;
        this.endereco = endereco;
        this.telefone = telefone;
    }

    public Aluno() {
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

    public Date getData_nascimento() {
        return data_nascimento;
    }

    public void setData_nascimento(Date data_nascimento) {
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

}
