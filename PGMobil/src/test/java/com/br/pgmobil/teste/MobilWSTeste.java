package com.br.pgmobil.teste;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.br.pgmobil.consumer.IPagamentoConsumer;
import com.br.pgmobil.consumer.PagamentoConsumer;
import com.br.pgmobil.consumer.enums.EnumBandeira;
import com.br.pgmobil.consumer.exception.ServiceException;
import com.br.pgmobil.controller.DataBase;
import com.br.pgmobil.dto.PedidoDTO;
import com.br.pgmobil.dto.TransacaoDTO;
import com.br.pgmobil.util.Util;
import com.br.pgmobil.ws.enums.EnumStatusCartao;


/**
 * Classe criada para represenata os tetes nos sistema, 
 * esta classe esta utilizando o framework Junit para fazer os teste funcionais
 * @author tmeinel
 *
 */
public class MobilWSTeste {

	
	TransacaoDTO transacao;
	
	@Test
	public void testePagamentoAutorizado() throws ServiceException{


		transacao = new TransacaoDTO(null,new PedidoDTO(100L,new Date().toString(),null,null,"5899161494723904",null,null,null),null);
		
		IPagamentoConsumer pagamentoConsumer = new PagamentoConsumer();
		
		TransacaoDTO transacaoDto = pagamentoConsumer.efetuarPagamento(transacao);
		TransacaoDTO transacaoDto2 = pagamentoConsumer.validarPagamento(transacaoDto);

		assertEquals(EnumStatusCartao.AUTORIZADO.getCodigo(), transacaoDto2.getDadoPedido().getCodigoAutorizacao());


	}


	@Test
	public void testePagamentoNegado() throws ServiceException{

		TransacaoDTO transacao = new TransacaoDTO(null,new PedidoDTO(100L,new Date().toString(),null,null,"5899161494723904",null,null,null),null);
		
		IPagamentoConsumer pagamentoConsumer = new PagamentoConsumer();
		
		TransacaoDTO transacaoDto = pagamentoConsumer.efetuarPagamento(transacao);
		TransacaoDTO transacaoDto2 = pagamentoConsumer.validarPagamento(transacaoDto);

		assertEquals(EnumStatusCartao.NEGADO.getCodigo(), transacaoDto2.getDadoPedido().getCodigoAutorizacao());
	}

	@Test
	public void testePagamentoSaldoInsuficiente() throws ServiceException{

		TransacaoDTO transacao = new TransacaoDTO(null,new PedidoDTO(50000L,new Date().toString(),null,null,"5899161494723904",null,null,null),null);
		
		IPagamentoConsumer pagamentoConsumer = new PagamentoConsumer();
		
		TransacaoDTO transacaoDto = pagamentoConsumer.efetuarPagamento(transacao);
		TransacaoDTO transacaoDto2 = pagamentoConsumer.validarPagamento(transacaoDto);

		assertEquals(EnumStatusCartao.SALDO_INSUFICIENTE.getCodigo(), transacaoDto2.getDadoPedido().getCodigoAutorizacao());

	}
	
	@Test
	public void testePagamentoBandeiraX(){
		
		String bandeira = Util.buscarBandeira("5899161494723904");
		assertEquals(bandeira, EnumBandeira.BRANDX.getCodigo());
	}
	
	@Test
	public void testePagamentoBandeiraY(){
		
		String bandeira = Util.buscarBandeira("4899161494723904");
		assertEquals(bandeira, EnumBandeira.BRANDY.getCodigo());
	}

	@Test
	public void testeRetornoNaoNulo() throws ServiceException{

		TransacaoDTO transacao = new TransacaoDTO(null,new PedidoDTO(50000L,new Date().toString(),null,null,"5899161494723903",null,null,null),null);
		
		PagamentoConsumer pagamentoConsumer = new PagamentoConsumer();
		
		TransacaoDTO transacaoDto = pagamentoConsumer.efetuarPagamento(transacao);
		TransacaoDTO transacaoDto2 = pagamentoConsumer.validarPagamento(transacaoDto);

		assertNotNull(transacaoDto);
		assertNotNull(transacaoDto2);

	}
	
	
	@Test
	public void testeRetornoServico500(){
		
	}

	@Test
	public void testeListaTransacaoes() throws ServiceException{
		
		List<TransacaoDTO> transacoes = DataBase.getAll();
		assertNotNull(transacoes);
	}

	@Test
	public void testeCancelarNegado() throws ParseException, ServiceException{
		
		TransacaoDTO trans = DataBase.getById(transacao.getTid());
		
		Date data = Util.converterStringToDate(trans.getDadoPedido().getDataHora(), "dd/MM/YYYY HH:mm:ss");
		Util.getMinutos(data, new Date());
		assertEquals("NEGADO", transacao.getDadoPedido().getStatusCancelamento());
	}
	
	@Test
	public void testeCancelarAutorizado() throws ParseException, ServiceException{
		
		TransacaoDTO trans = DataBase.getById(transacao.getTid());
		
		Date data = Util.converterStringToDate(trans.getDadoPedido().getDataHora(), "dd/MM/YYYY HH:mm:ss");
		Util.getMinutos(data, new Date());
		assertEquals("AUTORIZADO", transacao.getDadoPedido().getStatusCancelamento());
	}


}
