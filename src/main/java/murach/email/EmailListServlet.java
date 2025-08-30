
package murach.email;
import murach.business.User;
import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import murach.business.User;
import murach.data.UserDB;


public class EmailListServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String url = "/index.html";   // mặc định quay lại form

        // lấy action từ request
        String action = request.getParameter("action");
        if (action == null) {
            action = "join";   // mặc định
        }

        //  hướng theo action
        if (action.equals("join")) {
            url = "/index.html";   // hiển thị form
        }
        else if (action.equals("add")) {
            // lấy dữ liệu từ form
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String email = request.getParameter("email");

            // tạo User object
            User user = new User(firstName, lastName, email);

            // lưu vào database (tạm gọi)
            UserDB.insert(user);

            // gắn vào request scope
            request.setAttribute("user", user);

            // chuyển hướng tới trang cảm ơn
            url = "/thanks.jsp";
        }

        // forward request/response tới trang đích
        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}