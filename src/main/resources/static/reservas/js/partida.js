var elementoSelect = false;
var cancha, hora, fecha
function getReservaPorFecha(elemnt){
    console.log("Cambio de fecha")
    window.location.href = "/partida/lista/" + elemnt.value;
}

function reserva(elemento, h, c, f){
    cancha = c
    hora = h;
    fecha= f 
    console.log(f)
    if(!elementoSelect){
        elemento.setAttribute("class", "horaAmarilla");
        elemento.setAttribute("id", "selecionado");
        elementoSelect= true
    }else{ 
        var aux= document.getElementById("selecionado");
        aux.setAttribute("class", "hora");
        aux.removeAttribute("id");
        elemento.setAttribute("class", "horaAmarilla");
        elemento.setAttribute("id", "selecionado");
       
    }
    cancha= cancha
}
function imp(){
    Swal.fire({
        title: '¿Quieres reservar la partida?',
        text: "Podras jugar con otros jugadores ",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Si, reservar!',
        cancelButtonText: 'No'
      }).then((result) => {
        if (result.isConfirmed && elementoSelect) {
            let formulario = document.getElementById("formulario");
            let inputcancha =document.getElementById("cancha")
            let inputHora =document.getElementById("horaInicio")
            let inputfecha =document.getElementById("fecha")
            inputcancha.setAttribute("value", cancha)
            inputHora.setAttribute("value", hora)
            inputfecha.setAttribute("value", fecha)
            console.log(formulario)
            Swal.fire(
                'Partida reservada!',
                'Tu partida se a reservado .',
                'success'
              )
            
            formulario.submit();
        }else{
            Swal.fire(
                'Debes selecionar una hora!',
                'Tu partida no se puedo reservar.',
                'error'
              )
        }
      })



}

function ingresar(id){
    Swal.fire({
        title: '¿Quieres unirte a esta partida?',
        text: "Vamos a jugar",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Si, unirme!',
        cancelButtonText: 'No'
      }).then((result) => {
        if (result.isConfirmed ) {
            fetch("/partida/addJugador/" + id,{
                method:'GET'
            })
            .then(function(res){ 

                window.location.href = "/partida/lista";
                Swal.fire(
                    'Cancha reservada!',
                    'Tu ingreso se a registrado .',
                    'success'
                  )
            })
            .catch(function(res){ 
                Swal.fire(
                    'Ooop .. !',
                    'Acaba de ocurrir un problema.',
                    'error'
                  )

             })
        }
  })}

