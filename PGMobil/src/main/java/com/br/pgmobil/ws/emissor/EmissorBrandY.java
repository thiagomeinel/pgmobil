package com.br.pgmobil.ws.emissor;

import com.br.pgmobil.consumer.exception.ServiceException;
import com.br.pgmobil.dto.TransacaoDTO;
import com.br.pgmobil.ws.enums.EnumStatus;
import com.br.pgmobil.ws.servico.ProcessamentoController;

/**
 * 
 * @author tmeinel
 *
 */
public class EmissorBrandY extends TipoPagamento implements IEmissor {

	public TransacaoDTO enviar(TransacaoDTO transacao, String acao) throws ServiceException {
		
		try {
			
			switch (EnumStatus.valueOf(acao)) {
			
			case PAGAR:
				transacao.getFormaPagamento().setTipo(processarFormaPagamento(transacao.getFormaPagamento().getQuantidade(), transacao));
				transacao = new ProcessamentoController().processarPedido(transacao);
				
				break;
			case CANCELAR:
				transacao = new ProcessamentoController().cancelarPedido(transacao);
				break;
				
			default:
				break;
			}
			
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	
		return transacao;
		
	}
	
}
