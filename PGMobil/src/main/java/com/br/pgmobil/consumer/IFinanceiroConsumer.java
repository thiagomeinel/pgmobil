package com.br.pgmobil.consumer;

import java.util.List;

import com.br.pgmobil.consumer.exception.ServiceException;
import com.br.pgmobil.dto.TransacaoDTO;

public interface IFinanceiroConsumer {

	List<TransacaoDTO> consular()throws ServiceException;
	TransacaoDTO cancelar(TransacaoDTO transacao)throws ServiceException;
	TransacaoDTO validarCancelamento(TransacaoDTO transacao)throws ServiceException;
}
