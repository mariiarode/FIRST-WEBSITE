package pk.wieik.it.model;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class Tools {

    public static void includeJSP(String file, ServletContext context, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = context.getRequestDispatcher("/WEB-INF/view/"+file);
        dispatcher.include(request, response);
    }
    public static String getTemplate(String file, ServletContext context) throws IOException {
        StringBuffer output = new StringBuffer("");
        String text = "";
        InputStream is = context.getResourceAsStream("/WEB-INF/view/" + file);
        if (is != null) {
            InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
            BufferedReader reader = new BufferedReader(isr);

            while((text = reader.readLine()) != null) {
                output.append(text).append("\n");
            }
        } else {
            output.append("No " + file + " file");
        }

        return output.toString();
    }

    public static String fill(String template, String tag, String file, ServletContext context) throws IOException {
        StringBuffer output = new StringBuffer("");
        String text = "";
        InputStream is = context.getResourceAsStream("/WEB-INF/view/" + file);
        if (is != null) {
            InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
            BufferedReader reader = new BufferedReader(isr);

            while((text = reader.readLine()) != null) {
                output.append(text).append("\n");
            }
        } else {
            output.append("No " + file + " file");
        }

        return template.replace("[[" + tag + "]]", output.toString());
    }

    public static int parseInteger(String input, int def) {
        int output;
        try {
            output = Integer.parseInt(input);
        } catch (NumberFormatException var4) {
            output = def;
        }

        return output;
    }

    public static String parsePage(String input, String valid) {
        String output = "main";
        String[] pages = valid.split(";");
        if (input == null) {
            input = "main";
        }

        String[] var4 = pages;
        int var5 = pages.length;

        for(int var6 = 0; var6 < var5; ++var6) {
            String proper = var4[var6];
            if (input.equals(proper)) {
                return input;
            }
        }

        return output;
    }


    public static String addScript(String template, String[] jsFiles) {
        StringBuilder scriptTags = new StringBuilder();
        for (String jsFile : jsFiles) {
            scriptTags.append("<script type='text/javascript' src='").append(jsFile).append("'></script>\n");
        }
        return template.replace("[[SCRIPTS]]", scriptTags.toString());
    }

    public static String generateLoginForm() {
        return "<form action=\"MR\" method=\"post\">\n" +
                "    <input type=\"hidden\" name=\"action\" value=\"login\">\n" +
                "    <label for=\"login\">Login:</label>\n" +
                "    <input type=\"text\" id=\"login\" size=\"14\" name=\"login\"><br>\n" +
                "    <label for=\"password\">Password:</label>\n" +
                "    <input type=\"password\" id=\"password\" size=\"14\" name=\"password\"><br>\n" +
                "    <input type=\"submit\" value=\"Login\">\n" +
                "</form>";
    }

    public static String generateLogoutButton() {
        return "<form action=\"MR\" method=\"post\">\n" +
                "    <input type=\"hidden\" name=\"action\" value=\"logout\">\n" +
                "    <input type=\"submit\" value=\"Logout\">\n" +
                "</form>";
    }

}