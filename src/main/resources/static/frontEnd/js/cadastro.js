function getLocation() {
  if (navigator.geolocation) {
    navigator.geolocation.getCurrentPosition(showPosition);
  } else {
    console.log("Geolocation is not supported by this browser.");
  }
};

function showPosition(position) {
   const latitude = position.coords.latitude;
   const longitude = position.coords.longitude;
   const idlatitude = document.getElementById("latitude");
   const idlongitude = document.getElementById("longitude");
   idlatitude.value = latitude;
   idlongitude.value = longitude;
};

getLocation();




const form = document.getElementById("usuario-form")

form.addEventListener('submit', event => {
    event.preventDefault();

    const formData = new FormData(form);
    const data = Object.fromEntries(formData);

    fetch("http://localhost:8080/usuarios/create", {
        method: 'POST',
        headers: {
            'Content-Type':'application/json'
        },
        body: JSON.stringify(data)
    }).then(res => res.json())
        .then(data => refresh())
        .catch(error => console.log(error));
});

 function refresh()
 {
     alert("Usuário cadastrado com sucesso!");
     window.location.href="login.html";
 }




