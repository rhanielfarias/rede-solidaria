const form = document.getElementById("buscarPorUsuario")

form.addEventListener('submit', event => {
   event.preventDefault();

   const formData = new FormData(form);
   const data = Object.fromEntries(formData);

   fetch("http://localhost:8080/usuarios/telefone", {
       method: 'POST',
       headers: {
           'Content-Type':'application/json'
       },
       body: JSON.stringify(data)
   }).then(res => res.json())
       .then(data => atribuirCampos(data))
       .catch(error => refresh());
   });

   function atribuirCampos(data){

           const nome = document.querySelector("#nome");
           const telefone = document.querySelector("#telefonee");
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
