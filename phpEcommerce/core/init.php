<?php
$db = mysqli_connect('localhost','root','mysql','butick');

if (mysqli_connect_errno()) {
  echo "Database Connection Failed with following errors:".mysqli_connect_error();
  die();
}
require_once $_SERVER['DOCUMENT_ROOT'].'/phptutorial/config.php';
require_once BASEURL.'helpers/helpers.php';
?>
