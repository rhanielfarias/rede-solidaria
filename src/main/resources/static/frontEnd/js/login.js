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
        .then(data => window.location.href = "solicitarAjuda.html")//precisamos que esse caminho so retorne caso seja um
        //usuário a ter efetuado o login.
         .catch(error => alert("Login ou senha inválida!"));
    });

