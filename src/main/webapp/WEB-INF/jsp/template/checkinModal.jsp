<%@ page contentType="text/html; charset=UTF-8"%>
<div class="modal fade" id="checkInModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog modal-md" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h3 class="modal-title" id="myModalLabel">顧客資訊</h3>
				</div>

				<div class="modal-body text-center">
					<h4>顧客名稱</h4>
					<input class="form-control" type="text" id="checkInCustomerName"
						value="路人甲" />
					<h4>顧客電話</h4>
					<input class="form-control" type="text" id="checkInCustomerPhone"
						value="----------" />
					<h4>人數</h4>
					<div class="input-group">
						<div class="input-group-btn">
							<button type="button" aria-label="-1"
								class="btn btn-success minusBtn">
								<span class="glyphicon glyphicon-minus-sign" aria-hidden="true"></span>
							</button>
						</div>
						<input id="checkInPeopleCount" class="form-control volume" min="1"
							type="number" value="1" />
						<div class="input-group-btn">
							<button type="button" aria-label="+1"
								class="btn btn-danger plusBtn">
								<span class="glyphicon glyphicon-plus-sign" aria-hidden="true"></span>
							</button>
						</div>
					</div>
					<h4>桌號</h4>
					<input class="form-control" disabled type="text"
						id="checkInTableNumber" />
				</div>

				<div class="modal-footer">
					<div class="row" id="sendOrder">
						<button type="button" class="btn btn-danger btn-lg btn-block"
							onclick="customerCheckIn();">
							<span class="glyphicon glyphicon-cutlery" aria-hidden="true">
								CheckIn!</span>
						</button>
					</div>
				</div>
			</div>
		</div>
	</div>