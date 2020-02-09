package br.com.barata.microservice.fornecedor.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "pedido")
public class Pedido {

    @Id
    private String id;
    private Integer tempoDePreparo;
    private PedidoStatus status;
    private List<PedidoItem> itens;

    public Pedido(List<PedidoItem> itens) {
        this.itens = itens;
        this.status = PedidoStatus.RECEBIDO;
    }

    public Pedido() {
    }

    public List<PedidoItem> getItens() {
        return itens;
    }

    public void setItens(List<PedidoItem> itens) {
        this.itens = itens;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public PedidoStatus getStatus() {
        return status;
    }

    public void setStatus(PedidoStatus status) {
        this.status = status;
    }

    public Integer getTempoDePreparo() {
        return tempoDePreparo;
    }

    public void setTempoDePreparo(Integer tempoDePreparo) {
        this.tempoDePreparo = tempoDePreparo;
    }
}
