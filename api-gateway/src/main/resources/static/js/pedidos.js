// Função para buscar os pedidos da API e renderizar na página
async function carregarPedidos() {
    try {
        const response = await fetch('http://localhost:8799/pedidos/pedidos');
        if (!response.ok) {
            throw new Error('Erro ao buscar pedidos: ' + response.status);
        }

        const pedidos = await response.json();

        const container = document.getElementById('ordersContainer');
        container.innerHTML = ''; // limpa conteúdo antes de renderizar

        if (pedidos.length === 0) {
            container.innerHTML = '<p>Nenhum pedido encontrado.</p>';
            return;
        }

        pedidos.forEach((pedido, index) => {
            // Cria um div para cada pedido
            const pedidoDiv = document.createElement('div');
            pedidoDiv.classList.add('pedido');

            // Cria título do pedido (ex: Pedido #1, data)
            const titulo = document.createElement('h2');
            titulo.textContent = `Pedido #${index + 1} - ${new Date(pedido.dataCriacao).toLocaleString()}`;
            pedidoDiv.appendChild(titulo);

            // Cria uma lista para os produtos
            const listaProdutos = document.createElement('ul');

            pedido.products.forEach(produto => {
                const item = document.createElement('li');
                item.innerHTML = `<strong>${produto.nome}</strong>: ${produto.descricao} - R$ ${produto.preco.toFixed(2)}`;
                listaProdutos.appendChild(item);
            });

            pedidoDiv.appendChild(listaProdutos);

            // Total do pedido
            const total = document.createElement('p');
            total.innerHTML = `<strong>Total: R$ ${pedido.total.toFixed(2)}</strong>`;
            pedidoDiv.appendChild(total);

            // Adiciona o pedido no container
            container.appendChild(pedidoDiv);
        });
    } catch (error) {
        console.error('Erro:', error);
        const container = document.getElementById('ordersContainer');
        container.innerHTML = `<p>Erro ao carregar pedidos: ${error.message}</p>`;
    }
}

// Executa a função ao carregar a página
window.onload = carregarPedidos;
