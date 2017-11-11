package com.springframework.web.services;

import com.springframework.web.DAO.OffersDAO;
import com.springframework.web.model.Offer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OffersServiceImpl implements OffersService {

    private OffersDAO offersDAO;

    @Autowired
    public OffersServiceImpl(@Qualifier("offersDAOImpl") OffersDAO offersDAO) {
        this.offersDAO = offersDAO;
    }

    public List<Offer> getCurrent() {
        return offersDAO.getOffers();
    }

    public void createOffer(Offer offer) {
       offersDAO.saveOrUpdate(offer);
    }

    @Override
    public boolean hasOffer(String name) {

        List<Offer> offers = offersDAO.getOffers();

        if (offers.size() == 0)
            return  false;

        return true;
    }

    @Override
    public Offer getOffer(String username) {
        List<Offer> offers = offersDAO.getOffers(username);

        if (offers.size() == 0)
            return null;

        return offers.get(0);
    }

    @Override
    public void saveOrUpdate(Offer offer) {
        offersDAO.saveOrUpdate(offer);
    }

    @Override
    public void delete(int id) {
        offersDAO.delete(id);
    }


}
