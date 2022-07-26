package gw.toy.common.utils;

import lombok.extern.log4j.Log4j;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;


public class AlertExceptionUtil {

    public static void init(HttpServletResponse response) {
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
    }

    public static String alert(HttpServletResponse response, String message) {
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("utf-8");
        PrintWriter pwOut = null;

        try {
            pwOut = response.getWriter();
            if (message.length() > 100) { // 100자 이상의 메시지일 경우 한줄로 잘라낸다.
                StringTokenizer tokenMessage = new StringTokenizer(message, "\n");
                message = tokenMessage.nextToken();
            }
            pwOut.println("<!DOCTYPE html><html lang=\"ko\"><head><title>시스템안내</title><meta charset=\"utf-8\">");
            pwOut.println("<script language='javascript'>");
            pwOut.println("alert('" + message + "');");
            pwOut.println("history.back();");
            pwOut.println("</script>");
            pwOut.println("</head></html>");
        } catch (IOException e) {
            return null;
        } finally {
            pwOut.flush();
        }
        return null;
//        init(response);
//        PrintWriter out = null;
//        try {
//            out = response.getWriter();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        out.println("<script>alert('" + alertText + "'); history.go(-1);</script> ");
//        out.flush();
    }
}
