document.addEventListener("DOMContentLoaded", () => {
  // Atualiza data/hora na página
  const dateElem = document.getElementById("date");
  if (dateElem) {
    const now = new Date();
    dateElem.textContent = now.toLocaleString();
  }

  // Atualiza status do API Gateway (exemplo)
  const statusElem = document.getElementById("status");
  if (statusElem) {
    fetch("/status")  // ajuste a URL para o endpoint real da sua API
      .then(response => {
        if (!response.ok) {
          throw new Error("Resposta não OK: " + response.status);
        }
        return response.json();
      })
      .then(data => {
        statusElem.textContent = `Status do API Gateway: ${data.status || "Indisponível"}`;
        statusElem.style.color = "green";
      })
      .catch(error => {
        statusElem.textContent = "Erro ao obter status: " + error.message;
        statusElem.style.color = "red";
      });
  }
});
