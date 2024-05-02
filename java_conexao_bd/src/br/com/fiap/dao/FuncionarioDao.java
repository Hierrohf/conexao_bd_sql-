package br.com.fiap.dao;//Declara o pacote onde a classe FuncionarioDao está localizada.

import java.sql.Connection;//Connection é uma interface que representa uma conexão com um banco de dados
import java.sql.Date;// achei meio complexo então deixei uma esplicação do oraculo la no final do codigo pra vc/eu lembra caso esqueça  
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;// É uma classe em Java que implementa a interface List. Ela fornece uma implementação de uma lista redimensionável, que é semelhante a um array, mas pode crescer ou encolher automaticamente conforme necessário. Isso significa que você pode adicionar, remover e acessar elementos da lista de maneira flexível.
import java.util.List;
import br.com.fiap.model.Departamento;
import br.com.fiap.model.Funcionario;

public class FuncionarioDao {//Declara a classe FuncionarioDao, que será responsável por manipular as operações relacionadas aos funcionários no banco de dados.

	// classe para manipular Funcionario, CRUD, persistir no DB (tinha esquecido o do que crud kkkkkkkk : CRUD é um acrônimo que representa as quatro operações básicas usadas em bancos de dados relacionais e sistemas de informação: Create (Criar), Read (Ler), Update (Atualizar) e Delete (Excluir).)
	// tem os métodos para realizar as operações
	// gravar um Funcionário
	// classe - responsabilidade única FuncionarioDao só sabe persistir Funcionário

	
	private Connection conexao;//Declara uma variável conexao do tipo Connection, que será usada para estabelecer a conexão com o banco de dados.

	public FuncionarioDao(Connection conexao) {//Declara um construtor que recebe uma conexão como parâmetro e atribui essa conexão à variável conexao.
		this.conexao = conexao;
	}

