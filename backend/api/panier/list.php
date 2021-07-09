<?php

require_once __DIR__ . '/../../dao/panier.php';
require_once __DIR__ . '/../../utils/server.php';

ensureHttpMethod('GET');


$idUser = isset( $_GET['idUser'] ) ? htmlspecialchars( $_GET['idUser'] ) : null;



if( !$idUser ) {
    http_response_code(400);
    die();
}

$panier = getPanierFromUser($idUser);
if( $panier !== null ){
    
    header('Content-Type: application/json');
    echo json_encode($panier );

    http_response_code(200);
    die();
}


http_response_code(500);


