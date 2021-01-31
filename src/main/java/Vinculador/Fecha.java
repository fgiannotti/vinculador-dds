package Vinculador;

import Estructuras.IngresoEgreso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.concurrent.atomic.AtomicInteger;

public class Fecha implements CriterioVinculador {
    ArrayList<IngresoEgreso> egresos = null;

    public ArrayList<IngresoEgreso> getEgresos() {
        return egresos;
    }

    public void setEgresos(ArrayList<IngresoEgreso> egresos) {
        this.egresos = egresos;
    }

    public ArrayList<IngresoEgreso> getIngresos() {
        return ingresos;
    }

    public void setIngresos(ArrayList<IngresoEgreso> ingresos) {
        this.ingresos = ingresos;
    }

    ArrayList<IngresoEgreso> ingresos = null;


    @Override
    public String ejecutarCriterio() {
        this.ordenarPorFecha(this.getEgresos());
        this.ordenarPorFecha(this.getIngresos());
        return this.asignarEgresosIngresos(this.getEgresos(),this.getIngresos());
    }

    @Override
    public void setCriteriosVinculados(ArrayList<String> criteriosDeMix) {

    }

    public void ordenarPorFecha( ArrayList<IngresoEgreso> ingresosegresos ) {
        Collections.sort(ingresosegresos,new Comparator<IngresoEgreso>() {
            public int compare(IngresoEgreso ingEgre1, IngresoEgreso ingEgre2) {
                return ingEgre1.getFecha().compareTo(ingEgre2.getFecha());
            }
        });
    }


    private String asignarEgresosIngresos(ArrayList<IngresoEgreso> egresos, ArrayList<IngresoEgreso> ingresos) {



        ArrayList<ArrayList<Integer>> arrayDeRespuesta = new ArrayList<ArrayList<Integer>>();
        ingresos.forEach(ingreso -> arrayDeRespuesta.add(this.asignar(egresos,ingreso)));

        int cantidadDeIngresos=ingresos.size();
        String response="";
        for (int i = 0; i < cantidadDeIngresos; i++) {
            ArrayList<Integer> respuesta = arrayDeRespuesta.get(i);

            if (i < cantidadDeIngresos-1){
                response=
                        response
                                .concat("{\n\"IDIngreso\":")
                                .concat(String.valueOf(ingresos.get(i).getId()))
                                .concat(",\n")
                                .concat("\"IDSEgresos\":[")
                                .concat(this.arrayDeEgresos(respuesta))
                                .concat("]\n},");
            } else {
                response=
                        response
                                .concat("{\n\"IDIngreso\":")
                                .concat(String.valueOf(ingresos.get(i).getId()))
                                .concat(",\n")
                                .concat("\"IDSEgresos\":[")
                                .concat(this.arrayDeEgresos(respuesta))
                                .concat("]\n}");
            }
        }

        return response;
    }

    private String arrayDeEgresos(ArrayList<Integer> egresos) {
        String respuesta="";
        int total = egresos.size();
        for (int i = 0; i < total; i++) {
            if (!(total-1 == i)){
                respuesta=respuesta.concat(String.valueOf(egresos.get(i))).concat(",\n");
            } else {
                respuesta=respuesta.concat(String.valueOf(egresos.get(i)));
            }
        }
        egresos.clear();
        return respuesta;
    }

    private ArrayList<Integer> asignar(ArrayList<IngresoEgreso> egresos, IngresoEgreso ingreso) {
        AtomicInteger contador = new AtomicInteger();
        contador.set(0);
        ArrayList<Integer> response = new ArrayList<>();
        egresos.forEach(egreso -> contador.set((int) this.sumarA(contador.get(), ingreso, egreso, response)));
        response.forEach(idEgreso -> this.eliminarDeLista(idEgreso,egresos));
        return response;
    }

    private void eliminarDeLista(Integer idEgreso, ArrayList<IngresoEgreso> egresos) {
        egresos.removeIf(egreso -> egreso.getId() == idEgreso);
    }

    private float sumarA(int contador, IngresoEgreso ingreso, IngresoEgreso egreso, ArrayList<Integer> respuesta) {
        if (egreso.getMonto() + contador <= ingreso.getMonto()){
            respuesta.add(egreso.getId());
            return egreso.getMonto() + contador;
        } else {
            return contador;
        }
    }
}
