 <!-- BARRA DE NAVEGACION -->
    <nav class="navbar navbar-expand-lg navbar-light bg-light sticky-top" style="background-color: #e3f2fd;">
        <span class="navbar-text navbar-brand">
            Desarrollo Sistema Hospital.
        </span>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
        aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">
            </ul>
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link disabled" href="#"><strong>Rango:</strong> <?= $this->session->rango ?></a>
                </li>
                <li class="nav-item divider">
                    <a class="nav-link disabled" href="#"><strong>Nombre de Usuario:</strong> <?php echo $this->session->nombre_usuario ?></a>
                </li>
                <li class="nav-item" style="margin-left: 2em;">
                    <!-- <button class="btn btn-warning" type="submit" id="logout">Cerrar Sesión</button> -->
                    <a href="<?=base_url('login/logout')?>" class="btn btn-warning">Cerrar Sesión</a>
                </li>
            </ul>
        </div>
    </nav>