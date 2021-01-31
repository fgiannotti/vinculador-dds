package Vinculador;

import Estructuras.IngresoEgreso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.concurrent.atomic.AtomicInteger;

public class OrdenValorPrimerIngreso implements CriterioVinculador {

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
        this.ordenarPorValor(this.getIngresos());
        this.ordenarPorFecha(this.getEgresos());
        return this.asignarEgresosIngresos(this.getEgresos(),this.getIngresos());
    }

    @Override
    public void setCriteriosVinculados(ArrayList<String> criteriosDeMix) {

    }

    private String asignarEgresosIngresos(ArrayList<IngresoEgreso> egresos, ArrayList<IngresoEgreso> ingresos) {

        ArrayList<ArrayList<Integer>> arrayDeRespuesta = new ArrayList<ArrayList<Integer>>();
        egresos.forEach(egreso -> arrayDeRespuesta.add(this.asignar(ingresos,egreso)));

        int cantidadDeEgresos=egresos.size();
        String response="";
        for (int i = 0; i < cantidadDeEgresos; i++) {
            ArrayList<Integer> arrayDeIngresos = arrayDeRespuesta.get(i);
            String idEgreso = String.valueOf(egresos.get(i).getId());
            for (int j = 0; j < arrayDeIngresos.size(); j++) {
                response =
                        response
                                .concat("{\n\"IDIngreso\":")
                                .concat(String.valueOf(arrayDeIngresos.get(j)))
                                .concat(",\n")
                                .concat("\"IDSEgresos\":[")
                                .concat(idEgreso)
                                .concat("]\n}");
                if (j < arrayDeIngresos.size()-1){
                    response = response.concat(",");
                }
            }
            if ( i < cantidadDeEgresos - 1 && !arrayDeIngresos.isEmpty() && !arrayDeRespuesta.get(i+1).isEmpty()){
                response =
                        response
                                .concat(",\n");
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

    private ArrayList<Integer> asignar(ArrayList<IngresoEgreso> ingresos, IngresoEgreso egreso) {
        AtomicInteger contador = new AtomicInteger();
        contador.set(0);
        ArrayList<Integer> response = new ArrayList<>();
        ingresos.forEach(ingreso -> contador.set((int) this.sumarA(contador.get(), egreso, ingreso, response)));
        response.forEach(idIngreso -> this.eliminarDeLista(idIngreso,ingresos));
        return response;
    }

    private void eliminarDeLista(Integer idIngreso, ArrayList<IngresoEgreso> Ingresos) {
        Ingresos.removeIf(ingreso -> ingreso.getId() == idIngreso);
    }

    private float sumarA(int contador, IngresoEgreso egreso, IngresoEgreso ingreso, ArrayList<Integer> respuesta) {
        if (ingreso.getMonto() + contador <= egreso.getMonto()){
            respuesta.add(ingreso.getId());
            return ingreso.getMonto() + contador;
        } else {
            return contador;
        }
    }

    public void ordenarPorValor( ArrayList<IngresoEgreso> ingresosegresos ) {
        Collections.sort(ingresosegresos,new Comparator<IngresoEgreso>() {
            public int compare(IngresoEgreso ingEgre1, IngresoEgreso ingEgre2) {
                return (int) (ingEgre1.getMonto() - ingEgre2.getMonto());
            }
        });
    }
    public void ordenarPorFecha( ArrayList<IngresoEgreso> ingresosegresos ) {
        Collections.sort(ingresosegresos,new Comparator<IngresoEgreso>() {
            public int compare(IngresoEgreso ingEgre1, IngresoEgreso ingEgre2) {
                return ingEgre1.getFecha().compareTo(ingEgre2.getFecha());
            }
        });
    }
}
