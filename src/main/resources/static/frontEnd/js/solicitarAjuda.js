//pegar valores e preencher os campos que vem da pesquisa
    //pegar valor digitado pelo cliente no id
    
    const form1 = document.getElementById("solicitar")

    const idUser = document.querySelector("#id");
    const id = idUser.value;
        console.log(id);

        form1.addEventListener('click', (e) =>{
        const id = idUser.value;
        console.log(id);

        
      //cabeçalho que vai no fecth
       const options = {
        method: 'GET',
        cache: 'default'
       }
    
        //fetch
        //devemos no ${} usar a crase
        fetch(`http://localhost:8080/usuarios/solicitacao/${id}`, options)
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
        

       