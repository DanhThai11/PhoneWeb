package Control;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.DAO;
import Entity.Product;

/**
 * Servlet implementation class SearchControl
 */
@WebServlet("/search")
public class SearchControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		String txtSearch = request.getParameter("txt");
		DAO dao = new DAO();
		List<Product> list = dao.searchByName(txtSearch);
		request.setAttribute("listP1", list);
		request.setAttribute("txtS", txtSearch);
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

}