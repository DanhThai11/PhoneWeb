package Control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ProductControl")
public class ProductControl extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ProductControl() {
        super();
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html;charset=UTF-8");
        req.setCharacterEncoding("UTF-8");
        res.setCharacterEncoding("UTF-8");
        PrintWriter out = res.getWriter();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/phone_app2", "root", "Danhthai0311");

            String searchID = req.getParameter("searchid");

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<meta charset='utf-8'>");
            out.println("<meta name='viewport' content='width=device-width, initial-scale=1, shrink-to-fit=no'>");
            out.println("<title>Product Management</title>");
            out.println("<link rel='stylesheet' href='https://fonts.googleapis.com/css?family=Roboto|Varela+Round'>");
            out.println("<link rel='stylesheet' href='https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css'>");
            out.println("<link rel='stylesheet' href='https://fonts.googleapis.com/icon?family=Material+Icons'>");
            out.println("<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css'>");
            out.println("<script src='https://code.jquery.com/jquery-3.5.1.min.js'></script>");
            out.println("<script src='https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js'></script>");
            out.println("<script src='https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js'></script>");
            out.println("<style>");
            // CSS styles from pasted 1
            out.println("</style>");
            out.println("<script>");
            // JavaScript from pasted 1
            out.println("</script>");
            out.println("</head>");

            out.println("<body>");
            out.println("<div class='container-xl'>");
            out.println("<div class='table-responsive'>");
            out.println("<div class='table-wrapper'>");
            out.println("<div class='table-title'>");
            out.println("<div class='table-title' style='background-color: #435d7d; color: #fff; padding: 16px 30px; border-radius: 3px 3px 0 0;'>");
            out.println("<div class='row'>");
            out.println("<div class='col-sm-6'>");
            out.println("<h2>Manage <b>Products</b></h2>");
            out.println("</div>");
            out.println("<div class='d-flex justify-content-end col-sm-6'>"); // Sử dụng Flexbox để đẩy nút ra xa
            out.println("<a href='#addEmployeeModal' class='btn btn-success mr-2' data-toggle='modal' data-target='#addEmployeeModal'><i class='material-icons'>&#xE147;</i> Add New Product</a>");
            out.println("<a href='#deleteEmployeeModal' class='btn btn-danger'><i class='material-icons'>&#xE872;</i> Delete</a>");
            out.println("</div>");
            out.println("</div>");
            out.println("</div>");
            
            
            out.println("<table class='table table-striped table-hover'>");
            out.println("<thead>");
            out.println("<tr>");
            out.println("<th><span class='custom-checkbox'><input type='checkbox' id='selectAll'><label for='selectAll'></label></span></th>");
            out.println("<th>ID</th>");           
            out.println("<th>Name</th>");
            out.println("<th>Image</th>");
            out.println("<th>Price</th>");
            out.println("<th>Title</th>");
            out.println("<th>Description</th>");
            out.println("<th>CateID</th>");
            out.println("<th>Sell_ID</th>");
            out.println("<th>Actions</th>");
            out.println("</tr>");
            out.println("</thead>");
            out.println("<tbody>");

            Statement stmt = con.createStatement();
            String sql1 = "SELECT * FROM product";
            String sql2 = "SELECT * FROM product WHERE id LIKE '%" + searchID + "%'";
            ResultSet rs;
            if (searchID != null && !searchID.isEmpty()) {
                rs = stmt.executeQuery(sql2);
            } else {
                rs = stmt.executeQuery(sql1);
            }

            while (rs.next()) {
                String ID = rs.getString("id");
                String N = rs.getString("name");
                String imagePath = rs.getString("image");
                String P = rs.getString("price");
                String T = rs.getString("title");
                String D = rs.getString("description");
                String C = rs.getString("cateID");
                String S = rs.getString("sell_ID");

                out.println("<tr>");
                out.println("<td><span class='custom-checkbox'><input type='checkbox' id='checkbox" + ID + "' name='options[]' value='" + ID + "'><label for='checkbox" + ID + "'></label></span></td>");
                out.println("<td>" + ID + "</td>");
                out.println("<td>" + N + "</td>");
                out.println("<td style='text-align: center;'><img src='" + imagePath + "' alt='" + imagePath + "' width='100' height='100'></td>");                out.println("<td style='text-align: center;'>" + P + "</td>"); 
                out.println("<td style='text-align: center;'>" + T + "</td>"); 
                out.println("<td>" + D + "</td>");
                out.println("<td style='text-align: center;'>" + C + "</td>"); 
                out.println("<td style='text-align: center;'>" + S + "</td>"); // Căn giữa theo chiều ngang

                out.println("<td>");
                out.println("<a href='#editEmployeeModal' class='edit' data-toggle='modal'><i class='material-icons' data-toggle='tooltip' title='Edit'>&#xE254;</i></a>");
                out.println("<a href='ProductControlAction?action=delete&id=" + ID + "' class='delete' data-toggle='modal'><i class='material-icons' data-toggle='tooltip' title='Delete'>&#xE872;</i></a>");
                out.println("</td>");
                out.println("</tr>");
            }

