<?php
error_reporting(0);
include_once("dbconnect.php");

$username = $_POST['username'];
$email = $_POST['email'];
$oldpassword = $_POST['oldpass'];
$phone = $_POST['phone'];

$sqlcheck = "SELECT * FROM users WHERE username = '$username' AND password = '$oldpassword'";
$result = $conn->query($sqlcheck);
if ($result->num_rows > 0) {
 $sqlupdate = "UPDATE users SET email = '$email', phone = '$phone' WHERE username = '$username' AND password = '$oldpassword'";
  if ($conn->query($sqlupdate) === TRUE){
        echo 'success';
  }else{
      echo 'failed';
  }   
}else{
    echo "failed";
}

 
?>