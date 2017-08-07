<%@ page contentType="text/html; charset=UTF-8"%>

<!-- Modal -->
<div class="modal fade" id="checkInModal" tabindex="-1" role="dialog"
	aria-labelledby="CheckInModal" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<!-- header -->
			<div class="modal-header">
				<h3 class="modal-title">顧客資訊</h3>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<!-- body -->
			<div class="modal-body row">
				<!-- customer name -->
				<div class="col-12">
					<h4>姓名</h4>
					<input class="form-control" type="text" id="checkInCustomerName"
						value="路人甲" />
				</div>
				<!-- customer phone -->
				<div class="col-12 mt-3">
					<h4>顧客電話</h4>
					<input class="form-control" type="text" id="checkInCustomerPhone"
						value="" />
				</div>
				<!-- customer people count -->
				<div class="col-12 mt-3">
					<h4>人數</h4>
					<div class="input-group">
						<div class="input-group-btn">
							<button type="button" aria-label="-1" class="btn btn-success"
								onclick="Customer.checkInModal.peopleCountMinusBtn();"">
								<i class="material-icons">remove</i>
							</button>
						</div>
						<input id="checkInPeopleCount" class="form-control volume" min="1"
							type="number" value="1" />
						<div class="input-group-btn">
							<button type="button" aria-label="+1" class="btn btn-danger"
								onclick="Customer.checkInModal.peopleCountAddBtn();">
								<i class="material-icons">add</i>
							</button>
						</div>
					</div>
				</div>
				<!-- furnish id -->
				<input type="hidden" id="checkInFurnishID" />
			</div>
			<!-- footer -->
			<div class="modal-footer">
				<button type="button" class="btn btn-danger btn-block"
					onclick="Customer.checkInModal.checkIn();">
					<span class="glyphicon glyphicon-cutlery" aria-hidden="true">
						CheckIn!</span>
				</button>
			</div>

		</div>
	</div>
</div>
