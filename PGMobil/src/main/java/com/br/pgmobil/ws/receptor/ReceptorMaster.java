package com.br.pgmobil.ws.receptor;

import com.br.pgmobil.dto.TransacaoDTO;





public class ReceptorMaster implements IReceptor{

	public TransacaoDTO recebe(TransacaoDTO transacaoDTO) {
		TransacaoDTO retorno = transacaoDTO;
		return retorno;
	}
}
