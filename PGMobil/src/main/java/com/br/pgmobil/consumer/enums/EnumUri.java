package com.br.pgmobil.consumer.enums;

public enum EnumUri {

	PAGAMENTO_VISA("Pagamento.visa.efetuar","http://localhost:8080/PGMobil/rest/pgmobil/pagamento.visa.efetuar",new String[]{"{numeroCartao,valor}"}),
	PAGAMENTO_MASTER("Pagamento.master.efetuar","http://localhost:8080/PGMobil/rest/pgmobil/pagamento.master.efetuar",new String[]{"{numeroCartao,valor}"}),
	CONSULTAR("Consultar","http://loclahost:8080/ArtifactId/rest/servico/consultar/{bandeira}",new String[]{"{bandeira}"});
	
	private String key;
	private String target;
	private String [] parameter;
	

	private EnumUri(String key ,String target ,String [] parameter) {
		this.key = key;
		this.target = target;
		this.setParameter(parameter);
	}
	
	public String getParameter(int index) {
		return parameter[index];
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String alvo) {
		this.target = alvo;
	}

	public void setParameter(String [] parameter) {
		this.parameter = parameter;
	}
	
}
