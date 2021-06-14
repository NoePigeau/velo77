<?php

require_once __DIR__ . '/../../utils/server.php';
require_once __DIR__ . '/../../dao/user.php';

ensureHttpMethod('POST');

$content = file_get_contents('php://input');
$_POST = json_decode($content , true);

$firstname = isset( $_POST['firstname'] ) ? htmlspecialchars( $_POST['firstname'] ): null;
$lastname = isset( $_POST['lastname'] ) ? htmlspecialchars( $_POST['lastname'] ): null;
$email = isset($_POST['email']) ? htmlspecialchars( $_POST['email'] ) : null;
$pwd = isset( $_POST['password']) ? $_POST['password'] : null;


if( $firstname && $lastname && $email && $pwd ){

    if( emailExist( $email ) !== null ){
        http_response_code(409);
        die();
    }

    $idUser = userInsert( $firstname , $lastname , $email , $pwd);
    if($idUser){
        http_response_code(201); // CREATED
    }else{
        http_response_code(500);
    }

}else http_response_code(400);