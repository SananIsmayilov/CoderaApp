<?php
$response = array();

if ( isset($_POST['user_email']) && isset($_POST['user_password']) && isset($_POST['device_id'])) {

    $user_email = $_POST['user_email'];
    $user_password = $_POST['user_password'];
    $device_id = $_POST['device_id'];
  

    // DB_SERVER, DB_USER, DB_PASSWORD, DB_NAME değişkenleri alınır.
    require_once __DIR__ . '/config.php';
    
    // Bağlantı oluşturuluyor.
    $baglanti = mysqli_connect(DB_SERVER, DB_USER, DB_PASSWORD, DB_NAME);

    // Bağlantı kontrolü yapılır.
    if (!$baglanti) {
        die("Hatalı bağlantı: " . mysqli_connect_error());
    }



    $sqlsorgu = "INSERT INTO User (user_email, user_password,device_id) VALUES ('$user_email', '$user_password','$device_id')";

    if (mysqli_query($baglanti, $sqlsorgu)) {
        $response["success"] = 1;
        $response["message"] = "Successful registration";
        echo json_encode($response);
    } else {
        $response["success"] = 0;
        $response["message"] = "Error: " . mysqli_error($baglanti);
        echo json_encode($response);
    }
    // Bağlantı kapatılır.
    mysqli_close($baglanti);
} else {
    $response["success"] = 0;
    $response["message"] = "Required field(s) is missing";
    echo json_encode($response);
}
?>
