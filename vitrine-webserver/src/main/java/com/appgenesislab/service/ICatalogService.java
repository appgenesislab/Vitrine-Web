package com.appgenesislab.service;

import com.appgenesislab.model.Catalog;
import java.util.List;

/**
 * Created by mariopaulo on 2017-05-21.
 */
public interface ICatalogService
{

     public List<Catalog> findByProductName(String productName);

}
