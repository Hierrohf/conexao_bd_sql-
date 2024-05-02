package br.com.fiap;//Esta linha declara o pacote onde a classe RhApp está localizada.


/*Essas linhas importam classes de outros pacotes que serão utilizadas no programa.
LocalDate é importado do pacote java.time, List é importado do pacote java.util, e as classes Conexao, DepartamentoDao, FuncionarioDao, Departamento e Funcionario são importadas dos pacotes específicos da aplicação.*/
import java.time.LocalDate;
import java.util.List;

import br.com.fiap.dao.Conexao;
import br.com.fiap.dao.DepartamentoDao;
import br.com.fiap.dao.FuncionarioDao;
import br.com.fiap.model.Departamento;
import br.com.fiap.model.Funcionario;

public class RhApp {//Esta linha declara a classe RhApp.

	public static void main(String[] args) {//Esta linha declara o método main, que é o ponto de entrada do programa Java.

		//Essas linhas criam um objeto Funcionario, definem seus atributos (nome, salário, data de admissão e departamento) e os inicializam com valores específicos.
		Funcionario funcionario = new Funcionario();
		funcionario.setNome("Jon Snow");
		funcionario.setSalario(3255.0);
		funcionario.setDataAdmissao(LocalDate.of(2000, 12, 6)); // YYYY,M,D /// este é um método estático da classe LocalDate que cria uma instância de LocalDate representando uma data específica. No exemplo dado, LocalDate.of(2000, 12, 6) cria um objeto LocalDate para a data 6 de dezembro de 2000.
		funcionario.setDepartamento(new Departamento(2, "Desenvolvimento"));
		
		//Essas linhas criam um objeto FuncionarioDao, passando uma conexão aberta como argumento, e então chamam o método gravar para inserir o funcionário no banco de dados.
		FuncionarioDao funcionarioDao = new FuncionarioDao(Conexao.abrirConexao());
		funcionarioDao.gravar(funcionario);

		//ESPLICAÇÔES DO ORACULO

		// ------- //Esses comentários representam uma operação de atualização (update) que está sendo omitida neste momento. Eles mostram como seria feita a atualização do salário e do departamento do funcionário, seguida pela chamada do método atualizar do FuncionarioDao.
		// update - salario departamento_id
//		funcionario.setSalario(4500.0);
//		funcionario.setDepartamento(new Departamento(1, "Projetos"));

//		funcionarioDao.atualizar(funcionario);

		// ------ //Esses comentários representam uma operação de exclusão que está sendo omitida neste momento. Eles mostram como seria feita a exclusão de um funcionário, especificando o ID do funcionário a ser excluído e chamando o método excluir do FuncionarioDao.
		// excluir
//		funcionario.setId(2);
//
//		funcionarioDao.excluir(funcionario);

		// ---- //Este comentário representa uma operação de busca por ID que está sendo omitida neste momento. Ele mostra como seria feita a busca de um funcionário pelo ID, chamando o método buscarFuncionarioPorId do FuncionarioDao.
		// buscar por Id

//		funcionarioDao.buscarFuncionarioPorId(3);

		// --- //Estes comentários representam uma operação de listagem que está sendo omitida neste momento. Eles mostram como seria feita a listagem de todos os funcionários, obtendo uma lista de funcionários chamando o método listarTodos do FuncionarioDao e, em seguida, percorrendo essa lista e imprimindo cada funcionário.

		// listar
//		List<Funcionario> funcionarios = funcionarioDao.listarTodos();

//		for (Funcionario f : funcionarios) {
//			System.out.println(f);
//		}

		// --- //Este comentário representa uma operação de busca por nome de departamento que está sendo omitida neste momento. Ele mostra como seria feita a busca de um departamento pelo nome, chamando o método buscarDepartamentoPorNome do DepartamentoDao. Em seguida, imprime o ID e o nome do departamento encontrado.

//		Departamento dp = new Departamento();
//
//		DepartamentoDao dao = new DepartamentoDao(Conexao.abrirConexao());
//		dp = dao.buscarDepartamentoPorNome("ojeto");
//
//		System.out.println(dp.getId() + dp.getNome());

	}

}
