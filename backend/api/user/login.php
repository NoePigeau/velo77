<?php

require_once __DIR__ . '/../../utils/server.php';
require_once __DIR__ . '/../../dao/session.php';
require_once __DIR__ . '/../../dao/user.php';

ensureHttpMethod('POST');

$content = file_get_contents('php://input');
$_POST = json_decode($content, true);

$email = isset( $_POST['email']) ? htmlspecialchars( $_POST['email']) : null;
$password = isset( $_POST['password']) ? $_POST['password'] : null;

if($email && $password){

    $idUser = checkLogin( $email, $password);
    if($idUser === null){
        http_response_code(404);
        die();
    }

    $token = sessionInsert( $idUser['idUser'] );
    header("Content-Type: application/json");
    echo json_encode([
        "token" => $token
    ]);

}else http_response_code(400);