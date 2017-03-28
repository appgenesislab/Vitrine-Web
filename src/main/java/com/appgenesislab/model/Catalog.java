package com.appgenesislab.model;


import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

@CompoundIndexes({
        @CompoundIndex(name = "primary_index", collection = "catalog", def = "{'name': 1}")
})
@Document(collection = "catalog")
@Builder(builderMethodName = "builder")
@EqualsAndHashCode(exclude = {"products"})
@AllArgsConstructor
@ToString
@Getter
@Setter
public class Catalog
{
    @Id
    private String id;

    private String name;

    private Date createdDate;

    private Date activeSince;

    private Date activeUntil;

    private String description;

    private List<Product> products;

    private Boolean active;

}
