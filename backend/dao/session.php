<?php

require_once __DIR__ . '/../utils/database.php';

function sessionInsert(int $idUser ): ?string {

    $tokenBytes = random_bytes(32);
    $token = bin2hex($tokenBytes);

    $date = new DateTime();
    $expirationDate = $date->add(new DateInterval("P15D"))->format("Y-m-d");

    $conn = getDatabaseConnection();
    $sql = "INSERT INTO session(token, expirationDate, idUser) VALUES(?,?,?)";
    $params = [$token, $expirationDate, $idUser ];

    databaseInsert($conn, $sql, $params);
    return $token;
}

function sessionWithToken(string $token): ?array {
    $connection = getDatabaseConnection();
    $sql = "SELECT token, idUser, expirationDate FROM session WHERE token = ? AND expirationDate > NOW()";
    $params = [
        $token
    ];
    return databaseFindOne($connection, $sql, $params);
}