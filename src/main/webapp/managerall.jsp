<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>Ministore</title>
    <style>
        body {
            text-align: center;
        }
        #image-container {
            margin-top: 20px; /* Adjust as needed */
        }
        img {
            width: 100%; /* Make the image take 100% of the container width */
            max-width: 600px; /* Set a maximum width for the image */
            display: block;
            margin: 0 auto; /* Center the image */
            border-radius: 10px; /* Optional: Add border radius for a rounded appearance */
        }
    </style>
</head>
<body>
    <h2 style='color: #fff; background: linear-gradient(to right, #4CAF50, #336699); padding: 15px; border-radius: 8px; border: 2px solid #336699; text-align: center;'>QUẢN LÝ MINISTORE</h2>

    <div style='display: flex; justify-content: space-around; background-color: #f1f1f1; padding: 10px;'>
    	<a href='' style='text-decoration: none; color: #fff; background-color: #3498db; padding: 8px 16px; border-radius: 5px; text-align: center; display: inline-block; margin-right: 10px;'>Account</a>
        <a href='manager' style='text-decoration: none; color: #fff; background-color: #3498db; padding: 8px 16px; border-radius: 5px; text-align: center; display: inline-block; margin-right: 10px;'>Product</a>
        <a href='' style='text-decoration: none; color: #fff; background-color: #3498db; padding: 8px 16px; border-radius: 5px; text-align: center; display: inline-block; margin-right: 10px;'>Category</a>
        <a href='' style='text-decoration: none; color: #fff; background-color: #3498db; padding: 8px 16px; border-radius: 5px; text-align: center; display: inline-block; margin-right: 10px;'>Cart</a>
    </div>

    <!-- Add the image container with centered and larger image -->
    <div id="image-container">
        <img src='https://scontent.fdad3-1.fna.fbcdn.net/v/t39.30808-6/300247557_469456001861269_482827160836948552_n.jpg?_nc_cat=103&ccb=1-7&_nc_sid=5f2048&_nc_ohc=rA23Ymiz_wcAb4iYMRV&_nc_ht=scontent.fdad3-1.fna&oh=00_AfCnkcM78LWroySQWNoxnO-VkAp76HuC_-Vz2rK54YDdTA&oe=6635619D' alt='Your Image Alt Text'>
    </div>

    <!-- Add the login form container -->
    <div class="form-container">
    </div>
</body>
</html>