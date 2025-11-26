package com.ifsp.Leel.Model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data_pedido")
    private LocalDateTime dataPedido;

    // Status: AGUARDANDO, PAGO, ENVIADO, CANCELADO
    @Column(name = "status")
    private String status;

    @Column(name = "valor_total")
    private double valorTotal;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemPedido> itens = new ArrayList<>();

    public Pedido() {
        this.dataPedido = LocalDateTime.now();
        this.status = "AGUARDANDO";
    }

    // Método auxiliar para adicionar itens e manter a consistência
    public void adicionarItem(ItemPedido item) {
        itens.add(item);
        item.setPedido(this);
        calculaTotal();
    }

    public void calculaTotal() {
        this.valorTotal = itens.stream()
                .mapToDouble(ItemPedido::getSubtotal)
                .sum();
    }
}