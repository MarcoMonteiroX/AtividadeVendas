package model;

public class Produto {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private Integer fornecedorId;
    private String nome;
    private Integer quantidadeEstoque;
    private Double valor;

    public Produto() {
    }

    public Produto(Integer fornecedorId, String nome, Integer quantidadeEstoque, Double valor) {
        this.fornecedorId = fornecedorId;
        this.nome = nome;
        this.quantidadeEstoque = quantidadeEstoque;
        this.valor = valor;
    }

    public Produto(Integer id, Integer fornecedorId, String nome, Integer quantidadeEstoque, Double valor) {
        this.id = id;
        this.fornecedorId = fornecedorId;
        this.nome = nome;
        this.quantidadeEstoque = quantidadeEstoque;
        this.valor = valor;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFornecedorId() {
        return fornecedorId;
    }

    public void setFornecedorId(Integer fornecedorId) {
        this.fornecedorId = fornecedorId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(Integer quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

}
