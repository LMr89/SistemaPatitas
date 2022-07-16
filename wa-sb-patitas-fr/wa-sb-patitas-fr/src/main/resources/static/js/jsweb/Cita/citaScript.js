
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
	$("#modalcita").modal("show");
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
	$.ajax({
		type: "GET",
		contentType: "application/json",
		url: "/Cita/buscarCliente",
		data: {
			dni: $("#txtcliente").val()
		},


		success: function(resultado) {
			if (resultado.estado) {
				mostrarMensaje("Cliente: " + resultado.nombre + " " + resultado.apellidos, "success")
				obtenerMascotasDelCliente(resultado.id);
			}
			else {
				mostrarMensaje("Cliente no encontrado", "danger")
			}
			$("#modaleliminarusuario").modal("hide");
		}
	})

});


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

			for (let i = 0; i < resultado.length; i++) {
				$("#cboMascota").append("<option value=\"" + resultado[i].id + "\">" + resultado[i].nombre + "</option>");
			}

			$("#cboMascota").append("	</select> <span id=\"errorestado\" class=\"text-danger\"></span>" +
				"</div>");

		}
	})

};

$(document).on("click", "#btnregistrarcita", function() {
	if ($("#txtnombre").val() === "") {
		$("#errornombre").text("Es obligarotio ingresar el nombre del usuario");
	}
	else {
		$("#errornombre").text("");
	}

	if ($("#txtapellidos").val() === "") {
		$("#errorapellidos").text("Es obligarotio ingresar el apellido del usuario");
	}
	else {
		$("#errorapellidos").text("");
	}
	if ($("#txtdni").val() === "") {
		$("#errordni").text("Es obligarotio ingresar el DNI del usuario");
	}
	else {
		$("#errordni").text("");
	}
	if ($("#txtdireccion").val() === "") {
		$("#errordireccion").text("Es obligarotio ingresar la direccion del usuario");
	}
	else {
		$("#errordireccion").text("");
	}
	if ($("#txtcorreo").val() === "") {
		$("#errorcorreo").text("Es obligarotio ingresar el correo del usuario");
	}
	else {
		$("#errorcorreo").text("");
	}
	if ($("#txttelefono").val() === "") {
		$("#errortelefono").text("Es obligarotio ingresar el telefono del usuario");
	}
	else {
		$("#errortelefono").text("");
	}
	if ($("#txtestado").val() === "") {
		$("#errorestado").text("Es obligarotio ingresar el estado del usuario");
	}
	else {
		$("#errorestado").text("");
	}


	if ($("#txtnombre").val() !== "" && $("#txtapellidos").val() !== "") {
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
				correo: $("#txtcorreo").val(),
				telefono: $("#txttelefono").val(),
				estado: $("#txtestado").val(),
			}),
			success: function(resultado) {
				if (resultado.respuesta) {
					mostrarMensaje(resultado.mensaje, "success")
					ListarUsuarios();
				}
				else {
					mostrarMensaje(resultado.mensaje, "danger")
				}
			}
		})
		$("#modalusuario").modal("hide");
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