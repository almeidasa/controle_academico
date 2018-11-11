package entities;

import java.sql.Date;

/**
 *
 * @author Alexandre Almeida
 */
public class Aluno {

    private String nome;
    private String cpf;
    private String data_nascimento;
    private String sexo;
    private String telefone;
    private String email;
    private String endereco;
    private int fk_foto_id_foto;

    public Aluno() {
    }

    public Aluno(String nome, String cpf, String data_nascimento, String sexo, String telefone, String email, String endereco, int fk_foto_id_foto) {
        this.nome = nome;
        this.cpf = cpf;
        this.data_nascimento = data_nascimento;
        this.sexo = sexo;
        this.telefone = telefone;
        this.email = email;
        this.endereco = endereco;
        this.fk_foto_id_foto = fk_foto_id_foto;
    }

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
}
