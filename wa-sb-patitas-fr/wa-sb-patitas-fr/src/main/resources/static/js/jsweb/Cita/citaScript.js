var diccionario_vete = NaN;
var diccionario_horariosVet = NaN;


$(document).ready(function(){
	listarCitas();
});

$(document).on("click", "#btnListar", function() {
	listarCitas();

});


$(document).on("click", "#btngenerarCita", function() {
	$("#msgVet").html("")
	$("#mensaje").html("")

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

	//alert($("#hddidcita").val());
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
			break;
		}

	}

	if (diccionario_horariosVet.length > 0 ){
		msg_horas = "Mañana: " + diccionario_horariosVet[0].manInicio + " - "+ diccionario_horariosVet[0].manFin+
			"<br>Tarde: " + diccionario_horariosVet[0].tarInicio + " - "+ diccionario_horariosVet[0].tarFin;

	}
	if (msg_horas !== "") {
		mostrarMensajeVet(msg_horas, "success");

	} else {
		$("#msgVet").html("")
	}

	//console.log(validarHoras());


});

$(document).on("click", ".btnactualizarcita", function() {



	$("#txtcliente").val($(this).attr("data-dnicliente"));
	$("#btnBuscarCliente").trigger("click"); // Se auto ejecuta el boton


	obtenerVeterinarios();

	$("#cboVet").val($(this).attr("data-veterinario"));

	$("#txtrecepcionista").val($(this).attr("data-codrecepcionista"));
	$("#txtfechaatencion").val($(this).attr("data-fechaatencion"));
	$("#txtpendiente").val($(this).attr("data-pendiente"));
	$("#hddidcita").val($(this).attr("data-codcita"));
	//alert($("#hddidcita").val());
	$("#modalcita").modal("show");
});

$(document).on("click", "#btnBuscarCliente", function() {
	if ($("#txtcliente").val() === "") {
		mostrarMensaje('Es obligatorio buscar un cliente', "danger");
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

				if (resultado.estado) {
					$("#idCliente").val(resultado.id);

					mostrarMensaje("Cliente: " + resultado.nombre + " " + resultado.apellidos, "success")
					obtenerMascotasDelCliente(resultado.id);

				}

				$("#cboVet").append("</select></div>");

			}
		}

	});
}

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

	if ($("#hddidcita").val() == 0) {

		//Si el codigo de cita es distinto de 0 este es un registro		
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
					recepcionista: { "idRecepcionista": 4 },

					fechaRegistro: new Date().toISOString(),
					fechaAtencion: new Date($("#txtfechaatencion").val()).toISOString(),
					pendiente: true

				}),
				success: function(resultado) {
					if (resultado.respuesta) {
						alertNotificadora("Cita registrada exitosamente", "success")
						listarCitas();
						$("#modalcita").modal("hide");
					}
					else {
						alertNotificadora(resultado.mensaje, "error")
					}
				}
			})


		}
	} else {
		if (validacionModal()) {
			//En el caso que el codigo es distinto de 0 este se actualizara
			$.ajax({
				type: "PUT",
				contentType: "application/json",
				url: "/Cita/actualizarCita",
				data: JSON.stringify({
					id: $("#hddidcita").val(),
					cliente: { "id": $("#idCliente").val() },
					mascota: { "idMascota": $("#cboMascota option:selected").val() },
					veterinario: { "id": $("#cboVet option:selected").val() },
					recepcionista: { "idRecepcionista": 4  },

					fechaRegistro: new Date().toISOString(),
					fechaAtencion: new Date($("#txtfechaatencion").val()).toISOString(),
					pendiente: true

				}),
				success: function(resultado) {
					if (resultado.respuesta) {
						alertNotificadora("Cita actualizada exitosamente", "success")
						listarCitas();
						$("#hddidcita").val(0);
						$("#modalcita").modal("hide");
					}
					else {
						alertNotificadora(resultado.mensaje, "error")
					}
				}
			})

		}

	};

});

$(document).on("click", ".btneliminarcita", function() {
	var nombreCliente = $(this).attr("data-nomCliente");

	var codigoCita = String($(this).attr("data-codcita"));
	//alert(codigoCita);

	Swal.fire({
		title: '¿Desea cancelar la cita del cliente ' + nombreCliente,
		text: "No se revertiran los cambios",
		icon: 'warning',
		showCancelButton: true,
		confirmButtonColor: '#3085d6',
		cancelButtonColor: '#d33',
		confirmButtonText: 'Si, cancelar'
	}).then((result) => {
		if (result.isConfirmed) {
			if (eliminarCita(codigoCita)) {
				Swal.fire(
					'Cancelado con exito!',
					'La cita fue cancelada.',
					'success'
				)
				listarCitas();
			}

		}
	})

});

