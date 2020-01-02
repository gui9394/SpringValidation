package com.gui9394.apivalidation.service;

import java.util.List;
import java.util.stream.Collectors;

import com.gui9394.apivalidation.dto.PersonDto;
import com.gui9394.apivalidation.error.ConflictException;
import com.gui9394.apivalidation.error.NotFoundException;
import com.gui9394.apivalidation.model.Person;
import com.gui9394.apivalidation.repository.PersonRepository;

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

    public PersonDto findOne(Long id) {
        try {
            return PersonDto.fromEntity(this.pessoaRepository.getOne(id));
        } catch (Exception e) {
            throw new NotFoundException("Registro não encontrado");
        }
    }

    public List<PersonDto> findAll() {
        return this.pessoaRepository.findAll().stream().map((Person pessoa) -> {
            return PersonDto.fromEntity(pessoa);
        }).collect(Collectors.toList());
    }

}