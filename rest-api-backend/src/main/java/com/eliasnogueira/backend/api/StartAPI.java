/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eliasnogueira.backend.api;

import com.eliasnogueira.backend.controller.PersonJpaController;
import com.eliasnogueira.backend.pojo.Person;
import com.google.gson.Gson;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import static spark.Spark.*;

/**
 * Simple API
 * @author Elias Nogueira (@eliasnogueira) & Edson Yanaga (@yanaga)
 */
public class StartAPI {

    private static final String SUCCESS = "success";
    private static final String ERROR = "error";
    private static final String JSON = "application/json";

    public static void main(String[] args) {
        enableCORS();
        
        Gson gson = new Gson();

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("javaone-backend");
        PersonJpaController personController = new PersonJpaController(emf);

        /*
         * get all persons
         */
        get("api/v1/person", (req, res) -> gson.toJson(personController.findPersonEntities()));
       
        /*
         * get person by id
         */
        get("api/v1/person/:id",
                (req, res) -> {
            int id = Integer.
                    parseInt(req.params("id"));
            Person person =
                    personController.findPerson(id);

            res.type(JSON);
            if (person == null) {
                res.status(200);
                return gson.toJson(new Status(ERROR, "Person does not exists"));
            } else {
                res.status(200);
                return gson.toJson(person);
            }
        });

        /*
         * post a person
         */
        post("/api/v1/person", JSON, (req, res) -> {
            Person person = gson.fromJson(req.body(), Person.class);

            personController.create(person);

            res.type(JSON);
            res.status(201);
            return gson.toJson(person);
        });

        /*
         * update a person thought id
         */
        put("/api/v1/person/:id", (req, res) -> {
            int id = Integer.parseInt(req.params("id"));

            Person personInRequest = gson.fromJson(req.body(), Person.class);
            personInRequest.setId(id);

            Person personToChange = personController.findPerson(id);
            if (personInRequest.getName() != null && !personInRequest.getName().equals(personToChange.getName())) {
                personToChange.setName(personInRequest.getName());
            }

            if (personInRequest.getAddress() != null && !personInRequest.getName().equals(personToChange.getAddress())) {
                personToChange.setAddress(personInRequest.getAddress());
            }

            if (personInRequest.getHobbies() != null && !personInRequest.getName().equals(personToChange.getHobbies())) {
                personToChange.setHobbies(personInRequest.getHobbies());
            }

            personController.edit(personToChange);

            res.type(JSON);
            return gson.toJson(personInRequest);
        });

        /*
         * delete a person thought id
         */
        delete("/api/v1/person/:id", (req, res) -> {
            int id = Integer.parseInt(req.params("id"));

            personController.destroy(id);
            res.type(JSON);
            res.status(202);
            return gson.toJson(new Status(SUCCESS, "Person removed!"));
        });

    }

    /*
     * Status for responses
     */
    static class Status {

        final String statusMessage;
        final String message;

        private Status(String status, String message) {
            this.statusMessage = status;
            this.message = message;
        }
    }

    /*
     * Enable CORS for access the API thought another servers
     */
    private static void enableCORS() {
        options("/*",
            (req, res) -> {

                String accessControlRequestHeaders = req.headers("Access-Control-Request-Headers");
                if (accessControlRequestHeaders != null) {
                    res.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
                }

                String accessControlRequestMethod = req.headers("Access-Control-Request-Method");
                if (accessControlRequestMethod != null) {
                    res.header("Access-Control-Allow-Methods", accessControlRequestMethod);
                }

                return "OK";
            }
        );

        before((req, res) -> res.header("Access-Control-Allow-Origin", "*"));
    }
}
