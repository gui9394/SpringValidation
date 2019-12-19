package com.gui9394.apivalidation.services;

import java.util.List;
import java.util.stream.Collectors;

import com.gui9394.apivalidation.dtos.PessoaDTO;
import com.gui9394.apivalidation.models.Pessoa;
import com.gui9394.apivalidation.repositories.PessoaRepository;

import org.springframework.stereotype.Service;

@Service
public class PessoaService {

    PessoaRepository pessoaRepository;

    PessoaService(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    public PessoaDTO create(PessoaDTO pessoaDTO) {
        return this.pessoaRepository.save(pessoaDTO.toEntity()).toDTO();
    }

    public List<PessoaDTO> findAll() {
        return this.pessoaRepository.findAll().stream()
            .map((Pessoa item) -> { return item.toDTO(); })
            .collect(Collectors.toList());
    }

}