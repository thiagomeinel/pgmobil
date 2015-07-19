package com.br.pgmobil.dto;

import java.io.Serializable;

public class PagamentoDTO implements Serializable{


	private static final long serialVersionUID = 1L;
	
	private String tipo;
	private String quantidade;
	private String modalidade;
	private String parcela;
	
	public PagamentoDTO(){
		
	}
	
	public PagamentoDTO(String tipo, String quantidade, String modalidade, String parcela) {
		super();
		this.tipo = tipo;
		this.quantidade = quantidade;
		this.modalidade = modalidade;
		this.parcela = parcela;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(String quantidade) {
		this.quantidade = quantidade;
	}

	public String getModalidade() {
		return modalidade;
	}

	public void setModalidade(String modalidade) {
		this.modalidade = modalidade;
	}

	public String getParcela() {
		return parcela;
	}

	public void setParcela(String parcela) {
		this.parcela = parcela;
	}
	
}
