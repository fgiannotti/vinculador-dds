package Vinculador;

import Estructuras.IngresoEgreso;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class Mix implements CriterioVinculador {
    public ArrayList<String> getCriteriosVinculados() {
        return criteriosVinculados;
    }

    @Override
    public void setCriteriosVinculados(ArrayList<String> criteriosDeMix) {
        criteriosVinculados=criteriosDeMix;
    }
    private ArrayList<String> criteriosVinculados;

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
        AtomicReference<String> response= new AtomicReference<>("");
        AtomicInteger contador = new AtomicInteger();
        contador.set(1);
        this.getCriteriosVinculados().forEach(
                stringCriterio ->
                        response.set(
                                response.get().concat(
                                        this.ejecutarUnCriterio(
                                                stringCriterio,
                                                this.getCriteriosVinculados().size(),
                                                contador
                                                )
                                )
                        )
        );
        return response.get();
    }

    private String ejecutarUnCriterio(String stringCriterio, int size, AtomicInteger contador) {
        CriterioVinculador criterio = CriterioVinculador.getCriterioBy(stringCriterio);
        criterio.setIngresos(this.getIngresos());
        criterio.setEgresos(this.getEgresos());
        String response = criterio.ejecutarCriterio();
        if (contador.get() < size && !response.isEmpty())
        {
            response = response.concat(",\n");
        }
        contador.set(contador.get() + 1);
        return response;
    }
}
