<?php

error_reporting(0);
include_once("dbconnect.php");

$username = $_POST['username'];
$email = $_POST['email'];
$password = $_POST['password'];
$phone = $_POST['phone'];
$matric = $_POST['matric'];

if (strlen($email) > 0)
{
    $sqlinsert = "INSERT INTO users(username, email, password, phone, matric) VALUES('$username', '$email', '$password', '$phone', '$matric')";

    if ($conn -> query($sqlinsert) === TRUE)
    {
       echo "success";
    }
    else
    {
        echo "failed";
    }
}
else
{
    echo "nodata";
}

?>
