package com.gui9394.apivalidation.controllers;

import java.util.List;

import javax.validation.Valid;

import com.gui9394.apivalidation.dtos.PersonDto;
import com.gui9394.apivalidation.services.PersonService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {

    private PersonService pessoaService;

    PersonController(PersonService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @PostMapping()
    public ResponseEntity<PersonDto> create(@Valid @RequestBody PersonDto pessoa) {
        PersonDto bodyPessoa = this.pessoaService.create(pessoa);

        return ResponseEntity.ok(bodyPessoa);
    }

    @GetMapping()
    public ResponseEntity<List<PersonDto>> findAll() {
        List<PersonDto> bodyListaPessoa = this.pessoaService.findAll();

        return ResponseEntity.ok(bodyListaPessoa);
    }

}
