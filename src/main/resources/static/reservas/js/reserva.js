
function getReservaPorFecha(elemnt){
    window.location.href = "/reserva/lista/" + elemnt.value;
}
function reserva(hora, cancha, fecha){
    var misCookies = document.cookie;
    misCookies = misCookies.split(";")[0].split('=')[1];
   if(misCookies==""){
    Swal.fire(
        'Debes iniciar sesión para poder reservar!',
        'Registrate.',
        'warning'
      )
   }else {
    Swal.fire({
        title: '¿Quieres reservar la cancha?',
        text: "Prodras ver tus reservas al presionar el 'Mis Reservas'",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Si, reservar!',
        cancelButtonText: 'No'
      }).then((result) => {
        if (result.isConfirmed ){
            fetch("/reserva/agregar/" + fecha+"/"+cancha+"/"+hora,{
                method:'POST'
            })
            .then(function(res){ 

                window.location.href = "/reserva/lista/" + fecha;
                Swal.fire(
                    'Cancha reservada!',
                    'Tu reserva se a registrado .',
                    'success'
                  )
            })
            .catch(function(res){ 
                Swal.fire(
                    'Ooop .. !',
                    'Tu reserva no se puedo concretar.',
                    'error'
                  )

             })
        }

      })
   }



}