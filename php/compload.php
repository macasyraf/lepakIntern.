<?php
error_reporting(0);
include_once("dbconnect.php");
$availability = $_POST['compavailability'];

if (strcasecmp($availability, "Available") == 0)
{
    $sql = "SELECT * FROM companies WHERE compavailability = 'Available'";
}
else
{
    $sql = "SELECT * FROM companies WHERE compavailability = 'Disable'";
}

$result = $conn->query($sql);

if ($result->num_rows > 0)
{
    $response["companies"] = array();
    while ($row = $result ->fetch_assoc())
    {
        $restlist = array();
        $restlist[compid] = $row["compid"];
        $restlist[compname] = $row["compname"];
        $restlist[compwebsite] = $row["compwebsite"];
        $restlist[compcontact] = $row["compcontact"];
        $restlist[complocation] = $row["complocation"];
        $restlist[compsalary] = $row["compsalary"];
        $restlist[comptask] = $row["comptask"];
        $restlist[compavailability] = $row["compavailability"];
        array_push($response["companies"], $restlist);
    }
    
    echo json_encode($response);
}
else
{
    echo "nodata";
}

?>
