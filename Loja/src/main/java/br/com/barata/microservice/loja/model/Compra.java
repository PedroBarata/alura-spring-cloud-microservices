package br.com.barata.microservice.loja.model;

import br.com.barata.microservice.loja.enums.CompraStatus;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "compra")
public class Compra {

	@Id
    private String id;
    private String pedidoId;
    private Integer tempoDePreparo;
    private String enderecoDestino;
    private LocalDate dataParaEntrega;
    private String entregaId;
    private CompraStatus compraStatus;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(String pedidoId) {
        this.pedidoId = pedidoId;
    }

    public Integer getTempoDePreparo() {
        return tempoDePreparo;
    }

    public void setTempoDePreparo(Integer tempoDePreparo) {
        this.tempoDePreparo = tempoDePreparo;
    }

    public String getEnderecoDestino() {
        return enderecoDestino;
    }

    public void setEnderecoDestino(String enderecoDestino) {
        this.enderecoDestino = enderecoDestino;
    }

    public LocalDate getDataParaEntrega() {
        return dataParaEntrega;
    }

    public void setDataParaEntrega(LocalDate dataParaEntrega) {
        this.dataParaEntrega = dataParaEntrega;
    }

    public String getEntregaId() {
        return entregaId;
    }

    public void setEntregaId(String entregaId) {
        this.entregaId = entregaId;
    }

    public CompraStatus getCompraStatus() {
        return compraStatus;
    }

    public void setCompraStatus(CompraStatus compraStatus) {
        this.compraStatus = compraStatus;
    }
}
