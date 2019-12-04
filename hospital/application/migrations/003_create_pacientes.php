<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class Migration_create_pacientes extends CI_Migration {

    public function up(){
        
        $this->dbforge->add_field(array(
            'id' => array(
                'type' => 'INT',
                'constraint' => 12,
                'unsigned' => TRUE,
                'auto_increment' => TRUE,
                    // 'unique' => TRUE
            ),
            'nombre' => array(
                'type' => 'VARCHAR',
                'constraint' => '100',
                'null' => TRUE
            ),
            'apellido' => array(
                'type' => 'VARCHAR',
                'constraint' => '100',
                'null' => TRUE
            ),
            'fecha_nacimiento' => array(
                'type' => 'DATE',
                // 'constraint' => '10',
                'null' => TRUE
            ),
            'peso' => array(
                'type' => 'VARCHAR',
                'constraint' => '10',
                'null' => TRUE
            ),
            'sexo' => array(
                'type' => 'ENUM("masculino","femenino")',
                'null' => TRUE
            ),
            'alta_fecha' => array(
                'type' => 'TIMESTAMP',
                'null' => FALSE,
                'default' => 'CURRENT_TIMESTAMP',
                // 'on_create' => 'CURRENT_TIMESTAMP'
            ),
        ));
        $this->dbforge->add_key('id', TRUE);
        // $this->dbforge->add_foreign_key();
        $this->dbforge->create_table('PACIENTES');
    }

    public function down(){

        $this->dbforge->drop_table('PACIENTES');

    }
}