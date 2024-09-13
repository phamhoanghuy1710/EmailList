

package murach.email;

import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

import murach.business.User;

public class EmailListServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "/index.html"; 
        
        // get current action
        String action = request.getParameter("action");  // Corrected typo
        if (action == null) {
            action = "join";  // default action
        }
        
        // perform action and set URL to appropriate page
        if (action.equals("join")) {
            url = "/index.html";
        } 
        else if (action.equals("add")) {
            // get parameters from the request
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String email = request.getParameter("email");
            String message;
            
            User user = new User(firstName, lastName, email);
            
            //validate infor
            if (firstName == null || lastName == null || email == null || firstName.isEmpty() || lastName.isEmpty()|| email.isEmpty()){
                message = "Please enter complete information";
                url = "/index.jsp";
            }
            else{
                message = null;
                url = "/thanks.jsp";
            }
            
            // set User object in request object and URL
            request.setAttribute("user", user);
            request.setAttribute("message", message);
        }
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
