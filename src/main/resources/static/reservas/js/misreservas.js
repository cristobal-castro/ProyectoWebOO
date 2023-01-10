
function eliminar(id) {
    Swal.fire({
        title: '¿Quiere eliminar tu reserva?',
        text: "Esta acción no se puede retornar",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Si,eliminar!',
        cancelButtonText: 'No'
    }).then((result) => {
        if (result.isConfirmed) {
            fetch("/reserva/delete/" + id, {
                method: 'GET'
            })
                .then(function (res) {

                    window.location.href = "/reserva/mis-reservas";
                    Swal.fire(
                        'Eliminada!',
                        'Tu reserva se eliminado',
                        'success'
                    )
                })
                .catch(function (res) {
                    Swal.fire(
                        'Ooop .. !',
                        'No pudimos concretar la eliminación.',
                        'error'
                    )

                })
        }

    })

    console.log("ID::",id)
}

