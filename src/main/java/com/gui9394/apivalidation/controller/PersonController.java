package com.gui9394.apivalidation.controller;

import java.util.List;

import javax.validation.Valid;

import com.gui9394.apivalidation.dto.PersonDto;
import com.gui9394.apivalidation.service.PersonService;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/person")
public class PersonController {

    private PersonService pessoaService;

    PersonController(PersonService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @PostMapping()
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public PersonDto create(@Valid @RequestBody PersonDto pessoa) {
        return this.pessoaService.create(pessoa);
    }

    @GetMapping("/{id}")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public PersonDto findOne(@PathVariable Long id) {
        return this.pessoaService.findOne(id);
    }

    @GetMapping()
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public List<PersonDto> findAll() {
        return this.pessoaService.findAll();
    }

}
