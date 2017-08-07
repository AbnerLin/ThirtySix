<%@ page contentType="text/html; charset=UTF-8"%>

<!-- Modal -->
<div class="modal fade" id="serviceModal" tabindex="-1" role="dialog"
	aria-labelledby="serviceModal" aria-hidden="true">
	<div class="modal-dialog modal-lg" role="document">
		<div class="modal-content">
			<!-- header -->
			<div class="modal-header">
				<h3 class="modal-title">服務選單</h3>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<!-- body -->
			<div class="modal-body row">
				<div class="container">
					<div class="row w-100 p-3">
						<!-- nav -->
						<ul class="nav  nav-pills justify-content-center">
							<li class="nav-item"><a class="nav-link active"
								data-toggle="tab" href="#menu">菜單</a></li>
							<li class="nav-item"><a class="nav-link" data-toggle="tab"
								href="#checkOut">結帳</a></li>
							</li>
						</ul>
					</div>
					<div class="row w-100 p-3">
						<!-- nav-tab -->
						<div class="tab-content">
							<div class="tab-pane fade show active" id="menu" role="tabpanel">menu.</div>
							<div class="tab-pane fade" id="checkOut" role="tabpanel">check
								out.</div>
						</div>
					</div>
				</div>
			</div>
			<!-- footer -->
			<div class="modal-footer"></div>

		</div>
	</div>
</div>

<!-- <div class="modal fade" id="serviceModal" tabindex="-1" role="dialog" -->
<!-- 		aria-labelledby="myModalLabel"> -->
<!-- 		<div class="modal-dialog modal-lg" role="document"> -->
<!-- 			<div class="modal-content"> -->

<!-- 				<div class="modal-header"> -->
<!-- 					<button type="button" class="close" data-dismiss="modal" -->
<!-- 						aria-label="Close"> -->
<!-- 						<span aria-hidden="true">&times;</span> -->
<!-- 					</button> -->
<!-- 					<h3 class="modal-title" id="myModalLabel">服務選單</h3> -->
<!-- 				</div> -->

<!-- 				<div class="modal-body"> -->
<!-- 					Nav tabs -->
<!-- 					<ul class="nav nav-tabs" role="tablist"> -->
<!-- 						<li role="presentation" class="active"><a -->
<!-- 							href="#orderPageTab" aria-controls="orderPageTab" role="tab" -->
<!-- 							data-toggle="tab">點餐</a></li> -->
<!-- 						<li role="presentation"><a href="#checkOutTab" -->
<!-- 							aria-controls="checkOutTab" role="tab" data-toggle="tab">結帳</a></li> -->
<!-- 					</ul> -->

<!-- 					Tab panes -->
<!-- 					<div class="tab-content"> -->
<!-- 						order tab -->
<!-- 						<div role="tabpanel" class="tab-pane active" id="orderPageTab"> -->
<%-- 														<%@ include file="/WEB-INF/jsp/template/orderPage.jsp"%> --%>
<!-- 						</div> -->
<!-- 						check out -->
<!-- 						<div role="tabpanel" class="tab-pane" id="checkOutTab"> -->
<%-- 														<%@ include file="/WEB-INF/jsp/template/checkOutPage.jsp"%> --%>
<!-- 						</div> -->
<!-- 					</div> -->
<!-- 				</div> -->

<!-- 				<div class="modal-footer"> -->
<!-- 					<div class="row" id="sendOrder"> -->
<!-- 						<button type="button" class="btn btn-danger btn-lg btn-block" -->
<!-- 							onclick="sendOrder();"> -->
<!-- 							<span class="glyphicon glyphicon-shopping-cart" -->
<!-- 								aria-hidden="true"> 出餐</span> -->
<!-- 						</button> -->
<!-- 					</div> -->
<!-- 				</div> -->
<!-- 			</div> -->
<!-- 		</div> -->
<!-- 	</div> -->