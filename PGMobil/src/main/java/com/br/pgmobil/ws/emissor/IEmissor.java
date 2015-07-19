package com.br.pgmobil.ws.emissor;


import com.br.pgmobil.consumer.exception.ServiceException;
import com.br.pgmobil.dto.TransacaoDTO;

/**
 * Interface para os metodos emissores.
 * @author tmeinel
 *
 */
public interface IEmissor {

	TransacaoDTO enviar(TransacaoDTO transacao, String acao) throws ServiceException;
}
