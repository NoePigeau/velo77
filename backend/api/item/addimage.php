<?php

require_once __DIR__ . '/../../dao/item.php';
require_once __DIR__ . '/../../utils/server.php';

ensureHttpMethod('POST');

$img = $_FILES['img'];

if( $img === null ){
    http_response_code(400);
    die();
}

if( !isset($_POST['id']) || getItemById( $_POST['id'] ) === null ) {

    http_response_code(404);
    die();

}


$idItem = imgInsert( $img , $_POST['id'] );
if( $idItem == false ){
    http_response_code(500);
    die();

}

http_response_code(201);


