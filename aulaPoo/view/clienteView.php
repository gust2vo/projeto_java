<?php
namespace App\view;

use App\model\Cliente;

class ClienteView{

    public static function formulario($msg = null, $cliente = null){
        if (isset($msg)): ?>
        <div class="sucesso">
            <?= $msg ?>
            <span class="close" onclick="this.parentElement.style.display='none'">&times;</span>        
        </div>
        <?php endif; ?>
            <form action="<?php isset($cliente)? "?p=alt": "?p=cad" ?>"method="post">
            <?php
            if (isset($cliente)) {
                echo '<label for="id">id:</label><input type="text" name="id id="id" value="'. $cliente->__get("id").'" readonly>';
            }
            ?>
            <label for="nome">Nome: </label>
            <input type="text" name="nome" id="nome" value="<?= $cliente->__get("nome") ?? "" ?>">

            <label for="sobrenome">Sobrenome:</label>
            <input type="text" name="sobrenome" id="sobrenome" value="<?= $cliente->__get("sobrenome") ?? "" ?>">

            <label for="ddd">DDD:</label>
            <input type="number" name="ddd" id="ddd" min="0" max="99" value="<?= $cliente->__get("ddd") ?? "" ?>">

            <label for="telefone">Telefone:</label>
            <input type="text" name="telefone" id="telefone" maxlength="10" value="<?= $cliente->__get("telefone") ?? "" ?>">

            <button type="submit"><?= isset($cliente)? "confirmar" : "salvar"?></button>
            </form>
        <?php
    }

    public static function listar($clientes, $id = null){
        if(isset($id)){
          ?>
          <div class="alert">
            Você deseja realmente deletar
            <a href="?p=deletar&deletar=<?= $id?>">Confirmar</a>
            <a href="?p=list">Cancelar</a>
            <span class="close" onclick="this.parentElement.style.display='none';">&times;</span>
          </div>  
    <?php
        }
    ?>
    <table>
        <tr>
            <th>Id</th>
            <th>Nome</th>
            <th>Sobrenome</th>
            <th>DDD</th>
            <th>Telefone</th>
            <th>Alterar</th>
            <th>Excluir</th>
        </tr>
        <?php foreach($clientes as $cliente): ?>
        <tr>
            <td><?= $cliente->__get("id") ?></td>
            <td><?= $cliente->__get("nome") ?></td>
            <td><?= $cliente->__get("sobrenome") ?></td>
            <td><?= $cliente->__get("ddd") ?></td>
            <td><?= $cliente->__get("telefone") ?></td>
            <td><a href="?p=alt&alt=<?= $cliente->__get("id") ?>">Alterar</a></td>
            <td><a href="?p=deletar&del=<?= $cliente->__get("id") ?>">Excluir</a></td>
        </tr>
        <?php endforeach; ?>
    </table>



    <?php
    }

}