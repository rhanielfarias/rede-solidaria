    const form = document.getElementById("usuario-form")

    form.addEventListener('submit', event => {
        event.preventDefault();

        const formData = new FormData(form);
        const data = Object.fromEntries(formData);
        const deficienciaArray = data.deficiencias.split(",");
        const arrayfinal = [];

        deficienciaArray.forEach(element => arrayfinal.push({tipoDaDeficiencia:element}));
        data.deficiencias=arrayfinal;
        console.log(data);

        fetch("http://localhost:8080/usuarios/create", {
            method: 'POST',
            headers: {
                'Content-Type':'application/json'
            },
            body: JSON.stringify(data)
        }).then(res => res.json())
            .then(data => console.log(data))
            .then(data => window.location.href="login.html")
            .catch(error => console.log(error));
    });

//    function msg() {
//        alert("Salvo com sucesso!");
//        window.location.reload(true)
//        window.location.href="login.html";
//      };
