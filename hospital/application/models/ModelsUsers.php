<?php
class ModelsUsers extends CI_Model{
    public function __construct(){
        $this->load->database();
    }
    public function save($user,$user_info){
        $this->db->trans_start();
            $this->db->insert('usuarios',$user); 
            $user_info['id_usuario'] = $this->db->insert_id();   
            $this->db->insert('medicos',$user_info);
        $this->db->trans_complete();
        return !$this->db->trans_status() ? false : true;
    }
    public function getUsers(){
        $sql = $this->db->order_by('id','DESC')->get('usuarios'); 
        return $sql->result();
    }
    public function getPaginate($limit,$offset){
        $sql = $this->db->order_by('id','DESC')->get('usuarios',$limit,$offset);
        return $sql->result();
    }
    public function updateUser($id,$data){
        $this->db->where('id',$id);
        $this->db->update('medicos',$data);
    }
    public function getUser($id){
        // SELECT *
        // FROM usuarios 
        // JOIN medicos 
        //     ON usuarios.id = medicos.id_usuario
        // WHERE usuarios.id = $id LIMIT 1
        $this->db->join('medicos','usuarios.id = medicos.id_usuario');
        $user = $this->db->get_where('usuarios',array('usuarios.id' => $id),1);
        return $user->row_array();
    }
    public function deleteUser($id){
        $this->db->where('id',$id);
        $this->db->delete('usuarios');
    }
}