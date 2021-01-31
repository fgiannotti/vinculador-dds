package Server;

import Estructuras.IngresoEgreso;
import java.util.ArrayList;
import java.util.List;
import spark.Spark;
import spark.debug.DebugScreen;

import static Server.Router.getHerokuAssignedPort;

public class Server {
    
    public Server() {
    }
    
    public static void main(String[] args){
        Spark.port(getHerokuAssignedPort());
        Router.init();
        DebugScreen.enableDebugScreen();
    }
}
