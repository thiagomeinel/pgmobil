package com.br.pgmobil.consumer;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.MalformedURLException;


import com.br.pgmobil.consumer.exception.ServiceException;
import com.br.pgmobil.consumer.util.ProtocoloComunicacao;
import com.br.pgmobil.dto.TransacaoDTO;
import com.br.pgmobil.util.Conversor;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class PagamentoConsumer extends ProtocoloComunicacao implements IPagamentoConsumer{
	
	Gson gson = null;
	Conversor<TransacaoDTO> conversor = null;
	
	@Override
	public TransacaoDTO efetuarPagamento(TransacaoDTO transacao) throws ServiceException {	
		TransacaoDTO  transacaoRetorno = null;

		try {
			gson = new Gson();
			String mensagem = httpPost(transacao,TransacaoDTO.class,"http://localhost:8080/PGMobil/rest/pgmobil/pagamento.efetuar");
			/*conversor = new Conversor<TransacaoDTO>();
			transacaoRetorno = conversor.fromJson( mensagem);*/
			
			Type transacaoType = new TypeToken<TransacaoDTO>() {}.getType();
			transacaoRetorno = gson.fromJson(mensagem, transacaoType);
			
			

		}catch (MalformedURLException e) {                                          
			throw new ServiceException(e);			
		}catch (IOException io) {
			throw new ServiceException(io);
		}catch (Exception ex) {
			throw new ServiceException(ex);
		}
		return transacaoRetorno;
	}
	
	@Override
	public TransacaoDTO validarPagamento(TransacaoDTO transacao)throws ServiceException {

		try{
			gson = new Gson();
			String mensagem =  httpPost(transacao,TransacaoDTO.class,"http://localhost:8080/PGMobil/rest/pgmobil/pagamento.validar");;
/*			conversor = new Conversor<TransacaoDTO>();
			transacaoRetorno = conversor.fromJson(transacao, mensagem);*/
			Type transacaoType = new TypeToken<TransacaoDTO>() {}.getType();
			transacao = gson.fromJson(mensagem, transacaoType);

		}catch (MalformedURLException e) {                                                             
			throw new ServiceException(e);		
		}catch (IOException io) {
			throw new ServiceException(io);
		}
		return transacao;
	}
}
