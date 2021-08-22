package view;

import javax.swing.JOptionPane;

import controller.KillController;

public class Main {

	public static void main(String[] args) {
		KillController kill = new KillController();
		int opcao;
		
		try {
			do {
				opcao = Integer.parseInt(JOptionPane.showInputDialog("Digite o que deseja fazer ?\n"
						+ "1 - Listar os processos no console\n"
						+ "2 - Matar um processo por pid\n"
						+ "3 - Matar um processo por nome\n"
						+ "4 - Finalizar"));
				switch(opcao) {
					case 1:
						kill.listaProcessos();
						break;
					case 2:
						int pid = Integer.parseInt(JOptionPane.showInputDialog("Informe o pid do processo"));
						kill.mataPid(pid);
						break;
					case 3:
						String nome = JOptionPane.showInputDialog("Informe o pid do processo");
						kill.mataNome(nome);
						break;
					case 4:
						JOptionPane.showMessageDialog(null, "Até a próxima!!!");
						break;
					default:
						JOptionPane.showMessageDialog(null, "Opção inválida, digite novamente!");
				}
			} while(opcao != 4);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}	
	}

}
