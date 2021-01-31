# vinculador-dds
API para vincular egresos con ingresos bajo ciertos criterios

A continuación la interfaz mas villera que vas a ver en tu hermosa vida
```
 {
    "Egresos": [
        {
            "fecha": "2020-10-03",
            "monto": "19949.7",
            "id": "1"
        }
    ],
    "Configuracion": {
        "fechaHasta": "2021-09-10",
        "fechaDesde": "2020-01-01",
        "condicionVinculador": "PeriodoAceptabilidad",
        "criterioVinculacion": "Mix",
        "criteriosDeMix": [
            "OrdenValorPrimerIngreso"
        ]
    },
    "Ingresos": [
        {
            "fecha": "2020-02-25",
            "monto": "20000.0",
            "id": "1"
        },
        {
            "fecha": "2020-08-03",
            "monto": "10000.0",
            "id": "2"
        },
        {
            "fecha": "2020-05-02",
            "monto": "10000.0",
            "id": "3"
        },
        {
            "fecha": "2020-01-01",
            "monto": "100000.0",
            "id": "4"
        }
    ]
}
```

```
{
      "Relaciones":[
             {
                   "IDIngreso":2,
                   "IDSEgresos":[1]
              }
        ]
    }
```
