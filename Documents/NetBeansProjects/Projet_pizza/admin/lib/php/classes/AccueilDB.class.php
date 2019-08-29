<?php

class AccueilDB extends Accueil {
    private $_db;
    private $_array = array();

    public function __construct($db){
        $this->_db = $db;
    }

    public function getTexteAccueil(){
        try{
            $query = "select * from pizza";
            $resultset = $this->_db->prepare($query);
            $resultset->execute();

            while($data = $resultset->fetch()){
                $_array[] = new Accueil($data);
            }
        }
        catch(PDOException $e){
            print $e->getMessage();
        }
        if(!empty($_array)){
            return $_array;
        }
        else {
            return null;
        }
    }
}
