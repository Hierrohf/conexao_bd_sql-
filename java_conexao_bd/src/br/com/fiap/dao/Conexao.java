package br.com.fiap.dao;

//Essas linhas importam as classes necessárias para trabalhar com banco de dados SQL.
import java.sql.Connection;//Connection é uma interface que representa uma conexão com um banco de dados, 
import java.sql.DriverManager;//DriverManager é uma classe que gerencia os drivers de JDBC (Java Database Connectivity), 
import java.sql.SQLException;//SQLException é uma classe que representa uma exceção que pode ocorrer durante a interação com o banco de dados.

public class Conexao { //Esta linha declara a classe Conexao, que é pública e pode ser acessada de fora do pacote.
	
	// dados para a conexão
	private static final String URL = "#############################################";
	private static final String USUARIO = "#########"; //padrão para aluno RM - FIAP
	private static final String SENHA = "#######"; //padrão para aluno SENHA - FIAP
	
	// método que retorna uma conexão com o DB ////um método estático é um método que pode ser chamado sem criar uma instância da classe que o declara.
	public static Connection abrirConexao() {
		
		// criar a conexão - pode gerar exception entao devemos fazer o tratamento!!!

		Connection conexao = null;//Esta linha declara uma variável conexao do tipo Connection e a inicializa como null
		
		// aqui tratamos Exception(execões)
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");//tenta estabelecer uma conexão com o banco de dados Oracle. Primeiro, ele carrega o driver JDBC Oracle usando Class.forName("oracle.jdbc.driver.OracleDriver")
			//pegar conexão pode gerar exception - add catch
			//URL, USUARIO, SENHA
			conexao = DriverManager.getConnection(URL, USUARIO, SENHA);//chama DriverManager.getConnection(URL, USUARIO, SENHA) para obter uma conexão usando os detalhes de URL, usuario e senha.
			
		} catch (ClassNotFoundException e) {		
			System.err.println("Erro de conexão");
			
		} catch (SQLException e) {
			System.err.println("Erro de conexão! Url, usuário ou senha");
			//e.printStackTrace();
		} 		
				
		return conexao;//Esta linha retorna a conexão estabelecida, se for bem-sucedida, ou null caso contrário.
		
	}

}

/*ClassNotFoundException : ocorre quando o Java não consegue encontrar uma classe específica durante a execução do programa
 * No contexto desse código, isso acontece quando o programa tenta carregar o driver JDBC Oracle, mas não consegue encontrá-lo no ambiente de execução
 * Por exemplo, se o arquivo JAR que contém o driver Oracle não estiver no local esperado ou se houver um erro no nome da classe do driver especificado no método Class.forName(),
 *  essa exceção será lançada.
*/

/*SQLException : é uma classe no Java que representa uma exceção que pode ocorrer durante a interação com um banco de dados SQL
 *  Essa exceção é lançada quando ocorre um erro relacionado à execução de instruções SQL,
 * conexão com o banco de dados, ou qualquer outra operação relacionada a banco de dados.
 * (em outras palavra ela capitura qualquer erro SQL pois ela e uma Exepiton generica nao especifica)
 * 
 */


/* ojdbc11.jar : é um arquivo JAR que contém o driver JDBC (Java Database Connectivity) para o Oracle Database versão 11.

Este arquivo é fornecido pela Oracle e é usado para permitir que aplicativos Java se conectem e interajam com um banco de dados Oracle. */ 

/*########LEMBRETE IMPORTANTE######## 
VC ESTA TENDO MUINTA DIFICULDADE COM TUDO ISSO (TUDO MSM) ENTÃO SE N PRATICAR VC VAI ESQUCER ISSO EM 1 DIA OU MENOS  
 */