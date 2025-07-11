package com.example.reactive.repository;

import com.example.reactive.model.DocFiscal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Manoel Campos
 */
@Repository
public interface DocFiscalRepository extends JpaRepository<DocFiscal,Long> {
    public Optional<DocFiscal> findByProtocolo(String protocolo);
}
