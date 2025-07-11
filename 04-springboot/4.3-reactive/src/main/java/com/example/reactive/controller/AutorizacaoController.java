package com.example.reactive.controller;

import com.example.reactive.dto.DocAutorizacaoDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Simula um endpoint de uma API REST externa que realiza a autorização de documentos fiscais.
 * Assim, não precisamos de um servidor externo de fato para mostrar o funcionamento de
 * chamadas reativas com o WebClient.
 * @author Manoel Campos
 */
@RestController
@RequestMapping("/autorizacao")
public class AutorizacaoController {
    /**
     * Este endpoint simula a autorização de um documento fiscal que demora.
     */
     @GetMapping
     public ResponseEntity<DocAutorizacaoDTO> autorizarDocumento(
         @RequestParam long idCliente, @RequestParam long idServico)
     {
         sleep(); // Simula uma demora para autorizar o documento
         final var doc = new DocAutorizacaoDTO(idCliente, idServico);
         return ResponseEntity.ok(doc);
     }

    private static void sleep() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {/**/}
    }
}
