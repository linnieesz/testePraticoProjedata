package br.com.etads;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class Pessoa {
	private String nome;
	private LocalDate dataNascimento;
	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	public Pessoa(String nome, LocalDate dataNascimento) {
		this.nome = nome;
		this.dataNascimento = dataNascimento;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public int getIdade() {
		return Period.between(this.dataNascimento, LocalDate.now()).getYears();
	}

	public void exibirInformacoes() {
		System.out.println("Nome: " + nome + ", Data de Nascimento: " + dataNascimento.format(FORMATTER));
	}

	@Override
	public String toString() {
		return "Nome: " + nome + ", Data de Nascimento: " + dataNascimento.format(FORMATTER) + ", Idade: " + getIdade()
				+ " anos";
	}
}
