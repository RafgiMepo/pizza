<?php

class Connexion {
    private static $_instance = null;

    public static function getInstance($dsn, $user, $pass) {
        // :: = appel à une var ou fct statique  
        //self:: référence la classe, tandis que $this référence l'objet instancié
        //ici, obligation d'utiliser self car classe statique sans instanciation
        if (!self::$_instance) {
            try {
                self::$_instance = new PDO($dsn, $user, $pass);
                //spécifie la manière dont PDO rapportera les erreurs : on demande ici une exception 
                //(plutôt qu'un warning PDO::ERRMODE_WARNING)
                self::$_instance->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
                //print "Connect&eacute; gt_bt_jq_mysql";
                
            } catch (PDOException $e) {
                print "Erreur de connexion : ".$e->getMessage()." ".$e->getLine();
                //print "pass : ".$pass. " user = ".$user;
            }
        }
        return self::$_instance;
    }
}
