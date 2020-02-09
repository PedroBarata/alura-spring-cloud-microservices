package br.com.barata.microservice.fornecedor.repository;

import br.com.barata.microservice.fornecedor.model.PedidoItem;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PedidoItemRepository extends MongoRepository<PedidoItem, String> {
}
