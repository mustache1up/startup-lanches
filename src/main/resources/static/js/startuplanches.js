var mainApp = new Vue({
  el: '#main-app',
  data: {
	opcoesCardapio: [],
	detalhesLanche: {}
  },
  mounted: function () {
        
      var self = this;
        
//        $.get("/api/opcoesCardapio")
//        .done(data => {
//            self.channels = data;
//        })
//        .fail(error => {
//            console.log(error);
//        });
      
      this.opcoesCardapio = [{id:1, nome:"X-Bacon"}];
     
  },
  methods: {
	mostraDetalhesLanche: function(opcaoId) {
   
//      $.get("/api/opcoesCardapio/" + opcaoId, 
//        data => this.atualizaDetalhesLanche(data))
//      .fail(error => {
//        console.log(error);
//      });
		
		if(!opcaoId) {
			
			opcaoId = {id: -1};
		}
		
		this.detalhesLanche = {id: opcaoId};
      
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
