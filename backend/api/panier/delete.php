<?php

require_once __DIR__ . '/../../dao/panier.php';
require_once __DIR__ . '/../../utils/server.php';

ensureHttpMethod('GET');


$id = isset( $_GET['id'] ) ? intval( $_GET['id'] ) : null;



if( !$id ) {
    http_response_code(400);
    die();
}

$panier = deletePanier($id);
if( $panier !== null ){
    
    http_response_code(410);
    die();
}


http_response_code(500);


