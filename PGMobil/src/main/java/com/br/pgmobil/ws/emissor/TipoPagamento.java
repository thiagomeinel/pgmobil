package com.br.pgmobil.ws.emissor;

import com.br.pgmobil.dto.TransacaoDTO;

public abstract class TipoPagamento {

	protected String processarFormaPagamento(int quantidade, TransacaoDTO transacao){
		
		String retorno = null;
		if(quantidade == 1){
			
			retorno = "AVISTA"; 

		}else if (quantidade > 1 && quantidade < 4){
			retorno = "PARCELADO LOJA";

		}else if (quantidade > 4){
			retorno = "PARCELADO ADM";
		}
		
		return retorno;
	}
}
