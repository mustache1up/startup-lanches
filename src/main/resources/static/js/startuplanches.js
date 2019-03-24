var mainApp = new Vue({
  el: '#main-app',
  data: {
	opcoesCardapio: [],
	detalhesLanche: {}
  },
  mounted: function () {
        
      $.get("/api/lanches/")
      .done(data => {
          this.opcoesCardapio = data;
      })
      .fail(error => {
          console.log(error);
      });
      
  },
  methods: {
	mostraDetalhesLanche: function(opcaoId) {
   
      $.get("/api/lanches/" + opcaoId, 
        data => this.atualizaDetalhesLanche(data))
      .fail(error => {
        console.log(error);
      });
     
    },
    atualizaDetalhesLanche : function(data) {
    
      console.log('recebido json: ' + JSON.stringify(data));
      
      this.detalhesLanche = data;
    },
    limpaDetalhesLanche : function() {
    	
      this.detalhesLanche = {};
    }
  }
})
