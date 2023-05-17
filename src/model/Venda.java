package model;

import java.util.Date;

public class Venda {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private Integer clienteId;
    private Date dataVenda;

    public Venda() {
    }

    public Venda(Integer clienteId, Date dataVenda) {
        this.clienteId = clienteId;
        this.dataVenda = dataVenda;
    }

    public Venda(Integer id, Integer clienteId, Date dataVenda) {
        this.id = id;
        this.clienteId = clienteId;
        this.dataVenda = dataVenda;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getClienteId() {
        return clienteId;
    }

    public void setClienteId(Integer clienteId) {
        this.clienteId = clienteId;
    }

    public Date getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(Date dataVenda) {
        this.dataVenda = dataVenda;
    }

}
