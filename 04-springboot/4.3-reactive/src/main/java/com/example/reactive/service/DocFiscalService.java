package com.example.reactive.service;

import com.example.reactive.model.DocFiscal;
import com.example.reactive.repository.DocFiscalRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Optional;

/**
 * @author Manoel Campos
 */
@Service
public class DocFiscalService {
    private final DocFiscalRepository repository;
    private final WebClient webClient;

    public DocFiscalService(DocFiscalRepository repository) {
        this.repository = repository;
        this.webClient = WebClient.builder().baseUrl("http://localhost:8080").build();
    }

    public void realizarAutorizacaoAPIExterna(long idCliente, long idServico, String protocolo){
        final var endpoint = "/autorizacao?idCliente=%d&idServico=%d".formatted(idCliente, idServico);
        webClient.get()
                 .uri(endpoint)
                 .retrieve()
                 .bodyToMono(String.class)
                 .doOnNext(docFiscal -> salvarDocFiscal(docFiscal, protocolo))
                 .doOnError(throwable -> System.out.println("Erro ao consultar API externa: " + throwable.getMessage()))
                 .subscribe();
    }

    private void salvarDocFiscal(String documentoJson, String protocolo) {
        System.out.println("\nSolicitação atendida pela API externa. Protocolo: " + protocolo + "\n");
        final var docFiscal = new DocFiscal(protocolo, documentoJson);
        repository.save(docFiscal);
    }

    public Optional<DocFiscal> findByProtocolo(String protocolo) {
        return repository.findByProtocolo(protocolo);
    }

    public List<DocFiscal> findAll() {
        return repository.findAll();
    }
}
