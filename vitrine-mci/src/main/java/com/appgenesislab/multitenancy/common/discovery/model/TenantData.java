package com.appgenesislab.multitenancy.common.discovery.model;


import java.util.Date;
import lombok.Data;

@Data
public abstract class TenantData
{
     protected final String name;
     protected final String company;
     protected final String owner;
     protected final Date dateCreated;
     protected final Object dataSourceId;
}
