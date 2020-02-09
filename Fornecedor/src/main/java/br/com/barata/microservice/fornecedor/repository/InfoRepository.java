package br.com.barata.microservice.fornecedor.repository;

import br.com.barata.microservice.fornecedor.model.InfoFornecedor;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InfoRepository extends MongoRepository<InfoFornecedor, String> {

    @Query(fields="{ 'endereco' : 1}")
    InfoFornecedor findByEstado(String estado);

    List<InfoFornecedor> findAll();
}
