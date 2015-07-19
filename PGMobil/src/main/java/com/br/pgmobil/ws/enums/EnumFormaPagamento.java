package com.br.pgmobil.ws.enums;

import java.util.HashMap;
import java.util.Map;

public enum EnumFormaPagamento {

	AVISTA("AVISTA"),
	PACELADO_LOJA("PARCELADO LOJA"),
	PARCELADO_ADM("PACELADO ADM");

	private String nome;


	private static final Map<String, EnumFormaPagamento> LOOKUP = new HashMap<String, EnumFormaPagamento>();
	static {
		for (EnumFormaPagamento item : EnumFormaPagamento.values()) {
			LOOKUP.put(item.nome, item);
		}
	}

	private EnumFormaPagamento(String codigo) {
		this. nome = codigo;
	}	

	public String getCodigo() {
		return nome;
	}

	public void setCodigo(String codigo) {
		this.nome = codigo;
	}
}
