<?php
session_start();
require './admin/lib/php/admin_liste_include.php';
$cnx=Connexion::getInstance($dsn, $user, $pass);
?>

<html>  
    <head>
        <title>Jenn's pizza</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <script src="./admin/lib/js/jquery.editable.js"></script>
		<script src="admin/lib/js/fonctionsJqueryDA.js"></script>
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.2/css/all.css" integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr" crossorigin="anonymous">
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
        <link rel="stylesheet" type="text/css" href="lib/css/bootstrap.min.css"/>
        <link rel="stylesheet" type="text/css" href="lib/css/Projet2019.css"/>
        
    </head>
    <body> 
        <header>
           
                <?php
                if (file_exists('lib/php/Menu.php')) {
                    include ('lib/php/Menu.php');
                } 
                ?>               
           
        </header>
       
        
       <section>
            <div class="page" id="main">
               
<?php
	 
                if (!isset($_SESSION['page'])) {
                    $_SESSION['page'] = "accueil.php";
                }
                if (isset($_GET['page'])) {
                    $_SESSION['page'] = $_GET['page'];
                }
                $path = "./pages/" . $_SESSION['page'];
                if (file_exists($path)) {
                    include ($path);
                } else {
                    include ('./admin/pages/page404.php');
                }
                
                
?>
            </div>
        </section>
        
        
        
       
        
        
        
        
    
        <footer class="page-footer font-small">

          <div class="footer-copyright text-center py-3">© 2019 Copyright:
            <a href="http://localhost/Projet_pizza/"> Jenn's pizza</a>
          </div>


        </footer>


    </body>
</html>