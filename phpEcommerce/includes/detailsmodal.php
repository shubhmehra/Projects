<?php 
require_once '../core/init.php';
$id  =  $_POST['id'];
$sql  = "SELECT * FROM products WHERE id =  '$id'";
$result = $db->query($sql);
$product  = mysqli_fetch_assoc($result);
$brand_id = $product['brand'];
$sql  = "SELECT brand FROM brand WHERE ID = '$brand_id'";
// we dont need sql above so we can override it
$brand_query  = $db->query($sql);
$brand  = mysqli_fetch_assoc($brand_query);
$sizestring = $product['sizes'];
$size_array = explode(',', $sizestring);
?>
<!-- details lightbox -->
<?php ob_start();
// This function will turn output buffering on. While output buffering is active no output is sent from the script (other than headers), instead the output is stored in an internal buffer.
// The contents of this internal buffer may be copied into a string variable using ob_get_contents(). To output what is stored in the internal buffer, use ob_end_flush(). Alternatively, ob_end_clean() will silently discard the buffer contents.
// Warning:Some web servers (e.g. Apache) change the working directory of a script when calling the callback function. You can change it back by e.g. chdir(dirname($_SERVER['SCRIPT_FILENAME'])) in the callback function.
// Output buffers are stackable, that is, you may call ob_start() while another ob_start() is active. Just make sure that you call ob_end_flush() the appropriate number of times. If multiple output callback functions are active, output is being filtered sequentially through each of them in nesting order.
?>
<div class="modal fade details-1" id="details-modal" tabindex="-1" role="document" aria-labelledby="details-1" aria-hidden="true">
    <div class="modal-dialog modal-lg">
       <div class="modal-content">
            <div class="modal-header">
                <button class="close" type="button" onclick="closeModal()" aria-label="close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title text-center"><?=$product['title']; ?></h4>
            </div>
            <div class="modal-body">
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-sm-6">
                            <div class="center-block">
                                <img src="<?=$product['image']; ?>" alt="Levis Jeans" class="details img-responsive">
                            </div>
                        </div>
                        <div class="col-sm-6">
                            <h4>Details</h4>
                            <p><?=$product['description']; ?></p>
                            <hr>
                            <p>Price: $<?=$product['price']; ?></p>
                            <p>Brand: <?=$product['brand']; ?></p>
                            <form action="add_cart.php" method="post">
                                <div class="form-group">
                                    <div class="col-xs-3">
                                        <label for="quantity">Quantity:</label>
                                        <input type="text" class="form-control" id="quantity" name="quantity">
                                    </div>
                                </div><br><br>
                                <div class="form-group">
                                    <label for="size">Size:</label>
                                    <select name="size" id="size" class="form-control">
                                        <option value=""></option>
                                        <?php foreach ($size_array as $string) {
                                          $string_array = explode(':', $string);
                                          $size = $size_array[0];
                                          $quantity = $string_array[1];
                                          echo "<option value = ".'$size'." >$size($quantity Available)</option>";
                                        }  ?>
                                    </select>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button class="btn btn-default" onclick="closeModal()">Close</button>
                <button class="btn btn-warning" type="submit"><span class="glyphicon glyphicon-shopping-cart"></span>Add to cart</button>
            </div>
        </div>
    </div>
</div>
<script>
function closeModal(){
  jQuery('#details-modal').modal('hide');
  setTimeout(function(){
    jQuery('#details-modal').remove();
    jQuery('.modal-backdrop').remove();
  },500);
}
</script>
<?php
echo ob_get_clean();
?>
