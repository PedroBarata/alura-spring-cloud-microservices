package br.com.barata.microservice.loja.controller;

import br.com.barata.microservice.loja.dto.CompraDTO;
import br.com.barata.microservice.loja.service.CompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/compra")
public class LojaController {

    @Autowired
    private CompraService compraService;

    @RequestMapping(method = RequestMethod.POST)
    public void realizaCompra(@RequestBody CompraDTO compraDTO) {
        compraService.realizaCompra(compraDTO);
    }
}
