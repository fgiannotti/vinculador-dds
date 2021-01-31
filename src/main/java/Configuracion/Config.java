package Configuracion;

import Vinculador.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import javax.print.DocFlavor;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Config {
    public Config(Date fechaDesde, Date fechaHasta, String condicionVinculador, String criterioVinculacion) {
        this.fechaDesde = fechaDesde;
        this.fechaHasta = fechaHasta;
        this.criterioVinculacion = criterioVinculacion;
        this.setCondicionVinculador(condicionVinculador);
    }

    public Date getFechaDesde() {
        return fechaDesde;
    }

    public void setFechaDesde(String fechaDesde) throws ParseException {
        this.fechaDesde = new SimpleDateFormat("yyyy-MM-dd").parse(fechaDesde);
    }

    public Date getFechaHasta() {
        return fechaHasta;
    }

    public void setFechaHasta(String fechaHasta) throws ParseException {
        this.fechaHasta = new SimpleDateFormat("yyyy-MM-dd").parse(fechaHasta);
    }

    public CriterioVinculador getCriterioVinculacion() {
        return CriterioVinculador.getCriterioBy(this.criterioVinculacion);
    }

    public void setCriterioVinculacion(String criterioVinculacion) {
        this.criterioVinculacion = criterioVinculacion;
    }

    public PeriodoAceptabilidad getCondicionVinculador() {
        if(this.condicionVinculador.equals("PeriodoAceptabilidad")) {
            return new PeriodoAceptabilidad(this.fechaDesde, this.fechaHasta);
        } else{
            throw new RuntimeException("No se ha encontrado una condicion de vinculacion");
        }
    }

    public void setCondicionVinculador(String condicionVinculador) {
            this.condicionVinculador = condicionVinculador;
    }

    private Date fechaDesde;
    private Date fechaHasta;
    private String criterioVinculacion;
    private String condicionVinculador;

    public ArrayList<String> getCriteriosDeMix() {
        return criteriosDeMix;
    }

    public void setCriteriosDeMix(ArrayList<String> criteriosDeMix) {
        this.criteriosDeMix = criteriosDeMix;
    }

    private ArrayList<String> criteriosDeMix;

    @Override
    public String toString() {
        return "Config{" +
                "fechaDesde=" + fechaDesde +
                ", fechaHasta=" + fechaHasta +
                ", criterioVinculacion='" + this.getCriterioVinculacion() + '\'' +
                ", condicionVinculador='" + this.getCondicionVinculador().toString()+ '\'' +
                ", criteriosDeMix='" + criteriosDeMix.toString() + '\'' +
                '}';
    }
}