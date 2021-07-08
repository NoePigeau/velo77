<?php

require_once __DIR__ . '/../../dao/item.php';
require_once __DIR__ . '/../../utils/server.php';

ensureHttpMethod('GET');

if( !empty($_GET['img']) ){
    $img = imagecreatefrompng("../../img-item/item_2.PNG");
    $img2 = imagecreatefrompng("../../img-item/item_1.png");
    header('Content-Type: image/png');
    header('Content-Type: application/json');
    $images = [ $img , $imgs ];
    echo json_encode($images, true);


}else {

    http_response_code(400);
}

