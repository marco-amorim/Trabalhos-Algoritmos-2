
import java.io.*;

import javax.swing.JOptionPane;

public class Trabalho3 {

	static int tamanhoLinha = 0, tamanhoColuna = 0;
	static int i = 0, j = 0, menu;
	static int Alunas = 0;
	static final int limiteTurma = 50;
	static final String listaAlunos = "ListaAlunos.txt";

	// 1) Crie uma matriz global do tipo Aluno e com o tamanho informado pelo
	// usuário, para armazenar as seguintes informações: Nome, Nota, Genero e Idade.
	static Aluno alunos[][] = new Aluno[1000][1000];

	public static class Aluno {

		String nome, genero;
		double nota;
		int idade;

	}

	public static void Menu() {

		switch (menu) {

		case 1:
			TamanhoPlanilha();

			while (limiteTurma < (tamanhoLinha * tamanhoColuna)) {
				JOptionPane.showMessageDialog(null, "A acomodação máxima para essa sala é de 50 Alunos. \n"
						+ "Por favor, diminua o tamanho de sua planilha.");
				TamanhoPlanilha();
			}

			LerAlunos();
			break;
		case 2:
			JOptionPane.showMessageDialog(null, "A quantidade de vagas abertas na turma é: " + VagasAbertas());
			break;
		case 3:
			JOptionPane.showMessageDialog(null,
					"A quantidade de Alunas do sexo feminino é: " + QuantidadeAlunas(Alunas));
			break;
		case 4:
			JOptionPane.showMessageDialog(null, "A média de notas da turma é: " + MediaTurma());
			break;
		case 5:
			JOptionPane.showMessageDialog(null,
					"Preencha as informações a seguir para o cálculo de Alunos em recuperação final.");
			break;
		case 6:
			JOptionPane.showMessageDialog(null,
					"Preencha as informações a seguir para o cálculo da porcentagem de Alunos que atendem aos requisitos.");
			break;
		case 7:
			CriarArquivo();
			break;
		case 8:
			JOptionPane.showMessageDialog(null, LerArquivo());
			break;
		case 9:
			JOptionPane.showMessageDialog(null, "Obrigado pelo acesso.");
			break;
		default:
			JOptionPane.showMessageDialog(null, "Por favor, insira uma opção válida.");

		}
	}

	// 2) Crie um algoritmo para Ler as informações de todos os Alunos, (utilize uma
	// sub-rotina do tipo procedimento).
	public static void LerAlunos() {

		int referenciaAlunos = 1;
		for (i = 0; i < tamanhoLinha; i++) {
			for (j = 0; j < tamanhoColuna; j++) {
				alunos[i][j] = new Aluno();
				alunos[i][j].nome = JOptionPane.showInputDialog("Digite o Nome do Aluno número: " + referenciaAlunos);
				alunos[i][j].idade = Integer
						.parseInt(JOptionPane.showInputDialog("Digite a Idade do Aluno número: " + referenciaAlunos));
				alunos[i][j].genero = JOptionPane
						.showInputDialog("Digite o Gênero (M ou F) do Aluno número: " + referenciaAlunos);
				alunos[i][j].nota = Double
						.parseDouble(JOptionPane.showInputDialog("Digite a Nota do Aluno número: " + referenciaAlunos));

				referenciaAlunos++;
			}
		}
	}

	// 3) Crie uma sub-rotina do tipo função para calcular quantas vagas abertas
	// ainda existem na sua sala, (Para essa questão utilize uma variável constante
	// para definir a capacidade máxima da sala).
	public static int VagasAbertas() {

		int vagasAbertas, alunosMatriculados;

		alunosMatriculados = tamanhoLinha * tamanhoColuna;

		vagasAbertas = limiteTurma - alunosMatriculados;

		return vagasAbertas;
	}

	// 4) Faça um algoritmo que possui uma sub-rotina do tipo função para calcular a
	// quantidade de Alunas do sexo feminino, (utilize variáveis globais para a
	// passagem de parâmetro por referência).
	public static int QuantidadeAlunas(int quantidadeAlunas) {

		for (i = 0; i < tamanhoLinha; i++) {
			for (j = 0; j < tamanhoColuna; j++) {
				if (alunos[i][j].genero.equalsIgnoreCase("f")) {
					quantidadeAlunas++;
				}
			}
		}
		return quantidadeAlunas;
	}

	// 5) Faça um algoritmo para calcular a média de notas da turma, (utilize uma
	// sub-rotina do tipo função).
	public static double MediaTurma() {

		double somaNotas = 0.0, media = 0.0;

		for (i = 0; i < tamanhoLinha; i++) {
			for (j = 0; j < tamanhoColuna; j++) {
				somaNotas += alunos[i][j].nota;
			}
		}

		media = somaNotas / (tamanhoLinha * tamanhoColuna);

		return media;
	}

