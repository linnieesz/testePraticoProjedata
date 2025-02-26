package br.com.etads;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		OpcoesFuncionario opcoesFuncionario = new OpcoesFuncionario();

		System.out.print("Gostaria de adentrar no sistema?\n1. Sim.\n2. Não.\nInsira o número: ");
		String system = scanner.nextLine();

		while (system.equals("1")) {
			System.out.println(
					"O que deseja fazer?\n1. Adicionar funcionário.\n2. Remover funcionário.\n3. Visualizar tabela de funcionários.\n4. Visualizar tabela de funcionários agrupados por função.\n5. Mostrar funcionários que nasceram os meses 10 e 12.\n6. Mostrar o funcionário mais velho.\n7. Mostrar o total entre os salários dos funcionários.\n8. Mostrar a quantidade de salários mínimos de cada funcionário.\n9. Lista de funcionários em ordem alfabética.\n10.Dê um aumento para os funcionários.\n11. Finalizar.");
			String systemOpt = scanner.nextLine();

			if (systemOpt.equals("1")) {
				System.out.println("Insira o novo funcionário: ");

				System.out.println("Nome: ");
				String nameFunc = scanner.nextLine();

				System.out.println("Data de nascimento (dd/MM/yyyy): ");
				String dataFuncString = scanner.nextLine();

				LocalDate dataFunc = LocalDate.parse(dataFuncString, formatter);

				System.out.println("Salário: ");
				double salaryFunc = scanner.nextDouble();
				scanner.nextLine();

				System.out.println("Função: ");
				String funcaoFunc = scanner.nextLine();

				opcoesFuncionario.adicionarFuncionario(new Funcionario(nameFunc, dataFunc, salaryFunc, funcaoFunc));

			} else if (systemOpt.equals("2")) {
				System.out.println("Insira o nome do funcionário a ser removido:");
				String nomeFunc = scanner.nextLine();

				opcoesFuncionario.removerFuncionarioPorNome(nomeFunc);

				System.out.println("Lista de Funcionários:");
				for (Funcionario f : opcoesFuncionario.getFuncionarios()) {
					System.out.println(f);
				}

				System.out.println("Aperte enter para continuar.");
				scanner.nextLine();

			} else if (systemOpt.equals("3")) {
				System.out.println("Lista de Funcionários:");
				for (Funcionario f : opcoesFuncionario.getFuncionarios()) {
					System.out.println(f);
				}

				System.out.println("Aperte enter para continuar.");
				scanner.nextLine();

			} else if (systemOpt.equals("4")) {
				System.out.println("Lista de funcionários agrupados por função: ");
				Map<String, List<Funcionario>> agrupados = opcoesFuncionario.agruparFuncionariosPorFuncao();

				for (Map.Entry<String, List<Funcionario>> entry : agrupados.entrySet()) {
					System.out.println("\nFunção: " + entry.getKey());
					for (Funcionario f : entry.getValue()) {
						f.exibirInformacoes();
					}
				}

				System.out.println("Aperte enter para continuar.");
				scanner.nextLine();

			} else if (systemOpt.equals("5")) {
				System.out.println("Estes são os funcionários que fazem aniversário em Outubro e Dezembro: ");

				for (Funcionario f : opcoesFuncionario.getFuncionarios()) {
					int mesNascimento = f.getDataNascimento().getMonthValue();

					if (mesNascimento == 10 || mesNascimento == 12) {
						f.exibirInformacoes();
					}
				}

				System.out.println("Aperte enter para continuar.");
				scanner.nextLine();

			} else if (systemOpt.equals("6")) {
				System.out.println("Este é o funcionário mais velho: ");

				Funcionario funcionarioMaisVelho = null;

				for (Funcionario f : opcoesFuncionario.getFuncionarios()) {
					if (funcionarioMaisVelho == null) {
						funcionarioMaisVelho = f;
					} else {
						if (f.getDataNascimento().isBefore(funcionarioMaisVelho.getDataNascimento())) {
							funcionarioMaisVelho = f;
						}
					}
				}

				if (funcionarioMaisVelho != null) {
					System.out.println("O funcionário mais velho: " + funcionarioMaisVelho.getNome() + "\nIdade: "
							+ funcionarioMaisVelho.getIdade());
				} else {
					System.out.println("Não há funcionários cadastrados.");
				}

				System.out.println("Aperte enter para continuar.");
				scanner.nextLine();

			} else if (systemOpt.equals("7")) {
				System.out.println("O total entre os salários dos funcionários: ");

				double somaSalarios = 0;

				for (Funcionario f : opcoesFuncionario.getFuncionarios()) {
					somaSalarios += f.getSalario();
				}

				System.out.println("A soma total dos salários é: R$" + somaSalarios);

				System.out.println("Aperte enter para continuar.");
				scanner.nextLine();

			} else if (systemOpt.equals("8")) {
				System.out.println("Quantidade de salários mínimos de cada funcionário.");
				for (Funcionario f : opcoesFuncionario.getFuncionarios()) {
					System.out.println(f.getNome() + " ganha " + String.format("%.2f", f.calcularSalariosMinimos())
							+ " salários mínimos.");
				}
			} else if (systemOpt.equals("9")) {
				System.out.println("Lista de funcionários por ordem alfabética.");

				opcoesFuncionario.getFuncionarios().stream()
						.sorted((fun1, fun2) -> fun1.getNome().compareTo(fun2.getNome()))
						.forEach(Funcionario::exibirInformacoes);

				System.out.println("Aperte enter para continuar.");
				scanner.nextLine();

			} else if (systemOpt.equals("10")) {
				System.out.println("Digite a porcentagem de aumento geral a ser aplicada a todos os funcionários:");
				double porcentagemAumento = scanner.nextDouble();
				scanner.nextLine(); 

				opcoesFuncionario.aplicarAumentoGeral(porcentagemAumento);

				System.out.println("Aperte enter para continuar.");
				scanner.nextLine();
			} else if (systemOpt.equals("11")) {
				System.out.println("Até!");
				break;
			} else {
				System.out.println("Opção não compreendida. Use o número, por favor.");
			}
		}

		System.out.println("Finalizado.");
		scanner.close();

	}
}
