package tpv.apitvpin.modelos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import javax.persistence.*;



@Entity
@Table(name = "product")
@Data
public class Product {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("productName")
    @Column(name = "productName")
    private String productName;
    @JsonProperty("ingredients")
    private String ingredients;
    @JsonProperty("price")
    private float price;
    @JsonProperty("categories")
    private String categories;
    @JsonProperty("routeImage")
    @Column(name = "routeImage")
    private String routeImage;
    @JsonProperty("preparation")
    @Column(length = 500)
    private String preparation;

}
