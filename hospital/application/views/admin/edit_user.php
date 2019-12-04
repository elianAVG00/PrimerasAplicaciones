<h1 class="text-center">Editando Usuario</h1>
<?php
// echo '<pre>';
// var_dump($user);
// echo '</pre>';
?>

<div class="container-fluid">
    <h4>Datos de la cuenta</h4>
    <form method="POST" action="<?= base_url('users/update') ?>">
    <div class="card">
        <div class="card-body">
            <div class="form-row">
                <div class="form-group col-md-4">
                    <label>Nombre de usuario</label>
                    <input type="text" name="username" value="<?= set_value('username', isset($user['nombre_usuario']) ? $user['nombre_usuario'] : '') ?>" class="form-control" placeholder="" readonly>
                    <input type="hidden" value="<?=set_value('_id',isset($user['id']) ? $user['id'] : '')?>" name="_id">
                </div>
                <div class="form-group col-md-4">
                    <label>Email</label>
                    <input type="email" name="email" value="<?=set_value('email',isset($user['correo']) ? $user['nombre_usuario'] : '')?>" class="form-control" placeholder="" readonly>
                </div>
                <div class="form-group col-md-4">
                    <label>Status</label>
                    <input type="text" name="status" value="<?=set_value('status',isset($user['status']) ? $user['status'] : '')?>"  class="form-control" placeholder="" readonly>
                </div>
            </div>
        </div>
    </div>
    <br>
    <h4>Información personal</h4>
    <div class="card">
        <div class="card-body">
            <div class="form-row">
                <div class="form-group col-md-4">
                    <label>Nombre(s)</label>
                    <input type="text" name="nombre" value="<?=set_value('nombre',isset($user['nombre']) ? $user['nombre']  : '')?>" class="form-control" placeholder="">
                    <?= form_error('nombre','<p class="text-danger">','</p>') ?>
                </div>      
                <div class="form-group col-md-4">
                    <label>Apellidos</label>
                    <input type="text" name="apellidos" value="<?=set_value('apellidos',isset($user['apellido']) ? $user['apellido']  : '')?>" class="form-control" placeholder="">
                    <?= form_error('apellidos','<p class="text-danger">','</p>') ?>
                </div>      
                <div class="form-group col-md-4">
                    <label>Cédula</label>
                    <input type="text" name="cedula" value="<?=set_value('cedula',isset($user['cedula']) ? $user['cedula']  : '')?>" class="form-control" placeholder="">
                    <?= form_error('cedula','<p class="text-danger">','</p>') ?>
                </div>      
            </div>
            <div class="form-row">
                <div class="form-group col-md-6">
                    <label>Especialidad</label>
                    <input type="text" name="especialidad" value="<?=set_value('especialidad',isset($user['especialidad']) ? $user['especialidad']  : '')?>" class="form-control" placeholder="">
                    <?= form_error('especialidad','<p class="text-danger">','</p>') ?>
                </div>      
                <div class="form-group col-md-6">
                    <label>Area</label>
                    <input type="text" name="area" value="<?=set_value('area',isset($user['area']) ? $user['area']  : '')?>" class="form-control" placeholder="">
                    <?= form_error('area','<p class="text-danger">','</p>') ?>
                </div>      
            </div>
        </div>
    </div>
    <br>
    <input type="submit" class="btn btn-primary btn-lg" value="Actualizar">
    </form>
</div>