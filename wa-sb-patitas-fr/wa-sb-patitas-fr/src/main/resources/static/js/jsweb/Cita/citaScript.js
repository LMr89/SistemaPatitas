var diccionario_vete = NaN;
var diccionario_horariosVet = NaN;

$(document).on("click", "#btnListar", function() {
	listarCitas();

});

$(document).on("click", "#btngenerarCita", function() {
	$("#txtcliente").val("");
	$("#txtmascota").val("");
	$("#txtveterinario").val("");
	$("#txtrecepcionista").val("");
	$("#txtfecharegistro").val("");
	$("#txtfechaatencion").val("");
	$("#txtpendiente").val("");
	$("#hddidcita").val("0");

	/*Cargo todos los veterinarios*/
	obtenerVeterinarios();

	/*Cargar nombre de la recepcionista*/
	$("#txtrecepcionista").text("Andrea");

	$("#modalcita").modal("show");
});

/*Funcion que escuchara los cambios del combobox de veterinarios y este cargara los horarios de cada uno*/


$("#cboVet").change(function() {
	var idVeterinario = $("#cboVet option:selected").val()

	var msg_horas = "";
	//console.log(diccionario_vete);
	for (let i = 0; i < diccionario_vete.length; i++) {
		if (diccionario_vete[i].id == idVeterinario) {
			diccionario_horariosVet = diccionario_vete[i].horarios;
			//console.log("Horarios obtenidos: " + lista_horarios);

			msg_horas += "De: ";
			for (let i = 0; i < diccionario_horariosVet.length; i++) {

				msg_horas += diccionario_horariosVet[i].fechaInicio + " a " + diccionario_horariosVet[i].fechaFin + "\ny ";
				//console.log(diccionario_horasVet);
			}
			break;
		}
	}
	if (msg_horas !== "") {

		mostrarMensajeVet(msg_horas, "success");
	} else {
		$("#msgVet").html("")
	}
	console.log(diccionario_horariosVet);

});


$(document).on("click", ".btnactualizarcita", function() {
	$("#txtcliente").val($(this).attr("data-cliente"));
	$("#txtmascota").val($(this).attr("data-mascota"));
	$("#txtveterinario").val($(this).attr("data-veterinario"));
	$("#txtrecepcionista").val($(this).attr("data-recepcionista"));
	$("#txtfecharegistro").val($(this).attr("data-fecharegistro"));
	$("#txtfechaatencion").val($(this).attr("data-fechaatencion"));
	$("#txtpendiente").val($(this).attr("data-pendiente"));
	$("#hddidcita").val($(this).attr("data-codusuario"));
	$("#modalcita").modal("show");
});


$(document).on("click", "#btnBuscarCliente", function() {
	if ($("#txtcliente").val() === "") {
		mostrarMensaje("Es obligatorio buscar un cliente", "danger");
		ok = false;
	} else {
		$.ajax({
			type: "GET",
			contentType: "application/json",
			url: "/Cita/buscarCliente",
			data: {
				dni: $("#txtcliente").val()
			},


			success: function(resultado) {
				if (resultado.estado) {
					$("#idCliente").val(resultado.id);

					mostrarMensaje("Cliente: " + resultado.nombre + " " + resultado.apellidos, "success")
					obtenerMascotasDelCliente(resultado.id);
				}
				else {
					mostrarMensaje("Cliente no encontrado", "danger")
				}
				$("#modaleliminarusuario").modal("hide");
			}
		})
	}


});


function obtenerVeterinarios() {
	$.ajax({
		type: "GET",
		contentType: "application/json",
		url: "/Veterinario/listar-vet",
		dataType: 'json',

		success: function(resultado) {
			diccionario_vete = resultado;


			$("#cboVet").html("");

			$("#cboVet").append(

				"<div class=\"mb-3\">" +
				"	<select id=\"cboVet\" class=\"form-select\" aria-label=\"Default select example\" required>"
				//"			<option value=\"0\">Seleccione el veterinario</option>"
			);
			$("#cboVet").append("<option value=\"0\">Seleccione el veterinario</option>");
			for (let i = 0; i < resultado.length; i++) {
				$("#cboVet").append("<option value=\"" + resultado[i].id + "\">" + resultado[i].nombre + " " + resultado[i].apellidos + "</option>");
			}

			$("#cboVet").append("</select></div>");

		}
	})

}



