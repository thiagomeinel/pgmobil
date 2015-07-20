package com.br.pgmobil.teste;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.br.pgmobil.consumer.IPagamentoConsumer;
import com.br.pgmobil.consumer.PagamentoConsumer;
import com.br.pgmobil.consumer.enums.EnumBandeira;
import com.br.pgmobil.consumer.exception.ServiceException;
import com.br.pgmobil.controller.DataBase;
import com.br.pgmobil.dto.PagamentoDTO;
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


	String tid;
	
	@Test
	public void testePagamentoAutorizado() {

		try {

			TransacaoDTO transacao = new TransacaoDTO(null,
					new PedidoDTO(4000L,new Date().toLocaleString(),"loja de informatica",null,"4899-1614-9472-3904",null,null,null),
					new PagamentoDTO(null, 1, null, null));

			IPagamentoConsumer pagamentoConsumer = new PagamentoConsumer();

			TransacaoDTO transacaoDto = pagamentoConsumer.efetuarPagamento(transacao);
			TransacaoDTO transacaoDto2 = pagamentoConsumer.validarPagamento(transacaoDto);

			assertEquals(EnumStatusCartao.AUTORIZADO.getCodigo(), transacaoDto2.getDadoPedido().getStatus());
			tid = transacao.getTid();

		} catch (ServiceException se) {

		} catch(Exception e){

		}



	}


	@Test
	public void testePagamentoNaoAutorizado() {

		try {

			TransacaoDTO transacao = new TransacaoDTO(null,
					new PedidoDTO(4000L,"19/07/2015 20:48:32","loja de informatica",null,"5899-1614-9472-3903",null,null,null),
					new PagamentoDTO(null, 1, null, null));

			IPagamentoConsumer pagamentoConsumer = new PagamentoConsumer();

			TransacaoDTO transacaoDto = pagamentoConsumer.efetuarPagamento(transacao);
			TransacaoDTO transacaoDto2 = pagamentoConsumer.validarPagamento(transacaoDto);

			assertEquals(EnumStatusCartao.NAO_AUTORIZADO.getCodigo(), transacaoDto2.getDadoPedido().getStatus());

		} catch (ServiceException se) {

		} catch(Exception e){

		}
	}

	@Test
	public void testePagamentoSaldoInsuficiente() throws ServiceException{

		try {

			TransacaoDTO transacao = new TransacaoDTO(null,
					new PedidoDTO(40000L,"19/07/2015 20:48:32","loja de informatica",null,"4899-1614-9472-3904",null,null,null),
					new PagamentoDTO(null, 1, null, null));

			IPagamentoConsumer pagamentoConsumer = new PagamentoConsumer();

			TransacaoDTO transacaoDto = pagamentoConsumer.efetuarPagamento(transacao);
			TransacaoDTO transacaoDto2 = pagamentoConsumer.validarPagamento(transacaoDto);

			assertEquals(EnumStatusCartao.SALDO_INSUFICIENTE.getCodigo(), transacaoDto2.getDadoPedido().getStatus());

		} catch (ServiceException se) {

		} catch(Exception e){

		}

	}

	@Test
	public void testePagamentoBandeiraX(){
		try {
			String bandeira = Util.buscarBandeira("5899-1614-9472-3904");
			assertEquals(bandeira, EnumBandeira.BRANDX.getCodigo());

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Test
	public void testePagamentoBandeiraY(){
		try {
			String bandeira = Util.buscarBandeira("4899-1614-9472-3904");
			assertEquals(bandeira, EnumBandeira.BRANDY.getCodigo());

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Test
	public void testeListaTransacaoes() throws ServiceException{
		try {

			List<TransacaoDTO> transacoes = DataBase.getAll();
			assertNotNull(transacoes);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}


	@Test
	public void testeCancelarNegado() throws ParseException, ServiceException{
		try {

			TransacaoDTO transacao = DataBase.getById(tid);

			Date data = Util.converterStringToDate(transacao.getDadoPedido().getDataHora(), "dd/MM/YYYY HH:mm:ss");
			Util.getMinutos(data, new Date());
			assertEquals("NEGADO", transacao.getDadoPedido().getStatusCancelamento());

		} catch (ParseException p) {

		} catch(Exception e){

		}
	}


	@Test
	public void testeCancelarAutorizado() {

		try {

			TransacaoDTO transacao = DataBase.getById(tid);

			Date data = Util.converterStringToDate(transacao.getDadoPedido().getDataHora(), "dd/MM/YYYY HH:mm:ss");
			Util.getMinutos(data, new Date());
			assertEquals("AUTORIZADO", transacao.getDadoPedido().getStatusCancelamento());

		} catch(ParseException p){

		} catch(Exception e){

		}

	}


	@Test
	public void testePagamentoPaceladoLoja() {

		try {

			TransacaoDTO transacao = new TransacaoDTO(null,
					new PedidoDTO(40000L,"19/07/2015 20:48:32","loja de informatica",null,"4899-1614-9472-3904",null,null,null),
					new PagamentoDTO(null, 3, null, null));

			PagamentoConsumer pagamentoConsumer = new PagamentoConsumer();
			TransacaoDTO transacaoDto = pagamentoConsumer.efetuarPagamento(transacao);
			transacaoDto = pagamentoConsumer.validarPagamento(transacaoDto);

			assertEquals("PARCELADO LOJA", transacaoDto.getFormaPagamento().getTipo());

		} catch (ServiceException se) {

		} catch(Exception e){

		}

	}

	@Test
	public void testePagamentoPaceladoADM() throws ServiceException{

		try {

			TransacaoDTO transacao = new TransacaoDTO(null,
					new PedidoDTO(40000L,"19/07/2015 20:48:32","loja de informatica",null,"4899-1614-9472-3904",null,null,null),
					new PagamentoDTO(null, 8, null, null));

			PagamentoConsumer pagamentoConsumer = new PagamentoConsumer();
			TransacaoDTO transacaoDto = pagamentoConsumer.efetuarPagamento(transacao);
			transacaoDto = pagamentoConsumer.validarPagamento(transacaoDto);

			assertEquals("PARCELADO ADM", transacaoDto.getFormaPagamento().getTipo());

		} catch (ServiceException se) {
			
		} catch(Exception e){

		}

	}

}
