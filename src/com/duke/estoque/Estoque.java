package com.duke.estoque;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Estoque {
    private List<Produto> produtos;

    public Estoque() {
        produtos = new ArrayList<>();
    }

    // Método para adicionar um produto ao estoque
    public void adicionarProduto(Produto produto) {
        produtos.add(produto);
    }

    // Método para buscar um produto pelo nome
    public Produto buscarProduto(String nome) {
        for (Produto produto : produtos) {
            if (produto.getNome().equalsIgnoreCase(nome)) {
                return produto;
            }
        }
        return null; // Retorna null se o produto não for encontrado
    }

    // Método para remover um produto pelo nome
    public void removerProduto(String nome) {
        produtos.removeIf(produto -> produto.getNome().equalsIgnoreCase(nome));
    }

    // Método para listar todos os produtos
    public void listarProdutos() {
        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto encontrado.");
        } else {
            for (Produto produto : produtos) {
                System.out.println(produto);
            }
        }
    }

    // Método para salvar produtos em um arquivo
    public void salvarEmArquivo(String caminho) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(caminho))) {
            oos.writeObject(produtos);
        } catch (IOException e) {
            System.out.println("Erro ao salvar os produtos: " + e.getMessage());
        }
    }

    // Método para carregar produtos de um arquivo
    public void carregarDeArquivo(String caminho) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(caminho))) {
            produtos = (List<Produto>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro ao carregar os produtos: " + e.getMessage());
        }
    }
}