	// 6) Faça um algoritmo para mostrar a quantidade de Alunos que ficaram em
	// recuperação final, peça para o usuário informar uma nota de corte, (utilize
	// uma sub-rotina do tipo função).
	public static int RecFinal(double notaDeCorte) {

		int alunosRecFinal = 0;

		for (i = 0; i < tamanhoLinha; i++) {
			for (j = 0; j < tamanhoColuna; j++) {
				if (alunos[i][j].nota < notaDeCorte) {

					alunosRecFinal++;
				}
			}
		}
		return alunosRecFinal;
	}

	// 7) Faça um algoritmo para calcular a porcentagem de Alunos que estejam acima
	// ou na mesma situação dos requisitos de Nota e Idade, peça as respectivas
	// informações ao usuário, (utilize uma sub-rotina do tipo função).
	public static double PorcentagemNotaIdade(double notaMinima, int idadeMinima) {

		int quantidadeAlunos = 0;
		double porcentagem = 0.0;

		for (i = 0; i < tamanhoLinha; i++) {
			for (j = 0; j < tamanhoColuna; j++) {

				if (alunos[i][j].nota >= notaMinima && alunos[i][j].idade >= idadeMinima) {

					quantidadeAlunos++;
				}
			}
		}

		porcentagem = (quantidadeAlunos * 100) / (tamanhoLinha * tamanhoColuna);
		return porcentagem;
	}

	public static void TamanhoPlanilha() {

		tamanhoLinha = Integer
				.parseInt(JOptionPane.showInputDialog("Digite o tamanho da Linha para a sua planilha de Alunos."));

		tamanhoColuna = Integer
				.parseInt(JOptionPane.showInputDialog("Digite o tamanho da Coluna para sua planilha de Alunos."));

	}

	public static void CriarArquivo() {

		String nomeLista = "";
		String generoLista = "";
		double notaLista = 0.0;
		int idadeLista = 0;

		try {

			BufferedWriter lista = null;
			String adicionar = JOptionPane.showInputDialog("Deseja salvar os dados dos Alunos em arquivo? (S/N)");

			if (adicionar.equalsIgnoreCase("S")) {

				lista = new BufferedWriter(new FileWriter(new File(listaAlunos), true));

			} else {

				lista = new BufferedWriter(new FileWriter(new File(listaAlunos)));
			}

			for (i = 0; i < tamanhoLinha; i++) {
				for (j = 0; j < tamanhoColuna; j++) {

					nomeLista = alunos[i][j].nome;
					generoLista = alunos[i][j].genero;
					notaLista = alunos[i][j].nota;
					idadeLista = alunos[i][j].idade;
					lista.write("Nome: " + nomeLista + " / " + "Gênero: " + generoLista + " / " + "Nota: " + notaLista
							+ " / " + "Idade: " + idadeLista);
					lista.newLine();
				}
			}

			lista.close();

		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Não foi possível abrir o arquivo para a escrita.");
		}
	}

	public static String LerArquivo() {

		String mostrar = "";
		String erro = "Erro ao abrir arquivo para a leitura.";

		try {

			BufferedReader ler = new BufferedReader(new FileReader(new File(listaAlunos)));
			String dados = ler.readLine();

			while (dados != null) {
				mostrar = mostrar + dados + "\n";
				dados = ler.readLine();
			}

			ler.close();

		} catch (IOException e) {

			return erro;

		}

		return mostrar;
	}

	public static void main(String[] args) {

		double notaDeCorte, notaQuestao7;
		int idadeQuestao7;

		do {

			menu = Integer.parseInt(JOptionPane.showInputDialog("Por favor, digite a opção desejada: \n"
					+ "1) Para criar sua planilha e preencher as informações dos Alunos. \n"
					+ "2) Para calcular a quantidade de vagas abertas na turma. \n"
					+ "3) Para calcular a quantidade de Alunas do sexo feminino. \n"
					+ "4) Para calcular a média de notas da turma. \n"
					+ "5) Para quantidade de Alunos em recuperação final. \n"
					+ "6) Para calcular a porcentagem de Alunos que se enquadram nos requisitos de nota e idade. \n"
					+ "7) Para criar um arquivo com os dados dos Alunos. \n"
					+ "8) Para mostrar o arquivo com os dados dos Alunos. \n" + "9) Para sair."));

			Menu();

			if (menu == 5) {

				notaDeCorte = Double
						.parseDouble(JOptionPane.showInputDialog("Digite a nota de corte para a recuperação final."));

				JOptionPane.showMessageDialog(null,
						"A quantidade de Alunos que ficaram em recuperação final é: " + RecFinal(notaDeCorte));
			}

			if (menu == 6) {

				notaQuestao7 = Double.parseDouble(JOptionPane.showInputDialog("Digite a nota mínima."));
				idadeQuestao7 = Integer.parseInt(JOptionPane.showInputDialog("Digite a idade mínima."));

				JOptionPane.showMessageDialog(null, "A porcentagem de Alunos que se enquadram nos requisitos é: "
						+ PorcentagemNotaIdade(notaQuestao7, idadeQuestao7) + "%");
			}

		} while (menu != 9);
	}
}