package br.com.alura.screenmatch.desafios;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class UltimoMain {
    public static void main(String[] args) {
        List<Integer> numeros = Arrays.asList(10, 20, 30, 40, 50);
        Optional<Integer> max = numeros.stream()
                .max(Integer::compare);
        max.ifPresent(System.out::println); // Esperado: 50

        List<String> palavras = Arrays.asList("java", "stream", "lambda", "code");
        Map<Integer, List<String>> agrupamento = palavras.stream()
                .collect(Collectors.groupingBy(String::length));
        System.out.println(agrupamento);
        // Esperado: {4=[java, code], 6=[stream, lambda]}

        List<String> nomes = Arrays.asList("Alice", "Bob", "Charlie");
        String resultado = nomes.stream()
                .collect(Collectors.joining(", "));
        System.out.println(resultado); // Esperado: "Alice, Bob, Charlie"

        numeros = Arrays.asList(1, 2, 3, 4, 5, 6);
        int somaDosQuadrados = numeros.stream()
                .filter(n -> n % 2 == 0)
                .map(n -> n * n)
                .reduce(0, Integer::sum);
        System.out.println(somaDosQuadrados); // Esperado: 56 (4 + 16 + 36)

        numeros = Arrays.asList(1, 2, 3, 4, 5, 6);
        Map<Boolean, List<Integer>> particionado = numeros.stream()
                .collect(Collectors.partitioningBy(n -> n % 2 == 0));
        System.out.println("Pares: " + particionado.get(true));  // Esperado: [2, 4, 6]
        System.out.println("Ímpares: " + particionado.get(false)); // Esperado: [1, 3, 5]

        List<Produto> produtos = Arrays.asList(
                new Produto("Smartphone", 800.0, "Eletrônicos"),
                new Produto("Notebook", 1500.0, "Eletrônicos"),
                new Produto("Mesa", 700.0, "Móveis"),
                new Produto("Cadeira", 300.0, "Móveis"),
                new Produto("Fone de Ouvido", 100.0, "Eletrônicos"),
                new Produto("Caneta", 5.0, "Papelaria")
        );

        Map<String, List<Produto>> produtosPorCategoria = produtos.stream()
                .collect(Collectors.groupingBy(Produto::getCategoria));

        System.out.println(produtosPorCategoria);

        Map<String, Long> contagemPorCategoria = produtos.stream()
                .collect(Collectors.groupingBy(Produto::getCategoria, Collectors.counting()));

        System.out.println(contagemPorCategoria);
    }
}
