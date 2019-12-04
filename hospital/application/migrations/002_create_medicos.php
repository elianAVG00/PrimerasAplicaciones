<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class Migration_create_medicos extends CI_Migration {

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
            'area' => array(
                'type' => 'VARCHAR',
                'constraint' => '150',
                'null' => TRUE
            ),
            'cedula' => array(
                'type' => 'VARCHAR',
                'constraint' => '100',
                'null' => TRUE
            ),
            'especialidad' => array(
                'type' => 'VARCHAR',
                'constraint' => '150',
                'null' => TRUE
            ),
            'id_usuario' => array(
                'type' => 'INT',
                'constraint' => 12,
                'unsigned' => TRUE,
            )
            
                
        ));
        $this->dbforge->add_key('id', TRUE);
        // $this->dbforge->add_foreign_key();
        $this->dbforge->create_table('MEDICOS');
    }

    public function down(){

        $this->dbforge->drop_table('MEDICOS');

    }
}