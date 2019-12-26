package com.gui9394.apivalidation.dtos;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.gui9394.apivalidation.enums.PersonStatus;
import com.gui9394.apivalidation.models.Person;
import com.gui9394.apivalidation.validation.DateValidation;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.br.CPF;

public class PersonDto implements Serializable {
    private static final long serialVersionUID = 1L;

    @Range(message = "O campo nome tem ser > 0")
    private Long id;

    @Length(min = 10, max = 30, message = "O campo nome tem que ter entre 10 e 30 caracteres")
    @NotEmpty(message = "O campo nome nao pode ser vazio")
    private String name;

    @CPF(message = "CPF invalido")
    @NotEmpty(message = "O campo CPF nao pode ser vazio")
    private String cpf;

    @DateValidation
    @NotEmpty(message = "O campo dataNascimento nao pode ser vazio")
    private String dateBirth;

    @Range(min = 100, max = 101)
    @NotNull(message = "O campo sexo nao pode ser vazio")
    private int status;

    public PersonDto(Long id, String name, String cpf, String dateBirth, int status) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.dateBirth = dateBirth;
        this.status = status;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return this.cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getDateBirth() {
        return this.dateBirth;
    }

    public void setDateBirth(String dateBirth) {
        this.dateBirth = dateBirth;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Person toEntity() {
        return new Person(this.id, this.name, this.cpf, this.dateBirth, PersonStatus.valueOf(this.status));
    }

    public static PersonDto fromEntity(Person entity) {
        return new PersonDto(entity.getIdPerson(), entity.getName(), entity.getCpf(), entity.getDateBirth(),
                entity.getStatus().getValue());
    }
}