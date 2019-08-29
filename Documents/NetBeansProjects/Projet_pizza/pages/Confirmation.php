<?php
    if(isset($_GET['submit'])){
        print "<meta http-equiv=\"refresh\": Content=\"0;URL=../index.php?page=accueil.php\">";
    }
?>
<h2>votre commande a bien été enregistrée dans notre base de données.</h2>

<form action="<?php print $_SERVER['PHP_SELF']?>" method="get">
    <br>
    <input class="acheter" type="submit" name="submit" value="Retour à la page d'accueil">
</form>

