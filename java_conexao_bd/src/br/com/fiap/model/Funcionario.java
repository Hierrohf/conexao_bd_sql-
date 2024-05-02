package br.com.fiap.model;//Declaração do pacote onde a classe Funcionario está localizada.

import java.time.LocalDate;//Importação da classe LocalDate do pacote java.time. Essa classe é usada para representar uma data sem informações de horário, sendo útil para representar a data de admissão do funcionário.

public class Funcionario {//Declaração da classe Funcionario.
	
	//declaração dos atributos
	private int id;
	private String nome;
	private Double salario;
	private LocalDate dataAdmissao;
	private Departamento departamento;
	
	public Funcionario() {//construtor padrao
		
	}

	//Construtor sobrecarregado da classe Funcionario, que recebe um id, um nome, um salario, uma dataAdmissao e um departamento como parâmetros e inicializa os atributos correspondentes da instância.
	public Funcionario(int id, String nome, Double salario, LocalDate dataAdmissao, Departamento departamento) {
		super();
		this.id = id;
		this.nome = nome;
		this.salario = salario;
		this.dataAdmissao = dataAdmissao;
		this.departamento = departamento;
	}

	// GET : Método de acesso (getter) para obter o valor do atributo 
	// SET : Método modificador (setter) para definir o valor do atributo
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getSalario() {
		return salario;
	}

	public void setSalario(Double salario) {
		this.salario = salario;
	}

	public LocalDate getDataAdmissao() {
		return dataAdmissao;
	}

	public void setDataAdmissao(LocalDate dataAdmissao) {
		this.dataAdmissao = dataAdmissao;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}
	
	//tive dificuldade de entender!! axplicão ta no final
	@Override
	public String toString() {
		return "Funcionario id = " + id + ", nome = " + nome + ", salario = " + salario + ", data Admissao = " + dataAdmissao
				+ ", depto nome = " + departamento.getNome();
	}	

}

/*toString: O método toString() é um método da classe Object que é herdado por todas as classes em Java. Ele é utilizado para retornar uma representação em forma de string do objeto. Geralmente, essa representação é usada para fins de depuração ou para exibir informações sobre o objeto de forma legível.

Quando você sobrescreve o método toString() em uma classe, você está substituindo a implementação padrão desse método para fornecer uma representação personalizada do objeto.

No caso específico do método toString() na classe Funcionario, a implementação retorna uma string que contém os valores dos atributos do funcionário formatados de uma maneira legível. Aqui está a explicação detalhada da linha dentro do método toString() */



/*@Override (sobrescrita de metodo): A sobrescrita de método é um conceito de programação orientada a objetos que permite a uma classe filho (subclasse) fornecer uma implementação específica para um método que já está definido em sua classe pai (superclasse). Isso significa que a subclasse redefine a implementação de um método que foi herdado da superclasse, oferecendo uma versão alternativa desse método.

Quando uma classe filho sobrescreve um método da classe pai, ela mantém a mesma assinatura (nome do método, tipo e ordem dos parâmetros) do método na superclasse. Isso permite que a subclasse forneça uma implementação personalizada para aquele método específico, adequada ao seu contexto ou necessidades específicas.

A sobrescrita de método é usada principalmente para estender ou modificar o comportamento de um método herdado. Ela permite que a classe filho forneça uma implementação mais específica para o método, adaptada às suas próprias necessidades, sem alterar a implementação original na superclasse.

Por exemplo, imagine uma classe Animal que possui um método fazerBarulho(). Uma subclasse Cachorro pode sobrescrever esse método para que um objeto Cachorro faça um latido, enquanto uma subclasse Gato pode sobrescrevê-lo para que um objeto Gato faça um miado. Isso permite que cada tipo de animal tenha seu próprio comportamento específico ao fazer barulho, mesmo compartilhando um método comum da classe pai. */