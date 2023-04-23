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

@WebServlet (urlPatterns = "/get")

public class ServletList extends HttpServlet {
    Model model = Model.getInstance();
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    //    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        response.setContentType("text/html;charset=utf-8");
//        PrintWriter pw = response.getWriter();
//        int id = Integer.parseInt(request.getParameter("id"));
//        if (id==0){
//            pw.print("<html>" +
//                    "<h3>Доступные пользователи:</h3></br>" +
//                    "id ползьзователя: " +
//                    "<ul>");
//            for (Map.Entry<Integer, User> entry:model.getFromList().entrySet()) {
//                pw.print("<li>" + entry.getKey() + "</li>" +
//                        "<ul>" +
//                        "<li>Имя: " + entry.getValue().getName() + "</li>" +
//                        "<li>Фамилия: " + entry.getValue().getSurname() + "</li>" +
//                        "<li>Зарплата: " + entry.getValue().getSalary() + "</li>" +
//                        "</ul>");
//            }
//            pw.print("</ul>" +
//                    "<a href=\"index.jsp\">Домой</a>" +
//                    "</html>");
//        } else if (id > 0){
//            if (id > model.getFromList().size()){
//                pw.print("<html>" +
//                        "<h3>Такого пользователя нет</h3>" +
//                        "<a href=\"index.jsp\">Домой</a>" +
//                        "</html>");
//            } else {
//                pw.print("<html>" +
//                        "<h3>Запрошенный пользователь: </h3>" +
//                        "</br>" +
//                        "Имя: " + model.getFromList().get(id).getName() + "</br>" +
//                        "Фамилия: " + model.getFromList().get(id).getSurname() + "</br>" +
//                        "Зарплата: " + model.getFromList().get(id).getSalary() + "</br>" +
//                        "<a href=\"index.jsp\">Домой</a>" +
//                        "</html>");
//            }
//        }else {
//            pw.print("<html>" +
//                    "<h3>id должен быть больше 0</h3>" +
//                    "<a href=\"index.jsp\">Домой</a>" +
//                    "</html>");
//        }
//    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");

        int id = Integer.parseInt(request.getParameter("id"));

        PrintWriter pw = response.getWriter();

        if (id == 0) {
            pw.print(gson.toJson(model.getFromList()));
        } else if (id > 0) {
            if (id > model.getFromList().size()) {
                Mesage mesage = new Mesage();
                mesage.code = "ERROR";
                mesage.message = "Пользователя с таким ID не существует!";
                pw.print(gson.toJson(mesage));
            } else {
                pw.print(gson.toJson(model.getFromList().get(id)));
            }
        } else {
            Mesage mesage = new Mesage();
            mesage.code = "ERROR";
            mesage.message = "Id должен быть больше 0!";
            pw.print(gson.toJson(mesage));
        }
    }


}
