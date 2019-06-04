<?php

error_reporting(0);
include_once("dbconnect.php");

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
<?php

$email = $_POST['email'];
$oldpass = $_POST['key'];
$newpassword = $_POST['newpass'];
$sqlupdate = "UPDATE users SET password = '$newpassword' WHERE email = '$email' AND password = '$oldpass'";
if ($conn->query($sqlupdate) === TRUE)
{
  ?>
  <h1>Succeed!</h1>
  <p> You may now login using this password: <?php echo $newpassword ?></p>
  <?php
}
else
{
  ?>
  <h1>Failed!</h1>
  <p> Ouch, that's hurt. You might want to start it over!</p>
  <?php
}

?>

</div>
</div>
<div class="copy-right">
    <p>© 2019 lepakIntern. All Rights Reserved.</p>
</div>
<!--element end here-->
</body>
</html>
