package com.thirtySix.repository.repoImpl;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.thirtySix.model.SeatPosition;
import com.thirtySix.repository.SeatPositionDAO;

@Repository
public class SeatPositionDAOImpl extends GenericDAOImpl<SeatPosition> implements
		SeatPositionDAO {

	public SeatPositionDAOImpl() {
		super(SeatPosition.class);
	}

	@Override
	public void deleteByMapID(String mapID) {
		try {

			Query query = this
					.getSessionFactory()
					.getCurrentSession()
					.createQuery(
							"DELETE FROM SeatPosition WHERE seatMap = :mapID");
			query.setString("mapID", mapID);
			query.executeUpdate();

		} catch (Exception e) {
			logger.error("刪除地圖的擺設發生錯誤。", e);
		}
	}
}
