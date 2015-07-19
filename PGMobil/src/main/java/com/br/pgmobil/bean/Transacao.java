package com.br.pgmobil.bean;

import javax.persistence.Entity;

@Entity
public class Transacao {

	private String tid;
	
	private Pedido pedido;
	
	private Pagamento pagamento;

	public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Pagamento getPagamento() {
		return pagamento;
	}

	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}
	
	
}
