
<?php 

if(isset($_GET['submit'])) {
    $pseudo=$_GET['pseudo'];
    $e_mail=$_GET['e_mail'];
    $mdp=$_GET['mdp'];

    if($pseudo=="" ||$e_mail==""||$mdp=="")
    {
        echo 'Veuillez remplir tous les champs';
    }
    else {
    $inscrip=new UtilisateurDB($cnx);
    $test=$inscrip->ajoutUtilisateur($pseudo,$e_mail,$mdp);
    }
  
}
?> 


<div class="i_design" >  

  <div class="container">

      

      
<legend>Inscris toi ! </legend>
    
<div class="design-inscrip">


        <form name="Utilisateur" class="text-center" style="color: #757575;" action="<?php print $_SERVER['PHP_SELF'];?>" method="get">
            
                <div class="col-xs-3">
                    <div class="md-form">
                        <input type="text" id="materialRegisterFormFirstName" class="form-control mb-4" placeholder="pseudo" width=100px name="pseudo">
                        <label for="materialRegisterFormFirstName"></label>
                    </div>
                </div>
                <div class="col-xs-3">
                    <div class="md-form mt-0">
                        <input type="email" id="materialRegisterFormEmail" class="form-control mb-4" placeholder="E-mail" name="e_mail">
                        <label for="materialRegisterFormEmail"></label>
                    </div>
                </div>
                <div class="col-xs-3">    
                    <div class="md-form">
                        <input type="password" id="materialRegisterFormPassword" class="form-control mb-4" aria-describedby="materialRegisterFormPasswordHelpBlock" name="mdp">
                        <label for="materialRegisterFormPassword"></label>
                        <small id="materialRegisterFormPasswordHelpBlock" class="form-text text-muted mb-4">

                        </small>
                    </div>
                </div>
            
          
                    <div class="form-check">
                        <input type="checkbox" class="form-check-input" id="materialRegisterFormNewsletter">
                        <label class="form-check-label" for="materialRegisterFormNewsletter">Subscribe to our newsletter</label>
                    </div>

                    <button class="btn btn-outline-info btn-rounded btn-block my-4 waves-effect z-depth-0" type="submit" name="submit">Sign in</button>
             
                    <p>or sign up with:</p>

                    <a type="button" class="btn-floating btn-fb btn-sm">
                        <i class="fab fa-facebook-f"></i>
                    </a>
                    <a type="button" class="btn-floating btn-tw btn-sm">
                        <i class="fab fa-twitter"></i>
                    </a>
                    <a type="button" class="btn-floating btn-li btn-sm">
                        <i class="fab fa-linkedin-in"></i>
                    </a>
                    <a type="button" class="btn-floating btn-git btn-sm">
                        <i class="fab fa-github"></i>
                    </a>


            <p>By clicking
                <em>Sign up</em> you agree to our
                <a href="" target="_blank">terms of service</a>

        </form>
        </div>
    </div>
</div>

