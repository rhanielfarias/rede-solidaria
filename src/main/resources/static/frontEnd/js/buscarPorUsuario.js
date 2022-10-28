const form1 = document.getElementById("usuario")

const idUser = document.querySelector("#id");
const id = idUser.value;
        console.log(id);

form1.addEventListener('click', (e) =>{
const id = idUser.value;
console.log(id);


const options = {
method: 'GET',
cache: 'default'
}


fetch(`http://localhost:8080/usuarios/${id}`, options)
        .then(response => {response.json()
        .then(data => atribuirCampos(data))
        })
        .catch(e => console.log("Deu erro: " + e))
        })

function atribuirCampos(data){

        const nome = document.querySelector("#nome");
        const telefone = document.querySelector("#telefone");
        const categoria = document.querySelector("#categoria");
        const deficiencias = document.querySelector("#deficiencias");
        const email = document.querySelector("#email ");
        const latitude = document.querySelector("#latitude");
        const longitude = document.querySelector("#longitude");

        nome.value = data.nome;
        telefone.value = data.telefone;
        categoria.value = data.categoria;
        deficiencias.value = data.deficiencias;
        email.value = data.email;
        latitude.value = data.latitude;
        longitude.value = data.longitude;
}