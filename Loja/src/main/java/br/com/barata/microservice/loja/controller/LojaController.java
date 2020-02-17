package br.com.barata.microservice.loja.controller;

import br.com.barata.microservice.loja.dto.CompraDTO;
import br.com.barata.microservice.loja.model.Compra;
import br.com.barata.microservice.loja.service.CompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
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
    public Compra realizaCompra(@RequestBody CompraDTO compraDTO) {
        return compraService.realizaCompra(compraDTO);
    }
    
    @RequestMapping("{id}")
    public Compra getById(@PathVariable("id") String id) {
    	return compraService.getById(id);
    }
}
