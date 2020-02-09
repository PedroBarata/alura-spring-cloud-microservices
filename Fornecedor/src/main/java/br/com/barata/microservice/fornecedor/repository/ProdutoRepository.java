package br.com.barata.microservice.fornecedor.repository;

import br.com.barata.microservice.fornecedor.model.Produto;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProdutoRepository extends MongoRepository<Produto, String> {

	List<Produto> findByEstado(String estado);
	
	List<Produto> findByIdIn(List<String> ids);
}
