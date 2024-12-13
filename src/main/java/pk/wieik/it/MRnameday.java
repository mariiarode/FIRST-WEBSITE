package pk.wieik.it;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.*;
import java.util.ArrayList;


@WebServlet(name = "suggestions", value = "/suggestions")
public class MRnameday extends HttpServlet {
    private databaseNameDay databaseNameDay = new databaseNameDay();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");
        Reader input = new InputStreamReader(request.getInputStream());
        JSONObject json;
        JSONParser jsonParser = new JSONParser();
        try {
            json = (JSONObject) jsonParser.parse(input);
        } catch (ParseException e) {
            json = new JSONObject();
        }
        String query = ((String) json.get("value")).toLowerCase(); // Convert query to lowercase
        PrintWriter out = response.getWriter();
        ArrayList<String> suggestions = new ArrayList<>();
        try {
            String[][] matrix = databaseNameDay.getMatrix();

            for (int month = 0; month < 12; month++) {
                for (int day = 0; day < 31; day++) {
                    if (matrix[month][day] != null) {
                        String[] names = matrix[month][day].split(";");
                        for (String name : names) {
                            if (name.toLowerCase().startsWith(query)) {
                                String date = (day) + " " + getMonthName(month);
                                suggestions.add(name + ": " + date);
                            }
                        }
                    }
                }
            }

            json.put("suggestions", suggestions);
        } finally {
            out.println(json.toJSONString());
            out.close();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    private String getMonthName(int month) {
        String[] months = {
                "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio",
                "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"
        };
        return months[month];
    }
}
