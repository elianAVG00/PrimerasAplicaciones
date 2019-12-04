<form action="<?= base_url('users/store') ?>" method="POST">
	<h3>Datos de la Cuenta</h3>
	<hr>
	<div class="form-group">
        <div class="form-row">
        		<div class="col-7">
        			<label for="">Nombre Usuario</label>
        			<input type="text" name="user" class="form-control" placeholder="Inserte nombre de usuario" value="<?= set_value('user') ?>">
					<div class="text-danger"><?= form_error('user') ?></div>
        		</div>
        		<div class="col">
        			<label for="">Correo</label>
        			<input type="text" name="correo" class="form-control" placeholder="correo@mail.com" value="<?= set_value('correo') ?>">
					<div class="text-danger"><?= form_error('correo') ?></div>
        		</div>
        		<div class="col">
        			<label for="">Rango de Usuario</label>
        			<select name="range" class="custom-select">
        				<option selected value="">Seleccione el rango</option>
        				<option <?= set_value('range') == 'admin' ? 'selected' : ''; ?> value="admin">Administrador</option>
        				<option <?= set_value('range') == 'user' ? 'selected' : ''; ?> value="user">Usuario</option>
        			</select>
					<div class="text-danger"><?= form_error('range') ?></div>
        		</div>
        	</div>
        	<br>
        	<h3>Información del usuario</h3>
        	<hr>
        	<div class="form-row">
        		<div class="col-7">
        			<label for="">Nombre(s)</label>
        			<input name="name" class="form-control" type="text" placeholder="Inserte su nombre" value="<?= set_value('name') ?>">
					<div class="text-danger"><?= form_error('name') ?></div>
        		</div>
        		<div class="col">
        			<label for="">Apellidos</label>
        			<input name="lastname" class="form-control" type="text" placeholder="Inserten sus apellidos" value="<?= set_value('lastname') ?>">
					<div class="text-danger"><?= form_error('lastname') ?></div>
        		</div>
        		<div class="col">
        			<label for="">Area</label>
        			<select name="area" class="custom-select">
        				<option selected value="">Seleccione el Area</option>
        				<option <?= set_value('area') == 'Medicina general' ? 'selected' : ''; ?> value="Medicina general">Medicina general</option>
        				<option <?= set_value('area') == 'Genetica' ? 'selected' : ''; ?> value="Genetica">Genetica</option>
        				<option <?= set_value('area') == 'Clinica del higado' ? 'selected' : ''; ?> value="Clinica del higado">Clinica del higado</option>
        			</select>
					<div class="text-danger"><?= form_error('area') ?></div>
        		</div>
        	</div>
    </div>
    <div class="form-group">
    	<div class="form-row">
    		<div class="col-7">
    			<label for="">Especialidad</label>
    			<input name="especialidad" type="text" class="form-control" placeholder="Ingrese la especialidad" value="<?= set_value('especialidad') ?>">
				<div class="text-danger"><?= form_error('especialidad') ?></div>
				
    		</div>
    		<div class="col-5">
    			<label for="">Cédula</label>
				<input name="cedula" type="text" class="form-control" placeholder="XXXXXXXXX-X" value="<?= set_value('cedula') ?>">
				<div class="text-danger"><?= form_error('cedula') ?></div>
			</div>
    	</div>
    </div>
    <div class="form-group">
        <input type="submit" class="btn btn-primary" value="Dar de Alta Usuario">
    </div>
</form>
