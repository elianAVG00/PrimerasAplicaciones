<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
	<title>Hospital</title>
</head>
<body>
	<div class="container">
		<div class="row justify-content-lg-center d-flex align-items-center" style="height: 70vh">
			<div class="col-lg-6">
				<form id="frm-login">
                    <div class="form-group">
                        <h1>Login</h1>
                    </div>
					<div class="form-group" id="email">
						<label for="exampleInputEmail1">Correo</label>
						<input type="email" name="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Escriba su email">
						<div class="invalid-feedback"></div>
					</div>
					<div class="form-group" id="password">
						<label for="exampleInputPassword1">Contraseña</label>
						<input type="password" name="password" class="form-control" id="exampleInputPassword1" placeholder="Ingrese su contraseña">  
						<div class="invalid-feedback"></div>
					</div>
					<div class="form-group">
						<button type="submit" class="btn btn-primary">Ingresar</button>
					</div>
					<div class="form-group" id="alert">

					</div>
					
				</form>
			</div>
		</div>
	</div>
</body>
<script src="https://code.jquery.com/jquery-3.3.1.js" integrity="sha256-2Kok7MbOyxpgUVvAk/HJ2jigOSYS2auK4Pfzbm7uH60=" crossorigin="anonymous"></script>
<!-- <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js" integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T" crossorigin="anonymous"></script> -->
<script src="<?=base_url('assets/js/auth/login.js')?>"></script>
</html>
