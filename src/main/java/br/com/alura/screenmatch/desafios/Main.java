package br.com.alura.screenmatch.desafios;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Integer> numeros = Arrays.asList(1, 2, 3, 4, 5, 6);
        numeros.stream()
                .filter(n -> n % 2 == 0)
                .forEach(System.out::println); // Esperado: 2, 4, 6
        List<String> palavras = Arrays.asList("java", "stream", "lambda");
        palavras.stream()
                .map(String::toUpperCase)
                .forEach(System.out::println); // Esperado: JAVA, STREAM, LAMBDA
        numeros = Arrays.asList(1, 2, 3, 4, 5, 6);
        List<Integer> resultado = numeros.stream()
                .filter(n -> n % 2 == 1)
                .map(n -> n * 2)
                .collect(Collectors.toList());
        System.out.println(resultado); // Esperado: [2, 6, 10]
        palavras = Arrays.asList("apple", "banana", "apple", "orange", "banana");
        List<String> unicas = palavras.stream()
                .distinct()
                .collect(Collectors.toList());
        System.out.println(unicas); // Esperado: [apple, banana, orange]

        List<List<Integer>> listaDeNumeros = Arrays.asList(
                Arrays.asList(1, 2, 3, 4),
                Arrays.asList(5, 6, 7, 8),
                Arrays.asList(9, 10, 11, 12)
        );

        List<Integer> numerosPrimos = listaDeNumeros.stream()
                .flatMap(List::stream)  // Achatar as sublistas em uma única stream
                .filter(Main::ehPrimo)  // Filtrar apenas números primos
                .sorted()               // Ordenar em ordem crescente
                .collect(Collectors.toList()); // Coletar em uma lista

        System.out.println(numerosPrimos); // Saída esperada: [2, 3, 5, 7, 11]

        List<Pessoa> pessoas = Arrays.asList(
                new Pessoa("Alice", 22),
                new Pessoa("Bob", 17),
                new Pessoa("Charlie", 19)
        );

        pessoas.stream()
                .filter(p -> p.getIdade() > 18)
                .map(Pessoa::getNome)
                .sorted()
                .forEach(System.out::println); // Esperado: "Alice", "Charlie"

        List<Produto> produtos = Arrays.asList(
                new Produto("Smartphone", 800.0, "Eletrônicos"),
                new Produto("Notebook", 1500.0, "Eletrônicos"),
                new Produto("Teclado", 200.0, "Eletrônicos"),
                new Produto("Cadeira", 300.0, "Móveis"),
                new Produto("Monitor", 900.0, "Eletrônicos"),
                new Produto("Mesa", 700.0, "Móveis")
        );

        List<Produto> produtosFiltrados = produtos.stream()
                .filter(p -> p.getCategoria().equals("Eletrônicos")) // Filtrar pela categoria
                .filter(p -> p.getPreco() < 1000)                   // Filtrar pelo preço
                .sorted((p1, p2) -> Double.compare(p1.getPreco(), p2.getPreco())) // Ordenar pelo preço
                .collect(Collectors.toList()); // Coletar em uma lista

        System.out.println(produtosFiltrados);
    }

    // Função para verificar se um número é primo
    private static boolean ehPrimo(int numero) {
        if (numero < 2) return false; // Números menores que 2 não são primos
        for (int i = 2; i <= Math.sqrt(numero); i++) {
            if (numero % i == 0) {
                return false; // Divisível por outro número que não 1 e ele mesmo
            }
        }
        return true;
    }
}

class Pessoa {
    private String nome;
    private int idade;

    public Pessoa(String nome, int idade) {
        this.nome = nome;
        this.idade = idade;
    }

    public String getNome() {
        return nome;
    }

    public int getIdade() {
        return idade;
    }
}

class Produto {
    private String nome;
    private Double preco;
    private String categoria;

    public Produto(String nome, Double preco, String categoria) {
        this.nome = nome;
        this.preco = preco;
        this.categoria = categoria;
    }

    public Double getPreco() {
        return preco;
    }

    public String getCategoria() {
        return categoria;
    }

    @Override
    public String toString() {
        return "Produto{" +
                "nome='" + nome + '\'' +
                ", preco=" + preco +
                ", categoria='" + categoria + '\'' +
                '}';
    }
}
