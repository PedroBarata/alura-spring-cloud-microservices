package br.com.barata.microservice.fornecedor.repository;

import br.com.barata.microservice.fornecedor.model.Pedido;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PedidoRepository extends MongoRepository<Pedido, String> {

}
