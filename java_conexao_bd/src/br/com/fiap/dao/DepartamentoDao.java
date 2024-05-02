package br.com.fiap.dao;//Declara o pacote onde a classe DepartamentoDao está localizada.

//Importa as classes necessárias para trabalhar com operações de banco de dados,
import java.sql.Connection;//Connection é uma interface que representa uma conexão com um banco de dados, 
import java.sql.PreparedStatement;//PreparedStatement e manipulação de resultados de consultas em outra palavras (é uma interface em Java que representa uma instrução SQL pré-compilada que pode ser executada de forma eficiente no banco de dados) 
// uma classe em Java usada para executar instruções SQL pré-compiladas no banco de dados Ele representa uma instrução SQL
import java.sql.ResultSet;//e manipulação de resultados de consultas 
import java.sql.SQLException;//importa a exceção SQLException que é usada para lidar com erros de banco de dados

import br.com.fiap.model.Departamento;//Importa a classe Departamento do pacote br.com.fiap.model que é usada para representar objetos de departamento.

public class DepartamentoDao {//Declara a classe DepartamentoDao. Essa classe é responsável por manipular operações relacionadas a departamentos no banco de dados.

	// classe para manipular Departamento, CRUD, persistir no DB
	// tem os métodos para realizar as operações
	// gravar um Departamento
	// classe - responsabilidade única DepartamentoDao só sabe persistir
	// Departamento

	// injetando uma dependência conexão
	private Connection conexao;//Declara uma variável conexao do tipo Connection que será usada para estabelecer conexão com o banco de dados.

	public DepartamentoDao(Connection conexao) {//Declara um construtor que recebe uma conexão como parâmetro e atribui essa conexão à variável conexao

		this.conexao = conexao;
	}

	public void gravar(Departamento departamento) {//Declara um método gravar que recebe um objeto Departamento como parâmetro e é responsável por salvar esse departamento no banco de dados.
		//ainda nao implementei 
	}

	public void excluir(Departamento departamento) {// ou int id //// Declara um método excluir que recebe um objeto Departamento como parâmetro e é responsável por excluir esse departamento do banco de dados.
		//ainda nao implementei
	}

	public void atualizar(Departamento departamento) {//Declara um método atualizar que recebe um objeto Departamento como parâmetro e é responsável por atualizar os dados desse departamento no banco de dados.
		//ainda nao implementei
	}

	public void listarTodos() {//Declara um método listarTodos que é responsável por listar todos os departamentos salvos no banco de dados.

	}

	public Departamento buscarDepartamentoPorId(int id) {

		String sql = "SELECT * FROM tb_departamento WHERE id = ?";//Esta linha cria uma string sql que contém a instrução SQL para selecionar todos os campos da tabela tb_departamento onde o campo id é igual a um valor específico
		Departamento departamento = new Departamento();//cria um novo objeto do tipo Departamento chamado (departamento) que será usado para armazenar os dados do departamento recuperado do banco de dados.
		
		//tratamento e execões 
		try {
			PreparedStatement ps = conexao.prepareStatement(sql);

			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				departamento.setId(rs.getInt("id"));
				departamento.setNome(rs.getString("nome"));
			}

			ps.close();
			rs.close();
			conexao.close();

		} catch (SQLException e) {
			System.out.println(e.getMessage());

		}

		return departamento;

	}
	
	public Departamento buscarDepartamentoPorNome(String nome) {

		String sql = "SELECT * FROM tb_departamento WHERE UPPER(nome) LIKE UPPER (?) ";
		Departamento departamento = new Departamento();

		try {
			PreparedStatement ps = conexao.prepareStatement(sql);//Nesta linha, você está preparando a consulta SQL para execução. estou associando a string sql criada anterior mente

			ps.setString(1, "%" + nome + "%");//###ESTOU COM DIFICULDADE PARA ENTENDER ESSE COMANDO ### (la no final tem uma esplicaçao detalhada que o oracolo chat gpt me deu!!)

			ResultSet rs = ps.executeQuery();//Esta linha executa a consulta SQL usando o objeto PreparedStatement e armazena o resultado em um objeto ResultSet chamado rs. O ResultSet contém os resultados da consulta, se houver.

			/*Esta linha verifica se há pelo menos uma linha no ResultSet usando o método next(). Se houver pelo menos uma linha, ela extrai os
			 valores das colunas "id" e "nome" do ResultSet usando os métodos getInt() e getString() e os define no objeto departamento usando os métodos setId() e setNome() */
			if (rs.next()) {
				departamento.setId(rs.getInt("id"));
				departamento.setNome(rs.getString("nome"));
			}

			/*Estas linhas fecham o objeto PreparedStatement (ps), o ResultSet (rs)
			 e a conexão com o banco de dados (conexao) para liberar recursos após a conclusão da consulta. */
			ps.close();
			rs.close();
			conexao.close();

		} catch (SQLException e) {//Se ocorrer uma exceção do tipo SQLException durante a execução do código dentro do bloco try, ela será capturada aqui. A mensagem de erro associada à exceção é impressa no console. (lembrando q SQLException e generioco capta todo tipo de erro SQL) ###NOTA/LEMBRETE (pretendo tratar exception mais especificas)###
			System.out.println(e.getMessage());

		}

		return departamento;//este comando retorna o objeto departamento, que contém os dados do departamento recuperados do banco de dados

	}

}

/*ps.setString(1, "%" + nome + "%"); : Essa linha está dentro do método buscarDepartamentoPorNome e faz parte da preparação de uma consulta SQL dinâmica para buscar departamentos com base em um nome parcial.

ps: É o objeto PreparedStatement que foi criado anteriormente para executar consultas preparadas no banco de dados.
setString(1, "%" + nome + "%"): Este método é usado para definir o valor do parâmetro na consulta SQL preparada. Aqui, 1 indica o índice do primeiro parâmetro na consulta SQL (a primeira interrogação (?) encontrada na consulta).
Agora, a explicação mais detalhada:

%: Este é um caractere especial em SQL usado para corresponder a qualquer sequência de caracteres (incluindo zero caracteres). No contexto de uma consulta SQL com LIKE, % indica que pode haver qualquer sequência de caracteres antes ou depois do valor especificado.

+: Este é o operador de concatenação em Java. Ele é usado para combinar strings.

nome: Esta é a variável que contém o nome parcial que estamos buscando. Por exemplo, se nome for "depto", estamos procurando departamentos que contenham "depto" em qualquer parte do nome.
Portanto, "%"+ nome + "%" cria uma string que contém o valor de nome com % adicionado antes e depois. Isso significa que a consulta SQL buscará qualquer nome de departamento que contenha o valor de nome, independentemente do que estiver antes ou depois dele. 

ESQUECI PRA QUE SERVE O COMANDO LIKE EM SQL KKKKKK
LIKE : O comando LIKE em SQL é usado para buscar padrões em dados de texto. Ele é comumente usado em conjunto com os símbolos de porcentagem (%) para corresponder a qualquer sequência de caracteres ou com o sublinhado (_) para corresponder a um único caractere.

Por exemplo, suponha que você tenha uma tabela chamada clientes com uma coluna chamada nome, e você deseja encontrar todos os clientes cujo nome começa com "Jo":
SELECT * FROM clientes WHERE nome LIKE 'Jo%';
Isso retornaria todos os registros da tabela clientes onde o nome começa com "Jo".
*/