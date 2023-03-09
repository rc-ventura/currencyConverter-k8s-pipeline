
$(document).ready(function() {
    $("#converter-btn").click(function(event) {
      event.preventDefault(); // impede o envio do formulário tradicional
  
      var from = $("#from").val();
      var to = $("#to").val();
      var amount = $("#amount").val();
      console.log(from);
      console.log(to);
      console.log(amount);

      $.ajax({
        url: "http://localhost:8080/convert/" + from + "/" + to,
        type: "GET",
        data: {
          amount: amount
        },
        success: function(result) {
          $("#result").text(result);
          console.log(result);
        },
        error: function(xhr, textStatus, errorThrown) {
          $("#result").text("Erro na conversão de moedas. Status: " + textStatus + ", Código de erro: " + xhr.status + ", Mensagem de erro: " + errorThrown);
        }
      });
    });
  });
  