package com.br.pgmobil.form;

import java.util.List;

import com.br.pgmobil.dto.TransacaoDTO;

public class FinanceiroForm {

	private List<TransacaoDTO> transacoes;

	public List<TransacaoDTO> getTransacoes() {
		return transacoes;
	}

	public void setTransacoes(List<TransacaoDTO> transacoes) {
		this.transacoes = transacoes;
	}

}
