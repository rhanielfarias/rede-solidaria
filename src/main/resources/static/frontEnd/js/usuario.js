    fetch("http://localhost:8080/usuarios").then((data)=> {
return data.json();
    }).then((todosUsuarios) =>{
        let data1= "";
        todosUsuarios.map((values) => {
            data1 += `
<tbody>
<tr>
    <th scope="row">${values.id}</td>
    <td>${values.nome}</td>
    <td>${values.telefone} </td>
    <td>${values.cpf} </td>
    <td>${values.email}</td>
    <td>${values.latitude}</td>
    <td>${values.longitude}</td>
    </tr>
    </tbody>
`
        })
 document.getElementById("usuarioDados").innerHTML = data1;
 console.log(todosUsuarios);
    })
