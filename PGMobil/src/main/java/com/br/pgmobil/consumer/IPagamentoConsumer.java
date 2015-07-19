package com.br.pgmobil.consumer;

import com.br.pgmobil.consumer.exception.ServiceException;
import com.br.pgmobil.dto.TransacaoDTO;

public interface IPagamentoConsumer {

	TransacaoDTO efetuarPagamento(TransacaoDTO transacao)throws ServiceException;
	TransacaoDTO validarPagamento(TransacaoDTO transacao)throws ServiceException;
	
}
