$(document).on("click", "#btnagregarusuario", function () {
    $("#txtnombre").val("");
    $("#txtapellidos").val("");
    $("#txtdni").val("");
    $("#txtdireccion").val("");
    $("#txtcorreo").val("");
    $("#txttelefono").val("");
    $("#txtestado").val("");
    $("#hddidusuario").val("0");
    $("#modalusuario").modal("show");
});

function validarModal() {
    ok = true;
    regexValidarEmail = /^(([^<>()[\]\.,;:\s@\"]+(\.[^<>()[\]\.,;:\s@\"]+)*)|(\".+\"))@(([^<>()[\]\.,;:\s@\"]+\.)+[^<>()[\]\.,;:\s@\"]{2,})$/i;

    if ($("#txtnombre").val() === "") {
        $("#errornombre").text("Es obligarotio ingresar el nombre del usuario");
        ok = false;
    } else {
        $("#errornombre").text("");
    }

    if ($("#txtapellidos").val() === "") {
        $("#errorapellidos").text("Es obligarotio ingresar el apellido del usuario");
        ok = false;
    } else {
        $("#errorapellidos").text("");
    }
    if ($("#txtdni").val() === "" || $("#txtdni").val().length != 8) {
        $("#errordni").text("Es obligarotio ingresar un DNI valido");
        ok = false;
    } else {
        $("#errordni").text("");
    }
    if ($("#txtdireccion").val() === "") {
        $("#errordireccion").text("Es obligarotio ingresar la direccion del usuario");
        ok = false;
    } else {
        $("#errordireccion").text("");
    }
    if ($("#txtcorreo").val() === "" || regexValidarEmail.test($("#txtcorreo").val()) == false) {
        $("#errorcorreo").text("Es obligarotio ingresar un correo valido");
        ok = false;
    } else {
        $("#errorcorreo").text("");
    }
    if ($("#txttelefono").val() === "" || $("#txttelefono").val().length != 9) {
        $("#errortelefono").text("Es obligarotio ingresar el telefono valido");
        ok = false;
    } else {
        $("#errortelefono").text("");
    }
    if ($("#txtestado").val() === "") {
        $("#errorestado").text("Es obligarotio ingresar el estado del usuario");
        ok = false;
    } else {
        $("#errorestado").text("");
    }

    return ok;
}

function validarModalParaActualizar() {
    ok = true;
    regexValidarEmail = /^(([^<>()[\]\.,;:\s@\"]+(\.[^<>()[\]\.,;:\s@\"]+)*)|(\".+\"))@(([^<>()[\]\.,;:\s@\"]+\.)+[^<>()[\]\.,;:\s@\"]{2,})$/i;

    if ($("#txtActNombre").val() === "") {
        $("#errorActnombre").text("Es obligarotio ingresar el nombre del usuario");
        ok = false;
    } else {
        $("#errorActnombre").text("");
    }

    if ($("#txtActApellidos").val() === "") {
        $("#errorActapellidos").text("Es obligarotio ingresar el apellido del usuario");
        ok = false;
    } else {
        $("#errorActapellidos").text("");
    }
    if ($("#txtActDni").val() === "" || $("#txtActDni").val().length != 8) {
        $("#errorActdni").text("Es obligarotio ingresar un DNI valido");
        ok = false;
    } else {
        $("#errorActdni").text("");
    }
    if ($("#txtActDireccion").val() === "") {
        $("#errorActdireccion").text("Es obligarotio ingresar la direccion del usuario");
        ok = false;
    } else {
        $("#errorActdireccion").text("");
    }
    if ($("#txtActCorreo").val() === "" || regexValidarEmail.test($("#txtActCorreo").val()) == false) {
        $("#errorActcorreo").text("Es obligarotio ingresar un correo valido");
        ok = false;
    } else {
        $("#errorActcorreo").text("");
    }
    if ($("#txtActTelefono").val() === "" || $("#txtActTelefono").val().length != 9) {
        $("#errorActtelefono").text("Es obligarotio ingresar el telefono valido");
        ok = false;
    } else {
        $("#errorActtelefono").text("");
    }
    if ($("#txtActEstado").val() === "") {
        $("#errorEstado").text("Es obligarotio ingresar el estado del usuario");
        ok = false;
    } else {
        $("#errorEstado").text("");
    }

    if ($("#txtActUsuario").val() === "") {
        $("#errorUsuario").text("Es obligarotio ingresar un nombre");
        ok = false;
    } else {
        $("#errorUsuario").text("");
    }

    if ($("#txtActContrasenia").val() === "") {
        $("#errorContrasenia").text("Es obligarotio ingresar  una contraseña");
        ok = false;
    } else {
        $("#errorContrasenia").text("");
    }


    if ($("#cboAccesos").val() === '0') {
        $("#errorAcceso").text("Es obligatorio escoger un perfil ");
        ok = false;
    } else {
        $("#errorAcceso").text("");
    }

    return ok;
}

$(document).on("click", "#btnregistrarusuario", function () {
    if ($("#hddidusuario").val() == 0) {
        if (validarModal()) {
            $.ajax({
                type: "POST",
                contentType: "application/json",
                url: "/Usuario/registrarUsuario",
                data: JSON.stringify({
                    id: $("#hddidusuario").val(),
                    nombre: $("#txtnombre").val(),
                    apellidos: $("#txtapellidos").val(),
                    dni: $("#txtdni").val(),
                    direccion: $("#txtdireccion").val(),
                    email: $("#txtcorreo").val(),
                    telefono: $("#txttelefono").val(),
                    estado: true,
                }),
                success: function (resultado) {
                    if (resultado.respuesta) {
                        alertNotificadora(resultado.mensaje, "success")
                        listarUsuarios();
                    } else {
                        alertNotificadora(resultado.mensaje, "error" +
                            "")
                    }
                }
            })
            $("#modalusuario").modal("hide");
        }
    }

});

$(document).on("click", "#btnCancelar", function () {
    $("#modalActualizar").modal("hide");
});


$(document).on("click", "#btnGuardarCambios", function () {
    //nuevo_estado = document.getElementById("cboEstados").selectedIndex;

    if (validarModalParaActualizar()) {
        //console.log("validado para actualizaar")
        $.ajax({
            type: "PUT",
            contentType: "application/json",
            url: "/Usuario/actualizarUsuario",
            data: JSON.stringify({
                id: $("#hddidusuario").val(),
                nombre: $("#txtActNombre").val(),
                apellidos: $("#txtActApellidos").val(),
                dni: $("#txtActDni").val(),
                direccion: $("#txtActDireccion").val(),
                email: $("#txtActCorreo").val(),
                telefono: $("#txtActTelefono").val(),
                estado: true
                //estado: (nuevo_estado != 1 ? true : false),
            }),
            success: function (resultado) {
                if (resultado.respuesta) {
                    if (parseInt($("#idPerfil").val() ) == 0){
                        if (crearPerfil($("#hddidusuario").val() , document.getElementById("cboAccesos").selectedIndex) == true){
                            alertNotificadora(resultado.mensaje, "success")
                            listarUsuarios();

                            $("#modalActualizar").modal("hide");
                        }else{
                            alertNotificadora('No se pudo crear el perfil', "error")
                        }
                    }else{

                        if (actualizarPerfil($("#hddidusuario").val(), document.getElementById("cboAccesos").selectedIndex) == true) {
                            alertNotificadora(resultado.mensaje, "success")
                            listarUsuarios();

                            $("#modalActualizar").modal("hide");
                        } else {
                            alertNotificadora('No se pudo actualizar', "error")
                        }
                    }


                } else {
                    alertNotificadora(resultado.mensaje, "error")
                }
            }
        })

    } else {
        console.log("No se valido")
    }

})

function actualizarPerfil(idUsuario, cboAccesoIndice) {
    var actualizado = true;
    $.ajax({
        type: "PUT",
        contentType: "application/json",
        url: "/PerfilUsuario/actualizar",
        data: JSON.stringify({
            idPerfil: $("#idPerfil").val(),
            nombreUsuario: $("#txtActUsuario").val(),
            contrasenia: $("#txtActContrasenia").val(),
            idAcceso: cboAccesoIndice,
            idUsuario: idUsuario,

        }),
        success: function (resultado) {
            if (!resultado.respuesta) {
                actualizado = false;

            } else {
                console.log("Actualiza Perfil: " + resultado.mensaje);
            }
        }
    });
    //alert(actualizado)
    return actualizado;
}

function crearPerfil(idUsuario, cboAccesoIndice) {
    var creado = true;
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/PerfilUsuario/crear",
        data: JSON.stringify({
            nombreUsuario: $("#txtActUsuario").val(),
            contrasenia: $("#txtActContrasenia").val(),
            idAcceso: cboAccesoIndice,
            idUsuario: idUsuario,

        }),
        success: function (resultado) {
            if (!resultado.respuesta) {
                creado = false;

            } else {
                console.log("Perfil creado: " + resultado.mensaje);
            }
        }
    });
    //alert(actualizado)
    return creado;

}

$(document).on("click", "#btnagregarusuario", function () {
    $("#txtnombre").val("");
    $("#txtapellidos").val("");
    $("#txtdni").val("");
    $("#txtdireccion").val("");
    $("#txtcorreo").val("");
    $("#txttelefono").val("");
    $("#txtestado").val("");
    $("#hddidusuario").val("0");
    $("#modalusuario").modal("show");
});



$(document).on("click", ".btnactualizarusuario", function () {
    $("#txtActNombre").val($(this).attr("data-nombre"));
    $("#txtActApellidos").val($(this).attr("data-apellidos"));
    $("#txtActDni").val($(this).attr("data-dni"));
    $("#txtActDireccion").val($(this).attr("data-direccion"));
    $("#txtActCorreo").val($(this).attr("data-correo"));
    $("#txtActTelefono").val($(this).attr("data-telefono"));
    $("#txtActEstado").val($(this).attr("data-estado"));
    $("#hddidusuario").val($(this).attr("data-codusuario"));

    //Setear el combo box
    //$("#cboEstados").val((String($("#txtActEstado").val()) == "true" ? "1" : "0"))
    obtenerPerfilUsuario();
    $("#modalActualizar").modal("show");
});

$(document).on("click", ".btneliminarusuario", function () {
    $("#hddidusuarioeliminar").val("");
    $("#hddidusuarioeliminar").val($(this).attr("data-codusuario"));
    //alert($("#hddidusuarioeliminar").val());

    //$("#modaleliminarusuario").modal("show");
    Swal.fire({
        title: "¿Esta seguro de eliminar al usuario: " +
            $(this).attr("data-nombre") + "?",
        text: "No se revertiran los cambios",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Si, eliminar'
    }).then((result) => {
        if (result.isConfirmed) {
            $.ajax({
                type: "POST",
                contentType: "application/json",
                url: "/Usuario/eliminarUsuario",
                data: JSON.stringify({
                    id: $("#hddidusuarioeliminar").val(),

                }),
                success: function (resultado) {
                    if (resultado.respuesta) {
                        Swal.fire(
                            'Eliminado con exito!',
                            'El usuario ha sido eliminado',
                            'success'
                        )
                        listarUsuarios();
                    } else {
                        alertNotificadora(resultado.mensaje, "error")
                    }
                    //$("#modaleliminarusuario").modal("hide");
                }
            })


        }
    })
});

function obtenerPerfilUsuario() {
    $.ajax({
        type: "GET",
        url: "/PerfilUsuario/obtenerPerfil",
        dataType: 'json',
        data: {
            idUsuario: $("#hddidusuario").val()
        },
        success: function (resultado) {
            $("#idPerfil").val(resultado.id);
            $("#txtActUsuario").val(resultado.nombreUsuario);
            $("#txtActContrasenia").val(resultado.contraseniaUsuario);
            tUsuario = definirTipoDeUsuarioDesdeAccesos(resultado);

            $("#cboAccesos").val(tUsuario);

            //alert( $("#idPerfil").val());


        }
    })
}

function definirTipoDeUsuarioDesdeAccesos(diccionario) {
    TipoUsuario = "";
    try {
        gCitas = Boolean(diccionario.idAcceso.gestionCitas);
        gAtencion = Boolean(diccionario.idAcceso.gestionAtencion);
        gAdmision = Boolean(diccionario.idAcceso.gestionAdmision);
        gSeguridad = Boolean(diccionario.idAcceso.gestionSeguridad);

        TipoUsuario = "";
        if (gCitas && gAtencion && gAdmision && gSeguridad) {
            TipoUsuario = "1" //Viene a hacer el administrador en el select del HTML
        }
        if (gCitas && gAtencion != true && gAdmision && gSeguridad != true) {
            TipoUsuario = "2" //Viene a hacer el Recepcionista en el select del HTML
        }
        if (gCitas != true && gAtencion && gAdmision != true && gSeguridad != true) {
            TipoUsuario = "3" //Viene a hacer el Veterinario en el select del HTML
        }
        console.log("Usuario -> " + TipoUsuario);
    } catch (e) {
        TipoUsuario = "0"
        console.log("Hubo valores nulos");

    }

    return TipoUsuario;

}

function listarUsuarios() {
    $.ajax({
        type: "GET",
        url: "/Usuario/listarUsuarios",
        dataType: 'json',
        success: function (resultado) {
            $("#tblusuario > tbody").html("");
            $.each(resultado, function (index, value) {
                estado = (value.estado ? "Activo" : "Inactivo");
                $("#tblusuario > tbody").append(`<tr><td>${value.id}</td><td>${value.nombre}</td><td>${value.apellidos}</td><td>${value.dni}</td><td>${value.direccion}</td><td>${value.email}</td><td>${value.telefono}</td><td>${estado}</td><td><button type='button' class='btn btn-primary btnactualizarusuario' data-codusuario='${value.id}'  data-nombre='${value.nombre}'  data-apellidos='${value.apellidos}'  data-dni='${value.dni}'  data-direccion='${value.direccion}'  data-correo='${value.email}'  data-telefono='${value.telefono}'  data-estado='${value.estado}' ><i class='fa-solid fa-pen'></i></button></td>  
                <td><button type='button' class='btn btn-danger btneliminarusuario' data-codusuario='${value.id}'  data-nombre='${value.nombre}' ><i class='fa-solid fa-trash-can'></i></button></td></tr>`
                )
            })
        }
    })

}

function alertNotificadora(msg, icon) {

    Swal.fire({
        //position: 'top-end',
        icon: icon,
        title: msg,
        showConfirmButton: false,
        timer: 2500
    })
}

	
 