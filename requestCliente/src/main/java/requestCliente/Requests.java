package requestCliente;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Requests {
	public static void main(String[] args) throws IOException {
		OkHttpClient client = new OkHttpClient().newBuilder()
				  .build();
				MediaType mediaType = MediaType.parse("text/plain");
				RequestBody body = RequestBody.create(mediaType, "");
				Request request = new Request.Builder()
				  .url("http://localhost:8070/api/usuario/listar")
				  .method("GET", body)
				  .build();
				Response response = client.newCall(request).execute();
	}

}
