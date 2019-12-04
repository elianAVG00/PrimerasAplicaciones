<?php

if(!function_exists('getLoginRules')){
    function getLoginRules(){
        return array(
            array(
                'field' => 'email',
                'label' => 'Correo Electrónico',
                'rules' => 'required|valid_email',
                'errors' => array(
                    'required' => 'El %s es requerido.',
                    'valid_email' => 'El formato de %s es invalido.'
                )
            ),
            array(
                'field' => 'password',
                'label' => 'Contraseña',
                'rules' => 'required',
                'errors' => array(
                    'required' => 'El %s es requerido.',
                )
            ),
          
            
        );
    }
}