package com.springframework.web.services;

import com.springframework.web.model.Offer;

import javax.validation.Valid;
import java.util.List;

public interface OffersService {

    List<Offer> getCurrent();

    void createOffer(Offer offer);

    boolean hasOffer(String name);

    Offer getOffer(String username);

    void saveOrUpdate(Offer offer);

    void delete(int id);
}
