<?php

error_reporting(0);
include_once("dbconnect.php");

$email = $_POST['email'];
$username = $_POST['username'];
$sql = "SELECT * FROM users WHERE username = '$username'";

$result = $conn->query($sql);

if ($result->num_rows > 0)
{
    $response["user"] = array();
    while ($row = $result ->fetch_assoc())
    {
        $userarray = array();
        $userarray[username] = $row["username"];
        $userarray[email] = $row["email"];
        $userarray[password] = $row["password"];
        $userarray[phone] = $row["phone"];
        $userarray[matric] = $row["matric"];
        array_push($response["user"], $userarray);
    }
    echo json_encode($response);
}

?>
