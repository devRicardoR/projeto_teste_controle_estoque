package com.duke.estoque;

import java.io.*;
import java.text.NumberFormat;
import java.util.*;

public class EstoqueApp {
    private static Estoque estoque = new Estoque();
    private static List<Usuario> usuarios = new ArrayList<>(); // Lista para armazenar os usuários
    private static Scanner scanner = new Scanner(System.in);
    private static NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

    public static void main(String[] args) {
        int opcao;

        do {
            exibirMenu();
            try {
                opcao = scanner.nextInt();
                scanner.nextLine(); // Limpar o buffer

                switch (opcao) {
                    case 1:
                        adicionarProduto();
                        break;
                    case 2:
                        buscarProduto();
                        break;
                    case 3:
                        removerProduto();
                        break;
                    case 4:
                        listarProdutos();
                        break;
                    case 5:
                        salvarProdutos();
                        break;
                    case 6:
                        carregarProdutos();
                        break;
                    case 7:
                        cadastrarUsuario();
                        break;
                    case 0:
                        System.out.println("Saindo...");
                        break;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Por favor, insira um número válido.");
                scanner.nextLine(); // Limpar o buffer caso o input seja inválido
                opcao = -1; // Definir como inválido para não sair do loop
            }
        } while (opcao != 0);
    }

    private static void exibirMenu() {
        System.out.println("=== Sistema de Controle de Estoque ===");
        System.out.println("1. Adicionar produto");
        System.out.println("2. Buscar produto");
        System.out.println("3. Remover produto");
        System.out.println("4. Listar produtos");
        System.out.println("5. Salvar produtos em arquivo");
        System.out.println("6. Carregar produtos de arquivo");
        System.out.println("7. Cadastrar usuário"); // Opção de cadastro de usuário
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static void adicionarProduto() {
        System.out.print("Digite o nome do produto: ");
        String nome = scanner.nextLine();

        double preco = 0;
        int quantidade = 0;

        // Verificação de entrada para preço
        while (true) {
            try {
                System.out.print("Digite o preço do produto: ");
                preco = scanner.nextDouble();
                scanner.nextLine(); // Limpar o buffer
                break;
            } catch (InputMismatchException e) {
                System.out.println("Por favor, insira um valor numérico válido para o preço.");
                scanner.nextLine(); // Limpar o buffer caso o input seja inválido
            }
        }

        // Verificação de entrada para quantidade
        while (true) {
            try {
                System.out.print("Digite a quantidade do produto: ");
                quantidade = scanner.nextInt();
                scanner.nextLine(); // Limpar o buffer
                break;
            } catch (InputMismatchException e) {
                System.out.println("Por favor, insira um valor numérico válido para a quantidade.");
                scanner.nextLine(); // Limpar o buffer caso o input seja inválido
            }
        }

        // Formatação do preço em reais
        String precoFormatado = currencyFormatter.format(preco);

        Produto produto = new Produto(nome, preco, quantidade);
        estoque.adicionarProduto(produto);

        System.out.println("Produto adicionado com sucesso!");
        System.out.println("Nome: " + nome);
        System.out.println("Preço: " + precoFormatado);
        System.out.println("Quantidade: " + quantidade);
    }

    private static void buscarProduto() {
        System.out.print("Digite o nome do produto a ser buscado: ");
        String nome = scanner.nextLine();
        Produto produto = estoque.buscarProduto(nome);

        if (produto != null) {
            System.out.println("Produto encontrado: " + produto);
        } else {
            System.out.println("Produto não encontrado.");
        }
    }

    private static void removerProduto() {
        System.out.print("Digite o nome do produto a ser removido: ");
        String nome = scanner.nextLine();
        estoque.removerProduto(nome);
        System.out.println("Produto removido (se existia)!");
    }

    private static void listarProdutos() {
        System.out.println("=== Lista de Produtos ===");
        estoque.listarProdutos();
    }

    private static void salvarProdutos() {
        System.out.print("Digite o caminho do arquivo para salvar os produtos: ");
        String caminho = scanner.nextLine();
        estoque.salvarEmArquivo(caminho);
        System.out.println("Produtos salvos com sucesso!");
    }

    private static void carregarProdutos() {
        System.out.print("Digite o caminho do arquivo para carregar os produtos: ");
        String caminho = scanner.nextLine();
        estoque.carregarDeArquivo(caminho);
        System.out.println("Produtos carregados com sucesso!");
    }

    private static void cadastrarUsuario() {
        System.out.print("Digite o nome do usuário: ");
        String nome = scanner.nextLine();

        System.out.print("Digite o email do usuário: ");
        String email = scanner.nextLine();

        Usuario usuario = new Usuario(nome, email);
        usuarios.add(usuario);

        System.out.println("Usuário cadastrado com sucesso!");
    }
}
