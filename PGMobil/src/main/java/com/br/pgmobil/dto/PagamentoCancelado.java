package com.br.pgmobil.dto;

import java.io.Serializable;

public abstract class PagamentoCancelado extends PagamentoDTO implements Serializable{

	private static final long serialVersionUID = -7869927909573749705L;
	
	private String modalidade;
	private String parcelas;
	
	public String getModalidade() {
		return modalidade;
	}
	public void setModalidade(String modalidade) {
		this.modalidade = modalidade;
	}
	public String getParcelas() {
		return parcelas;
	}
	public void setParcelas(String parcelas) {
		this.parcelas = parcelas;
	}
	
}
