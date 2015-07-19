package com.br.pgmobil.consumer.enums;

public enum EnumBandeira {

	
	BRANDX("BRANDX"),
	BRANDY("BRANDY"),
	OUTRO("OUTRO");
	
	private String codigo;
	
	private EnumBandeira(String codigo) {
		this.codigo = codigo;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String alvo) {
		this.codigo = alvo;
	}
}
