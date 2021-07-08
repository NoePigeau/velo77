<?php

require_once __DIR__ . '/../../dao/panier.php';
require_once __DIR__ . '/../../utils/server.php';

ensureHttpMethod('POST');

$content = file_get_contents('php://input');
$_POST = json_decode( $content, true);

$idUser= isset( $_POST['idUser'] ) ? intval( $_POST['idUser'] ) : null;
$idItem = isset( $_POST['idItem']) ? intval( $_POST['idItem'] ) : null;



if( !($idUser && $idItem) ){
    http_response_code(400);
    die();
}



if( getPanier($idUser, $idItem) === null ){


    if( insertPanier($idUser, $idItem) ){

        http_response_code(201);
        die();

    }
    
    
}else {


    if(updatePanier($idUser, $idItem)){
        http_response_code(200);
        die();

    }
    

}

http_response_code(500);

