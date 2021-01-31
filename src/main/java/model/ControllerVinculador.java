package model;

import Estructuras.JsonIngresosEgresos;
import Vinculador.*;
import com.google.gson.Gson;
import spark.Request;
import spark.Response;

public class ControllerVinculador {
    public String estaEntreFechas (Request req, Response resp){
        req.body();
        Gson g = new Gson();
        JsonIngresosEgresos JO = g.fromJson(req.body(), JsonIngresosEgresos.class);

        Vinculador vinculador = new Vinculador();
        vinculador.cambiarCondicion(JO.getConfiguracion().getCondicionVinculador());
        vinculador.cambiarCriterio(JO.getConfiguracion().getCriterioVinculacion());

        String jsonInString =  vinculador.ejecutarVinculador(JO);

        String response="{\n" +
                "\"Relaciones\":[\n";

        response=response.concat(jsonInString);
        response=response.concat("]\n}");
        return response;
    }
}
