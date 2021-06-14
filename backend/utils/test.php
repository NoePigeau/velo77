<?php
$url = "http://localhost:80/devweb/velo77/backend/api/user/testlog.php";
$context = stream_context_create(array(
    'http' => array(
        'header' => "Authorization: Bearer eb74ad3fbf6877e02023e3b77c4b63972db1d22df65b9466854b4a40d6fb7502"
    )
));


$res = file_get_contents($url, false, $context);

$res_json = json_decode( $res ,true);

echo $res_json['idUser'];