package com.gui9394.apivalidation.services;

import java.util.List;
import java.util.stream.Collectors;

import com.gui9394.apivalidation.dtos.PersonDto;
import com.gui9394.apivalidation.errors.ConflictException;
import com.gui9394.apivalidation.models.Person;
import com.gui9394.apivalidation.repositories.PersonRepository;

import org.springframework.stereotype.Service;

@Service
public class PersonService {

    PersonRepository pessoaRepository;

    PersonService(PersonRepository personRepository) {
        this.pessoaRepository = personRepository;
    }

    public PersonDto create(PersonDto pessoaDTO) {

        try {
            return PersonDto.fromEntity(this.pessoaRepository.save(pessoaDTO.toEntity()));
        } catch (Exception e) {
            throw new ConflictException("CPF ja cadastrado");
        }

    }

    public List<PersonDto> findAll() {
        return this.pessoaRepository.findAll().stream().map((Person pessoa) -> {
            return PersonDto.fromEntity(pessoa);
        }).collect(Collectors.toList());
    }

}