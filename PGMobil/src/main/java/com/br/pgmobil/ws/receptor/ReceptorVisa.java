package com.br.pgmobil.ws.receptor;

import com.br.pgmobil.dto.TransacaoDTO;




/**
 * Classe responsavel por receber o valor enviado pelo emissor com a bandeira VISA
 * @author tmeinel
 *
 */
public class ReceptorVisa implements IReceptor{

	/**
	 * Metodo reponsavel por retornar o valor.
	 * @return TransacaoDTO
	 * @param Transacao transacaoDTO
	 */
	public TransacaoDTO recebe(TransacaoDTO transacaoDTO) {
		TransacaoDTO retorno = transacaoDTO;
		return retorno;
	}
}
