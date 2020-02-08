package br.com.barata.microservice.fornecedor.service;

import br.com.barata.microservice.fornecedor.model.InfoFornecedor;
import br.com.barata.microservice.fornecedor.repository.InfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InfoService {

    @Autowired
    private InfoRepository infoRepository;

    public InfoFornecedor getInfoPorEstado(String estado) {
        return infoRepository.findByEstado(estado);
    }
}
