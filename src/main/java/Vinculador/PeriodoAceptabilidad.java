package Vinculador;

import Configuracion.Config;
import Estructuras.IngresoEgreso;

import java.util.ArrayList;
import java.util.Date;
import java.util.stream.Collectors;

public class PeriodoAceptabilidad implements CondicionVinculador{
    Date fechaDesde;
    Date fechaHasta;

    public PeriodoAceptabilidad(Date fecha_desde, Date fecha_hasta) {
        this.fechaDesde = fecha_desde;
        this.fechaHasta = fecha_hasta;
    }

    @Override
    public ArrayList<IngresoEgreso> ejecutarCondicion(ArrayList<IngresoEgreso> arrayList) {

        ArrayList<IngresoEgreso> filtrado;
        filtrado = (ArrayList<IngresoEgreso>) arrayList.
                stream().
                filter(
                        ingresoEgreso -> (ingresoEgreso.getFecha().compareTo(fechaDesde) * ingresoEgreso.getFecha().compareTo(fechaHasta) <= 0)
                ).collect(Collectors.toList());
        return filtrado;
    }

    @Override
    public String toString() {
        return "PeriodoAceptabilidad{" +
                "fechaDesde=" + fechaDesde +
                ", fechaHasta=" + fechaHasta +
                '}';
    }
}
