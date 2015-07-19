package com.br.pgmobil.dto;

public class Pagamento {

	private TransacaoDTO transacaoDto;
	private PedidoDTO pedidoDto;
	private PagamentoDTO formaPagamentoDto;
	
	public TransacaoDTO getTransacaoDto() {
		return transacaoDto;
	}
	public void setTransacaoDto(TransacaoDTO transacaoDto) {
		this.transacaoDto = transacaoDto;
	}
	public PedidoDTO getPedidoDto() {
		return pedidoDto;
	}
	public void setPedidoDto(PedidoDTO pedidoDto) {
		this.pedidoDto = pedidoDto;
	}
	public PagamentoDTO getFormaPagamentoDto() {
		return formaPagamentoDto;
	}
	public void setFormaPagamentoDto(PagamentoDTO formaPagamentoDto) {
		this.formaPagamentoDto = formaPagamentoDto;
	}
	
	@Override
	public String toString() {
		return "Pagamento [transacaoDto=" + transacaoDto + ", pedidoDto="
				+ pedidoDto + ", formaPagamentoDto=" + formaPagamentoDto + "]";
	}
}
