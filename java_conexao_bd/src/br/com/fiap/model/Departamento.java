package br.com.fiap.model;//Declaração do pacote onde a classe Departamento está localizada.

public class Departamento {//Declaração da classe Departamento.
	
	//Declaração de duas variáveis de instância privadas: id, do tipo int, e nome, do tipo String, que representam os atributos de um departamento.
	private int id;
	private String nome;
	
	public Departamento() {//Construtor padrão da classe Departamento, que não recebe argumentos e não realiza nenhuma operação especial.
		
	}

	public Departamento(int id, String nome) {//Construtor sobrecarregado da classe Departamento, que recebe um id e um nome como parâmetros e inicializa os atributos correspondentes da instância
		super();//O comando super(); em Java é usado para chamar o construtor da classe pai (superclasse) dentro do construtor de uma subclasse.
		this.id = id;
		this.nome = nome;
	}

	public int getId() {//Método de acesso (getter) para obter o valor do atributo id.
		return id;
	}

	public void setId(int id) {//Método modificador (setter) para definir o valor do atributo id.
		this.id = id;
	}

	public String getNome() {//Método de acesso (getter) para obter o valor do atributo nome.
		return nome;
	}

	public void setNome(String nome) {//Método modificador (setter) para definir o valor do atributo nome.
		this.nome = nome;
	}
	
	
	
	
	
	

}


/*INFORMAÇÔES DO ORACULO : Essa classe é um modelo simples que representa um departamento,
com dois atributos: id e nome, e métodos getter e setter para acessar e modificar esses atributos.
Ela é frequentemente utilizada para encapsular dados relacionados a departamentos em sistemas de informação.*/