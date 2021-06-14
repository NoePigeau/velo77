<?php

require_once __DIR__ . '/../utils/database.php';


function itemInsert(string $name, string $description, float $price, string $size, string $collection, string $series, string $type, string $brand): ?string {
    $conn = getDatabaseConnection();
    $sql = "INSERT INTO item(name, description, price, size, collection, gamme, type, brand) VALUES (?,?,?,?,?,?,?,?)";
    $params = [ $name, $description, $price, $size, $collection, $series, $type, $brand ];

    return databaseInsert($conn, $sql, $params);

}

function searchItem(int $offset, int $limit, ?string $order, ?string $dir,
                    ?string $name, ?string $type, ?string $collection, ?string $series, ?string $brand): ?array {

    $connection = getDatabaseConnection();
    $where = [];
    $params = [];
    $sql = "SELECT * FROM item";

    if( $name ) {
        array_push($where , "name LIKE ?" );
        array_push($params, "%" . $name . "%" );
    }

    if( $series ){
        array_push($where , "gamme LIKE ?" );
        array_push($params, "%" . $series . "%" );

    }

    if( $brand ){
        array_push($where , "brand LIKE ?" );
        array_push($params, "%" . $brand . "%" );

    }

    if( checkType($type) ) {
        array_push($where , "type = ?" );
        array_push($params, $type);

    }

    if( checkCollection($collection) ) {
        array_push($where , "collection = ?" );
        array_push($params, $collection);

    }






    if(count($where) > 0) $sql .= " WHERE " . join(" AND ", $where);
    if( checkOrder($order) )  $sql.= " ORDER by " . $order . " " . $dir;
    $sql .= " LIMIT $offset, $limit";

    return databaseFindAll($connection, $sql, $params);

}

function checkType( ?string $type): bool {
    $types = [ 'normal' , 'electric' , 'accessory' , 'part' ];

    if($type === null ) return false;

    for( $i = 0 ; $i < count($types) ; $i++ )
        if($types[$i] == $type ) return true;

    return false;
}

function checkOrder( ?string $order): bool {
    $orders = [ 'name' , 'price' , 'brand' , 'gamme' ];

    if($order === null ) return false;

    for( $i = 0 ; $i < count($orders) ; $i++ )
        if($orders[$i] == $order ) return true;

    return false;
}

function checkCollection( ?string $collection): bool {
    $collections = [ 'men' , 'women' , 'child' ];

    if($collection === null ) return false;

    for( $i = 0 ; $i < count($collections) ; $i++ )
        if($collections[$i] == $collection ) return true;

    return false;
}


function getItemById(string $id): ?array {
    $connection = getDatabaseConnection();
    $sql = "SELECT id,name FROM item WHERE id = ?";
    $params = [$id];
    return databaseFindOne($connection, $sql, $params);
}

function getItemByName(string $name): ?array {
    $connection = getDatabaseConnection();
    $sql = "SELECT id,name FROM item WHERE name = ?";
    $params = [$name];
    return databaseFindOne($connection, $sql, $params);
}