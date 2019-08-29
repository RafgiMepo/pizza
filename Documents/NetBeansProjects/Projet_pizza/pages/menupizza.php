
<html>
   <head>
        
        <meta http-equiv="content-type" content="text/html; charet=utf-8">
        
        <title>Jenn's pizza</title>
  
     </head>     
    <html lang="en" class="full-height">
        
    
    <div class="container">

<div class="mur1">
  <table class="table-responsive">
      <?php
 	$cnx=new PDO('pgsql:host=localhost;dbname=Pizza','postgres','scooby');


        $reponse=$cnx->query('SELECT * FROM Pizza');
        ?>
                  <tr>
                      <th>N°</th>
                      <th>Nom pizza </th>
                      <th>Garniture </th>
                      <th>Prix</th>
                  </tr>
             
                  
                  
                  <?php
            while($donnees=$reponse->fetch())
            {
            ?>
                
                <tr class="colorbd">
                  
                    <th class="pt-lg-3"><?php echo $donnees['id_pizza'];?></th>
                    <th class="pt-lg-3"><?php echo $donnees['nom_pizza'];?></th>
                    <th class="pt-lg-3"><?php echo $donnees['garniture'];?></th>
                    <th class="pt-lg-3"><?php echo $donnees['prix_unitaire'];?></th>
                  
                </tr>
           <?php
            }
            ?>
              
  </table>
</div> 
</div>
        
    
    

</html>

