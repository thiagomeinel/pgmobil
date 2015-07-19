package com.br.pgmobil.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import com.br.pgmobil.consumer.enums.EnumBandeira;



public class Util {

	public static String gerarTid(){
		
	    Random radom  = new Random();
	    int numeroTmp = 0;
	    for(int i=0;i<10; i++) {
	        numeroTmp=radom.nextInt(3000);
	    }
		
		return "0000000"+numeroTmp;
	}
	
	public static String geraCodigoAutorizacao(){
		
	    Random radom  = new Random();
	    int numeroTmp = 0;
	    for(int i=0;i<10; i++) {
	        numeroTmp=radom.nextInt(3000);
	    }
		
		return "0000"+numeroTmp;
	}
	
	public static String geraCodigoNSU(){
		
	    Random radom  = new Random();
	    int numeroTmp = 0;
	    for(int i=0;i<10; i++) {
	        numeroTmp=radom.nextInt(3000);
	    }
		
		return "000"+numeroTmp;
	}
	
	
	
	public static String buscarBandeira(String numeroCartao){
		String bandeira = null;
		
		if(numeroCartao.startsWith("5")){
			bandeira = EnumBandeira.BRANDX.getCodigo();
		}else if (numeroCartao.startsWith("4")){
			bandeira = EnumBandeira.BRANDY.getCodigo();
		}else{
			bandeira = EnumBandeira.OUTRO.getCodigo();
		}
		
		return bandeira;	
	}
	
	
	public static boolean validarNumero(String valor){
		
		boolean validaNumero=false;
		for (int i=0;i<valor.length(); i++){
			if (Character.isDigit(valor.charAt(i))){
				validaNumero=true;
			}else{
				validaNumero=false;
				break;
			}
		}
		return validaNumero;
	}
	
    public static long getMinutos(Date dataInicial, Date dataFinal) {  	
    	
    	Long intervalo = (dataFinal.getTime() - dataInicial.getTime()) / 60000;
    	long dias = intervalo / (24 * 60);  
    	intervalo -= (dias * 24 * 60);  
    	long horas = intervalo / 60;  
    	intervalo -= (horas * 60);    
    	long minutos = intervalo; 

        return minutos;
    }  
    
    public static String criptografar(String numeroCartao) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-1");
 
        BigInteger hash = new BigInteger(1, md.digest(numeroCartao.getBytes()));

		return hash.toString();

    }
    
    
    public static String mascarar(String num){
    	String mascara = "-XXXX-";
    	String[] numero = num.split("-");
    	String numeroCartaoMascarado = numero[0]+mascara+mascara+numero[3];
    	
    	return numeroCartaoMascarado;
    }
    
    
    public static Date converterStringToDate(String data, String formato) throws ParseException{
    	DateFormat format = new SimpleDateFormat(formato);
    	Date dataFormatada = (Date) format.parse(data);
    	
    	return dataFormatada;
    }
    
	
/*    public static void main(String[] args) {
    	Clock clock = Clock.systemDefaultZone();
    	LocalDate now = LocalDate.now(clock.systemUTC());
    	System.out.println(now);
    	
    	LocalDateTime agora = LocalDateTime.now();
    	DateTimeFormatter formatador = DateTimeFormatter
    	  .ofLocalizedDateTime(FormatStyle.SHORT)
    	  .withLocale(new Locale("pt", "br"));
    	agora.format(formatador); //08/04/14 10:02
    	System.out.println(agora);
    	
    	LocalDate homemNoEspaco = LocalDate.of(2015, Month.APRIL, 12);
    	LocalDate homemNaLua = LocalDate.of(1969, Month.MAY, 25);
    	 
    	Period periodo = Period.between(homemNoEspaco, homemNaLua);
    	 
    	System.out.printf("%s anos, %s mês e %s dias", 
    	  periodo.getYears() , periodo.getMonths(), periodo.getDays()/6000);
    	  //8 anos, 1 mês e 13 dias
	}*/
}
