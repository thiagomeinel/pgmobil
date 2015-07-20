package com.br.pgmobil.consumer.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import com.br.pgmobil.consumer.exception.ServiceException;
import com.google.gson.Gson;

public class ProtocoloComunicacao {

	protected static String httpGet(String urlWebService) throws IOException, MalformedURLException,ServiceException{

		HttpURLConnection connection = null;
		URL url;
		try {
			url = new URL(urlWebService);

			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			
			connection.setConnectTimeout(15000);
			connection.connect();

			int statusCode = connection.getResponseCode();
			String responseJson = null;

			if(isStatusCodeOK(statusCode)){
				responseJson = inputStreamToString(connection.getInputStream());
				connection.disconnect();
				return responseJson;

			}else{
				throw new ServiceException("Ocorreu um Erro Interno no Servidor status code "+statusCode);
			}

		} catch (MalformedURLException e) {
			throw new ServiceException(e);

		} catch (ProtocolException e) {
			throw new ServiceException(e);

		} catch (IOException e) {
			throw new ServiceException(e);
		}

		/*URL obj = new URL(urlWebService);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		StringBuffer response = null;
		con.setRequestMethod("GET");

		int responseCode = con.getResponseCode();

		if(HttpURLConnection.HTTP_OK == responseCode){

			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			response  = new StringBuffer();

			while((inputLine = in.readLine()) != null){
				response.append(inputLine);
			}
			in.close();
		}

		return response.toString();*/
	}


	protected static String httpPost(Object objEnvio, Class<?> tipoObjRetorno,String urlWebService) throws IOException,ServiceException{
		Gson gson = new Gson();
		String responseJson = null;
		URL url = null;
		try{
			url = new URL(urlWebService);
			responseJson = gson.toJson(objEnvio);

			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setDoOutput(true);
			connection.setUseCaches(false);
			connection.setConnectTimeout(15000);
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setRequestProperty("Accept", "application/json");
			connection.setRequestProperty("Content-Length", Integer.toString(responseJson.length()));

			DataOutputStream stream = new DataOutputStream(connection.getOutputStream());
			stream.write(responseJson.getBytes("UTF-8"));
			stream.flush();
			stream.close();
			int statusCode = connection.getResponseCode();

			if(isStatusCodeOK(statusCode)){
				responseJson = inputStreamToString(connection.getInputStream());
				connection.disconnect();
				return responseJson;

			}else{
				throw new ServiceException("Error Interno no Servidor status code : "+statusCode
						+", Causado por : "+inputStreamToString(connection.getErrorStream()));
			}

		} catch (MalformedURLException e) {
			throw new ServiceException(e);

		} catch (ProtocolException e) {
			throw new ServiceException(e);

		} catch (IOException e) {
			throw new ServiceException(e);
		}



/*		String retorno = gson.toJson(objEnvio);
		URL url = new URL(urlWebService);

		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("POST");
		connection.setDoOutput(true);
		connection.setUseCaches(false);
		connection.setConnectTimeout(15000);
		connection.setRequestProperty("Content-Type", "application/json");
		connection.setRequestProperty("Accept", "application/json");
		connection.setRequestProperty("Content-Length", Integer.toString(retorno.length()));

		DataOutputStream stream = new DataOutputStream(connection.getOutputStream());
		stream.write(retorno.getBytes("UTF-8"));
		stream.flush();
		stream.close();
		connection.connect();

		String responseJson = inputStreamToString(connection.getInputStream());
		connection.disconnect(); 

		return responseJson;*/

	}

	private static boolean isStatusCodeOK(int statusCode) {
		return HttpURLConnection.HTTP_OK ==statusCode;
	}


	public static String inputStreamToString(InputStream is) throws IOException {
		if (is != null) {
			Writer writer = new StringWriter();

			char[] buffer = new char[1024];
			try {
				Reader reader = new BufferedReader(new InputStreamReader(is,"UTF-8"));
				int n;
				while ((n = reader.read(buffer)) != -1) {
					writer.write(buffer, 0, n);
				}
			} finally {
				is.close();
			}
			return writer.toString();
		} else {
			return "";
		}
	}
}
