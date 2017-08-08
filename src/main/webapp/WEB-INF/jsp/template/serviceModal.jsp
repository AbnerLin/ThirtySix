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
			<div class="modal-body  container">
				<div class="row p-3">
					<!-- nav -->
					<ul class="nav nav-pills justify-content-center">
						<li class="nav-item"><a class="nav-link active"
							data-toggle="tab" href="#menu">菜單</a></li>
						<li class="nav-item"><a class="nav-link" data-toggle="tab"
							href="#checkOut">結帳</a></li>
						</li>
					</ul>
				</div>
				<div class="row">
					<!-- nav-tab -->
					<div class="tab-content col-12">
						<!-- menu tab. -->
						<div class="tab-pane fade show active row" id="menu"
							role="tabpanel">


							<div class="col-12 d-flex justify-content-end">
								<button type="button" style="display: none;" id="sendOrderBtn"
									onclick="" class="btn btn-success">送單</button>
							</div>
							<div class="col-12">
								<ul class="list-group">
									<li class="list-group-item justify-content-between">Use
										Template to generate this. And Use Object to Keep Order list. ?<span
										class="badge badge-default badge-pill">2</span>
									</li>
								</ul>
							</div>

							<div class="col-12" id="itemMenu" role="tablist"
								aria-multiselectable="true">
								<%@ include file="/WEB-INF/jsp/template/menu.jsp"%>
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