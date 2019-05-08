 <?php
$db_name = "ally";
$mysql_username = "admin";#your_username(default is root)
$mysql_password = "data0101";#your_password(default is empty)
$server_name = "localhost";
$conn = mysqli_connect($server_name, $mysql_username, $mysql_password, $db_name);
#if($conn)
#{
#	echo "Connection Success!";
#}
#else
#{
#	echo "connection not success";
#}
?>
