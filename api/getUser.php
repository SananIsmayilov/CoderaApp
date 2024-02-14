<?php

$response = array();
if(isset($_POST['user_email']) && isset($_POST['user_password']) && isset($_POST['device_id'])){
    $user_email = $_POST['user_email'];
        $user_password = $_POST['user_password'];
            $device_id = $_POST['device_id'];
require_once __DIR__ . '/config.php';

$baglanti = mysqli_connect(DB_SERVER, DB_USER, DB_PASSWORD, DB_NAME);

if (!$baglanti) {
    die("Hatalı bağlantı : " . mysqli_connect_error());
}

$sqlsorgu = "SELECT * FROM User WHERE user_email = '$user_email' AND user_password = '$user_password' AND device_id = '$device_id'";

$result = mysqli_query($baglanti, $sqlsorgu);

if (mysqli_num_rows($result) > 0) {
    $response["success"] = 1;
    $response["message"] = "success";
    echo json_encode($response);
} else {
    $response["success"] = 0;
     $response["message"] = "no data";
    echo json_encode($response);
}

mysqli_close($baglanti);}
else {
   $response["success"] = 0;
    $response["message"] = "not send data";
    echo json_encode($response); 
}

?>
