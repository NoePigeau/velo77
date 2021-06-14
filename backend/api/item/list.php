<?php

require_once __DIR__ . '/../../dao/item.php';
require_once __DIR__ . '/../../utils/server.php';

ensureHttpMethod('GET');

$limit = isset($_GET['limit']) ? intval($_GET['limit']) : 15;
$offset = isset($_GET['offset']) ? intval($_GET['offset']) : 0;

$name = isset($_GET['name']) ? htmlspecialchars($_GET['name']) : null;
$type = isset($_GET['type']) ? htmlspecialchars($_GET['type']) : null;
$collection = isset($_GET['collection']) ? htmlspecialchars($_GET['collection']) : null;
$series = isset($_GET['series']) ? htmlspecialchars($_GET['series']) : null;
$brand = isset($_GET['brand']) ? htmlspecialchars($_GET['brand']) : null;
$order = isset($_GET['order']) ? htmlspecialchars($_GET['order']) : null;
$dir = isset($_GET['dir']) ? htmlspecialchars($_GET['dir']) : null;


$items = searchItem($offset, $limit, $order, $dir, $name, $type, $collection, $series, $brand);
if( $items ){

    $json = json_encode($items);
    header('Content-Type: application/json');
    echo $json;

}else {
    http_response_code(500);
}