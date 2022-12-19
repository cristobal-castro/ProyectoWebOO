
function getReservaPorFecha(elemnt){
    window.location.href = "/reserva/lista/" + elemnt.value;
}
function reserva(hora, cancha, fecha){
    console.log(hora)

    fetch("/reserva/agregar/" + fecha+"/"+cancha+"/"+hora,{
        method:'POST'
    })
    .then(function(res){ 
        window.location.href = "/reserva/lista/" + fecha;
    })
    .catch(function(res){ console.log(res) })

}