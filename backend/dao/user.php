<?php

require_once  __DIR__ . '/../utils/database.php';

function getUserById(string $id): ?array {
    $connection = getDatabaseConnection();
    $sql = "SELECT idUser, email FROM user WHERE idUser = ?";
    $params = [$id];
    return databaseFindOne($connection, $sql, $params);
}

function emailExist( string $email ): ?array {

    $conn = getDatabaseConnection();
    $sql = "SELECT idUser FROM user WHERE email=?";
    $params = [ $email ];

    return databaseFindOne( $conn , $sql , $params );
}

function userInsert(string $firstname , string $lastname , string $email , string $password ): ?string
{

    $conn = getDatabaseConnection();
    $sql = "INSERT INTO user( firstname , lastname , email , password) VALUES(?,?,?,?)";
    $params = [ $firstname, $lastname, $email, hash('sha512', $password) ];

    return databaseInsert($conn, $sql, $params);


}

function checkLogin( string $email, string $password): ?array {
    $conn = getDatabaseConnection();
    $sql = "SELECT idUser FROM user WHERE email=? AND password=?";
    $params = [ $email, hash('sha512' , $password) ];

    return databaseFindOne($conn, $sql, $params);
}