function obtenerMascotasDelCliente(idCliente) {
	$.ajax({
		type: "GET",
		contentType: "application/json",
		url: "/Cita/obtener-mascotas",
		data: {
			id: idCliente
		},

		success: function(resultado) {
			$("#cboMascota").html("");

			$("#cboMascota").append(

				"<div class=\"mb-3\">" +
				"	<label for=\"txtestado\" class=\"form-label\">Estado </label>" +
				"	<select id=\"txtestado\" class=\"form-select\" aria-label=\"Default select example\">");

			$("#cboMascota").append("<option value=\"0\">Seleccione la mascota</option>");
			for (let i = 0; i < resultado.length; i++) {

				$("#cboMascota").append("<option value=\"" + resultado[i].idMascota + "\">" + resultado[i].nombre + "</option>");
			}

			$("#cboMascota").append("	</select> <span id=\"errorestado\" class=\"text-danger\"></span>" +
				"</div>");

		}
	})

};

/*Metodo que registrara la cita pero ya validadndo los campos*/
$(document).on("click", "#btnregistrarcita", function() {
	$("#mensaje").html("")
	if (validacionModal()) {
		$.ajax({
			type: "POST",
			contentType: "application/json",
			url: "/Cita/registrarCita",
			data: JSON.stringify({
				id: 0,
				cliente: { "id": $("#idCliente").val() },
				mascota: { "idMascota": $("#cboMascota option:selected").val() },
				veterinario: { "id": $("#cboVet option:selected").val() },
				recepcionista: { "idRecepcionista": 1 },
				fechaRegistro: new Date().toISOString(),
				fechaAtencion: new Date($("#txtfechaatencion").val()).toISOString(),
				pendiente: true
			}),
			success: function(resultado) {
				if (resultado.respuesta) {
					mostrarMensaje(resultado.mensaje, "success")
				}
				else {
					mostrarMensaje(resultado.mensaje, "danger")
				}
			}
		})
		$("#modalcita").modal("hide");
		listarCitas();
	}


});




$(document).on("click", ".btneliminarusuario", function() {
	$("#hddidusuarioeliminar").val("");
	$("#mensajeeliminar").text("¿esta seguro de eliminar al usuario: " +
		$(this).attr("data-nombre") + "?");
	$("#hddidusuarioeliminar").val($(this).attr("data-codusuario"));

	$("#modaleliminarusuario").modal("show");
});

$(document).on("click", "#btneliminarusuario", function() {

	$.ajax({
		type: "DELETE",
		contentType: "application/json",
		url: "/Usuario/eliminarUsuario",
		data: JSON.stringify({
			id: $("#hddidusuarioeliminar").val(),

		}),
		success: function(resultado) {
			if (resultado.respuesta) {
				mostrarMensaje(resultado.mensaje, "success")
				ListarUsuarios();
			}
			else {
				mostrarMensaje(resultado.mensaje, "danger")
			}
			$("#modaleliminarusuario").modal("hide");
		}
	})

});

function validacionModal() {
	var ok = true;

	if ($("#txtcliente").val() === "") {
		//mostrarMensaje("Es obligatorio buscar un cliente", "danger");
		$("#errCliente").text("Es obligatorio buscar un cliente");
		ok = false;
	}else{
		$("#errCliente").text("");
	}

	if ($("#cboMascota option:selected").text() === "Seleccione la mascota") {
		$("#errormascota").text("Es obligarotio escoger una mascota");
		ok = false;
	} else {
		$("#errormascota").text("");
	}

	if ($("#cboVet option:selected").text() === "Seleccione el veterinario") {
		//mostrarMensajeVet("Es obligarotio escoger un veterinario", "danger");
			$("#errVet").text("Es obligarotio escoger un veterinario");
		ok = false;
	} else {
		$("#errVet").html("");
	}

	//alert($("#txtfechaatencion").val() === NaN? "Vacio":"lleno");
	var fechaInput = new Date($("#txtfechaatencion").val());
	if(fechaInput < Date.now()){
		$("#errorfechaatencion").text("La fecha escogida no debe ser menor a la fecha actual");
		ok = false;
	}else{
		if (validarHorasConVeterinario() !== true) {
		$("#errorfechaatencion").text("La fecha de atencion no coincide con el horario del veterinario");
		ok = false;
	} else {
		$("#errorfechaatencion").text("");
	}
	}
	


	return ok;

}




