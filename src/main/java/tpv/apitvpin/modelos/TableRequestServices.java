package tpv.apitvpin.modelos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class TableRequestServices {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("idProducto")
    private Integer idProduct;
    @JsonProperty("cantidad")
    private Integer cantidadProduct = 1;
    @JsonProperty("idResponsable")
    private Integer idUserResponsable;
    @JsonProperty("empieza")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy hh:mm:ss")
    private Date timeBegin;
    @JsonProperty("enProceso")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy hh:mm:ss")
    private Date inProcess;
    @JsonProperty("listaParaServir")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy hh:mm:ss")
    private Date preparate;
    @JsonProperty("enMesa")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy hh:mm:ss")
    private Date service;
    @JsonProperty("comentarios")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy hh:mm:ss")
    private String comments;

    @JsonProperty("fkidService")
    @ManyToOne
    @JoinColumn(name="fkidService")
    private TableServices tableServices;

}
