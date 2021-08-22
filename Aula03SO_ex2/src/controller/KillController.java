package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.swing.JOptionPane;

public class KillController {

	public KillController() {}
	
	private String os() {	
		return System.getProperty("os.name");
	}
	
	private void executaProcesso(String processo) {
		try {
			Runtime.getRuntime().exec(processo);
		} catch(Exception e) {
			String msgErro = e.getMessage();
			if (msgErro.contains("740")){				
				StringBuffer buffer = new StringBuffer();
				buffer.append("cmd /c");
				buffer.append(" ");
				buffer.append(processo);
				try {
					Runtime.getRuntime().exec(buffer.toString());
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			} else {
				System.err.println(msgErro);
			}
		}		
	}
	
	public void listaProcessos() {
		String os = os();
		String comando = os.contains("Windows") ?
				"TASKLIST /FO TABLE" : "ps -ef";
		
		Process p;
		try {
			p = Runtime.getRuntime().exec(comando);
			InputStream fluxo = p.getInputStream();
			InputStreamReader leitor = new InputStreamReader(fluxo);
			BufferedReader buffer = new BufferedReader(leitor);
			String linha = buffer.readLine();
			while(linha != null) {
				System.out.println(linha);
				linha = buffer.readLine();
			}
			buffer.close();
			leitor.close();
			fluxo.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void mataPid(int processo) {
		String os = os();
		String comando = os.contains("Windows") ?
				"TASKKILL /PID " : "kill -9 ";
		comando += Integer.toString(processo);
		
		executaProcesso(comando);
		
		JOptionPane.showMessageDialog(null, "O processo faleceu :)");
	}
	
	public void mataNome(String processo) {
		String os = os();
		String comando = os.contains("Windows") ?
				"TASKKILL /IM " : "pkill -f ";
		comando += processo;
		
		executaProcesso(comando);
		
		JOptionPane.showMessageDialog(null, "O processo faleceu :)");
	}
}
