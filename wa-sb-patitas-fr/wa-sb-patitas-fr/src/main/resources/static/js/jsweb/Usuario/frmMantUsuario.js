var veterinarioTieneHorario = false;

$("#cboAccesos").change(function() {
	if ($("#cboAccesos").val() === "3") {
		$("#formHorario").show();
	} else {
		$("#formHorario").hide();
	}
})

$("#hManInicio").change(function() {
	alert($("#hManInicio").val())
})

$(document).on("click", "#btnagregarusuario", function() {
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

var campo = {
	nombre : false,
	apellidos : false ,
	dni : false,
	direccion : false,
	correo : false,
	telefono : false,
	estado : false
};
function validarModal() {

	let regexValidarEmail = /^(([^<>()[\]\.,;:\s@\"]+(\.[^<>()[\]\.,;:\s@\"]+)*)|(\".+\"))@(([^<>()[\]\.,;:\s@\"]+\.)+[^<>()[\]\.,;:\s@\"]{2,})$/i;

	if ($("#txtnombre").val() === "") {
		$("#errornombre").text("Es obligarotio ingresar el nombre del usuario");

		campo['nombre']  = false;
	} else {
		$("#errornombre").text("");
		campo['nombre']  = true;
	
	}

	if ($("#txtapellidos").val() === "") {
		$("#errorapellidos").text("Es obligarotio ingresar el apellido del usuario");
		campo['apellidos']  = false;
	} else {
		$("#errorapellidos").text("");
		campo['apellidos']  = true;
	}
	if ($("#txtdni").val() === "" || $("#txtdni").val().length != 8) {
		$("#errordni").text("Es obligarotio ingresar un DNI valido");
		campo['dni']  = false;

	} else {
		$("#errordni").text("");
		campo['dni']  = true;
	}
	if ($("#txtdireccion").val() === "") {
		$("#errordireccion").text("Es obligarotio ingresar la direccion del usuario");
		campo['direccion']  = false;
	} else {
		$("#errordireccion").text("");
		campo['direccion']  = true;
	}
	if ($("#txtcorreo").val() === "" || regexValidarEmail.test($("#txtcorreo").val()) == false) {
		$("#errorcorreo").text("Es obligarotio ingresar un correo valido");
		campo['correo']  = false;
	} else {
		$("#errorcorreo").text("");
		campo['correo']  = true;
	}
	if ($("#txttelefono").val() === "" || $("#txttelefono").val().length != 9) {
		$("#errortelefono").text("Es obligarotio ingresar el telefono valido");
		campo['telefono']  = false;
	} else {
		$("#errortelefono").text("");
		campo['telefono']  = true;
	}
	if ($("#txtestado").val() === "") {
		$("#errorestado").text("Es obligarotio ingresar el estado del usuario");
		campo['estado']  = false;
	} else {
		$("#errorestado").text("");
		campo['estado']  = true;
	}

	if (campo.nombre && campo.apellidos && campo.dni &&
			campo.direccion && campo.correo && campo.telefono && campo.estado){
		return true;
	}else{
		return false;
	}
}


var campAct = {
	nombre : false,
	apellidos : false ,
	dni : false,
	direccion : false,
	correo : false,
	telefono : false,
	estado : false,
	username : false,
	password : false,
	acceso : false
};
function validarModalParaActualizar() {

	let regexValidarEmail = /^(([^<>()[\]\.,;:\s@\"]+(\.[^<>()[\]\.,;:\s@\"]+)*)|(\".+\"))@(([^<>()[\]\.,;:\s@\"]+\.)+[^<>()[\]\.,;:\s@\"]{2,})$/i;

	if ($("#txtActNombre").val() === "") {
		$("#errorActnombre").text("Es obligarotio ingresar el nombre del usuario");
		campAct['nombre'] = false;
	} else {
		$("#errorActnombre").text("");
		campAct['nombre'] = true;
	}

	if ($("#txtActApellidos").val() === "") {
		$("#errorActapellidos").text("Es obligarotio ingresar el apellido del usuario");
		campAct['apellidos'] = false;
	} else {
		$("#errorActapellidos").text("");
		campAct['apellidos'] = true;
	}
	if ($("#txtActDni").val() === "" || $("#txtActDni").val().length != 8) {
		$("#errorActdni").text("Es obligarotio ingresar un DNI valido");
		campAct['dni'] = false;
	} else {
		$("#errorActdni").text("");
		campAct['dni'] = true;

	}

	if ($("#txtActDireccion").val() === "") {
		$("#errorActdireccion").text("Es obligarotio ingresar la direccion del usuario");
		campAct['direccion'] = false;
	} else {
		$("#errorActdireccion").text("");
		campAct['direccion'] = true;
	}
	if ($("#txtActCorreo").val() === "" || regexValidarEmail.test($("#txtActCorreo").val()) == false) {
		$("#errorActcorreo").text("Es obligarotio ingresar un correo valido");
		campAct['correo'] = false;
	} else {
		$("#errorActcorreo").text("");
		campAct['correo'] = true;
	}
	if ($("#txtActTelefono").val() === "" || $("#txtActTelefono").val().length != 9) {
		$("#errorActtelefono").text("Es obligarotio ingresar el telefono valido");
		campAct['telefono'] = false;
	} else {
		$("#errorActtelefono").text("");
		campAct['telefono'] = true;
	}
	if ($("#txtActEstado").val() === "") {
		$("#errorEstado").text("Es obligarotio ingresar el estado del usuario");
		campAct['estado'] = false;
	} else {
		$("#errorEstado").text("");
		campAct['estado'] = true;
	}

	if ($("#txtActUsuario").val() === "") {
		$("#errorUsuario").text("Es obligarotio ingresar un nombre");
		ok = false;
		campAct['username'] = false;
	} else {
		$("#errorUsuario").text("");
		campAct['username'] = true;
	}

	if ($("#txtActContrasenia").val() === "") {
		$("#errorContrasenia").text("Es obligarotio ingresar  una contraseña");
		campAct['password'] = false;

	} else {
		$("#errorContrasenia").text("");
		campAct['password'] = true;
	}


	if ($("#cboAccesos").val() === '0') {
		$("#errorAcceso").text("Es obligatorio escoger un perfil ");
		campAct['acceso'] = false;

	} else {
		$("#errorAcceso").text("");
		campAct['acceso'] = true;
	}

	if (campAct.nombre && campAct.apellidos && campAct.dni &&
		campAct.direccion && campAct.correo && campAct.telefono && campAct.estado
		&& campAct.username && campAct.password && campAct.acceso){
		return true;
	}else{
		return false;
	}
}

$(document).on("click", "#btnregistrarusuario", function() {
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
				success: function(resultado) {
					if (resultado.respuesta) {
						//alertNotificadora(resultado.mensaje, "success")
						listarUsuarios();
						/*Alerta que le informara al usuario que debe asignarle nuevos permisos
						* al usuario creado*/

						Swal.fire({
							//position: 'top-end',
							icon: 'success',
							title: resultado.respuesta,
							showConfirmButton: false,
							timer: 3500,
							didClose : ()=>{
								Swal.fire({
									title: "Siguiente paso",
									html: 'Ahora que ya has creado un nuevo usuario <br> configura sus credenciales y accesos. <br> CLickea el boton  <i class=\"fa-solid fa-pen\"></i> ',
									icon: "warning",
									button: "Entendido",
								});
							}
						})




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

$(document).on("click", "#btnCancelar", function() {
	$("#modalActualizar").modal("hide");
});

$(document).on("click", "#btnGuardarCambios", function() {
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
			success: function(resultado) {
				if (resultado.respuesta) {
					if (parseInt($("#idPerfil").val()) == 0) {
						if (crearPerfil($("#hddidusuario").val(), document.getElementById("cboAccesos").selectedIndex) == true) {

							setTimeout(function() {
								if (document.getElementById("cboAccesos").selectedIndex == 3) {
									registrarHorario()
								}
							}, 2000);

							alertNotificadora(resultado.mensaje, "success")
							listarUsuarios();

							$("#modalActualizar").modal("hide");
						} else {
							alertNotificadora('No se pudo crear el perfil', "error")
						}
					} else {

						if (actualizarPerfil($("#hddidusuario").val(), document.getElementById("cboAccesos").selectedIndex) == true) {
							if (document.getElementById("cboAccesos").selectedIndex == 3) {

								/*Si el veterinario no tiene horario se crea 1 sino se le actualiza*/
								if (veterinarioTieneHorario == false){
									registrarHorario()
									veterinarioTieneHorario = false;
								}else{
									//Actualizacion del horario
									actualizarHorario();
								}
							}
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

function usuarioTieneHorario(){
	$.ajax({
		type: "GET",
		contentType: "application/json",
		url: "/horario/existe-horario",
		data: {idUsu : $("#hddidusuario").val() },
		success: function(resultado) {
			if (resultado.respuesta) {
				veterinarioTieneHorario = true;
				alert("Existe un horario: " + veterinarioTieneHorario);

			}else{
				alert(resultado.mensaje)

			}

		}
	});
}

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
		success: function(resultado) {
			if (!resultado.respuesta) {
				actualizado = false;

			}
			//Chequear este condicional
			else {
				console.log("Actualiza Perfil: " + resultado.mensaje);
			}
		}
	});

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
		success: function(resultado) {
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

$(document).on("click", ".btnactualizarusuario", function() {
	$("#txtActNombre").val($(this).attr("data-nombre"));
	$("#txtActApellidos").val($(this).attr("data-apellidos"));
	$("#txtActDni").val($(this).attr("data-dni"));
	$("#txtActDireccion").val($(this).attr("data-direccion"));
	$("#txtActCorreo").val($(this).attr("data-correo"));
	$("#txtActTelefono").val($(this).attr("data-telefono"));
	$("#txtActEstado").val($(this).attr("data-estado"));
	$("#hddidusuario").val($(this).attr("data-codusuario"));

	usuarioTieneHorario();

	//Setear el combo box
	//$("#cboEstados").val((String($("#txtActEstado").val()) == "true" ? "1" : "0"))
	obtenerPerfilUsuario();
	$("#modalActualizar").modal("show");

});

$(document).on("click", ".btneliminarusuario", function() {
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
				success: function(resultado) {
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
		success: function(resultado) {
			$("#idPerfil").val(resultado.id);
			$("#txtActUsuario").val(resultado.nombreUsuario);
			$("#txtActContrasenia").val(resultado.contraseniaUsuario);
			tUsuario = definirTipoDeUsuarioDesdeAccesos(resultado);


			if (tUsuario !== "3") {
				$("#formHorario").hide();

			} else {
				$("#formHorario").show();
			}


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
		success: function(resultado) {
			$("#tblusuario > tbody").html("");
			$.each(resultado, function(index, value) {
				estado = (value.estado ? "Activo" : "Inactivo");
				$("#tblusuario > tbody").append(`<tr><td>${value.id}</td><td>${value.nombre}</td><td>${value.apellidos}</td><td>${value.dni}</td><td>${value.direccion}</td><td>${value.email}</td><td>${value.telefono}</td><td>${estado}</td><td><button type='button' class='btn btn-primary btnactualizarusuario' data-codusuario='${value.id}'  data-nombre='${value.nombre}'  data-apellidos='${value.apellidos}'  data-dni='${value.dni}'  data-direccion='${value.direccion}'  data-correo='${value.email}'  data-telefono='${value.telefono}'  data-estado='${value.estado}' ><i class='fa-solid fa-pen'></i></button></td>  
                <td><button type='button' class='btn btn-danger btneliminarusuario' data-codusuario='${value.id}'  data-nombre='${value.nombre}' ><i class='fa-solid fa-trash-can'></i></button></td></tr>`
				)
			})
		}
	})

}

function obtenerIdVetPorIdUsuario() {

	$.ajax({
		type: "GET",
		url: "/Veterinario/obtener-idVet",
		dataType: 'json',
		data: {
			idUsuario: $("#hddidusuario").val()
		},
		success: function(resultado) {
			IdVet = resultado;
			$("#idVeterinario").val(IdVet);
			console.log("Id de veterinario obtenido: " + IdVet);
		}
	})

}

function registrarHorario() {
	var registrado = false;


	try {
		var hManIncio = $("#hManInicio").val();
		var hManFin = $("#hManFin").val();
		var hTardIncio = $("#hTarInicio").val();
		var hTardFin = $("#hTarFin").val();

		/*No le estoy mandando el id del veterinario sino el id del usuario*/

		/*Registrar el horario del usuario*/
		$.ajax({
			type: "POST",
			url: "/horario/registrar-horario",
			contentType: "application/json",
			data: JSON.stringify({
				idVet: $("#hddidusuario").val(),
				manInicio: $("#hManInicio").val(),
				manFin: $("#hManFin").val(),
				tarInicio: $("#hTarInicio").val(),
				tarFin: $("#hTarFin").val()
			})
		})
	} catch (e) {
		console.log("Hubo un error al insertar los horarios ")

	}
}

function actualizarHorario(){
	try {
		var hManIncio = $("#hManInicio").val();
		var hManFin = $("#hManFin").val();
		var hTardIncio = $("#hTarInicio").val();
		var hTardFin = $("#hTarFin").val();

		/*No le estoy mandando el id del veterinario sino el id del usuario*/

		/*Registrar el horario del usuario*/
		$.ajax({
			type: "PUT",
			url: "/horario/actualizar-horario",
			contentType: "application/json",
			data: JSON.stringify({
				idVet: $("#hddidusuario").val(),
				manInicio: $("#hManInicio").val(),
				manFin: $("#hManFin").val(),
				tarInicio: $("#hTarInicio").val(),
				tarFin: $("#hTarFin").val()
			})
		})
	} catch (e) {
		console.log("Hubo un error al actualizar los horarios ")

	}
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


