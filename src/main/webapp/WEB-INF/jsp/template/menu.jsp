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
                        	    			<div class="col-8 p-3 itemClassBtn" data-toggle="collapse"
                        	    				data-parent="\#{{= mealType}}List" href="\#{{= eleId}}" aria-expanded="true"
                        	    				aria-controls="{{= mealType}}">{{= name}}</div>
                                
                        	    			<div class="input-group col-4 p-0">
                        	    				<div class="input-group-btn">
                        	    					<button type="button" aria-label="-1"
                        	    						class="btn btn-success btn-sm"
                        	    						onclick="Menu.serviceModal.amountMinus(this);">
                        	    						<i class="material-icons">remove</i>
                        	    					</button>
                        	    				</div>
                        	    				<input class="form-control itemAmount" min="0"
                        	    					type="number" value="0" />
												<input id="{{= id}}" type="hidden" />
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
							    				<div class="col-4">
							    					<img class="itemImage" src="{{= imagePath}}" />
							    				</div>
							    				<div class="col-8">
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

<script>
	<div class="col-12">
		<ul class="list-group">
			<li class="list-group-item justify-content-between">
				Use	Template to generate this. 
				UT failed.UT failed.UT failed.UT failed.UT failed.UT failed.UT failed.UT failed.
				<span class="badge badge-default badge-pill">2</span>
			</li>
		</ul>
	</div>
</script>

