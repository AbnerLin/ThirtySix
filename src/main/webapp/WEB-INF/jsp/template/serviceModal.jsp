<%@ page contentType="text/html; charset=UTF-8"%>

<!-- Modal -->
<div class="modal fade" id="serviceModal" tabindex="-1" role="dialog"
	aria-labelledby="serviceModal" aria-hidden="true">
	<div class="modal-dialog modal-lg" role="document">
		<div class="modal-content" id="serviceModalContent">
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
				<div class="col-12 p-3">
					<!-- nav -->
					<ul class="nav nav-pills justify-content-start">
						<li class="nav-item"><a class="nav-link active"
							data-toggle="tab" href="#menu">菜單</a></li>
						<li class="nav-item"><a class="nav-link" data-toggle="tab"
							href="#checkOut">結帳</a></li>
						</li>
					</ul>
				</div>
				<div class="col-12">
					<!-- nav-tab -->
					<div class="tab-content">
						<!-- menu tab. -->
						<div class="tab-pane fade show active" id="menu" role="tabpanel">
							<div class="row">
								<div class="col-12 col-sm-4 p-0">
									<div class="col-12 pr-sm-0">
										<p>餐點資訊</p>
										<ul class="list-group" id="orderTmpList">
											<!-- template -->
										</ul>
									</div>
									<div class="col-12 my-3 px-sm-0">
										<div class="row p-3">
											<div class="col-7 d-flex justify-content-start align-items-center">
												$ <span id="totalCost">0</span>
											</div>
											<div class="col-5 p-0 d-flex justify-content-end">
												<input id="orderTmpCustomerId" type="hidden" />
												<button type="button" disabled id="sendOrderBtn" onclick="Order._sendOrder(this);"
													class="btn btn-success">送單</button>
											</div>
										</div>
									</div>
								</div>

								<div class="col-12 col-sm-8" id="itemMenu" role="tablist"
									aria-multiselectable="true">
									<%@ include file="/WEB-INF/jsp/template/menu.jsp"%>
								</div>
							</div>
						</div>
						<!-- checkOut tab. -->
						<div class="tab-pane fade" id="checkOut" role="tabpanel">check
							out.</div>
					</div>
				</div>
				<!-- footer -->
				<div class="modal-footer"></div>

			</div>
		</div>
	</div>