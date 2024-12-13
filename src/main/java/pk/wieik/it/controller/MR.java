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


import java.awt.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import pk.wieik.it.model.MRuser;
import pk.wieik.it.model.Tools;

import javax.tools.Tool;

import static java.lang.System.out;

@WebServlet(
        name = "MR",
        value = {"/MR"}
)
public class MR extends HttpServlet {

    public HashMap<String, MRuser> users;
    public MR() {
    }
    public void init() throws ServletException {
        // Initialize users
        this.users = new HashMap<>();
        users.put("user1", new MRuser("user1", "user1", 1));
        users.put("user2", new MRuser("user2", "user2", 1));
        users.put("user3", new MRuser("user3", "user3", 2));
        users.put("admin", new MRuser("admin", "admin", 2));
        getServletContext().setAttribute("users", users);
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

        String loginStatusMessage = "";
        if (loggedIn) {
            String userType = user.getPrivileges() == 2 ? "admin" : "user";
            loginStatusMessage = "You are logged in as a <strong>" + userType + "</strong>.";
        }


        Tools.includeJSP("/index.jsp", context, request, response);

        String[] jsFiles = {"script.js", "quadratic.js"};

    }



    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("login".equals(action)) {
            String login = request.getParameter("login");
            String password = request.getParameter("password");

            HashMap<String, MRuser> users = (HashMap<String, MRuser>) getServletContext().getAttribute("users");

            if (users != null && users.containsKey(login)) {
                MRuser user = users.get(login);
                if (user.getPassword().equals(password)) {
                    HttpSession session = request.getSession();
                    session.setAttribute("user", user);
                    ServletContext application = getServletContext();
                    String backgroundColor = user.getColor();
                    application.setAttribute("backgroundColor", backgroundColor);
                    response.sendRedirect("?page=main");
                    return;
                }
            }

            response.sendRedirect("?page=main");


        } else if ("logout".equals(action)) {
            HttpSession session = request.getSession();
            session.invalidate();
            response.sendRedirect("MR?page=index.jsp");
            ServletContext application = getServletContext();
            String backgroundColor = "white";
            application.setAttribute("backgroundColor", backgroundColor);


        } else if ("saveSettings".equals(action)) {
            HttpSession session = request.getSession();
            MRuser user = (MRuser) session.getAttribute("user");
            if (user != null) {
                String name = request.getParameter("name");
                String surname = request.getParameter("surname");
                String ageParam = request.getParameter("age");
                int age = ageParam != null ? Integer.parseInt(ageParam) : 0;
                user.setName(name);
                user.setSurname(surname);
                user.setAge(age);


                ServletContext application = getServletContext();
                String backgroundColor = request.getParameter("backgroundColor");
                if (backgroundColor == null) backgroundColor = ""; // Check if null
                application.setAttribute("backgroundColor", backgroundColor);
                user.setColor(backgroundColor);

                String page = "settings";
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/index.jsp?page=" + page);
                dispatcher.forward(request, response);
            }

            } else if ("administration".equals(action)){
            ServletContext application = getServletContext();
            String backgroundColor = request.getParameter("backgroundColor");
            if(backgroundColor == null) backgroundColor = "";
            application.setAttribute("backgroundColor", backgroundColor);

            String page = "administration";
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/index.jsp?page="+page);
            dispatcher.forward(request, response);


        } else if ("changePermissions".equals(action)) {
            HttpSession session = request.getSession();
            MRuser user = (MRuser) session.getAttribute("user");
            // Change user permissions
            if (user.getPrivileges() == 2) { // Check if the user is an administrator
                String login = request.getParameter("login");
                int permissions = Integer.parseInt(request.getParameter("permissions"));

                // Update user permissions
                MRuser targetUser = users.get(login);
                if (targetUser != null) {
                    targetUser.setPrivileges(permissions);
                }

                // Redirect back to administration page
                response.sendRedirect("MR?action=administration");
            } else {
                // Non-admin users are not allowed to change permissions
                response.sendRedirect("MR?action=administration");
            }
        } else if ("registration".equals(action)) {
            String loginRegistration = request.getParameter("loginRegistration");
            String passwordRegistration = request.getParameter("passwordRegistration");

            boolean userExists = false;
            for (Map.Entry<String, MRuser> entry : users.entrySet()) {
                MRuser storedUser = entry.getValue();
                if (storedUser.getLogin().equals(loginRegistration)) {
                    userExists = true;
                    break;
                }
            }

            if (userExists) {
                response.setContentType("text/html");
                PrintWriter out = response.getWriter();
                out.println("<html>");
                out.println("<h1>" + "Login already exists" + "</h1>");
                out.println("</html>");
                out.close(); // Es importante cerrar el PrintWriter después de usarlo.

            } else {
                users.put(loginRegistration, new MRuser(loginRegistration, passwordRegistration, 1));
                response.sendRedirect("MR?action=login");
            }
        }


    }

}