package com.br.pgmobil.ws.interfaces;

import java.util.List;

import com.br.pgmobil.consumer.exception.ServiceException;
import com.br.pgmobil.dto.TransacaoDTO;

public interface IProcessamentoController {

	TransacaoDTO processarPedido(TransacaoDTO transacao) throws ServiceException;
	TransacaoDTO cancelarPedido(TransacaoDTO transacao) throws ServiceException;
	List<TransacaoDTO> ConsultarPedido();
}