            out.println("</tbody>");
            out.println("</table>");

            // Phần hiển thị phân trang...

            out.println("</div>");
            out.println("</div>");
            out.println("</div>");

            // Thêm các modal như trong pasted 1
            out.println("<div id='addEmployeeModal' class='modal fade'>");
            out.println("    <div class='modal-dialog'>");
            out.println("        <div class='modal-content'>");
            out.println("            <form action='ProductControlAction?action=add' method='post' enctype=\"multipart/form-data\">");
            out.println("                <div class='modal-header'>");
            out.println("                    <h4 class='modal-title'>Add Product</h4>");
            out.println("                    <button type='button' class='close' data-dismiss='modal' aria-hidden='true'>&times;</button>");
            out.println("                </div>");
            out.println("                <div class='modal-body'>");
            out.println("                    <div class='form-group'>");
            out.println("                        <div class='form-group'>");
            out.println("                            <label>ID</label>");
            out.println("                            <input type='text' name='id' class='form-control' required>");
            out.println("                        </div>");
            out.println("                        <label>Name</label>");
            out.println("                        <input type='text' name='name' class='form-control' required>");
            out.println("                    </div>");
            out.println("                    <div class='form-group'>");
            out.println("                        <label>Image</label>");
            out.println("                        <input type='file' name='image' class='form-control' required>");
            out.println("                    </div>");
            out.println("                    <div class='form-group'>");
            out.println("                        <label>Price</label>");
            out.println("                        <input type='text' name='price' class='form-control' required>");
            out.println("                    </div>");
            out.println("                    <div class='form-group'>");
            out.println("                        <label>Title</label>");
            out.println("                        <input type='text' name='title' class='form-control' required>");
            out.println("                    </div>");
            out.println("                    <div class='form-group'>");
            out.println("                        <label>Description</label>");
            out.println("                        <textarea name='description' class='form-control' required></textarea>");
            out.println("                    </div>");
            out.println("                    <div class='form-group'>");
            out.println("                        <label>Category ID</label>");
            out.println("                        <input type='text' name='cateID' class='form-control' required>");
            out.println("                    </div>");
            out.println("                    <div class='form-group'>");
            out.println("                        <label>Seller ID</label>");
            out.println("                        <input type='text' name='sellID' class='form-control' required>");
            out.println("                    </div>");
            out.println("                </div>");
            out.println("                <div class='modal-footer'>");
            out.println("                    <input type='button' class='btn btn-default' data-dismiss='modal' value='Cancel'>");
            out.println("                    <input type='submit' class='btn btn-success' value='Add'>");
            out.println("                </div>");
            out.println("            </form>");
            out.println("        </div>");
            out.println("    </div>");
            out.println("</div>");

            
            
            
            out.println("<!-- Edit Modal HTML -->");
            out.println("<div id='editEmployeeModal' class='modal fade'>");
            out.println("<div class='modal-dialog'>");
            out.println("<div class='modal-content'>");
            out.println("<form action='ProductControlAction' method='post' enctype='multipart/form-data'>");
            out.println("<input type='hidden' name='action' value='edit'>");
            out.println("<input type='hidden' name='id' id='editId'>");
            out.println("<input type='hidden' name='oldImage' id='editImage'>");
            out.println("<div class='modal-header'>");
            out.println("<h4 class='modal-title'>Edit Product</h4>");
            out.println("<button type='button' class='close' data-dismiss='modal' aria-hidden='true'>&times;</button>");
            out.println("</div>");
            out.println("<div class='modal-body'>");
            out.println("<div class='form-group'>");
            out.println("<label>Name</label>");
            out.println("<input type='text' name='name' class='form-control' required>");
            out.println("</div>");
            out.println("<div class='form-group'>");
            out.println("                        <label>Image</label>");
            out.println("                        <input type='file' name='image' class='form-control' required>");
            out.println("</div>");
            out.println("<div class='form-group'>");
            out.println("<label>Price</label>");
            out.println("<input type='text' name='price' class='form-control' required>");
            out.println("</div>");
            out.println("<div class='form-group'>");
            out.println("<label>Title</label>");
            out.println("<input type='text' name='title' class='form-control' required>");
            out.println("</div>");
            out.println("<div class='form-group'>");
            out.println("<label>Description</label>");
            out.println("<textarea name='description' class='form-control' required></textarea>");
            out.println("</div>");
            out.println("<div class='form-group'>");
            out.println("<label>CateID</label>");
            out.println("<input type='text' name='cateID' class='form-control' required>");
            out.println("</div>");
            out.println("<div class='form-group'>");
            out.println("<label>Sell_ID</label>");
            out.println("<input type='text' name='sellID' class='form-control' required>");
            out.println("</div>");
            out.println("</div>");
            out.println("<div class='modal-footer'>");
            out.println("<input type='button' class='btn btn-default' data-dismiss='modal' value='Cancel'>");
            out.println("<input type='submit' class='btn btn-info' value='Save'>");
            out.println("</div>");
            out.println("</form>");
            out.println("</div>");
            out.println("</div>");
            out.println("</div>");
            
