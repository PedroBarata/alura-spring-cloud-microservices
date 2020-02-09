package br.com.barata.microservice.loja.dto;

public class InfoPedidoDTO {
    private String id;
    private Integer tempoDePreparo;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getTempoDePreparo() {
        return tempoDePreparo;
    }

    public void setTempoDePreparo(Integer tempoDePreparo) {
        this.tempoDePreparo = tempoDePreparo;
    }
}
