<?php
namespace App\dal;
use App\model\Cliente;
use \Exception;
use \PDOException;
use \PDO;

abstract class ClienteDao{

    public static function cadastrar(Cliente $cliente){
        try{
            $pdo = Conn::getConn();
            $sql = $pdo->prepare("INSERT INTO clientes VALUES (null, ?, ?, ?, ?)");
            $sql-> execute([$cliente->__get("nome"), $cliente->__get("sobrenome"),$cliente->__get("ddd"), $cliente->__get("telefone")]);

        }catch(PDOException $e){
            throw new Exception("Erro ao salvar no banco de dados". $e->getMessage(), 14);
            
        }catch(Exception $e){
            throw new Exception("Ocorreu um erro inesperado " . $e->getMessage() . $e->getCode());
        }
    }

    public static function listar(){
        try{
            $pdo = Conn::getConn();
            $sql = $pdo->prepare("SELECT * FROM clientes");
            $sql-> execute();

            return $sql->fetchAll(PDO::FETCH_CLASS, Cliente::class);
        }catch(Exception $e){
            throw new Exception("Ocorreu um erro inesperado " . $e->getMessage() . $e->getCode());
        }
    }

    public static function buscarPorId(int $id){
        try {
            $pdo = Conn::getConn();
            $sql = $pdo->prepare("SELECT * FROM clientes WHERE id=?");
            $sql->execute(array($id));

            return $sql->fetchObject(Cliente::class);

        } catch (PDOException $e) {
            throw new Exception("Ocorreu um erro inesperado " . $e->getMessage() . $e->getCode());
        }
    }

    public static function editar(Cliente $cliente){
        try {
            $pdo = Conn::getConn();
            $sql = $pdo->prepare("UPDATE clientes SET nome=?, sobrenome=?, ddd=?, telefone=? WHERE id=?");
            $sql->execute(array(
                $cliente->__get("nome"),
                $cliente->__get("sobrenome"),
                $cliente->__get("ddd"),
                $cliente->__get("telefone"),
                $cliente->__get("id"),
            ));
            if ($sql->rowCount() !== 1) {
                throw new Exception("erro ao alterar", 1);
            }

        } catch (PDOException $e) {
            throw new Exception("Ocorreu um erro inesperado " . $e->getMessage() . $e->getCode());
        }
    }

    public static function excluir(int $id){
        try {
            $pdo = Conn::getConn();
            $sql = $pdo->prepare("DELETE FROM clientes WHERE id=?");
            $sql->execute(array($id));

        } catch (PDOException $e) {
            throw new Exception("Ocorreu um erro inesperado " . $e->getMessage() . $e->getCode());
        }
    }
}