            out.println("<script>");
            out.println("$(document).ready(function() {");
            out.println("    $('.edit').on('click', function() {");
            out.println("        var productId = $(this).closest('tr').find('td:nth-child(2)').text();");
            out.println("        var productName = $(this).closest('tr').find('td:nth-child(3)').text();");
            out.println("        var productImage = $(this).closest('tr').find('td:nth-child(4) img').attr('src');");
            out.println("        var productPrice = $(this).closest('tr').find('td:nth-child(5)').text();");
            out.println("        var productTitle = $(this).closest('tr').find('td:nth-child(6)').text();");
            out.println("        var productDescription = $(this).closest('tr').find('td:nth-child(7)').text();");
            out.println("        var productCateID = $(this).closest('tr').find('td:nth-child(8)').text();");
            out.println("        var productSellID = $(this).closest('tr').find('td:nth-child(9)').text();");
            out.println("        $('#editId').val(productId);");
            out.println("        $('#editName').val(productName);");
            out.println("        $('#editImage').val(productImage);");
            out.println("        $('#editPrice').val(productPrice);");
            out.println("        $('#editTitle').val(productTitle);");
            out.println("        $('#editDescription').val(productDescription);");
            out.println("        $('#editCateID').val(productCateID);");
            out.println("        $('#editSellID').val(productSellID);");
            out.println("        $('#editEmployeeModal').modal('show');"); // Show the modal for editing
            out.println("    });");
            out.println("});");
            out.println("</script>");


            out.println("<!-- Delete Modal HTML -->");
            out.println("<div id='deleteEmployeeModal' class='modal fade'>");
            out.println("<div class='modal-dialog'>");
            out.println("<div class='modal-content'>");
            out.println("<form action='ProductControlAction' method='post'>");
            out.println("<input type='hidden' name='action' value='delete'>");
            out.println("<input type='hidden' name='id' id='deleteId'>");
            out.println("<div class='modal-header'>");
            out.println("<h4 class='modal-title'>Delete Product</h4>");
            out.println("<button type='button' class='close' data-dismiss='modal' aria-hidden='true'>&times;</button>");
            out.println("</div>");
            out.println("<div class='modal-body'>");
            out.println("<p>Are you sure you want to delete this product?</p>");
            out.println("<p class='text-warning'><small>This action cannot be undone.</small></p>");
            out.println("</div>");
            out.println("<div class='modal-footer'>");
            out.println("<input type='button' class='btn btn-default' data-dismiss='modal' value='Cancel'>");
            out.println("<input type='submit' class='btn btn-danger' value='Delete'>");
            out.println("</div>");
            out.println("</form>");
            out.println("</div>");
            out.println("</div>");
            out.println("</div>");

            out.println("<script>");
            out.println("$(document).ready(function() {");
            out.println("    $('.delete').on('click', function() {");
            out.println("        var productId = $(this).closest('tr').find('td:nth-child(2)').text();"); // Gets the ID from the second column of the row
            out.println("        $('#deleteId').val(productId);"); // Sets the ID into a hidden input field in the delete modal
            out.println("        $('#deleteEmployeeModal').modal('show');"); // Shows the delete modal
            out.println("    });");
            out.println("});");
            out.println("</script>");


            out.println("</body>");
            out.println("</html>");

                        stmt.close();
                        con.close();

                    } catch (Exception e) {
                        e.printStackTrace();
                        out.println("Error: " + e.getMessage());
                    }
                }

                protected void doPost(HttpServletRequest request, HttpServletResponse response)
                        throws ServletException, IOException {
                    doGet(request, response);
                }
            }