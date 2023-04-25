package com.cb.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;

@Service
public class DBService {

    public final DataSource dataSource;

    @Autowired
    public DBService(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    public LoginResponse getLoginDetails(LoginRequest loginRequest) throws Exception {

        String username = loginRequest.username;
        String query = "SELECT secret FROM Users WHERE (username = '" + username + "' AND NOT role = 'admin' LIMIT 1)";

        LoginResponse response = new LoginResponse();

        var connection = this.dataSource.getConnection();
        var stmt = connection.createStatement();
        var rs = stmt.executeQuery(query);

        while (rs.next()) {
            System.out.printf("%s %s%n",
                    rs.getString("first_name"), rs.getString("last_name"));
            response.firstname = rs.getString("first_name");
            response.lastname = rs.getString("last_name");
        }


        return response;


    }
}