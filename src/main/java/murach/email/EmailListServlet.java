package murach.email;

import murach.business.User;
import murach.data.UserDB;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;

public class EmailListServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String url = "/index.jsp";   // mặc định quay lại form
        String action = request.getParameter("action");
        if (action == null) {
            action = "join";
        }

        if (action.equals("join")) {
            url = "/index.jsp";
        }
        else if (action.equals("add")) {
            // lấy dữ liệu từ form
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String email = request.getParameter("email");

            // validate dữ liệu
            String message;
            if (firstName == null || firstName.isEmpty() ||
                    lastName == null || lastName.isEmpty() ||
                    email == null || email.isEmpty()) {
                message = "Please fill out all required fields.";
                url = "/index.jsp";
            }
            else if (!email.contains("@")) {
                message = "Please enter a valid email address.";
                url = "/index.jsp";
            }
            else {
                // dữ liệu hợp lệ → tạo User, lưu vào DB
                User user = new User(firstName, lastName, email);
                UserDB.insert(user);

                request.setAttribute("user", user);
                message = "";
                url = "/thanks.jsp";
            }
            request.setAttribute("message", message);
        }

        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
    }
}
