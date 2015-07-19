package com.br.pgmobil.ws.enums;

public enum EnumCartao {

	LIMITE_CARTAO_BRANDY(10000L),
	LIMITE_CARTAO_BRANDX(5000L),
	NUMERO_CARTAO_BRANDY(4899-1614-9472-3904L),
	NUMERO_CARTAO_BRANDX(5899-1614-9472-3904L),
	NUMERO_CARTAO_BLOQUEADO(5899-1614-9472-3903L);
	
	private Long codigo;
	
	private EnumCartao(Long codigo) {
		this.codigo = codigo;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long alvo) {
		this.codigo = alvo;
	}
}
