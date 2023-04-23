package ru.appline;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.appline.logic.Mesage;
import ru.appline.logic.Model;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/delete")
public class ServletDelete extends HttpServlet {

    Model model = Model.getInstance();
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");

        PrintWriter pw = response.getWriter();
        int id = Integer.parseInt(request.getParameter("id"));

        if (id > 0) {
            if (id > model.getFromList().size()) {
                Mesage mesage = new Mesage();
                mesage.code = "ERROR";
                mesage.message = "Пользователя с таким ID не существует!";
                pw.print(gson.toJson(mesage));
            } else {
                Mesage mesage = new Mesage();
                mesage.message = "Пользователя с ID " + id + " удален!";
                pw.print(gson.toJson(mesage));
                model.delete(id);
            }
        } else {
            Mesage mesage = new Mesage();
            mesage.code = "ERROR";
            mesage.message = "Id должен быть больше 0!";
            pw.print(gson.toJson(mesage));
        }
    }
}




