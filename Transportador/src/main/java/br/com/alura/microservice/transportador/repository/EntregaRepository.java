package br.com.alura.microservice.transportador.repository;

import br.com.alura.microservice.transportador.model.Entrega;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntregaRepository extends MongoRepository<Entrega, String> {

}
