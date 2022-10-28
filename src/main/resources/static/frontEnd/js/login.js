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
        .then(data => direcionarUsuario(data))
        .then(console.log(data))//precisamos que esse caminho so retorne caso seja um
        //usuário a ter efetuado o login. Foi solucionado criando uma função condicional para determinar a direção do login.
         .catch(error => console.log(categoria));
    });

    function direcionarUsuario(data){

        if(data.categoria == "VOLUNTARIO"){
        window.location.href = "buscarPorUsuario.html"
        }else if(data.categoria == "USUARIO") {
        window.location.href = "solicitarAjuda.html"
        }

    categoria.value = data.categoria;
 }