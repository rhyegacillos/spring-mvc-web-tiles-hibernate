package com.springframework.web.DAO;

import com.springframework.web.model.Offer;
import com.springframework.web.model.User;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class OfferRowMapper implements RowMapper {
    public Object mapRow(ResultSet resultSet, int i) throws SQLException {
        User user = new User();

        user.setName(resultSet.getString("name"));
        user.setAuthority(resultSet.getString("authority"));
        user.setEnabled(true);
        user.setEmail(resultSet.getString("email"));
        user.setUsername(resultSet.getString("username"));

        Offer offer = new Offer();

        offer.setId(resultSet.getInt("id"));
        offer.setText(resultSet.getString("text"));
        offer.setUser(user);

        return offer;

    }
}
