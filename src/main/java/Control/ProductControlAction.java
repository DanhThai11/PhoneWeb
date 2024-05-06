package Control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import javax.servlet.annotation.MultipartConfig;

import javax.servlet.http.Part;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ProductControlAction")
@MultipartConfig

public class ProductControlAction extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        try {
            String action = request.getParameter("action");

            if (action != null && action.equals("add")) {
                addProduct(request, response);
            } else if (action != null && action.equals("edit")) {
                editProduct(request, response);
            } else if (action != null && action.equals("delete")) {
                deleteProduct(request, response);
            }

        } catch (Exception e) {
            e.printStackTrace();
            out.println("Error: " + e.getMessage());
        }
    }

    private void addProduct(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String price = request.getParameter("price");
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        String cateID = request.getParameter("cateID");
        String sellID = request.getParameter("sellID");

        // Xử lý tải lên hình ảnh
        Part filePart = request.getPart("image");
        String imageFileName = filePart.getSubmittedFileName();
        String uploadPath = getServletContext().getRealPath("/") + "uploads" + File.separator + imageFileName;        FileOutputStream fos = new FileOutputStream(uploadPath);
        InputStream fileContent = filePart.getInputStream();
        byte[] buffer = new byte[1024];
        int bytesRead = 0;
        while ((bytesRead = fileContent.read(buffer)) != -1) {
            fos.write(buffer, 0, bytesRead);
        }
        fos.close();
        fileContent.close();

        Connection con = null;
        PreparedStatement pst = null;

        try {
            // Đường dẫn được lưu trong cơ sở dữ liệu là đường dẫn tương đối
            String image = "uploads/" + imageFileName;

            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/phone_app2", "root", "1234567890");
            String sql = "INSERT INTO product (id, name, image, price, title, description, cateID, sell_ID) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            pst = con.prepareStatement(sql);
            pst.setString(1, id);
            pst.setString(2, name);
            pst.setString(3, image);
            pst.setString(4, price);
            pst.setString(5, title);
            pst.setString(6, description);
            pst.setString(7, cateID);
            pst.setString(8, sellID);
            pst.executeUpdate();

            response.sendRedirect("ProductControl");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (pst != null) {
                pst.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    private void editProduct(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String price = request.getParameter("price");
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        String cateID = request.getParameter("cateID");
        String sellID = request.getParameter("sellID");

        Part filePart = request.getPart("image");
        String imageFileName = filePart.getSubmittedFileName();
        String image;

        if (imageFileName == null || imageFileName.isEmpty()) {
            // Nếu không có hình ảnh mới được tải lên, sử dụng hình ảnh cũ
            image = request.getParameter("oldImage");
        } else {
            // Xử lý tải lên hình ảnh mới
            String uploadPath = getServletContext().getRealPath("/") + "uploads" + File.separator + imageFileName;
            FileOutputStream fos = new FileOutputStream(uploadPath);
            InputStream fileContent = filePart.getInputStream();
            byte[] buffer = new byte[1024];
            int bytesRead = 0;
            while ((bytesRead = fileContent.read(buffer)) != -1) {
                fos.write(buffer, 0, bytesRead);
            }
            fos.close();
            fileContent.close();

            // Đường dẫn được lưu trong cơ sở dữ liệu là đường dẫn tương đối
            image = "uploads/" + imageFileName;
        }

        Connection con = null;
        PreparedStatement pst = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/phone_app2", "root", "Danhthai0311");
            String sql = "UPDATE product SET name = ?, image = ?, price = ?, title = ?, description = ?, cateID = ?, sell_ID = ? WHERE id = ?";
            pst = con.prepareStatement(sql);
            pst.setString(1, name);
            pst.setString(2, image);
            pst.setString(3, price);
            pst.setString(4, title);
            pst.setString(5, description);
            pst.setString(6, cateID);
            pst.setString(7, sellID);
            pst.setString(8, id);
            pst.executeUpdate();

            response.sendRedirect("ProductControl");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (pst != null) {
                pst.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    private void deleteProduct(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String id = request.getParameter("id");

        Connection con = null;
        PreparedStatement pst = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/phone_app2", "root", "Danhthai0311");
            String sql = "DELETE FROM product WHERE id = ?";
            pst = con.prepareStatement(sql);
            pst.setString(1, id);
            pst.executeUpdate();

            response.sendRedirect("ProductControl");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (pst != null) {
                pst.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }
}