package com.br.pgmobil.dto;

import java.io.Serializable;


public class TransacaoDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String tid;	
	private PedidoDTO dadoPedido;
	private PagamentoDTO formaPagamento;
	
	
	public TransacaoDTO(String tid, PedidoDTO dadoPedido,PagamentoDTO formaPagamento) {
		super();
		this.tid = tid;
		this.dadoPedido = dadoPedido;
		this.formaPagamento = formaPagamento;
	}
	
	
	public TransacaoDTO(){
		
	}
	
	public String getTid() {
		return tid;
	}
	public void setTid(String tid) {
		this.tid = tid;
	}
	public PedidoDTO getDadoPedido() {
		return dadoPedido;
	}
	public void setDadoPedido(PedidoDTO dadoPedido) {
		this.dadoPedido = dadoPedido;
	}
	public PagamentoDTO getFormaPagamento() {
		return formaPagamento;
	}
	public void setFormaPagamento(PagamentoDTO formaPagamento) {
		this.formaPagamento = formaPagamento;
	}


	@Override
	public String toString() {
		return "TransacaoDTO [tid=" + tid + ", dadoPedido=" + dadoPedido
				+ ", formaPagamento=" + formaPagamento + "]";
	}		
	
}
