<?php
if(empty($_SESSION['pseudoconnect']))
{ ?>
	<div class="container">
	<?php
	 echo"Pour passer une commande, veuillez vous connecter";
         header ("URL=index.php?page=./pages/connexion.php");
         exit(); 
         ?>
	</div>
<?php
}
?>

<?php
    if(isset($_GET['submit'])){
        $nom = $_GET['nom'];
        $prenom = $_GET['prenom'];
        $tel = $_GET['Ntel'];
        $adresse = $_GET['add'];
        $id_pizza = $_GET['id_pizza'];
        $nbr_pizza = $_GET['nbr_pizza'];
        if($nom == "" || $prenom == "" || $Ntel == "" || $adresse == "" || $id_pizza == "" || $nbr_pizza == ""){
            print "Tous les champs ne sont pas remplis !! ";
        }
        else{
            $cli = new CommandeDB($cnx);
            $rescli = $cli->ajoutClient($nom, $prenom, $tel, $adresse, $id_pizza,$nbr_pizza);
            $idcli = $cli->getId();
            $comm = new CommandeDB($cnx);
            $res = $comm->ajoutCommande($_SESSION['nom'], $_SESSION['prenom'], $_SESSION['Ntel'], $_SESSION['add'], $_SESSION['id_pizza'], $id_pizza, $_SESSION['nbr_pizza']);
            if(is_null($res)){
                print "Il y a un problème dans la commande";
            }      
        }
    }
?>

 
<legend> Faites votre commande ! </legend>


<form action="<?php print $_SERVER['PHP_SELF']; ?>" method="get" id="form_commande">
         <div class="form-group"> 
		<label for="full_name_id" class="control-label">Nom</label>
		<input type="text" class="form-control" id="full_name_id" name="nom" placeholder="Nom">
	</div>	
        <div class="form-group"> 
		<label for="full_name_id" class="control-label">Prénom</label>
		<input type="text" class="form-control" id="full_name_id" name="prenom" placeholder="prenom">
	</div>	
       <div class="form-group">
		<label for="full_name_id" class="control-label">N° téléphone</label>
		<input type="text" class="form-control" id="full_name_id" name="Ntel" placeholder="tel">
	</div>	

	<div class="form-group"> 
		<label for="full_name_id" class="control-label">N° pizza</label>
		<input type="text" class="form-control" id="full_name_id" name="id_pizza" placeholder="N° pizza">
	</div>	
            
        <select class="custom-select" size="3" name="nbrpizza">
            <option selected>Nombre de pizza</option>
            <option value="1">1</option>
            <option value="2">2</option>
            <option value="3">3</option>
        </select>


	<div class="form-group"> <!-- Street 1 -->
		<label for="street1_id" class="control-label">Addresse de livraison </label>
		<input type="text" class="form-control" id="street1_id" name="street1" placeholder="Street address, P.O. box, company name, c/o">
	</div>					
		
      <?php /*
             if(isset($_GET['id_pizza']))
            {
                $prix_total=0;
                foreach ($_POST['id_pizza'] as $nbr_pizza=>$id_pizza);
            }
             $requete="SELECT * FROM Pizza WHERE id_pizza = '$id_pizza'";
             $quantite=$_POST['quantite'][$nbrpizza];
             echo'<tr><td>'.$id_pizza.'</td><td>'.$recup['nom_pizza'].'</td><td>'.$recup['prix_unitaire'].'</td>';
             echo'<td>'.$quantite.'</td>';
             echo '<td>';
             $prix_unitaire=$recup['prix_produit'];
             $tot = $prix_unitaire*$quantite;
             echo $tot; */

      ?>
							
	<div class="form-group"> 
		<button type="submit" class="btn btn-primary">Buy!</button>
	</div>     
	
</form>			
