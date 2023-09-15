package data;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CriadorDeTabelas {

	public void construirTabelas(Connection conexao) {

		String sql = BancoDeDados.criarSql();

		try {
			Statement statement = conexao.createStatement();
			statement.executeUpdate(sql);
			System.out.println("Tabela criada com sucesso!");
		} catch (SQLException e) {
			System.out.println("Erro na criação da tabela!");
			System.out.println("Erro: " + e.getMessage());
		}
	}

}
