package com.br.pgmobil.consumer;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.util.List;

import com.br.pgmobil.consumer.exception.ServiceException;
import com.br.pgmobil.consumer.util.ProtocoloComunicacao;
import com.br.pgmobil.dto.TransacaoDTO;
import com.br.pgmobil.util.Conversor;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class FinanceiroConsumer extends ProtocoloComunicacao implements IFinanceiroConsumer{

	Gson gson = null;
	Conversor<TransacaoDTO> conversor = null;

	@Override
	public List<TransacaoDTO> consular() throws ServiceException {
		
		List<TransacaoDTO> transacoes = null;
		
		try {
			gson = new Gson();
			String mensagem = httpGet("http://localhost:8080/PGMobil/rest/pgmobil/pagamento.consultar");
			conversor = new Conversor<TransacaoDTO>();
			transacoes = conversor.fromJson(transacoes, mensagem);

		}catch (MalformedURLException e) {                                                             
			throw new ServiceException(e);			
		}catch (IOException io) {
			throw new ServiceException(io);
		}
		return transacoes;
	}

	@Override
	public TransacaoDTO cancelar(TransacaoDTO transacao) throws ServiceException {
		TransacaoDTO transacaoRetorno = null;
		try{
			gson = new Gson();
			String mensagem = httpPost(transacao,TransacaoDTO.class,"http://localhost:8080/PGMobil/rest/pgmobil/pagamento.cancelar");
			Type transacaoType = new TypeToken<TransacaoDTO>() {}.getType();
			transacaoRetorno = gson.fromJson(mensagem, transacaoType); 

		}catch (MalformedURLException e) {                                                             
			throw new ServiceException(e);			
		}catch (IOException io) {
			throw new ServiceException(io);
		}
		return transacaoRetorno;
	}
	

	@Override
	public TransacaoDTO validarCancelamento(TransacaoDTO transacao)throws ServiceException {
		
		TransacaoDTO transacaoRetorno = null;
		try{
			gson = new Gson();
			String mensagem = httpPost(transacao,TransacaoDTO.class,"http://localhost:8080/PGMobil/rest/pgmobil/pagamento.validarCancelar");
			Type transacaoType = new TypeToken<TransacaoDTO>() {}.getType();
			transacaoRetorno = gson.fromJson(mensagem, transacaoType); 

		}catch (MalformedURLException e) {                                                             
			throw new ServiceException(e);			
		}catch (IOException io) {
			throw new ServiceException(io);
		}
		return transacaoRetorno;

	}
}
