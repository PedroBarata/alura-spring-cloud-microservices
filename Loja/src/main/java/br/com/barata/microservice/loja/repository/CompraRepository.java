package br.com.barata.microservice.loja.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.barata.microservice.loja.model.Compra;

@Repository
public interface CompraRepository extends MongoRepository<Compra, String> {

}
