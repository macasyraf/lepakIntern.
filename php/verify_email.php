<?php
    //ini_set( 'display_errors', 1 );
    error_reporting(0);
    include_once("dbconnect.php");

    $email = $_POST['email'];

    $sql = "SELECT * FROM users WHERE email = '$email'";
    $result = $conn->query($sql);

    if ($result->num_rows > 0)
    {
         while ($row = $result ->fetch_assoc())
         {
             $ran = $row["password"];
         }

        $from = "lepakintern@githubbers.com";
        $to = $email;
        $subject = "lepakIntern. Password Reset :)";
        $message = "Kindly use the following link to reset your password:"."\n http://githubbers.com/sliice/reset_password.php?email=".$email."&key=".$ran;
        $headers = "From:" . $from;
        mail($email, $subject, $message, $headers);
        echo "success";
    }
    else
    {
        echo "failed";
    }


?>
