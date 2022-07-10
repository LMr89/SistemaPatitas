

$("#btningresar").click(function() {
	$.ajax({
		type: "POST",
		url: "http://localhost:8070/api/auth",
		contentType: "application/json; charset=utf-8",
		dataType: "json",
		data: JSON.stringify({
			nomUsuario: $("#txtusuario").val(),
			contrasenia: $("#txtpassword").val()
		}),
		success: function(resultado) {
			if (resultado.esAutenticado) {
				alert("Acceso valido")
				$(location).attr('href',"http://localhost:8040/index");
			}
			else {
				alert("Acceso no valido")
			}

		}

	});
});