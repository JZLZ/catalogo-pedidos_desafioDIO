// catalog.js
document.addEventListener("DOMContentLoaded", () => {
    const API_BASE = "http://localhost:8799/produtos/produtos"; // base do catálogo via gateway
    const API_CREATE_PRODUCT = "http://localhost:8799/produtos/createProduct";
    const API_DELETE_PRODUCT = " http://localhost:8799/produtos/delete/";
    const API_PEDIDOS = "http://localhost:8799/pedidos/pedidos";

    const tabelaBody = document.querySelector("#productsTable tbody");
    const formProduto = document.getElementById("createProductForm");
    const valorTotal = document.getElementById("totalValue");

    // Carrega produtos do catálogo
    async function carregarProdutos() {
        try {
            const response = await fetch(API_BASE);
            if (!response.ok) throw new Error(`Erro HTTP: ${response.status}`);

            const produtos = await response.json();
            tabelaBody.innerHTML = "";

            produtos.forEach(produto => {
                const row = document.createElement("tr");

                // Checkbox para seleção
                const selectCell = document.createElement("td");
                const checkbox = document.createElement("input");
                checkbox.type = "checkbox";
                checkbox.value = produto.id;
                checkbox.addEventListener("change", atualizarTotalSelecionado);
                selectCell.appendChild(checkbox);
                row.appendChild(selectCell);

                // Nome do produto
                const nomeCell = document.createElement("td");
                nomeCell.textContent = produto.nome;
                row.appendChild(nomeCell);

                // Preço formatado
                const precoCell = document.createElement("td");
                precoCell.textContent = produto.preco.toFixed(2);
                row.appendChild(precoCell);

                // Descrição do produto
                const descCell = document.createElement("td");
                descCell.textContent = produto.descricao || "";
                row.appendChild(descCell);

                tabelaBody.appendChild(row);
            });

            valorTotal.textContent = "Total: R$ 0,00"; // Inicializa zerado
        } catch (error) {
            console.error("Erro ao carregar produtos:", error);
            tabelaBody.innerHTML = "<tr><td colspan='4'>Erro ao carregar produtos</td></tr>";
        }
    }

    // Atualiza total dos selecionados
    function atualizarTotalSelecionado() {
        const selecionados = tabelaBody.querySelectorAll("input[type='checkbox']:checked");
        let total = 0;
        selecionados.forEach(cb => {
            const preco = parseFloat(cb.closest("tr").children[2].textContent);
            total += preco;
        });
        valorTotal.textContent = `Total: R$ ${total.toFixed(2)}`;
    }

    // Cria produto novo
    formProduto.addEventListener("submit", async (event) => {
        event.preventDefault();

        const novoProduto = {
            nome: document.getElementById("nome").value,
            descricao: document.getElementById("descricao").value,
            preco: parseFloat(document.getElementById("preco").value)
        };

        try {
            const response = await fetch(API_CREATE_PRODUCT, {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                // JSON em uma linha, assim como no curl
                body: JSON.stringify(novoProduto)
            });

            if (!response.ok) throw new Error("Erro ao criar produto");

            alert("Produto criado com sucesso!");
            formProduto.reset();
            carregarProdutos();
        } catch (error) {
            console.error("Erro ao criar produto:", error);
            alert("Erro ao criar produto.");
        }
    });

   // Função para criar pedido enviando JSON com os IDs selecionados
   window.criarPedido = async () => {
     // Pega todos os checkboxes marcados dentro do corpo da tabela
     const selecionados = Array.from(
       tabelaBody.querySelectorAll("input[type='checkbox']:checked")
     );

     // Verifica se algum produto foi selecionado
     if (selecionados.length === 0) {
       alert("Selecione pelo menos um produto para criar o pedido.");
       return; // Sai da função se nenhum checkbox estiver marcado
     }

     // Extrai os IDs dos produtos selecionados
     // Para cada checkbox selecionado, pega o texto da primeira célula da linha (coluna do ID)
     // Converte esse texto para número e monta o array idsSelecionados
     const idsSelecionados = selecionados.map(cb => {
       const idTexto = cb.closest("tr").children[0].textContent.trim();
       return Number(idTexto);
     });

     // Monta o objeto pedido com a propriedade ids contendo o array de IDs
     const pedido = { ids: idsSelecionados };

     try {
       // Envia a requisição POST para a API
       // Cabeçalho Content-Type JSON e corpo com o objeto pedido convertido para JSON
       const response = await fetch(API_PEDIDOS, {
         method: "POST",
         headers: { "Content-Type": "application/json" },
         body: JSON.stringify(pedido)
       });

       // Verifica se a resposta foi sucesso
       if (!response.ok) {
         throw new Error("Erro ao criar pedido");
       }

       alert("Pedido criado com sucesso!");
     } catch (error) {
       console.error("Erro ao criar pedido:", error);
       alert("Erro ao criar pedido.");
     }
   };

    // Exclui o produto selecionado (primeiro da lista)
window.excluirSelecionado = async () => {
  // Seleciona o primeiro checkbox marcado na tabela
  const selecionado = tabelaBody.querySelector("input[type='checkbox']:checked");

  if (!selecionado) {
    alert("Selecione um produto para excluir.");
    return;
  }

  const id = selecionado.value;

  try {
    // Faz a requisição DELETE para a API de exclusão, com a URL correta
    const response = await fetch(`${API_DELETE_PRODUCT}/${id}`, {
      method: "DELETE"
    });

    if (!response.ok) {
      // Caso o servidor retorne erro, lança exceção para ir para o catch
      const erroTexto = await response.text();
      throw new Error(`Erro ao excluir produto: ${response.status} - ${erroTexto}`);
    }

    alert("Produto excluído com sucesso!");

    // Recarrega a lista de produtos para atualizar a tabela
    await carregarProdutos();

  } catch (error) {
    console.error("Erro ao excluir produto:", error);
    alert("Erro ao excluir produto.");
  }
};

    // Inicializa ao carregar a página
    carregarProdutos();
});
