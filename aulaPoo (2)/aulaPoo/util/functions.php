<?php
namespace App\util;

class Functions{
    public static function prepararTexto($texto){
        return trim(htmlentities($texto));
    }
}