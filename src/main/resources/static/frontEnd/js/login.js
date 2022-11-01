const form = document.getElementById("usuario-login")

form.addEventListener('submit', event => {
    event.preventDefault();

    const formData = new FormData(form);
    const data = Object.fromEntries(formData);

    fetch("http://localhost:8080/usuarios/login", {
        method: 'POST',
        headers: {
            'Content-Type':'application/json'
        },
        body: JSON.stringify(data)
    }).then(res => res.json())
        .then(data => direcionarUsuario(data))
        .catch(error => refresh());
    });

function direcionarUsuario(data){

    localStorage.setItem("id",data.id)
    localStorage.setItem("nomeUsuario",data.nome)

    if(data.categoria == "VOLUNTARIO"){
    window.location.href = "buscarPorUsuario.html"
    }else if(data.categoria == "USUARIO") {
    window.location.href = "solicitarAjuda.html"
    }
 };

 function refresh()
 {
     alert("Usuário ou senha inválidos!");
     location.reload();
 }