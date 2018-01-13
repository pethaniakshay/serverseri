/**
 *  Under Developement
 */


$(document).ready(function(){
 
 $('#exec').click(function(){
   $.ajax({
     type: 'POST',
     url: '/serverseri/dev/ajax_exec_remote',
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