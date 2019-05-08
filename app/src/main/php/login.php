<?php
require "conn.php";
$username = $_POST["user_name"];#the key you passed in the BackgroundWorker class(user_name)
$password = $_POST["password"];
$mysql_query = "select * from employee where name like '$username' and password like '$password';";
$result = mysqli_query($conn, $mysql_query);
if(mysqli_num_rows($result)>0)
{
echo "login success";#response that goes to postExecute(String result)
}
else
{
	echo "login not success";
}
?>
