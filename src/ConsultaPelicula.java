import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsultaPelicula {

    public Pelicula buscaPelicula(int numeroDePelicula) {

        URI direccion = URI.create("https://swapi.py4e.com/api/films/"+numeroDePelicula+"/");
//        https://swapi.py4e.com/api/films/1/
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(direccion)
                .build();

        try {
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());

            //Devolver el Gson del Json del response para tranformarlo a nuestra película
            return new Gson().fromJson(response.body(), Pelicula.class);
            //Combinación de excepciones en un mismo catch
        } catch (Exception e) {
            throw new RuntimeException("No encontré esa película.");
        }

    }
}
