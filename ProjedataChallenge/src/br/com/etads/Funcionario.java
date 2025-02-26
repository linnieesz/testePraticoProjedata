package br.com.etads;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Funcionario extends Pessoa {
	private double salario;
	private String funcao;
	private static final DecimalFormat FORMATADOR_SALARIO = new DecimalFormat("#,##0.00");
	private static final DateTimeFormatter FORMATADOR_DATA = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	public Funcionario(String nome, LocalDate dataNascimento, double salario, String funcao) {
		super(nome, dataNascimento);
		this.salario = salario;
		this.funcao = funcao;
	}

	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}

	public String getFuncao() {
		return funcao;
	}

	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}

	@Override
	public void exibirInformacoes() {
		String nomeCapitalizado = capitalizeFirstLetter(getNome());
		String funcaoCapitalizada = capitalizeFirstLetter(funcao);

		System.out.println(
				"Nome: " + nomeCapitalizado + ", Data de Nascimento: " + getDataNascimento().format(FORMATADOR_DATA)
						+ ", Função: " + funcaoCapitalizada + ", Salário: R$" + FORMATADOR_SALARIO.format(salario));
	}

	public void exibirInformacoesPessoais() {
		super.exibirInformacoes();
	}

	@Override
	public String toString() {
		String nomeCapitalizado = capitalizeFirstLetter(getNome());
		String funcaoCapitalizada = capitalizeFirstLetter(funcao);

		return "Nome: " + nomeCapitalizado + ", Data de Nascimento: " + getDataNascimento().format(FORMATADOR_DATA)
				+ ", Função: " + funcaoCapitalizada + ", Salário: R$" + FORMATADOR_SALARIO.format(salario);
	}

	public void aplicarAumento(double porcentagem) {
		if (porcentagem < 0) {
			System.out.println("Porcentagem inválida. Não pode ser negativa.");
			return;
		}
		String nomeCapitalizado = capitalizeFirstLetter(getNome());
		double aumento = salario * (porcentagem / 100);
		salario += aumento;
		System.out.println("Aumento de " + porcentagem + "% aplicado com sucesso para " + nomeCapitalizado + ". Novo salário: R$"
				+ FORMATADOR_SALARIO.format(salario));
	}

	public double calcularSalariosMinimos() {
		final double SALARIO_MINIMO = 1212.0; 
		return salario / SALARIO_MINIMO;
	}

	private String capitalizeFirstLetter(String text) {
		if (text == null || text.isEmpty()) {
			return text;
		}
		String[] words = text.split(" ");
		StringBuilder capitalizedText = new StringBuilder();

		for (String word : words) {
			if (!word.isEmpty()) {
				capitalizedText.append(word.substring(0, 1).toUpperCase()).append(word.substring(1).toLowerCase())
						.append(" ");
			}
		}
		return capitalizedText.toString().trim();
	}
}
