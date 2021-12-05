package br.com.unisantos;

import java.net.*;
import java.io.*;

public class ClientAPI {
	private String url = "http://localhost:5000/";
	
	public ClientAPI() {
		
	}
	
	public String getGenero(String nome) throws Exception {
		String urlToSend = this.url+"?nome="+nome;
		URL request = new URL(urlToSend);
		URLConnection yc = request.openConnection();
		BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
		String inputLine, genero = "";
		
		while ((inputLine = in.readLine()) != null)
			genero = inputLine;
		in.close();
		return genero.replace('"', ' ');
	}
}