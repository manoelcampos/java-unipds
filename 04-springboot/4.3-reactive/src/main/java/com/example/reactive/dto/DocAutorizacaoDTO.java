package com.example.reactive.dto;

import java.time.LocalDateTime;
import java.util.Random;

/**
 * @author Manoel Campos
 */
public record DocAutorizacaoDTO(long idCliente, long idServico, LocalDateTime dataHora, String chaveAutorizacao) {
    public DocAutorizacaoDTO(long idCliente, long idServico) {
        this(idCliente, idServico, LocalDateTime.now(), String.format("%x", new Random().nextLong()));
    }
}
