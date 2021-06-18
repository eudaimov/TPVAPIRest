package tpv.apitvpin.modelos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.bind.DefaultValue;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class TableServices {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("empiezaServicio")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy hh:mm:ss")
    private Date beginService;
    @JsonProperty("terminaServicio")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy hh:mm:ss")
    private Date finishService;
    @JsonProperty("comensales")
    private Integer numberOfDiners;
    @JsonProperty("idMesa")
    private Integer idTable;
    @JsonProperty("Activo")
    private boolean active = true;
    @OneToMany( fetch = FetchType.LAZY, mappedBy = "tableServices")
    @JsonIgnore
    private List<TableRequestServices> listRequest = new ArrayList<>();

}
