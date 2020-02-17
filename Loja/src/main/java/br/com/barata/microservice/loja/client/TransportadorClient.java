package br.com.barata.microservice.loja.client;

import br.com.barata.microservice.loja.dto.InfoEntregaDTO;
import br.com.barata.microservice.loja.dto.VoucherDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("transportador")
public interface TransportadorClient {

    @RequestMapping(method = RequestMethod.POST, value = "/entrega")
    VoucherDTO reservaEntrega(@RequestBody InfoEntregaDTO pedidoDTO);
}
