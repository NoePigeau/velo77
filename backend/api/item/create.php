<?php

require_once __DIR__ . '/../../dao/item.php';
require_once __DIR__ . '/../../utils/server.php';

ensureHttpMethod('POST');

$content = file_get_contents('php://input');
$_POST = json_decode( $content, true);

$name = isset( $_POST['name'] ) ? htmlspecialchars( $_POST['name'] ) : null;
$description = isset( $_POST['description']) ? htmlspecialchars( $_POST['description'] ) : null;
$price = isset( $_POST['price']) ? floatval($_POST['price']) : null;
$size = isset( $_POST['size']) ? htmlspecialchars( $_POST['size'] ) : null;
$collection = isset( $_POST['collection']) ? htmlspecialchars( $_POST['collection'] ) : null;
$series = isset( $_POST['series']) ? htmlspecialchars( $_POST['series'] ) : null;
$type = isset( $_POST['type']) ? htmlspecialchars( $_POST['type'] ) : null;
$brand = isset( $_POST['brand']) ? htmlspecialchars( $_POST['brand'] ) : null;


if( !($name && $description && $price && $size && $collection && $series && $type && $brand) ){
    http_response_code(400);
    die();
}

if( getItemByName($name) !== null ){
    http_response_code(409);
    die();
}

$idItem = itemInsert($name, $description, $price, $size, $collection, $series, $type, $brand );
if( $idItem === null ){
    http_response_code(500);
    die();

}

http_response_code(201);


