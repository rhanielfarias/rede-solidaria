const solicitante = localStorage.getItem("nomeUsuario");
console.log(solicitante);
const nomesolictante = document.querySelector("#nome_soli");
nomesolictante.value = solicitante;


const form1 = document.getElementById("solicitar")

    form1.addEventListener('click', (e) =>{
    const id = localStorage.getItem("id");
    console.log(id);

    const options = {
    method: 'GET',
    cache: 'default'
   }

    fetch(`http://localhost:8080/usuarios/solicitacao/${id}`, options)
    .then
    (response => {response.json()
    .then(data => atribuirCampos(data))
    })
    .catch(e => console.log("Deu erro: " + e))
    })

function atribuirCampos(data)

{
    const nome = document.querySelector("#nome");
    const telefone = document.querySelector("#telefone");

    nome.value = data.nome;
    telefone.value = data.telefone;
}


       