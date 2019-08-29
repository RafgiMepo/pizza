<?php


  class pizzaDB extends pizza{
    private $_db;
    private $_array = array();

    public function __construct($db){
        $this->_db = $db;
    }
    
    public function getGarniture($num) {
      try{
          $query="select garniture from pizza where id_pizza = :id";
          $resultset=$this->_db->prepare($query);
          $resultset->bindValue(':id', $num);
          $resultset->execute();
          $g=$resultset->fetch()[0];   
      } catch (Exception $ex) {
         print $ex->getMessage();
      }
      return $g;
    }
    
    public function getPrix($num) {
      try{
          $query="select prix_unitaire from pizza where id_pizza = :id";
          $resultset=$this->_db->prepare($query);
          $resultset->bindValue(':id', $num);
          $resultset->execute();
          $g=$resultset->fetch()[0];   
      } catch (Exception $ex) {
         print $ex->getMessage();
      }
      return $g;
    }
    
    public function getNom($num) {
      try{
          $query="select nom_pizza from pizza where id_pizza = :id";
          $resultset=$this->_db->prepare($query);
          $resultset->bindValue(':id', $num);
          $resultset->execute();
          $g=$resultset->fetch()[0];   
      } catch (Exception $ex) {
         print $ex->getMessage();
      }
      return $g;
    }
    
}
