package com.springframework.web.DAO;

import com.springframework.web.model.Offer;

import javax.sql.DataSource;
import java.util.List;

public interface OffersDAO {

    List<Offer> getOffers(String username);
    List<Offer> getOffers();
    Offer getOffer(int id);
    void saveOrUpdate(Offer offer);
    void delete(int id);
}
