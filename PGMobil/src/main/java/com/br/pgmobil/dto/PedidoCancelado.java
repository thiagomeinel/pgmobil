package com.br.pgmobil.dto;

import java.io.Serializable;

public abstract class PedidoCancelado extends PedidoDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String nsu;
	private String codigoAutorizacao;
	private String statusCancelamento;
	
	
	public String getNsu() {
		return nsu;
	}
	public void setNsu(String nsu) {
		this.nsu = nsu;
	}
	public String getCodigoAutorizacao() {
		return codigoAutorizacao;
	}
	public void setCodigoAutorizacao(String codigoAutorizacao) {
		this.codigoAutorizacao = codigoAutorizacao;
	}
	public String getStatusCancelamento() {
		return statusCancelamento;
	}
	public void setStatusCancelamento(String statusCancelamento) {
		this.statusCancelamento = statusCancelamento;
	}
	
	
	

}
