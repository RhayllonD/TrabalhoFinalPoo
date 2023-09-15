package data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import model.Estudante;

public class BancoDeDados {

	private Connection conexao;
	private Scanner scanner;

	public BancoDeDados(Connection conexao, Scanner scanner) {
		this.conexao = conexao;
		this.scanner = scanner;
	}

	protected static String criarSql() {

		String sql = "CREATE TABLE IF NOT EXISTS estudantes (id SERIAL PRIMARY KEY, nome VARCHAR(100) NOT NULL, curso VARCHAR(100) NOT NULL);";
		return sql;
	}

	public void adicionarEstudante() {

		String nome;
		String curso;

		System.out.print("Insira o nome do estudante: ");
		nome = scanner.nextLine();
		System.out.print("Insira o curso do estudante: ");
		curso = scanner.nextLine();
		Estudante estudante = new Estudante(nome, curso);

		String sql = "INSERT INTO estudantes(nome, curso) VALUES ('" + estudante.getNome() + "', '"
				+ estudante.getCurso() + "')";

		Statement stmt;
		try {
			stmt = conexao.createStatement();
			stmt.executeUpdate(sql);
			System.out.println("\nEstudante adicionado com sucesso!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void editarEstudanteNome() {

		String nome;

		scanner.nextLine();
		System.out.print("Insira o nome do estudante que deseja editar: ");
		nome = scanner.nextLine();
		System.out.print("Escreva o novo nome do estudante: ");
		String novoNome = scanner.nextLine();

		String sql = "UPDATE estudantes SET nome=" + "'" + novoNome + "'" + " WHERE nome=" + "'" + nome + "'";

		Statement stmt;
		try {
			stmt = conexao.createStatement();
			int resultado = stmt.executeUpdate(sql);
			if (resultado <= 0) {
				System.out.println("\nOperação não realizada! Estudante inexistente com esse nome.");
			} else {
				System.out.println("\nEstudante editado com sucesso!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void editarEstudanteCurso() {

		String nome;

		scanner.nextLine();
		System.out.print("Insira o nome do estudante que deseja editar: ");
		nome = scanner.nextLine();
		System.out.print("Escreva o novo nome do curso do estudante: ");
		String novoNomeCurso = scanner.nextLine();

		String sql = "UPDATE estudantes SET curso=" + "'" + novoNomeCurso + "'" + " WHERE nome=" + "'" + nome + "'";

		Statement stmt;
		try {
			stmt = conexao.createStatement();
			int resultado = stmt.executeUpdate(sql);
			if (resultado <= 0) {
				System.out.println("\nOperação não realizada! Estudante inexistente com esse nome.");
			} else {
				System.out.println("\nEstudante editado com sucesso!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void editarEstudanteNomeECurso() {

		String nome;
		scanner.nextLine();
		System.out.print("Insira o nome do estudante que deseja editar: ");
		nome = scanner.nextLine();
		System.out.print("Escreva o novo nome do estudante: ");
		String novoNome = scanner.nextLine();
		System.out.print("Escreva o novo nome do curso do estudante: ");
		String novoNomeCurso = scanner.nextLine();

		String sql = "UPDATE estudantes SET nome=" + "'" + novoNome + "', " + "curso=" + "'" + novoNomeCurso + "'"
				+ " WHERE nome=" + "'" + nome + "'";

		Statement stmt;
		try {
			stmt = conexao.createStatement();
			int resultado = stmt.executeUpdate(sql);
			if (resultado <= 0) {
				System.out.println("\nOperação não realizada! Estudante inexistente com esse nome.");
			} else {
				System.out.println("\nEstudante editado com sucesso!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void removerEstudanteNome() {

		String nome;

		scanner.nextLine();
		System.out.print("Insira o nome do estudante que deseja remover: ");
		nome = scanner.nextLine();

		String sql = "DELETE FROM estudantes WHERE nome=" + "'" + nome + "'";
		Statement stmt;
		try {
			stmt = conexao.createStatement();
			int resultado = stmt.executeUpdate(sql);
			if (resultado <= 0) {
				System.out.println("\nOperação nao realizada! Estudante inexistente com esse nome.");
			} else {
				System.out.println("\nEstudante removido com sucesso!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void removerEstudanteId() {

		String id;

		scanner.nextLine();
		System.out.print("Insira o ID do estudante que deseja remover: ");
		id = scanner.nextLine();

		String sql = "DELETE FROM estudantes WHERE id=" + "'" + id + "'";
		Statement stmt;
		try {
			stmt = conexao.createStatement();
			int resultado = stmt.executeUpdate(sql);
			if (resultado <= 0) {
				System.out.println("\nOperação nao realizada! Estudante inexistente com esse ID.");
			} else {
				System.out.println("\nEstudante removido com sucesso!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void removerEstudantesCurso() {

		String nomeCurso;

		scanner.nextLine();
		System.out.print("Insira o nome do curso que deseja remover todos os estudantes: ");
		nomeCurso = scanner.nextLine();

		String sql = "DELETE FROM estudantes WHERE curso=" + "'" + nomeCurso + "'";
		Statement stmt;
		try {
			stmt = conexao.createStatement();
			int resultado = stmt.executeUpdate(sql);
			if (resultado <= 0) {
				System.out.println("\nOperação nao realizada! Curso inexistente.");
			} else {
				System.out.println("\nEstudantes removidos com sucesso!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void listarEstudantes() {

		String sql = "SELECT * FROM estudantes";
		Statement stmt;
		try {
			stmt = conexao.createStatement();

			ResultSet rs = stmt.executeQuery(sql);

			if (!rs.next()) {
				System.out.println("\nNão há estudantes registrados!");
			} else {
				System.out.println("\n\t\t  Estudantes\n");
				do {
					System.out.println("\tID: " + rs.getInt("id") + " | Nome: " + rs.getString("nome") + " | Curso: "
							+ rs.getString("curso"));
				} while (rs.next());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void listarEstudantesPorCurso() {

		String cursoNome;

		scanner.nextLine();
		System.out.print("Insira o nome do curso que deseja listar todos os estudantes: ");
		cursoNome = scanner.nextLine();

		String sql = "SELECT * FROM estudantes WHERE curso=" + "'" + cursoNome + "'";
		Statement stmt;
		try {
			stmt = conexao.createStatement();

			ResultSet rs = stmt.executeQuery(sql);

			if (!rs.next()) {
				System.out.println("\nNão há estudantes registrados nesse curso!");
			} else {
				System.out.println("\nEstudantes no curso de " + cursoNome + ": \n");
				do {
					System.out.println("\tId: " + rs.getInt("id") + " | Nome: " + rs.getString("nome") + " | Curso: "
							+ rs.getString("curso"));
				} while (rs.next());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void listarOEstudantesPorNome() {

		String estudanteNome;

		scanner.nextLine();
		System.out.print("Insira o nome do estudante que deseja listar: ");
		estudanteNome = scanner.nextLine();

		String sql = "SELECT * FROM estudantes WHERE nome=" + "'" + estudanteNome + "'";
		Statement stmt;
		try {
			stmt = conexao.createStatement();

			ResultSet rs = stmt.executeQuery(sql);

			if (!rs.next()) {
				System.out.println("\nNão há estudante registrado com esse nome!");
			} else {
				System.out.println("\nEstudante " + estudanteNome + ": \n");
				do {
					System.out.println("\tId: " + rs.getInt("id") + " | Nome: " + rs.getString("nome") + " | Curso: "
							+ rs.getString("curso"));
				} while (rs.next());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}