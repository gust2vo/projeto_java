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

    public static function listar(){
        $clientes = ClienteDao::listar();

        ClienteView::listar($clientes);
    }

    public static function editar(){
        if (isset($_GET["alt"])) {
            $cliente = ClienteDao::buscarPorId((int)$_GET["alt"]);
        }

        ClienteView::formulario(self::$msg, $cliente);// msg passa com self por que é um metodo estatico
    }
}