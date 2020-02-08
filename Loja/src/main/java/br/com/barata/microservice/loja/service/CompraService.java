package br.com.barata.microservice.loja.service;

import br.com.barata.microservice.loja.dto.CompraDTO;
import br.com.barata.microservice.loja.dto.InfoFornecedorDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CompraService {
    String FORNECEDOR_BASE_URL = "http://fornecedor";
    
    @Autowired
    private RestTemplate client;

    public void realizaCompra(CompraDTO compraDTO) {
        ResponseEntity<InfoFornecedorDTO> fornecedorDTOResponseEntity = client.exchange(FORNECEDOR_BASE_URL + "/info/" + compraDTO.getEndereco().getEstado(),
                HttpMethod.GET, null, InfoFornecedorDTO.class);
        fornecedorDTOResponseEntity.getBody();
    }
}
