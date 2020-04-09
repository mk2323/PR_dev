package com.zoomansa.homepage.stats.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import com.zoomansa.homepage.stats.dao.HomepageParkingStatsByAreaDao;
import com.zoomansa.homepage.stats.dao.OperatedStatsDao;
import com.zoomansa.homepage.stats.model.OperatedStats;
import com.zoomansa.homepage.stats.model.ParkingGraph;
import com.zoomansa.homepage.stats.model.ParkingStatsByArea;


/**
 * ParkingStatsByAreaServiceImpl
 * 지역별통계서비스
 */
@Service
public class ParkingStatsByAreaServiceImpl implements ParkingStatsByAreaService {
	
	private static final Logger log = LoggerFactory.getLogger(ParkingStatsByAreaServiceImpl.class);
	
	@Autowired
	private HomepageParkingStatsByAreaDao homepageParkingStatsByAreaDao;
	
	@Autowired
	private OperatedStatsDao operatedStatsDao;
	
	
	@Override
	@Cacheable(value = "stats" , key = "#statsUID")
	public ParkingGraph getTodayStatsByAreaListByStatsUID(int statsUID) {
		
		log.debug("ParkingStatsByAreaServiceImpl > getTodayStatsByAreaListByStatsUID");
		
		OperatedStats stats = operatedStatsDao.getStatsListByStatsUID(statsUID);
		
		ParkingGraph parkingGraph = new ParkingGraph();
		
		parkingGraph.setStatsUID(stats.getStatsUID());
		parkingGraph.setStatsName(stats.getStatsName());
		parkingGraph.setStatsByAreaList( homepageParkingStatsByAreaDao.getTodayStatsByAreaListByStatsUID(statsUID));
		
		setStatsByAreaLeveling((ArrayList<ParkingStatsByArea>) parkingGraph.getStatsByAreaList());
		
		return parkingGraph;
	}
	
	
	@Override
	@Cacheable(value = "statsAll")
	public List<ParkingGraph> getTodayStatsByAreaList() {
		
		log.debug("ParkingStatsByAreaServiceImpl > getTodayStatsByAreaList");
		
		ArrayList<ParkingGraph> graphList = new ArrayList<>();
		ArrayList<OperatedStats> operList = (ArrayList<OperatedStats>) operatedStatsDao.getStatsList();
		for(OperatedStats stats: operList) {
		
			ParkingGraph parkingGraph = new ParkingGraph();

			parkingGraph.setStatsUID(stats.getStatsUID());
			parkingGraph.setStatsName(stats.getStatsName());
			parkingGraph.setStatsByAreaList( homepageParkingStatsByAreaDao.getTodayStatsByAreaListByStatsUID(stats.getStatsUID()) );
			
			ArrayList<ParkingStatsByArea> tempList = new ArrayList<>();
			for(ParkingStatsByArea temp : parkingGraph.getStatsByAreaList() ) {
				if(stats.getStatsUID() == temp.getStatsUID()) {
					tempList.add(temp);
				}
			}			
			setStatsByAreaLeveling(tempList);
			graphList.add(parkingGraph);
		}
		
		return graphList;
	}
	
	
	@Caching( evict = { @CacheEvict(value="stats", allEntries=true), @CacheEvict(value="statsAll") } )
	public void clearStatsCache() {}
	

	private void setStatsByAreaLeveling(ArrayList<ParkingStatsByArea> statsList) {
		
		int max = 0;
		for(ParkingStatsByArea temp : statsList) {
			if(max < temp.getCnt()) {
				max = temp.getCnt();
			}
		}
		
		if(max > 0) {
			for(ParkingStatsByArea temp : statsList) {
				temp.setLevel(getLevel (temp.getCnt(), max));
			}
		}
		
	}
	
	private int getLevel(int cnt, int max) {
		int level = (int)((1.0*cnt/max) * 100);
		return level;
	}
	
}
