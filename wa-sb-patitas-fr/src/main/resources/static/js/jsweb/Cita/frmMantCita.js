$(document).on("click", "#btngenerarcita", function() {
	$("#txtfecharegistro").val("");
	$("#txtfechaatencion").val("");
	$("#txtpendiente").val("");
	$("#hddidusuario").val("0");
	$("#modalusuario").modal("show");
});

$(document).on("click", ".btnactualizarcita", function() {
	$("#txtnombre").val($(this).attr("data-nombre"));
	$("#txtapellidos").val($(this).attr("data-apellidos"));
	$("#txtdni").val($(this).attr("data-dni"));
	$("#txtdireccion").val($(this).attr("data-direccion"));
	$("#txtcorreo").val($(this).attr("data-correo"));
	$("#txttelefono").val($(this).attr("data-telefono"));
	$("#txtestado").val($(this).attr("data-estado"));
	$("#hddidusuario").val($(this).attr("data-codusuario"));
	$("#modalusuario").modal("show");
});



$(document).on("click", "#btnregistrarusuario", function() {
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
				else{
					mostrarMensaje(resultado.mensaje, "danger")
				}
			}
		})
		$("#modalusuario").modal("hide");
	}
});

$(document).on("click", ".btneliminarusuario", function() {
	$("#hddidusuarioeliminar").val("");
	$("#mensajeeliminar").text("¿esta seguro de eliminar al usuario: "+
		$(this).attr("data-nombre")+"?");
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
				else{
					mostrarMensaje(resultado.mensaje, "danger")
				}
				$("#modaleliminarusuario").modal("hide");
			}
		})

});


function ListarUsuarios() {
	$.ajax({
		type: "GET",
		url: "/Usuario/listarUsuarios",
		dataType: 'json',
		success: function(resultado) {
			$("#tblusuario > tbody").html("");
			$.each(resultado, function(index, value) {
				$("#tblusuario > tbody").append("<tr>" +
					"<td>" + value.id + "</td>" +
					"<td>" + value.nombre + "</td>" +
					"<td>" + value.apellidos + "</td>" +
					"<td>" + value.dni + "</td>" +
					"<td>" + value.direccion + "</td>" +
					"<td>" + value.correo + "</td>" +
					"<td>" + value.telefono + "</td>" +
					"<td>" + value.estado + "</td>" +
					"<td><button type='button' class='btn btn-primary btnactualizarusuario'" +
					" data-codusuario='" + value.id + "' " +
					" data-nombre='" + value.nombre + "' " +
					" data-apellidos='" + value.apellidos + "' " +
					
                    " data-dni='" + value.dni + "' " +
                    " data-direccion='" + value.direccion + "' " +
                    " data-correo='" + value.correo + "' " +
                    " data-telefono='" + value.telefono + "' " +
                    " data-estado='" + value.estado + "' " +
					
					">Actualizar</button></td>" +
					"<td><button type='button' class='btn btn-danger btneliminarusuario'" +
					" data-codusuario='" + value.id + "' " +
					" data-nombre='" + value.nombre + "' " +
					">Eliminar</button></td>" +
					"</tr>"
				)
			})
		}
	})
}


function mostrarMensaje(mensaje, estilo){
	$("#mensaje").html("")
	$("#mensaje").append("<div class='alert alert-"+estilo+" alert-dismissible fade show' role='alert'>"+
"<strong>"+mensaje+"</strong>"+
"<button type='button' class='btn-close' data-bs-dismiss='alert' aria-label='Close'></button>"+
"</div>")
	
}