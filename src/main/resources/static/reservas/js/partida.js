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
    let formulario = document.getElementById("formulario");
    let inputcancha =document.getElementById("cancha")
    let inputHora =document.getElementById("horaInicio")
    let inputfecha =document.getElementById("fecha")
    inputcancha.setAttribute("value", cancha)
    inputHora.setAttribute("value", hora)
    inputfecha.setAttribute("value", fecha)
    console.log(formulario)
    formulario.submit();

}
