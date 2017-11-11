package com.springframework.web.DAO;


import com.springframework.web.model.Offer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public class OffersDAOImpl implements OffersDAO {

    private SessionFactory sessionFactory;

    @Autowired
    public OffersDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Session session() {
        return sessionFactory.getCurrentSession();
    }

    public List<Offer> getOffers() {

        return session().createQuery("from offers where user.enabled=true ", Offer.class).getResultList();
    }

    public List<Offer> getOffers(String username) {

        Query query = session().createQuery("from offers where user.enabled=true and user.username= :username ", Offer.class);
        query.setParameter("username", username);

        return query.getResultList();
    }

    public Offer getOffer(int id) {

        Query<Offer> query = session().createQuery("from offers p where user.enabled=true and p.id= :id");
        query.setParameter("id", id);

        return query.uniqueResult();
    }

    public void delete(int id) {

        Offer offer = getOffer(id);

        session().delete(offer);
    }

    public void saveOrUpdate(Offer offer) {
        session().saveOrUpdate(offer);
    }

}
