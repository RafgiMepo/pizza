<?php
    if(file_exists('./admin/lib/php/PgConnect.php')){
        require './admin/lib/php/PgConnect.php';
        require './admin/lib/php/Autoload.php';
    }
    else if(file_exists('./lib/php/PgConnect.php')){
        require './lib/php/PgConnect.php';
        require './lib/php/Autoload.php';       
    }

