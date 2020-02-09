package br.com.barata.microservice.fornecedor.controller;

import br.com.barata.microservice.fornecedor.model.InfoFornecedor;
import br.com.barata.microservice.fornecedor.service.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/info")
public class InfoController {

    @Autowired
    private InfoService infoService;

    @GetMapping(value = "/{estado}")
    public InfoFornecedor getInfoPorEstado(@PathVariable("estado") String estado) {
       return infoService.getInfoPorEstado(estado);
    }

    @GetMapping()
    public List<InfoFornecedor> findAll() {
        return infoService.findAll();
    }
}
