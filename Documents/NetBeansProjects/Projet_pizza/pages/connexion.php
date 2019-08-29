
<?php

if(isset($_GET['submit'])){
        $pseudo = $_GET['pseudo'];
        $mdp = $_GET['mdp'];
        if($pseudo == "" || $mdp == ""){
            echo "Les champs ne sont pas tous remplis !";
        }
        else{
            $x = new ConnexionDB($cnx);
            $ut = $x->getConnexion1($pseudo, $mdp);
            if(is_null($ut)){
                echo "Erreur de login !";
            }
            else{
                $_SESSION['utilisateur'] = $ut;
                $_SESSION['pseudo'] = $x->getPseudo($pseudo, $mdp);
                $_SESSION['mdp'] = $mdp;
            }
}
}
?>

<div class="design-connect" >
<form class="text-center border border-light p-5" method="get" action="<?php print $_SERVER['PHP_SELF'];?> ">

    <p class="h4 mb-4">Sign in</p>


    <input type="text" class="form-control mb-4" placeholder="Pseudo">
    <input type="password" id="defaultLoginFormPassword" class="form-control mb-4" placeholder="Password">

    <div class="d-flex justify-content-around">
        <div>
           
            <div class="custom-control custom-checkbox">
                <input type="checkbox" class="custom-control-input" id="defaultLoginFormRemember">
                <label class="custom-control-label" for="defaultLoginFormRemember">Remember me</label>
            </div>
        </div>
        <div>
            
            <a href="">Forgot password?</a>
        </div>
    </div>

    <button class="btn btn-info btn-block my-4" type="submit" name="submit">Sign in</button>


    <p>Not a member?
        <a href="">Register</a>
    </p>

    <p>or sign in with:</p>

    <a type="button" class="light-blue-text mx-2">
        <i class="fab fa-facebook-f"></i>
    </a>
    <a type="button" class="light-blue-text mx-2">
        <i class="fab fa-twitter"></i>
    </a>
    <a type="button" class="light-blue-text mx-2">
        <i class="fab fa-linkedin-in"></i>
    </a>
    <a type="button" class="light-blue-text mx-2">
        <i class="fab fa-github"></i>
    </a>

</form>
    
</div>