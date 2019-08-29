
<?php


class UtilisateurDB extends Utilisateur {
    
    private $_db;
   

    public function __construct($db) {
        $this->_db = $db;
    }

    public function ajoutUtilisateur($pseudo,$e_mail,$mdp) {

        try {
            $query = "insert into utilisateurs(pseudo,e_mail,mdp) values (:pseudo,:e_mail,:mdp)";
            $resultset = $this->_db->prepare($query);
            $resultset->bindValue(':pseudo',$pseudo);
            $resultset->bindValue(':e_mail',$e_mail);
            $resultset->bindValue(':mdp',$mdp);
            $resultset->execute();
            return 1;
            
        } catch (PDOException $e) {
            print "Données non encodées";
            print $e->getMessage();
        }
    }
}
?>