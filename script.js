document.getElementById('estoque-form').addEventListener('submit', function(event) {
    event.preventDefault();

    const nome = document.getElementById('nome').value;
    const preco = parseFloat(document.getElementById('preco').value).toFixed(2);
    const quantidade = parseInt(document.getElementById('quantidade').value);

    if (nome && !isNaN(preco) && !isNaN(quantidade)) {
        fetch('/api/produtos', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ nome, preco, quantidade })
        })
        .then(response => response.json())
        .then(() => {
            listarProdutos();
            document.getElementById('estoque-form').reset();
        });
    }
});

function listarProdutos() {
    fetch('/api/produtos')
        .then(response => response.json())
        .then(produtos => {
            const produtoList = document.getElementById('produto-list');
            produtoList.innerHTML = '';
            produtos.forEach(produto => {
                const listItem = document.createElement('li');
                listItem.textContent = `Nome: ${produto.nome}, Preço: R$${produto.preco}, Quantidade: ${produto.quantidade}`;
                produtoList.appendChild(listItem);
            });
        });
}

// Inicializar a lista de produtos quando a página carrega
listarProdutos();