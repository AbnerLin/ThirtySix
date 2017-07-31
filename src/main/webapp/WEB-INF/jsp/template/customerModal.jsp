<%@ page contentType="text/html; charset=UTF-8"%>
<div class="modal fade" id="serviceModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog modal-lg" role="document">
			<div class="modal-content">

				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h3 class="modal-title" id="myModalLabel">服務選單</h3>
				</div>

				<div class="modal-body">
					<!-- Nav tabs -->
					<ul class="nav nav-tabs" role="tablist">
						<li role="presentation" class="active"><a
							href="#orderPageTab" aria-controls="orderPageTab" role="tab"
							data-toggle="tab">點餐</a></li>
						<li role="presentation"><a href="#checkOutTab"
							aria-controls="checkOutTab" role="tab" data-toggle="tab">結帳</a></li>
					</ul>

					<!-- Tab panes -->
					<div class="tab-content">
						<!-- order tab -->
						<div role="tabpanel" class="tab-pane active" id="orderPageTab">
							<%-- 							<%@ include file="/WEB-INF/jsp/template/orderPage.jsp"%> --%>
						</div>
						<!-- check out -->
						<div role="tabpanel" class="tab-pane" id="checkOutTab">
							<%-- 							<%@ include file="/WEB-INF/jsp/template/checkOutPage.jsp"%> --%>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<!-- 					<div class="row" id="sendOrder"> -->
					<!-- 						<button type="button" class="btn btn-danger btn-lg btn-block" -->
					<!-- 							onclick="sendOrder();"> -->
					<!-- 							<span class="glyphicon glyphicon-shopping-cart" -->
					<!-- 								aria-hidden="true"> 出餐</span> -->
					<!-- 						</button> -->
					<!-- 					</div> -->
				</div>
			</div>
		</div>
	</div>