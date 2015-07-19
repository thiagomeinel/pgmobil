package com.br.pgmobil.ws.receptor;

import com.br.pgmobil.consumer.enums.EnumBandeira;





public class Receptor {

	public IReceptor criarReceptor (String tipoDoReceptor ) {

		if( tipoDoReceptor == EnumBandeira.BRANDX.getCodigo()) {

			return new ReceptorVisa();

		} else if ( tipoDoReceptor == EnumBandeira.BRANDY.getCodigo()) {

			return new ReceptorMaster();

		} else {

			throw new IllegalArgumentException (" Tipo de receptor não suportado .") ;

		}

	}
}