function validarHorasConVeterinario() {
	var horaOk = false;
	var turnoManana = false;
	var turnoTarde = false;

	var horaInput = new Date($("#txtfechaatencion").val());

	for (let i = 0; i < diccionario_horariosVet.length; i++) {
		var horaFechaInicio = parseInt(String(diccionario_horariosVet[i].fechaInicio).substring(0, 2));
		//console.log(horaFechaInicio);

		var minutoFechaInicio = parseInt(String(diccionario_horariosVet[i].fechaInicio).substring(3, 5));
		//console.log(minutoFechaInicio);

		var horaFechaFin = parseInt(String(diccionario_horariosVet[i].fechaFin).substring(0, 2));
		//console.log(horaFechaFin);

		var minutoFechaFin = parseInt(String(diccionario_horariosVet[i].fechaFin).substring(3, 5));

		if (i === 0) {
			//Validcion del turno Mañana
			if (horaInput.getHours() >= horaFechaInicio && horaInput.getMinutes() >= minutoFechaInicio &&
				horaInput.getHours() < horaFechaFin) {
				turnoManana = true;
				console.log(turnoManana);
			}

		} else {
			//Validcion del turno Tarde
			if (horaInput.getHours() >= horaFechaInicio && horaInput.getMinutes() >= minutoFechaInicio &&
				horaInput.getHours() <= horaFechaFin) {

				turnoTarde = true;
				if (horaInput.getHours() == 17 && horaInput.getMinutes() >= minutoFechaFin) {
					turnoTarde = false;
				}
			}
		}
	}

	/*Validacion final del turno*/
	if (turnoManana == true || turnoTarde == true) {
		horaOk = true;
	}

	console.log(horaOk);
	return horaOk;

}

function listarCitas() {
	$.ajax({
		type: "GET",
		url: "/Cita/listarCitas",
		dataType: 'json',
		success: function(resultado) {
			$("#tblcita > tbody").html("");
			$.each(resultado, function(index, value) {
				$("#tblcita > tbody").append("<tr>" +
					"<td>" + value.id + "</td>" +
					"<td>" + value.cliente["nombre"] + "</td>" +
					"<td>" + value.mascota["nombre"] + "</td>" +
					"<td>" + value.veterinario["nombre"] + "</td>" +
					"<td>" + value.recepcionista["nombre"] + "</td>" +
					"<td>" + new Date(value.fechaRegistro).toUTCString() + "</td>" +
					"<td>" + new Date(value.fechaAtencion).toUTCString() + "</td>" +
					"<td>" + (value.pendiente ? "Activo" : "Inactivo") + "</td>" +
					"<td><button type =\"button\" class=\"btn btn-primary btnactualizarcita\"" +
					"	th:data-codcita=\"" + value.id + "\"" +
					"	th:data-codcliente=\"" + value.cliente + "\"" +
					"	th:data-codmascota=\"" + value.mascota + "\"" +
					"	th:data-codveterinario=\"" + value.veterinario + "\"" +
					"	th:data-codrecepcionista=\"" + value.recepcionista + "\"" +
					"	th:data-fecharegistro=\"" + value.fechaRegistro + "\"" +
					"	th:data-fechaatencion=\"" + value.fechaAtencion + "\"" +
					"	th:data-pendiente=\"" + value.pendiente + "\">Actualizar</button >" +
					"</td >" +
					"<td>" +
					"	<button type=\"button\" class=\"btn btn-danger btneliminarcita\"" +
					"		th:data-codcliente=\"" + value.id + "\"" +
					"		th:data-codmascota=\"" + value.mascota + "\"" +
					"		th:data-codveterinario=\"" + value.veterinario + "\"" +
					"		th:data-codrecepcionista=\"" + value.recepcionista + "\"" +
					"		th:data-fecharegistro=\"" + value.fechaRegistro + "\"" +
					"		th:data-fechaatencion=\"" + value.fechaAtencion + "\"" +
					"		th:data-pendiente=\"" + value.pendiente + "\">Eliminar</button>" +

					"</td>" +
					"</tr>"
				)
			})
		}
	})
}


function mostrarMensaje(mensaje, estilo) {
	$("#mensaje").html("")
	$("#mensaje").append("<div class='alert alert-" + estilo + " alert-dismissible fade show' role='alert'>" +
		"<strong>" + mensaje + "</strong>" +
		"<button type='button' class='btn-close' data-bs-dismiss='alert' aria-label='Close'></button>" +
		"</div>")

}
function mostrarMensajeVet(mensaje, estilo) {
	$("#msgVet").html("")
	$("#msgVet").append("<div class='alert alert-" + estilo + " alert-dismissible fade show' role='alert'>" +
		"<strong>" + mensaje + "</strong>" +
		"<button type='button' class='btn-close' data-bs-dismiss='alert' aria-label='Close'></button>" +
		"</div>")

}