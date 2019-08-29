<?php
if (!isset($_SESSION['admin'])) {
    ?>
    <meta http-equiv = "refresh": content = "0;url=./index.php?page=accueil.php">
    <?php
    exit();
}
