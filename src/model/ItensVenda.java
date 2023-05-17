package model;

public class ItensVenda {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private Integer produtoId;
    private Integer vendaId;
    private Integer quantidade;
    private Double valor;

    public ItensVenda() {
    }

    public ItensVenda(Integer produtoId, Integer vendaId, Integer quantidade, Double valor) {
        this.produtoId = produtoId;
        this.vendaId = vendaId;
        this.quantidade = quantidade;
        this.valor = valor;
    }

    public ItensVenda(Integer id, Integer produtoId, Integer vendaId, Integer quantidade, Double valor) {
        this.id = id;
        this.produtoId = produtoId;
        this.vendaId = vendaId;
        this.quantidade = quantidade;
        this.valor = valor;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Integer produtoId) {
        this.produtoId = produtoId;
    }

    public Integer getVendaId() {
        return vendaId;
    }

    public void setVendaId(Integer vendaId) {
        this.vendaId = vendaId;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Double getValorTotal() {
        return valor * quantidade;
    }

}
