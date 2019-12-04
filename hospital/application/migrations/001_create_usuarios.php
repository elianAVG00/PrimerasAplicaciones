<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class Migration_create_usuarios extends CI_Migration {

    public function up(){
        
        $this->dbforge->add_field(array(
            'id' => array(
                'type' => 'INT',
                'constraint' => 12,
                'unsigned' => TRUE,
                'auto_increment' => TRUE,
                    // 'unique' => TRUE
            ),
            'nombre_usuario' => array(
                'type' => 'VARCHAR',
                'constraint' => '100',
                'null' => TRUE
            ),
            'correo' => array(
                'type' => 'VARCHAR',
                'constraint' => '100',
                'null' => TRUE
            ),
            'contrasena' => array(
                'type' => 'VARCHAR',
                'constraint' => '100',
                'null' => TRUE
            ),
            'status' => array(
                'type' => 'TINYINT',
                'constraint' => '1',
                'null' => TRUE
            ),
            'rango' => array(
                'type' => 'ENUM("admin","user")',
                'null' => TRUE
            ),
                
        ));
        $this->dbforge->add_key('id', TRUE);
        $this->dbforge->create_table('USUARIOS');
    }

    public function down(){

        $this->dbforge->drop_table('USUARIOS');

    }
}