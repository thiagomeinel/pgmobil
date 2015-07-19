package com.br.pgmobil.ws.emissor;

import com.br.pgmobil.consumer.exception.ServiceException;
import com.br.pgmobil.dto.TransacaoDTO;
import com.br.pgmobil.ws.enums.EnumStatus;
import com.br.pgmobil.ws.servico.ProcessamentoController;

public class EmissorBrandY implements IEmissor{

	public TransacaoDTO enviar(TransacaoDTO transacao, String acao) throws ServiceException {
		
		switch (EnumStatus.valueOf(acao)) {
		
		case PAGAR:
			transacao = new ProcessamentoController().processarPedido(transacao);
			break;
		case CANCELAR:
			transacao = new ProcessamentoController().cancelarPedido(transacao);
			break;

		default:
			break;
		}
		
		return transacao;
		
	}
}
