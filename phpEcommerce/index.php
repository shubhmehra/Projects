<?php
  require_once "core/init.php";
  include "includes/head.php";
//<!-- Top Nav Bar -->

  include "includes/navigatebar.php";
//<!-- header Logo-->
  include "includes/headerfull.php";
// <!--Left Sidebar-->
  include "includes/leftbar.php";
// <!--Details Modal-->

  $sql  = "SELECT * FROM products WHERE featured = 1";
  $featured = $db->query($sql);
?>



<!--main content-->
<div class="col-md-8">
    <div class="row">
        <h2 class="text-center">Featured Products</h2>
        <?php while($product  = mysqli_fetch_assoc($featured)): ?>
          <!-- <?php var_dump($product); ?> Learn this function to debug. see result in screenshots-->
          <div class="col-md-3 text-center">
              <h4><?=$product['title']; ?></h4>
              <img src="<?=$product['image'];?>" alt="<?=$product['title']; ?>" class="img-thumb">
              <p class="list-price text-danger">List Price: <s>$<?=$product['list_price']; ?></s></p><!--s tag strike through-->
              <p class="price">Our Price:$<?=$product['price'];?></p>
              <button type="button" class="btn btn-sm btn-success" onclick="detailsmodal(<?= $product['id']; ?>)">Details</button>
          </div>
        <?php endwhile;?>


    </div>
</div>

<?php

include "includes/rightbar.php";
include "includes/footer.php";

?>