	public void gravar(Funcionario funcionario) {//Declara um método gravar que recebe um objeto Funcionario como parâmetro e é responsável por salvar esse funcionário no banco de dados.

		// preparando o SQL // Cria uma string sql que contém a instrução SQL para inserir um novo funcionário na tabela tb_funcionario.
		String sql = "INSERT INTO tb_funcionario(id, nome, salario, data_admissao, departamento_id) "
				+ "VALUES(sq_fun.nextval, ?, ?, ?, ?)";

		// classe para enviar as instruções ao DB
		try {
			PreparedStatement ps = conexao.prepareStatement(sql);//Cria um objeto PreparedStatement chamado ps usando a conexão com o banco de dados e a instrução SQL sql criada anteriormente
			//Define os valores dos parâmetros na consulta SQL preparada. Aqui, estamos definindo o nome e o salário do funcionário.
			ps.setString(1, funcionario.getNome());
			ps.setDouble(2, funcionario.getSalario());

			// data no Oracle é DATE e no Java LocalDate
			// precisamos converter
			// Date do java.sql
			// valueOf - converte
			//Converte a data de admissão do funcionário para o formato Date do SQL e define o valor do parâmetro na consulta SQL preparada. Também define o ID do departamento do funcionário.
			Date data = Date.valueOf(funcionario.getDataAdmissao());
			ps.setDate(3, data);
			ps.setInt(4, funcionario.getDepartamento().getId());

			// enviar o comando para DB // Executa a consulta SQL preparada para inserir o funcionário no banco de dados.
			ps.execute();

			// fecha statement
			ps.close();

			// fecha conexão
			conexao.close();

			System.out.println("Registro inserido com sucesso!");//Imprime uma mensagem indicando que o registro foi inserido com sucesso.

			//caso de ruim Captura e trata uma exceção SQLException, imprimindo a mensagem de erro no console. 
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void excluir(Funcionario funcionario) {//Declara um metodo para excluir uma linha da tabela funcionario

		String sql = "DELETE FROM tb_funcionario WHERE id = ?";//cria uma string sql que contém a instrução SQL para excluir um funcionário da tabela tb_funcionario com base no ID.

		//tratamento de excecões
		try {
			PreparedStatement ps = conexao.prepareStatement(sql);//Esta linha cria um objeto PreparedStatement chamado ps usando a conexão com o banco de dados e a instrução SQL sql criada anteriormente.

			ps.setInt(1, funcionario.getId());//Esta linha define o valor do primeiro parâmetro (?) na consulta SQL preparada como o ID do funcionário que será excluído. O método setId() é usado para recuperar o ID do funcionário a ser excluído do objeto funcionario, e ps.setInt() atribui esse valor ao parâmetro na consulta SQL

			ps.execute();//Esta linha executa a consulta SQL preparada para excluir o funcionário do banco de dados.

			//Estas linhas fecham o objeto PreparedStatement e a conexão com o banco de dados para liberar recursos após a conclusão da consulta.
			ps.close();
			conexao.close();

			System.out.println("Registro excluído com sucesso!");
		} catch (SQLException e) {//trata exception no geral

			System.err.println("Problema ao tentar excluir registro");
			e.printStackTrace();
		}

	}

	public void atualizar(Funcionario funcionario) {//Cria um metodo para fazer atualizações na tabela funcionario

		String sql = "UPDATE tb_funcionario SET salario = ?, departamento_id = ? WHERE id = ?";//Esta linha cria uma string sql que contém a instrução SQL para atualizar o salário e o ID do departamento de um funcionário na tabela tb_funcionario com base no ID do funcionário.

		try {
			PreparedStatement ps = conexao.prepareStatement(sql);

			ps.setDouble(1, funcionario.getSalario());//definem os valores dos parâmetros na consulta SQL preparada. ps.setDouble(1, funcionario.getSalario()) define o primeiro parâmetro como o salário do funcionário,
			ps.setInt(2, funcionario.getDepartamento().getId());//ps.setInt(2, funcionario.getDepartamento().getId()) define o segundo parâmetro como o ID do departamento do funcionário
			ps.setInt(3, funcionario.getId());//ps.setInt(3, funcionario.getId()) define o terceiro parâmetro como o ID do funcionário a ser atualizado.

			ps.execute();//Essa linha executa a consulta SQL preparada para atualizar o funcionário no banco de dados.

			//Essas linhas fecham o objeto PreparedStatement e a conexão com o banco de dados para liberar recursos após a conclusão da consulta.
			ps.close();
			conexao.close();

			System.out.println("Update com sucesso");
			
		} catch (SQLException e) {//trata qualquer exceção no geral
			System.err.println("Problema ao atualizar registro");
			e.printStackTrace();//e.printStackTrace() é um método utilizado para imprimir informações sobre a exceção que ocorreu. Ele imprime a pilha de chamadas de métodos que levou à exceção, junto com o tipo da exceção e sua mensagem de erro, o que pode ser extremamente útil para diagnóstico de problemas.
		}

	}

	public List<Funcionario> listarTodos() {//Metodo para criar uma lista/retornar todas as informações da tabela funcionario por ordem do ID 

		String sql = "SELECT * FROM tb_funcionario ORDER BY id";//Define uma string sql contendo a consulta SQL para selecionar todos os registros da tabela tb_funcionario, ordenados pelo ID.

		List<Funcionario> list = new ArrayList<Funcionario>();//Cria uma nova lista chamada list para armazenar os objetos Funcionario.

		try {

			PreparedStatement ps = conexao.prepareStatement(sql);//está criando um objeto PreparedStatement associado a uma conexão com o banco de dados, e essa instrução SQL está pronta para ser executada. Este objeto ps pode ser usado para definir parâmetros na consulta e executá-la de forma segura e eficiente no banco de dados.

			// resultado -> ResultSet
			// manda executar no DB
			ResultSet rs = ps.executeQuery();//Executa a consulta SQL preparada e armazena o resultado em um objeto ResultSet chamado rs. Este objeto contém os resultados da consulta, que podem ser iterados para acessar os dados do banco de dados.

			while (rs.next()) {//Inicia um loop while para iterar sobre cada linha no resultado do ResultSet. O método next() move o cursor para a próxima linha no resultado, e o loop continua enquanto houver mais linhas.
				Funcionario funcionario = new Funcionario();//Cria um novo objeto Funcionario chamado funcionario para armazenar os dados de um funcionário.
				funcionario.setId(rs.getInt("id")); // nome da coluna no DB//Define o ID do funcionário com base no valor da coluna "id" no resultado do ResultSet
				
				//Define o nome e o salário do funcionário com base nos valores das colunas "nome" e "salario" no resultado do ResultSet, respectivamente.
				funcionario.setNome(rs.getString("nome"));
				funcionario.setSalario(rs.getDouble("salario"));

				//Define a data de admissão do funcionário com base no valor da coluna "data_admissao" no resultado do ResultSet. A data é convertida de java.sql.Date para java.time.LocalDate.
				Date data = rs.getDate("data_admissao");
				funcionario.setDataAdmissao(data.toLocalDate());

				int idDepartamento = rs.getInt("departamento_id");//Recupera o ID do departamento do funcionário do resultado do ResultSet

				//Cria uma instância de DepartamentoDao e usa-a para buscar o departamento do funcionário pelo ID.
				DepartamentoDao dao = new DepartamentoDao(Conexao.abrirConexao());
				Departamento departamento = dao.buscarDepartamentoPorId(idDepartamento);

				funcionario.setDepartamento(departamento);//Define o departamento do funcionário com base no departamento recuperado
				list.add(funcionario);//Adiciona o objeto funcionario à lista list.
			}

			//Fecha o PreparedStatement, o ResultSet e a conexão com o banco de dados para liberar recursos após a conclusão da consulta
			ps.close();
			rs.close();
			conexao.close();

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return list;//retorna a lista de funcionários.

	}

	public Funcionario buscarFuncionarioPorId(int id) {//cria metodo para buscar funcionario por ID

		String sql = "SELECT * FROM tb_funcionario WHERE id = ?";//Define uma string sql contendo a consulta SQL para selecionar um funcionário da tabela tb_funcionario com base no ID fornecido.

		//tratamento de exceções
		try {
			//Cria um objeto PreparedStatement chamado ps usando a conexão com o banco de dados e a consulta SQL sql criada anteriormente. Em seguida, configura o parâmetro na consulta preparada com o valor do ID fornecido.
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();//Executa a consulta SQL preparada e armazena o resultado em um objeto ResultSet chamado rs. Este objeto contém os resultados da consulta, que podem ser iterados para acessar os dados do banco de dados.

			Funcionario funcionario = new Funcionario();//Cria um novo objeto Funcionario chamado funcionario para armazenar os dados do funcionário que será buscado.

			if (rs.next()) {//Verifica se há pelo menos uma linha de resultado no ResultSet. O método next() move o cursor para a próxima linha no resultado e retorna true se houver mais linhas, e false se não houver.
				funcionario.setId(rs.getInt("id")); // nome da coluna no DB

				//Define o nome e o salário do funcionário com base nos valores das colunas "nome" e "salario" no resultado do ResultSet
				funcionario.setNome(rs.getString("nome"));
				funcionario.setSalario(rs.getDouble("salario"));

				//Define a data de admissão do funcionário com base no valor da coluna "data_admissao" no resultado do ResultSet. A data é convertida de java.sql.Date para java.time.LocalDate.
				Date data = rs.getDate("data_admissao");
				funcionario.setDataAdmissao(data.toLocalDate());

				int idDepartamento = rs.getInt("departamento_id");//Recupera o ID do departamento do funcionário do resultado do ResultSet.

				//Cria uma instância de DepartamentoDao e usa-a para buscar o departamento do funcionário pelo ID.
				DepartamentoDao dao = new DepartamentoDao(conexao);
				Departamento departamento = dao.buscarDepartamentoPorId(idDepartamento);

				funcionario.setDepartamento(departamento);//Define o departamento do funcionário com base no departamento recuperado.

				System.out.println(funcionario);//mprime os dados do funcionário no console

				//fecha o PreparedStatement, o ResultSet e a conexão com o banco de dados para liberar recursos após a conclusão da consulta.
				ps.close();
				rs.close();
				conexao.close();
				
				return funcionario;//retorna o objeto funcionario//tve um pouco de dificuldade entao la no final tem uma explicação do oracolo
			}

		} catch (SQLException e) {//trata exceções SQL no geral

			e.printStackTrace();//Imprime a pilha de chamadas de métodos que levou à exceção no console. Isso ajuda no diagnóstico de problemas
		}
		
		return null;//Retorna null se não houver funcionário encontrado com o ID fornecido		

	}

}

/* A classe java.sql.Date é uma parte do Java que ajuda a lidar com datas em contextos de banco de dados. Aqui está um exemplo mais direto:

Imagine que você tem um banco de dados com uma tabela que registra as datas de nascimento dos usuários. Quando você recupera essas datas do banco de dados em seu programa Java, elas podem ser representadas como objetos do tipo java.sql.Date. Isso significa que você pode usar a classe Date para armazenar e manipular essas datas no seu código Java.

Por exemplo, você pode usar java.sql.Date para:

Armazenar Datas: Você pode criar objetos java.sql.Date para representar datas específicas, como "01/01/2000", "31/12/1990", etc.
Manipular Datas: Você pode comparar datas, calcular a diferença entre datas, e realizar outras operações relacionadas a datas usando métodos disponíveis na classe Date.
Exibir Datas: Você pode exibir as datas em um formato específico, como "01/01/2000" ou "2000-01-01", dependendo dos requisitos do seu aplicativo.
Basicamente, a classe java.sql.Date fornece uma maneira conveniente de trabalhar com datas em Java, especialmente quando você está lidando com banco de dados e precisa armazenar ou manipular datas em seus aplicativos. */



/* Vamos detalhar a linha return funcionario; dentro do método buscarFuncionarioPorId(int id):

Essa linha está dentro de um bloco condicional if que verifica se há pelo menos uma linha de resultado no ResultSet (ou seja, se um funcionário com o ID fornecido foi encontrado no banco de dados). Se a condição if (rs.next()) for verdadeira, significa que um funcionário foi encontrado e seus dados foram recuperados do banco de dados.

Nesse caso, o objeto funcionario foi criado e preenchido com os dados do funcionário encontrado. Depois de configurar todas as propriedades do objeto funcionario, a linha return funcionario; é usada para retornar esse objeto.

Em resumo, essa linha indica que o método buscarFuncionarioPorId deve retornar o objeto funcionario,
que contém os dados do funcionário encontrado no banco de dados. Esse objeto pode ser usado
pelo chamador do método para realizar operações adicionais ou exibir informações sobre o funcionário. Se nenhum funcionário for encontrado
com o ID fornecido, o método retornará null, indicando que nenhum funcionário foi encontrado.*/