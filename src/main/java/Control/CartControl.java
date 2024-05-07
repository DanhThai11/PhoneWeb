package Control;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.DAO;
import Entity.Cart;
import Entity.Product;

/**
 * Servlet implementation class CartControl
 */
@WebServlet("/cart")
public class CartControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartControl() {
        super();
        // TODO Auto-generated constructor stub
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	DAO dao = new DAO();
		String id = request.getParameter("pid");
		System.out.println("Send by cartcontrol : "+ id);
		if (id == null) {
			request.setAttribute("cart", DAO.productAndAmount);
	    	request.getRequestDispatcher("Cart.jsp").forward(request, response);
	    	return;
		} else {
			Product sellectProduct = dao.getProductByID(id);
//	    	HttpSession session = request.getSession();
//	        int accountId = (int) session.getAttribute("accountId");

	    	DAO.addCart(sellectProduct);
	        request.setAttribute("cart", DAO.productAndAmount);
	    	request.getRequestDispatcher("Cart.jsp").forward(request, response);
		}
    	

    }
}
