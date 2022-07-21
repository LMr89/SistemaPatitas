$(document).on("click", "#btnagregarcliente", function() {
	$("#txtnombre").val("");
	$("#txtapellidos").val("");
	$("#txtdni").val("");
	$("#txtdireccion").val("");
	$("#txtcorreo").val("");
	$("#txttelefono").val("");
	$("#txtestado").val("");
	$("#hddidcliente").val("0");
	$("#modalcliente").modal("show");
});

$(document).on("click", ".btnactualizarcliente", function() {
	$("#txtnombre").val($(this).attr("data-nombre"));
	$("#txtapellidos").val($(this).attr("data-apellidos"));
	$("#txtdni").val($(this).attr("data-dni"));
	$("#txtdireccion").val($(this).attr("data-direccion"));
	$("#txtcorreo").val($(this).attr("data-correo"));
	$("#txttelefono").val($(this).attr("data-telefono"));
	$("#txtestado").val($(this).attr("data-estado"));
	$("#hddidcliente").val($(this).attr("data-codcliente"));
	$("#modalcliente").modal("show");
});



$(document).on("click", "#btnregistrarcliente", function() {
	if ($("#txtnombre").val() === "") {
		$("#errornombre").text("Es obligarotio ingresar el nombre del cliente");
	}
	else {
		$("#errornombre").text("");
	}

	if ($("#txtapellidos").val() === "") {
		$("#errorapellidos").text("Es obligarotio ingresar el apellido del cliente");
	}
	else {
		$("#errorapellidos").text("");
	}
	if ($("#txtdni").val() === "") {
		$("#errordni").text("Es obligarotio ingresar el DNI del cliente");
	}
	else {
		$("#errordni").text("");
	}
	if ($("#txtdireccion").val() === "") {
		$("#errordireccion").text("Es obligarotio ingresar la direccion del cliente");
	}
	else {
		$("#errordireccion").text("");
	}
	if ($("#txtcorreo").val() === "") {
		$("#errorcorreo").text("Es obligarotio ingresar el correo del cliente");
	}
	else {
		$("#errorcorreo").text("");
	}
	if ($("#txttelefono").val() === "") {
		$("#errortelefono").text("Es obligarotio ingresar el telefono del cliente");
	}
	else {
		$("#errortelefono").text("");
	}
	if ($("#txtestado").val() === "") {
		$("#errorestado").text("Es obligarotio ingresar el estado del cliente");
	}
	else {
		$("#errorestado").text("");
	}
	
	
	if ($("#txtnombre").val() !== "" && $("#txtapellidos").val() !== "") {
		$.ajax({
			type: "POST",
			contentType: "application/json",
			url: "/Cliente/registrarCliente",
			data: JSON.stringify({
				idcliente: $("#hddidcliente").val(),
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
					ListarClientes();
				}
				else{
					mostrarMensaje(resultado.mensaje, "danger")
				}
			}
		})
		$("#modalcliente").modal("hide");
	}
});

$(document).on("click", ".btneliminarcliente", function() {
	$("#hddidclienteeliminar").val("");
	$("#mensajeeliminar").text("¿esta seguro de eliminar el cliente: "+
		$(this).attr("data-nombre")+"?");
	$("#hddidclienteeliminar").val($(this).attr("data-codcliente"));
	$("#modaleliminarcliente").modal("show");
});

$(document).on("click", "#btneliminarcliente", function() {
	$.ajax({
			type: "DELETE",
			contentType: "application/json",
			url: "/Cliente/eliminarCliente",
			data: JSON.stringify({
				idcliente: $("#hddidclienteeliminar").val(),

			}),
			success: function(resultado) {
				if (resultado.respuesta) {
					mostrarMensaje(resultado.mensaje, "success")
					ListarClientes();
				}
				else{
					mostrarMensaje(resultado.mensaje, "danger")
				}
				$("#modaleliminarcliente").modal("hide");
			}
		})

});


function ListarClientes() {
	$.ajax({
		type: "GET",
		url: "/Cliente/listarClientes",
		dataType: 'json',
		success: function(resultado) {
			$("#tblcliente > tbody").html("");
			$.each(resultado, function(index, value) {
				$("#tblcliente > tbody").append("<tr>" +
					"<td>" + value.idcliente + "</td>" +
					"<td>" + value.nombre + "</td>" +
					"<td>" + value.apellidos + "</td>" +
					"<td>" + value.dni + "</td>" +
					"<td>" + value.direccion + "</td>" +
					"<td>" + value.correo + "</td>" +
					"<td>" + value.telefono + "</td>" +
					"<td>" + value.estado + "</td>" +
					"<td><button type='button' class='btn btn-primary btnactualizarcliente'" +
                    " data-codcliente='" + value.idcliente + "' " +
                    " data-nombre='" + value.nombre + "' " +
                    " data-apellidos='" + value.apellidos + "' " +
                    " data-dni='" + value.dni + "' " +
                    " data-direccion='" + value.direccion + "' " +
                    " data-correo='" + value.correo + "' " +
                    " data-telefono='" + value.telefono + "' " +
                    " data-estado='" + value.estado + "' " +
					
					">Actualizar</button></td>" +
					"<td><button type='button' class='btn btn-danger btneliminarcliente'" +
					" data-codcliente='" + value.idcliente + "' " +
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

