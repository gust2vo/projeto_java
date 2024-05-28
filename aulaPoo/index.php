<?php
namespace App;
$token = bin2hex(random_bytes(32));

$options = [
    'name' => "_secure",
    'cookie_httponly' => true,
    'cookie_domain' => "meu dominio",

];
session_start($options);
session_regenerate_id();
$_SESSION["teste"] = "Meu teste";
echo "<pre>";
var_dump($_SESSION);
echo "</pre>";

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
        "alt" => cliente::editar(),
        "deletar" => cliente::deletar(),
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
