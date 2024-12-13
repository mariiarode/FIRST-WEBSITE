//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package pk.wieik.it.controller;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import jakarta.servlet.RequestDispatcher;


import java.io.IOException;
import java.io.PrintWriter;

import pk.wieik.it.model.MRuser;
import pk.wieik.it.model.Tools;

import javax.tools.Tool;

@WebServlet(
        name = "MR",
        value = {"/MR"}
)
public class MR extends HttpServlet {
    public MR() {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
        ServletContext context = this.getServletContext();
        PrintWriter out = response.getWriter();
        String page = request.getParameter("page");
        page = Tools.parsePage(page, "main;quadratic;third");

        HttpSession session = request.getSession();
        MRuser user = (MRuser) session.getAttribute("user");

        boolean loggedIn = user != null && user.getPrivileges() > 0;

        String loginLogoutButton = loggedIn ? Tools.generateLogoutButton() : Tools.generateLoginForm();
        //String settingsItem = loggedIn ? "<li><a href=\"settings.html\">Settings</a></li>" : "";

        String loginStatusMessage = "";
        if (loggedIn) {
            String userType = user.getPrivileges() == 2 ? "admin" : "user";
            loginStatusMessage = "You are logged in as a <strong>" + userType + "</strong>.";
        }


        Tools.includeJSP("/index.jsp", context, request, response);

        String[] jsFiles = {"script.js", "quadratic.js"};

    }



    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        MRuser user = (MRuser) session.getAttribute("user");

        if (user == null) {
            user = new MRuser();
            session.setAttribute("user", user);
        }

        String action = request.getParameter("action");

        if ("login".equals(action)) {
            String login = request.getParameter("login");
            String password = request.getParameter("password");

            boolean loginSuccessful = false; // Flag to track login success

            // Check if login and password are valid
            if (("user".equals(login) && "user".equals(password)) ||
                    ("admin".equals(login) && "admin".equals(password))) {
                loginSuccessful = true; // Set flag to true for successful login

                if ("admin".equals(login)) {
                    user.setPrivileges(1); // Administrator
                } else {
                    user.setPrivileges(1); // Logged-in user
                }
                user.setLogin(login);
                session.setAttribute("loggedIn", true); // Set user as logged in
                response.sendRedirect("MR?page=index.jsp");
            }

            if (!loginSuccessful) {
                // Incorrect login credentials, redirect to main page with error message
                session.setAttribute("loggedIn", false); // Set user as not logged in
                response.sendRedirect("MR?page=index.jsp");
            }
        } else if ("logout".equals(action)) {
            // Reset the session
            session.invalidate();
            response.sendRedirect("MR?page=index.jsp");
        }
    }

}
