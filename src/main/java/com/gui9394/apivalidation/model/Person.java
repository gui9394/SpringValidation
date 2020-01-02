package com.gui9394.apivalidation.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.gui9394.apivalidation.enumeration.PersonStatus;

@Entity
public class Person implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long idPerson;

    private String name;

    @Column(unique = true)
    private String cpf;

    private LocalDate dateBirth;

    @Enumerated(EnumType.STRING)
    private PersonStatus status;

    public Person() {
        this(0l, "", "", LocalDate.now(), PersonStatus.ENABLED);
    }
    
    public Person(Long idPerson, String name, String cpf, LocalDate dateBirth, PersonStatus status) {
        this.idPerson = idPerson;
        this.name = name;
        this.cpf = cpf;
        this.dateBirth = dateBirth;
        this.status = status;
    }

    public Long getIdPerson() {
        return this.idPerson;
    }

    public void setIdPerson(Long idPerson) {
        this.idPerson = idPerson;
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

}