package Estructuras;

import Configuracion.Config;

import java.util.ArrayList;

public class JsonIngresosEgresos {
    public ArrayList<IngresoEgreso> Ingresos;

    public ArrayList<IngresoEgreso> getIngresos() {
        return Ingresos;
    }

    public void setIngresos(ArrayList<IngresoEgreso> ingresos) {
        Ingresos = ingresos;
    }

    public ArrayList<IngresoEgreso> getEgresos() {
        return Egresos;
    }

    public void setEgresos(ArrayList<IngresoEgreso> egresos) {
        Egresos = egresos;
    }

    public ArrayList<IngresoEgreso> Egresos;

    public Config getConfiguracion() {return Configuracion;}

    public void setConfiguracion(Config configuracion) {Configuracion = configuracion;}

    public Config Configuracion;

    @Override
    public String toString() {
        return "JsonIngresosEgresos{" +
                "Ingresos=" + Ingresos +
                ", Egresos=" + Egresos +
                ", Configuracion=" + Configuracion +
                '}';
    }
}
