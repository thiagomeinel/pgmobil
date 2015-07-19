package com.br.pgmobil.ws.enums;

import java.util.HashMap;
import java.util.Map;

public enum EnumStatusCartao {

	AUTORIZADO("AUTORIZADO"),
	NAO_AUTORIZADO("NAO AUTORIZADO"),
	NEGADO("NEGADO"),
	SALDO_INSUFICIENTE("SALDO_INSUFICIENTE");
	
	
	private String nome;
	
	
	private static final Map<String, EnumStatusCartao> LOOKUP = new HashMap<String, EnumStatusCartao>();
	static {
		for (EnumStatusCartao item : EnumStatusCartao.values()) {
			LOOKUP.put(item.nome, item);
		}
	}
	
	private EnumStatusCartao(String codigo) {
		this. nome = codigo;
	}


	public String getCodigo() {
		return nome;
	}

	public void setCodigo(String codigo) {
		this.nome = codigo;
	}
}
