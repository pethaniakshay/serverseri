<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
<script type="text/javascript">
$(document).ready(function() {
  $(function() {
    var token = $("input[name='_csrf']").val();
    var header = "X-CSRF-TOKEN";
    $(document).ajaxSend(function(e, xhr, options) {
      xhr.setRequestHeader(header, token);
    });
  });
});
</script>
