<?php
namespace App;
require_once("./autoload.php");
use App\controller\ClienteController as cliente;
?>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Página POO </title>
    <link rel="stylesheet" href="./assets/style.css">
</head>
<body>
    <header>
        <nav>
            <?php require_once("./menu.php"); ?>
        </nav>
    </header>
    <main>
    <?php
    $page = $_GET["p"] ?? "home";
    match($page){
        "home" => require_once("./view/home.php"),
        "cad" => cliente::cadastrar(),
        "list" => cliente::listar(),
        default => require_once("./view/404.php")
    };
    ?>
    </main>
    <footer>
        <small>
            Copyright &copy; - <?= date("Y")?>
        </small>
    </footer>
</body>
</html>
