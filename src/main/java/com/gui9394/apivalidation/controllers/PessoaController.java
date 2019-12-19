package com.gui9394.apivalidation.controllers;

import java.util.List;

import javax.validation.Valid;

import com.gui9394.apivalidation.dtos.PessoaDTO;
import com.gui9394.apivalidation.services.PessoaService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PessoaController {

    private PessoaService pessoaService;

    PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @PostMapping()
    public ResponseEntity<PessoaDTO> create(@Valid @RequestBody PessoaDTO pessoa) {
        PessoaDTO bodyPessoa = this.pessoaService.create(pessoa);

        return ResponseEntity.ok(bodyPessoa);
    }

    @GetMapping()
    public ResponseEntity<List<PessoaDTO>> findAll () {
        List<PessoaDTO> bodyListaPessoa = this.pessoaService.findAll();

        return ResponseEntity.ok(bodyListaPessoa);
    }

}