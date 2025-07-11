package com.example.reactive.controller;

import com.example.reactive.dto.ProtocoloDTO;
import com.example.reactive.dto.RequisicaoDTO;
import com.example.reactive.model.DocFiscal;
import com.example.reactive.service.DocFiscalService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * @author Manoel Campos
 */
@RestController
@RequestMapping("/docfiscal")
public class DocumentoFiscalController {
    private final DocFiscalService service;

    public DocumentoFiscalController(DocFiscalService service) {
        this.service = service;
    }

    @GetMapping("/{protocolo}")
    public ResponseEntity<DocFiscal> findByProtocolo(@PathVariable String protocolo) {
        return service.findByProtocolo(protocolo)
                      .map(ResponseEntity::ok)
                      .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/solicitar")
    public ResponseEntity<ProtocoloDTO> solicitar(@RequestBody RequisicaoDTO req){
        final var protocolo = UUID.randomUUID().toString();
        System.out.println("\nSolicitação recebida e em processamento. Aguarde... Protocolo: " + protocolo);
        service.realizarAutorizacaoAPIExterna(req.idCliente(), req.idServico(), protocolo);
        return ResponseEntity.accepted().body(new ProtocoloDTO(protocolo));
    }

    // findAll
    @GetMapping
    public ResponseEntity<List<DocFiscal>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }
}
