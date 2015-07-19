package com.br.pgmobil.dto;

import java.io.Serializable;

public class PedidoDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long valor;
	private String dataHora;
	private String descricao;
	private String nsu;
	private String numeroCartao;
	private String codigoAutorizacao;
	private String status;
	private String statusCancelamento;

	public PedidoDTO(Long valor, String dataHora, String descricao,String nsu, String numeroCartao, String codigoAutorizacao,String status, String statusCancelamento) {
		super();
		this.valor = valor;
		this.dataHora = dataHora;
		this.descricao = descricao;
		this.nsu = nsu;
		this.numeroCartao = numeroCartao;
		this.codigoAutorizacao = codigoAutorizacao;
		this.status = status;
		this.statusCancelamento = statusCancelamento;
	}

	public PedidoDTO(){
		
	}
	
	
	public Long getValor() {
		return valor;
	}

	public void setValor(Long valor) {
		this.valor = valor;
	}

	public String getDataHora() {
		return dataHora;
	}
	public void setDataHora(String dataHora) {
		this.dataHora = dataHora;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	public String getNumeroCartao() {
		return numeroCartao;
	}

	public void setNumeroCartao(String numeroCartao) {
		this.numeroCartao = numeroCartao;
	}

	public String getStatusCancelamento() {
		return statusCancelamento;
	}

	public void setStatusCancelamento(String statusCancelamento) {
		this.statusCancelamento = statusCancelamento;
	}
	
	

}
