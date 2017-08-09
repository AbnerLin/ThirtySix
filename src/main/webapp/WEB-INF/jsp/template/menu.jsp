<%@ page contentType="text/html; charset=UTF-8"%>

<script id="menuTemplate" type="text/html">
	{{if Object.keys(itemMap.data).length > 0}}
	    <div class="card">
	    	<div class="card-header p-0" role="tab" id="headingOne">
	    		<h5 class="mb-0">
	    			<div class="w-100 h-100 p-3 itemClassBtn" data-toggle="collapse"
	    				data-parent="#itemMenu" href="\#{{= mealType}}" aria-expanded="true"
	    				aria-controls="{{= mealType}}" style="background-image: url({{= imagePath}});">{{= name}}</div>
	    		</h5>
	    	</div>
        
	    	<div id="{{= mealType}}" class="collapse" role="tabpanel"
	    		aria-labelledby="headingOne">
	    		<div class="card-block">
        
	    			<div id="{{= mealType}}List" role="tablist" aria-multiselectable="true">
	    				{{each itemMap.data}}
	    					{{if isDisplay == 1 }}
	    					    <div class="card">
	    					    	<div class="card-header p-0" role="tab">
                        	    		<div class="row m-0">
                        	    			<div class="col-6 p-2 itemClassBtn" data-toggle="collapse"
                        	    				data-parent="\#{{= mealType}}List" href="\#{{= eleId}}" aria-expanded="true"
                        	    				aria-controls="{{= mealType}}">{{= name}} <p class="p-0 m-0">\$ {{= price}}</p></div>
                                
                        	    			<div class="input-group col-6 p-0">
                        	    				<div class="input-group-btn">
                        	    					<button type="button" aria-label="-1"
                        	    						class="btn btn-success btn-sm"
                        	    						onclick="Menu.serviceModal.amountMinus(this);">
                        	    						<i class="material-icons">remove</i>
                        	    					</button>
                        	    				</div>
                        	    				<input class="form-control itemAmount" min="0"
                        	    					type="number" value="0" />
												<input value="{{= id}}" class="itemId" type="hidden" />
                        	    				<div class="input-group-btn">
                        	    					<button type="button" aria-label="+1"
                        	    						class="btn btn-danger btn-sm"
                        	    						onclick="Menu.serviceModal.amountPlus(this);">
                        	    						<i class="material-icons">add</i>
                        	    					</button>
                        	    				</div>
                        	    			</div>
                        	    		</div>
                        	    	</div>
	    					    	
	    					    	{{if description != null && description != ""}}                    		
	    					    	<div id="{{= eleId}}" class="collapse show" role="tabpanel"
                        	    		aria-labelledby="headingOne">
                        	    		<div class="card-block">
							    			<div class="row">
							    				<div class="col-6">
							    					<img class="itemImage" src="{{= imagePath}}" />
							    				</div>
							    				<div class="col-6">
							    					{{= description}}
							    				</div>
							    			</div>
	    					    		</div>
                        	    	</div>
	    					    	{{/if}}
                        	    </div>
							{{/if}}
	    				{{/each}}
        
	    			</div>
	    		</div>
	    	</div>
	    </div>
	{{/if}}
</script>

<script id="itemOfOrderTemplate" type="text/html">
	<li class="list-group-item justify-content-between itemOfOrder" id="{{= id}}">
		{{= name}} 
		<span class="badge badge-default badge-pill itemAmountOfOrderText">{{= amount}}</span>
		<input type="hidden" class="itemIdOfOrder" value="{{= id}}" />
		<input type="hidden" class="itemAmountOfOrder" value="{{= amount}}" />
	</li>
</script>

