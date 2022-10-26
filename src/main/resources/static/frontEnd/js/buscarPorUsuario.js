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

        function atribuirCampos(data)

        {
        const id = document.querySelector("#id");
        const nome = document.querySelector("#nome");
        const telefone = document.querySelector("#telefone");


        id.value = data.id;
        nome.value = data.nome;
        telefone.value = data.telefone;

        }
