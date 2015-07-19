package com.br.pgmobil.ws.enums;

import java.util.HashMap;
import java.util.Map;

public enum EnumStatus {

	PAGAR("PAGAR"),
	CANCELAR("CANCELAR"),
	CONSULTAR("CONSULTAR");
	
	private String nome;
	
	
	private static final Map<String, EnumStatus> LOOKUP = new HashMap<String, EnumStatus>();
	static {
		for (EnumStatus item : EnumStatus.values()) {
			LOOKUP.put(item.nome, item);
		}
	}
	
	private EnumStatus(String codigo) {
		this. nome = codigo;
	}	

	public String getCodigo() {
		return nome;
	}

	public void setCodigo(String codigo) {
		this.nome = codigo;
	}
}
