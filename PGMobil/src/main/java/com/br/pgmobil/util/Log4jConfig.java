package com.br.pgmobil.util;

import org.apache.log4j.PropertyConfigurator;

public class Log4jConfig {

	public static void getLogProperties() throws Exception{	
		try{
			PropertyConfigurator.configure("C:\\webConfig\\properties\\log4jHealthConfig.properties");
		}catch(Exception e){
			throw new Exception("Ocorreu um erro no metodo getLogProperties da classe Log4jConfigure",e);
		}
	}
}
