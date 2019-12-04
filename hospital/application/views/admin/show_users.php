<?php if($msg = $this->session->flashdata('msg')): ?>
  <div class="alert alert-success text-center" role="alert">
    <?= $msg ?>
  </div>
<?php endif; ?>
<h1 class="text-center">Tabla de lista de usuarios registrados</h1>
<table class="table">
  <thead class="thead-dark">
    <tr>
      <th scope="col">id</th>
      <th scope="col">usuario</th>
      <th scope="col">correo</th>
      <th scope="col">status</th>
      <th scope="col">rango</th>
      <th scope="col">acciones</th>
    </tr>
  </thead>
  <tbody>
    <?php foreach($data as $item): ?>
    <tr>
      <th scope="row"><?= $item->id ?></th>
      <td><?= $item->nombre_usuario ?></td>
      <td><?= $item->correo ?></td>
      <td><?= $item->status == 1 ? 'activo' : 'inactivo' ?></td>
      <td><?= $item->rango ?></td>
      <td>
          <a class="btn btn-warning" href="<?=base_url('users/edit/'.$item->id)?>" role="button">Editar</a> / <a class="btn btn-danger" href="#" data-id="<?=$item->id?>" id="delete" role="button">Eliminar</a>
      </td>
    </tr>
    <?php endforeach; ?>
  </tbody>
</table>

<?= $this->pagination->create_links() ?>