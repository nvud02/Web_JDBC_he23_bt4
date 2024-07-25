import dal.CategoryDAO;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Category;

@WebServlet("/add")
public class AddCategoryServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String describe = request.getParameter("describe");
        
        CategoryDAO categoryDAO = new CategoryDAO();
        try {
            if (categoryDAO.existsCategory(id)) {
                // ID already exists
                request.setAttribute("error", "Category ID already exists. Please choose another ID.");
                request.getRequestDispatcher("addNewCategory.jsp").forward(request, response);
            } else {
                // Insert new category
                Category category = new Category(id, name, describe);
                categoryDAO.insert(category);
                response.sendRedirect("list"); // Redirect back to the category list
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error");
        }
    }
}
