<?php
namespace App\controller;

use App\util\Functions as Util;
use App\model\Cliente;
use App\dal\ClienteDao;
use App\view\ClienteView;
use \Exception;


abstract class ClienteController{
    private static $msg = null;

    public static function cadastrar(){
        if ($_SERVER["REQUEST_METHOD"] == "POST" AND
        isset($_POST["nome"])) {
            [$nome, $sobrenome, $ddd, $telefone] = array_map([Util::class, 'prepararTexto'], array_values($_POST));

            /* Validações */

            $cliente = new Cliente();
            $cliente-> iniciar(sobrenome:$sobrenome, ddd:$ddd, nome:$nome, telefone:$telefone);

            try{
                ClienteDao::cadastrar($cliente);
            }catch(Exception $e){
                self::$msg = $e->getMessage();
            }
            
        }
        ClienteView::formulario(self::$msg);
    }

    public static function listar(?int $id=null){
        $clientes = ClienteDao::listar();
        echo "<pre>";
        var_dump($_SESSION);
        echo "</pre>";
        ClienteView::listar($clientes, $id);
    }

    public static function editar(){
        if (isset($_GET["alt"])) {
            $cliente = ClienteDao::buscarPorId((int)$_GET["alt"]);
        }
        if ($_SERVER["REQUEST_METHOD"] == "POST" AND
        isset($_POST["nome"])) {
            [$id, $nome, $sobrenome, $ddd, $telefone] = array_map(fn($valor) => Util::prepararTexto($valor), array_values($_POST));//Util::class é o mesmo que um arrow function

            try{
            if ($nome == "") {
                throw new Exception("Preencha o nome", 123);
            }
            $cliente = new Cliente();
            $cliente = Cliente::pegarCliente($id, $nome, $sobrenome, $ddd, $telefone);
            
            ClienteDao::editar($cliente);
            header("location: ?p=cad");
            }catch(Exception $e){
                self::$msg = $e->getMessage() . "Cod" . $e->getCode();
            }
        }
        ClienteView::formulario(self::$msg, $cliente);// msg passa com self por que é um metodo estatico
    }

    public static function deletar(){
        if (isset($_GET["del"])) {
            self::listar((int)$_GET["del"]);
        }
        if (isset($_GET["deletar"])) {
            ClienteDao::excluir((int)$_GET["deletar"]);
            header("location: ?p=list");
        }
    }
}