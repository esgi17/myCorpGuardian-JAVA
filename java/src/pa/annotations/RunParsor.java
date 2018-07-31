package pa.annotations;
import pa.controllers.*;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;


public class RunParsor {

    public static void main(String[] args) throws Exception {

        Class [] classes = new Class[7];
        classes[0] = LoginController.class;
        classes[1] = DeviceController.class;
        classes[2] = DoorController.class;
        classes[3] = GroupController.class;
        classes[4] = HomeController.class;
        classes[5] = UserController.class;
        classes[6] = EventController.class;

        FileWriter fw = new FileWriter( "src/pa/annotations/webView/annotation.html" );
        PrintWriter pw = new PrintWriter( fw );
        pw.println("<!DOCTYPE html>"+
                        "<html>"+
                          "<head>"+
                            "<meta charset='utf-8'>"+
                            "<meta name='viewport' content='width=device-width, initial-scale=1'>"+
                            "<title>MyCorpGuardian</title>"+
                            "<link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/bulma/0.7.1/css/bulma.min.css'>"+
                            "<script defer src='https://use.fontawesome.com/releases/v5.1.0/js/all.js'></script>"+
                          "</head>"+
                         "<body>"+
                            "<section class='hero is-dark'>" +
                              "<div class='hero-body'>" +
                                "<div class='container'>" +
                                  "<h1 class='title'>" +
                                            "MyCorpGuardian" +
                                            "</h1>" +
                                  "<h2 class='subtitle'>" +
                                            "Function description" +
                                            "</h2>" +
                                "</div>" +
                              "</div>" +
                            "</section>" +
                            "<section class='section'>" +
                                 "<div class='container is-fluid'>" +
                                    "<div class='columns'>" +
                                        "<div class='column' is-12>");

        for(int i = 0 ; i < 7; i++ ){

            pw.println("<h1 class='title'>" + classes[i].getName().substring(15,classes[i].getName().indexOf("C")) + "</h1>"+
                    "<table class='table is-striped is-fullwidth is-hoverable'>" +
          "<thead>"+
            "<tr>" +
              "<th>Name</th>" +
              "<th>Description</th>" +
              "<th style='width:20%'>Created By</th>" +
              "<th style='width:20%'>Routes</th>" +
              "<th style='width:15%'>Last update</th>" +
            "</tr>" +
          "</thead>" +
          "<tbody>");

            for (Method method : classes[i].getDeclaredMethods()) {
                if (method.isAnnotationPresent( FunctionParsor.class )) {
                    Annotation annotation = method.getAnnotation( FunctionParsor.class );
                    FunctionParsor functionParsor = (FunctionParsor) annotation;

                    pw.println( "<tr><th>" + method.getName() + "</th>" );
                    pw.println( "<td>" + functionParsor.description() + "</td>" );
                    pw.println( "<td style='white-space:nowrap;'>" + functionParsor.createdBy() + "</td><td>" );

                    int nbRoutes = functionParsor.apiRoutes().length;
                    for (String route : functionParsor.apiRoutes()) {
                        if (nbRoutes > 1) {
                            pw.println( route + ",");
                        } else {
                            pw.println( route );
                        }
                        nbRoutes--;
                    }
                    pw.println( "</td><td>" + functionParsor.lastModified() + "</td></tr>" );
                }
            }
            pw.println("</tbody></table>");
        }
        pw.println("</div></div></div></section></body></html>");
        pw.close();
    }
}