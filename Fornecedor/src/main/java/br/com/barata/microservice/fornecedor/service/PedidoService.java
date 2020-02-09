package br.com.barata.microservice.fornecedor.service;

import br.com.barata.microservice.fornecedor.dto.ItemDoPedidoDTO;
import br.com.barata.microservice.fornecedor.model.Pedido;
import br.com.barata.microservice.fornecedor.model.PedidoItem;
import br.com.barata.microservice.fornecedor.model.Produto;
import br.com.barata.microservice.fornecedor.repository.PedidoItemRepository;
import br.com.barata.microservice.fornecedor.repository.PedidoRepository;
import br.com.barata.microservice.fornecedor.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private PedidoItemRepository pedidoItemRepository;

    public Pedido realizaPedido(List<ItemDoPedidoDTO> itens) {

        if (itens == null) {
            return null;
        }

        List<PedidoItem> pedidoItens = toPedidoItem(itens);
        Pedido pedido = new Pedido(pedidoItens);
        pedido.setTempoDePreparo(itens.size());
        pedidoItemRepository.saveAll(pedidoItens);
        return pedidoRepository.save(pedido);
    }

    public Pedido getPedidoPorId(String id) {
        return this.pedidoRepository.findById(id).orElse(new Pedido());
    }

    private List<PedidoItem> toPedidoItem(List<ItemDoPedidoDTO> itens) {

        List<String> idsProdutos = itens
                .stream()
                .map(item -> item.getId())
                .collect(Collectors.toList());

        List<Produto> produtosDoPedido = produtoRepository.findByIdIn(idsProdutos);

        List<PedidoItem> pedidoItens = itens
                .stream()
                .map(item -> {
                    Produto produto = produtosDoPedido
                            .stream()
                            .filter(p -> p.getId().equals(item.getId()))
                            .findFirst().get();

                    PedidoItem pedidoItem = new PedidoItem();
                    pedidoItem.setProduto(produto);
                    pedidoItem.setQuantidade(item.getQuantidade());
                    return pedidoItem;
                })
                .collect(Collectors.toList());
        return pedidoItens;
    }
}
