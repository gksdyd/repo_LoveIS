/* ===============================================-->
                      이미지 JS
   ===============================================--> */

$("input[type=file]").on("change", function() {
  const fReader = new FileReader();
  fReader.readAsDataURL($(this)[0].files[0]);
  alert($(this)[0].files[0].val());
  fReader.onloadend = function(event){
    // alert($(this)[0].files[0].val());
    // $(".currImage").empty();
    // for (let i = 0; i < $(this)[0].files.length; i++) {
    //   let text = "<img src='" + $(this)[0].files[i].val() + "' style='width: 100%;'>";
    // }
  }
});