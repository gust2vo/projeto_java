<?php
namespace App\model;

class Cliente{
    private $id, $nome, $sobrenome, $ddd, $telefone;

    public function __construct(){}

    public function iniciar($id = "", $nome = "", $sobrenome = "", $ddd = "", $telefone = ""){
        $this->id = $id;
        $this->nome = $nome;
        $this->sobrenome = $sobrenome;
        $this->ddd = $ddd;
        $this->telefone = $telefone;
    }

    public function __get($atributo){
        return $this->$atributo;
    }

    public function __set($atributo, $valor){
        $this->$atributo = $valor;
    }

}