function eliminarCita(idCita) {
	var eliminado = true;
	$.ajax({
		type: "DELETE",
		contentType: "application/json",
		url: "/Cita/eliminarCita/" + idCita,
		//data: JSON.stringify({
		//	idEliminar: idCita
		//}),
		success: function(resultado) {
			if (!resultado.respuesta) {
				eliminado = false;

			}
		}

	});
	return eliminado;
}

/*Serviran para las validaciones del formulario*/
const campos = {
	cliente : false,
	mascota : false,
	veterinario : false,
	fechaAtencion : false
}

function validacionModal() {
	if ($("#txtcliente").val() === "") {
		$("#errCliente").text("Es obligatorio buscar un cliente");
		campos['cliente'] = false;

	} else {
		$("#errCliente").text("");

		campos['cliente'] = true;
	}

	if ($("#cboMascota option:selected").text() === "Seleccione la mascota") {
		$("#errormascota").text("Es obligarotio escoger una mascota");
		campos['mascota'] = false;

	} else {
		$("#errormascota").text("");
		campos['mascota'] = true;
	}

	if ($("#cboVet option:selected").text() === "Seleccione el veterinario") {
		$("#errVet").text("Es obligarotio escoger un veterinario");
		campos['veterinario'] = false;

	} else {
		$("#errVet").html("");
		campos['veterinario'] = true;
	}

	var fechaInput = new Date($("#txtfechaatencion").val());
	if (fechaInput < Date.now()) {
		$("#errorfechaatencion").text("La fecha escogida no debe ser menor a la fecha actual");
		campos['fechaAtencion'] = false;
	} else {
		if (validarHoras() !== true) {
			$("#errorfechaatencion").text("La fecha de atencion no coincide con el horario del veterinario");

			campos['fechaAtencion'] = false;
		} else {
			$("#errorfechaatencion").text("");


			campos['fechaAtencion'] = true;
		}
	}
	if (campos.cliente && campos.mascota && campos.veterinario && campos.fechaAtencion){
		return true;
	}else{
		return false;
	}


}


function validarHorasConVeterinario() {
	var horaOk = false;
	var turnoManana = false;
	var turnoTarde = false;

	var horaInput = moment($("#txtfechaatencion").val());

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


	return horaOk;

}

function validarHoras(){
	var horaOk = false;
	var turnoManana = false;
	var turnoTarde = false;

	var horaInput = moment($("#txtfechaatencion").val()).format('HH:mm:ss');

	var manInicial = moment(diccionario_horariosVet[0].manInicio,'HH:mm:ss').format('HH:mm:ss');
	var manFin = moment(diccionario_horariosVet[0].manFin,'HH:mm:ss').format('HH:mm:ss');
	var tarInicio = moment(diccionario_horariosVet[0].tarInicio,'HH:mm:ss').format('HH:mm:ss');
	var tarFin = moment(diccionario_horariosVet[0].tarFin,'HH:mm:ss').format('HH:mm:ss');

	if (horaInput >= manInicial  && horaInput < manFin){
		console.log("turno mañna ok");
		turnoManana = true
	}else{
		console.log("turno mañna mal");
	}

	if (horaInput >= tarInicio  && horaInput < tarFin){
		console.log("turno tarde ok");
		turnoTarde = true;

	}else{
		console.log("turno tarde mal");
	}

	if (turnoManana || turnoTarde){
		horaOk  = true;
	}
	return horaOk;


}


function listarCitas() {
	const options1 = { weekday: 'long', year: 'numeric', month: 'long', day: 'numeric' };
	const formato_ES = new Intl.DateTimeFormat('es-ES', options1);
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
					"<td>" + formato_ES.format(new Date(value.fechaRegistro))+ "</td>" +
					"<td>" + formato_ES.format(new Date(value.fechaAtencion)) + "</td>" +
					"<td>" + (value.pendiente ? "Si" : "No") + "</td>" +
					"<td><button type =\"button\" class=\"btn btn-primary btnactualizarcita\"" +
					"	data-codcita=\"" + value.id + "\"" +
					"	data-dnicliente=\"" + value.cliente["dni"] + "\"" +
					"	data-codmascota=\"" + value.mascota["idMascota"] + "\"" +
					"	data-codveterinario=\"" + value.veterinario["id"] + "\"" +
					"	data-codrecepcionista=\"" + value.recepcionista["nombre"] + "\"" +
					//"	th:data-fecharegistro=\"" + value.fechaRegistro + "\"" +
					"	data-fechaatencion=\"" + value.fechaAtencion + "\"" +
					"	data-pendiente=\"" + value.pendiente + "\"><i class='fa-solid fa-pen'></button >" +
					"</td >" +
					"<td>" +
					"	<button type=\"button\" class=\"btn btn-danger btneliminarcita\"" +
					"		data-codcita=\"" + value.id + "\"" +
					"		data-nomCliente=\"" + value.cliente["nombre"] + "\"" +
					"		data-pendiente=\"" + value.pendiente + "\"><i class='fa-solid fa-trash-can'></button>" +

					"</td>" +"</tr>"
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