package br.com.barata.microservice.loja.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public class CompraDTO {

    @JsonIgnore
    private String compraId;
    private List<ItemCompraDTO> itens;
    private EnderecoDTO endereco;

    public String getCompraId() {
        return compraId;
    }

    public void setCompraId(String compraId) {
        this.compraId = compraId;
    }

    public List<ItemCompraDTO> getItens() {
        return itens;
    }

    public void setItens(List<ItemCompraDTO> itens) {
        this.itens = itens;
    }

    public EnderecoDTO getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoDTO endereco) {
        this.endereco = endereco;
    }
}
