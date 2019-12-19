package com.gui9394.apivalidation.dtos;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.gui9394.apivalidation.enums.Sexo;
import com.gui9394.apivalidation.models.Pessoa;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.br.CPF;

public class PessoaDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @Range(message = "O campo nome tem ser > 0")
    // @NotEmpty(message = "O campo nome id pode ser vazio")
    private Long id;

    @Length(min = 10, max = 30, message = "O campo nome tem que ter entre 10 e 30 caracteres")
    @NotEmpty(message = "O campo nome nao pode ser vazio")
    private String nome;

    @CPF(message = "CPF invalido")
    @NotEmpty(message = "O campo CPF nao pode ser vazio")
    private String cpf;

    @NotEmpty(message = "O campo dataNascimento nao pode ser vazio")
    private String dataNascimento;

    @NotNull(message = "O campo sexo nao pode ser vazio")
    private Sexo sexo;

    public PessoaDTO(Long idPessoa, String nome, String cpf, String dataNascimento, Sexo sexo) {
        this.id = idPessoa;
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.sexo = sexo;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return this.cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getDataNascimento() {
        return this.dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Sexo getSexo() {
        return this.sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public Pessoa toEntity() {
        return new Pessoa(this.id, this.nome, this.cpf, this.dataNascimento, this.sexo);
    }

    public static PessoaDTO fromEntity(Pessoa pessoa) {
        return new PessoaDTO(pessoa.getIdPessoa(), pessoa.getNome(), pessoa.getCpf(), pessoa.getDataNascimento(),
                pessoa.getSexo());
    }
}