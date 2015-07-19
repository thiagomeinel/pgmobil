package com.br.pgmobil.factory;

import com.br.pgmobil.consumer.enums.EnumBandeira;
import com.br.pgmobil.ws.emissor.Emissor;
import com.br.pgmobil.ws.emissor.IEmissor;
import com.br.pgmobil.ws.receptor.IReceptor;
import com.br.pgmobil.ws.receptor.Receptor;



public class MasterComunicadorFactory implements ComunicadorFactory{

	private Emissor criarEmissor = new Emissor() ;
	private Receptor criarReceptor = new Receptor() ;

	public IEmissor criarEmissor() {
		return criarEmissor.criarEmissor(EnumBandeira.BRANDX.getCodigo());
	}

	public IReceptor criarReceptor() {
		return criarReceptor.criarReceptor(EnumBandeira.BRANDY.getCodigo());
	}

}
