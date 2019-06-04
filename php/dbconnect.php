<?php

$servername = "localhost";
$username   = "githubbe_sliice";
$password   = "4rZk32v74";
$dbname     = "githubbe_dblepak";

$conn = new mysqli($servername, $username, $password, $dbname);

if ($conn -> connect_error)
{
    die("Connection failed: " . $conn -> connect_error);
}

?>
