</div>

<!--Footer-->
<div class="footer">
  <p>&copy;Copyright Shubham's Boutique</p>
</div>



	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.5/js/bootstrap.min.js"></script>

	<script>
        // parallax effect
        //shows how many pixel we scroll we can do some maths using it
        jQuery(window).scroll(function(){
            var vscroll =   jQuery(this).scrollTop();
        //console.log(vscroll); shows scrolls
            jQuery('#logotext').css({
                          "transform" :   "translate(0px,"+vscroll/2+"px)"    //logo will stay at center
            });


            var vscroll =   jQuery(this).scrollTop();
            //            console.log(vscroll); shows scrolls
                        jQuery('#back-flower').css({
                          "transform" :   "translate("+vscroll/5+"px,-"+vscroll/12+"px)"
                        });
            var vscroll =   jQuery(this).scrollTop();
            //            console.log(vscroll); shows scrolls
                        jQuery('#fore-flower').css({
                          "transform" :   "translate(0px, -"+vscroll/2+"px)"//flower move quickly
                        });

        });
        // What is AJAX?
        // AJAX = Asynchronous JavaScript and XML.
        //
        // AJAX is a technique for creating fast and dynamic web pages.
        //
        // AJAX allows web pages to be updated asynchronously by exchanging small amounts of data with the server behind the scenes. This means that it is possible to update parts of a web page, without reloading the whole page.
        //
        // Classic web pages, (which do not use AJAX) must reload the entire page if the content should change.
        //
        // Examples of applications using AJAX: Google Maps, Gmail, Youtube, and Facebook tabs.

        function  detailsmodal(id){
          var data  = {"id" :  id};
          jQuery.ajax({
            url : '/phptutorial/includes/detailsmodal.php',
            method  : "post",
            data : data,
            success : function(data){
              jQuery('body').append(data);
              jQuery('#details-modal').modal('toggle');
            },
            error : function(){
              alert('something went wrong');
            }
          });
          }
	</script>
  </body>
</html>
