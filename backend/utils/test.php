<?php
$url = "http://localhost:80/devweb/velo77/backend/api/item/getimage.php?img=2";
$context = stream_context_create(array(
    'http' => array(
        'header' => "Authorization: Bearer eb74ad3fbf6877e02023e3b77c4b63972db1d22df65b9466854b4a40d6fb7502"
    )
));


$res = file_get_contents($url, false, $context);

$res = json_decode($res, true);


?>
<!doctype html>
<html lang="fr">
<head>
  <meta charset="utf-8">
  <title>TEST</title>
</head>
<body>
  <?php foreach( $res as $key => $values){

  } ?>
</body>
</html>