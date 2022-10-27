const form = document.getElementById("usuario-login")

form.addEventListener('submit', event => {
    event.preventDefault();

    const formData = new FormData(form);
    const data = Object.fromEntries(formData); // Se torna em objeto

    fetch("http://localhost:8080/usuarios/login", {
        method: 'POST',
        headers: {
            'Content-Type':'application/json'
        },
        body: JSON.stringify(data)
    }).then(res => res.json())
        .then(data => window.location.href = "index.html")
        .catch(error => console.log(error));
});

function msg() {
    alert("Salvo com sucesso!");
    window.location.reload(true);
  }