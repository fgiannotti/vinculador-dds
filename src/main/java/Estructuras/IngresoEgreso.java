package Estructuras;

import java.util.Date;

public class IngresoEgreso {
    private int id;
    private float monto;
    private Date fecha;

    public IngresoEgreso(int id, float monto, Date fecha) {
        this.id = id;
        this.monto = monto;
        this.fecha = fecha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getMonto() {
        return monto;
    }

    public void setMonto(float monto) {
        this.monto = monto;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}
