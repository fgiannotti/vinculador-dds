package Vinculador;

import Estructuras.IngresoEgreso;

import java.util.ArrayList;

// primero valor otro por fecha en los de ordenar
public interface CriterioVinculador {
    void setEgresos(ArrayList<IngresoEgreso> egresos);
    void setIngresos(ArrayList<IngresoEgreso> ingresos);

    static CriterioVinculador getCriterioBy(String criterioVinculacion){
        if(criterioVinculacion.equals("OrdenValorPrimerIngreso")){
            return new OrdenValorPrimerIngreso();
        }else if(criterioVinculacion.equals("OrdenValorPrimerEgreso")){
            return new OrdenValorPrimerEgreso();
        }else if(criterioVinculacion.equals("Fecha")){
            return new Fecha();
        }else if(criterioVinculacion.equals("Mix")){
            return new Mix();
        } else{
            throw new RuntimeException("No se ha encontrado un criterio de vinculacion");
        }
    }
    String ejecutarCriterio();

    void setCriteriosVinculados(ArrayList<String> criteriosDeMix);
}
