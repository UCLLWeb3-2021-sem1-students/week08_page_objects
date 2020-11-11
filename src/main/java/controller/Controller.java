package controller;

import domain.db.PersonService;
import domain.model.Person;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/Controller")
public class Controller extends HttpServlet {
    private PersonService personService = new PersonService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String command = request.getParameter("command");
        if (command.equals("Register")) {
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String email = request.getParameter("email");
            String password = request.getParameter("password");

            List<String> errors = new ArrayList<String>();

            Person person = new Person();
            try {
                person.setFirstName(firstName);
            }
            catch (IllegalArgumentException exc) {
                errors.add(exc.getMessage());
            }
            try {
                person.setLastName(lastName);
            }
            catch (IllegalArgumentException exc) {
                errors.add(exc.getMessage());
            }
            try {
                person.setEmail(email);
            }
            catch (IllegalArgumentException exc) {
                errors.add(exc.getMessage());
            }
            try {
                person.setPassword(password);
            }
            catch (IllegalArgumentException exc) {
                errors.add(exc.getMessage());
            }

            if (errors.size() == 0) {
                personService.add(person);
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
            else {
                request.setAttribute("errors", errors);
                request.getRequestDispatcher("signUp.jsp").forward(request, response);
            }
        }
        if (command.equals("Overview")) {
            request.setAttribute("persons", personService.getAll());
            request.getRequestDispatcher("personoverview.jsp").forward(request, response);
        }
        if (command.equals("SignUp")) {
            request.getRequestDispatcher("signUp.jsp").forward(request, response);
        }
        if (command.equals("Home")) {
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }



}
