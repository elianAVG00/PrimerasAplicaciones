<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class Migration_create_consultas extends CI_Migration {

    public function up(){
        $this->dbforge->add_field(array(
            'id' => array(
                'type' => 'INT',
                'constraint' => 12,
                'unsigned' => TRUE,
                'auto_increment' => TRUE,
                    // 'unique' => TRUE
            ),
            'fecha' => array(
                'type' => 'DATE',
                'null' => TRUE
            ),
            'id_paciente' => array(
                'type' => 'INT',
                'constraint' => 12,
                'unsigned' => TRUE,
            ),
            'id_medico' => array(
                'type' => 'INT',
                'constraint' => 12,
                'unsigned' => TRUE,
            ),
            'id_recetas' => array(
                'type' => 'INT',
                'constraint' => 12,
                'unsigned' => TRUE,
            ),
        ));
        $this->dbforge->add_key('id', TRUE);
        // $this->dbforge->add_foreign_key();
        $this->dbforge->create_table('CONSULTAS');
    }

    public function down(){
        $this->dbforge->drop_table('CONSULTAS');
    }
}