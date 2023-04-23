package ru.appline;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import ru.appline.logic.Mesage;
import ru.appline.logic.Model;
import ru.appline.logic.User;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/put")
public class ServletPut extends HttpServlet {

    Model model = Model.getInstance();
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");

        StringBuffer sb = new StringBuffer();
        PrintWriter pw = response.getWriter();

        String line;
        try {
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (Exception e) {
            System.out.println("ERROR");
        }

        JsonObject jObj = gson.fromJson(String.valueOf(sb), JsonObject.class);

        int id = jObj.get("id").getAsInt();
        String name = jObj.get("name").getAsString();
        String surname = jObj.get("surname").getAsString();
        double salary = jObj.get("salary").getAsDouble();

        if (id > 0) {
            if (id > model.getFromList().size()) {
                Mesage dataset = new Mesage();
                dataset.code = "ERROR";
                dataset.message = "Пользователя с таким ID не существует!";
                pw.print(gson.toJson(dataset));
            } else {
                model.delete(id);
                User user = new User(name, surname, salary);
                model.put(user, id);

                Mesage mesage = new Mesage();
                mesage.message = "Пользователя с ID " + id + " изменен!";
                pw.print(gson.toJson(mesage));
            }
        } else {
            Mesage mesage = new Mesage();
            mesage.code = "ERROR";
            mesage.message = "Id должен быть больше 0!";
            pw.print(gson.toJson(mesage));
        }
    }
}
