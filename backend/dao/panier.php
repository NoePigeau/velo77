<?php

require_once __DIR__ . '/../utils/database.php';

function getPanier(int $idUser, int $idItem): ?array{

    $conn = getDatabaseConnection();
    $sql = "SELECT idPanier FROM panier WHERE idUser = ? AND idItem = ?";
    $params = [$idUser, $idItem];

    return databaseFindOne($conn, $sql, $params);

}

function insertPanier(int $idUser, int $idItem): ?string {


    $conn = getDatabaseConnection();
    $sql = "INSERT INTO panier(idUser, idItem, nbItem) VALUES(?,?,1)";
    $params = [$idUser, $idItem];

    return databaseInsert($conn, $sql, $params);

}

function updatePanier(int $idUser, int $idItem): ?string {


    $conn = getDatabaseConnection();
    $sql = "UPDATE panier SET nbItem = nbItem + 1 WHERE idUSer = ? AND idItem = ?";
    $params = [$idUser, $idItem];

    return databaseExec($conn, $sql, $params);

}


function getPanierFromUser(int $idUser) {

    $conn = getDatabaseConnection();
    $sql = "SELECT * FROM panier INNER JOIN item ON panier.idItem = item.id  WHERE panier.idUser = ?";
    $params = [$idUser];

    return databaseFindAll($conn, $sql, $params);

}

function deletePanier( int $id): ?int {

    $conn = getDatabaseConnection();
    $sql = "DELETE FROM panier WHERE idPanier = ?";
    $params = [$id];

    return databaseExec($conn, $sql, $params);


}