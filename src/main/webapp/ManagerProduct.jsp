<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset='utf-8'>
<meta name='viewport'
	content='width=device-width, initial-scale=1, shrink-to-fit=no'>
<title>Product Management</title>
<link rel='stylesheet'
	href='https://fonts.googleapis.com/css?family=Roboto|Varela+Round'>
<link rel='stylesheet'
	href='https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css'>
<link rel='stylesheet'
	href='https://fonts.googleapis.com/icon?family=Material+Icons'>
<link rel='stylesheet'
	href='https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css'>
<script src='https://code.jquery.com/jquery-3.5.1.min.js'></script>
<script
	src='https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js'></script>
<script
	src='https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js'></script>
<style>
</style>
</head>
<body>
	<div class='container-xl'>
		<div class='table-responsive'>
			<div class='table-wrapper'>
				<div class='table-title'>
					<div class='table-title'
						style='background-color: #435d7d; color: #fff; padding: 16px 30px; border-radius: 3px 3px 0 0;'>
						<div class='row'>
							<div class='col-sm-6'>
								<h2>
									Manage <b>Products</b>
								</h2>
							</div>
							<div class='d-flex justify-content-end col-sm-6'>
								<!-- Sử dụng Flexbox để đẩy nút ra xa -->
								<a href='#addEmployeeModal' class='btn btn-success mr-2'
									data-toggle='modal' data-target='#addEmployeeModal'><i
									class='material-icons'>&#xE147;</i> Add New Product</a> <a
									href='#deleteEmployeeModal' class='btn btn-danger'><i
									class='material-icons'>&#xE872;</i> Delete</a>
							</div>
						</div>
					</div>
					<table class='table table-striped table-hover'>
						<thead>
							<tr>
								<th><span class='custom-checkbox'><input
										type='checkbox' id='selectAll'><label for='selectAll'></label></span></th>
								<th>ID</th>
								<th>Name</th>
								<th>Image</th>
								<th>Price</th>
								<th>Title</th>
								<th>Description</th>
								<th>CateID</th>
								<th>Sell_ID</th>
								<th>Actions</th>
							</tr>
						</thead>
						<tbody>
						<tbody>
							<c:forEach var="o" items="${listP}">
								<tr>
									<td><span class='custom-checkbox'><input
											type='checkbox' id='checkbox${o.id}' name='options[]'
											value='1'><label
											for='checkbox'></label></span></td>
									<td>${o.id}</td>
									<td>${o.name}</td>
									<td style='text-align: center;'><img
										src='${o.iamge}' alt='${o.image}'
										width='100' height='100'></td>
									<td style='text-align: center;'>${o.price}</td>
									<td style='text-align: center;'>${o.title}</td>
									<td>${o.description}</td>
									<td style='text-align: center;'>${o.cateID}</td>
									<td style='text-align: center;'>${o.sell_ID}</td>
									<td><a href='#editEmployeeModal' class='edit'
										data-toggle='modal'><i class='material-icons'
											data-toggle='tooltip' title='Edit'>&#xE254;</i></a> <a
										href='ProductControlAction?action=delete&id=${o.id}'
										class='delete' data-toggle='modal'><i
											class='material-icons' data-toggle='tooltip' title='Delete'>&#xE872;</i></a>
									</td>
								</tr>
							</c:forEach>
						</tbody>
						</tbody>

					</table>
				</div>
			</div>
		</div>
	</div>
</body>
</html>