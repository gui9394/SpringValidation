package com.gui9394.apivalidation.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.gui9394.apivalidation.enumeration.PersonStatus;
import com.gui9394.apivalidation.model.Person;
import com.gui9394.apivalidation.validation.EnumOfAny;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.br.CPF;

public class PersonDto {

    @Range(message = "O campo nome tem ser > 0")
    private Long id;

    @Length(min = 10, max = 30, message = "O campo nome tem que ter entre 10 e 30 caracteres")
    @NotEmpty(message = "O campo nome nao pode ser vazio")
    private String name;

    @CPF(message = "CPF invalido")
    @NotEmpty(message = "O campo CPF nao pode ser vazio")
    private String cpf;

    @NotNull(message = "O campo status nao pode ser vazio")
    private LocalDate dateBirth;

    @EnumOfAny(anyOf = { "ENABLED" })
    @NotNull(message = "O campo status nao pode ser vazio")
    private PersonStatus status;

    public PersonDto(Long id, String name, String cpf, LocalDate dateBirth, PersonStatus status) {
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

    public LocalDate getDateBirth() {
        return this.dateBirth;
    }

    public void setDateBirth(LocalDate dateBirth) {
        this.dateBirth = dateBirth;
    }

    public PersonStatus getStatus() {
        return this.status;
    }

    public void setStatus(PersonStatus status) {
        this.status = status;
    }

    public Person toEntity() {
        return new Person(this.id, this.name, this.cpf, this.dateBirth, this.status);
    }

    public static PersonDto fromEntity(Person entity) {
        return new PersonDto(entity.getIdPerson(), entity.getName(), entity.getCpf(), entity.getDateBirth(),
                entity.getStatus());
    }
}
