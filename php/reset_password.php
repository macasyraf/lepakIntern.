<?php
$email = $_GET["email"];
$key = $_GET["key"];

if (strlen($key) < 1)
{
    header("Location: fault.html");
    exit;
}

?>

<!DOCTYPE HTML>
<html>
<head>
<title>lepakIntern. Reset Password</title>
<link href="css/style.css" rel="stylesheet" type="text/css" media="all"/>
<!-- Custom Theme files -->
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="Reset Password Form Responsive, Login form web template, Sign up Web Templates, Flat Web Templates, Login signup Responsive web template, Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyEricsson, Motorola web design" />
<!--google fonts-->
<link href='//fonts.googleapis.com/css?family=Roboto:400,100,300,500,700,900' rel='stylesheet' type='text/css'>
</head>
<body>
<!--element start here-->
<div class="elelment">
	<center><img src="images/logo_flat.png"></center>
	<div class="element-main">
		<h1>Forgot Password?</h1>
		<p> Selected Email: <?php echo $email ?></p>
		<form action="resetfinal.php" method="post">
			<input type="text" name="newpass" placeholder="New Password" required>
			<input type="hidden" name="key" value ="<?php echo $key ?>">
		  <input type="hidden" name="email" value ="<?php echo $email ?>">
			<input type="submit" value="Proceed">
		</form>
	</div>
</div>
<div class="copy-right">
			<p>© 2019 lepakIntern. All Rights Reserved.</p>
</div>

<!--element end here-->
</body>
</html>
