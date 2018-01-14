/**
 *  Under Developement
 */


$(document).ready(function(){


 $('#exec').click(function(){
   var data = {};
   data['file'] = $('#file').val();
   $.ajax({
     type: 'POST',
     url: $('#conPa').val()+'/dev/ajax_exec_remote',
     data : data,
     success: function(response){
       console.log(response);
     },
     error: function(xhr, type, exception) {
       // if ajax fails display error alert
       alert("ajax error response type "+type);
     }
   });
 });
});