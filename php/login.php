<?php

error_reporting(0);
include_once("dbconnect.php");

$email = $_POST['email'];
$password = $_POST['password'];

$sql = "SELECT * FROM users WHERE email = '$email' AND password = '$password'";
$result = $conn -> query($sql);

if ($result -> num_rows > 0)
{
    while ($row = $result -> fetch_assoc())
    {
        echo $data = $row["username"].",".$row["email"].",".$row["matric"];
    }
}
else
{
    echo "failed";
}

?>
